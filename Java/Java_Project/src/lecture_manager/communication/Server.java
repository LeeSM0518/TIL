package lecture_manager.communication;

import lecture_manager.database.Database;
import lecture_manager.database.Identity;
import lecture_manager.database.Result;
import lecture_manager.database.User;
import lecture_manager.message.Message;
import lecture_manager.information.Problem;
import lecture_manager.information.Student;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static int socketCount = 1;

    private ExecutorService executorService;
    private ServerSocket serverSocket;
    private List<SocketInServer> connections = new ArrayList<>();
    private Database database = new Database();
    private List<Problem> problemsArrayList = new ArrayList<>();
    private List<Student> studentList = new ArrayList<>();
    private static Student delStudent = null;

    private void startServer() {

        executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 5001));
        } catch (IOException e) {
            if (!serverSocket.isClosed()) {
                stopServer();
                return;
            }
        }

        Runnable runnable = () -> {
            System.out.println("[서버 시작]");

            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println(socket.getRemoteSocketAddress());
                    SocketInServer socketInServer = new SocketInServer(socket, socketCount);

                    connections.add(socketInServer);

                    Message message = new Message();
                    message.setConnectMessage(socketCount);

                    sendToTarget(message);

                } catch (Exception e) {
                    if (!serverSocket.isClosed()) {
                        stopServer();
                        break;
                    }
                }
            }

        };

        executorService.submit(runnable);
    }

    private void stopServer() {
        try {
            connections.forEach(client -> {
                try {
                    client.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

            if (executorService != null && !executorService.isShutdown()) {
                executorService.isShutdown();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("stopServer 에러");
        }

    }

    void sendToTarget(Message message) {
        switch (message.getType()) {
            case SEND_CODE_AND_RESULT:
                connections.forEach(connection -> {
                    if (connection.user.getIdentity() == Identity.PROFESSOR) {
                        connection.send(message);
                    }
                });
                break;
            case CONNECT:
                socketCount++;
                connections.forEach(connection -> {
                    if (connection.socketNumber == message.getTargetNumber()) {
                        connection.send(message);
                    }
                });
                break;
            default:
                System.out.println("잘못된 메시지 타입 입니다.");
                break;
        }
    }

    class SocketInServer {
        Socket socket;
        int socketNumber;
        User user;
        List<Problem> problemsForCheck = new ArrayList<>();

        SocketInServer(Socket socket, int socketNumber) {
            this.socket = socket;
            this.socketNumber = socketNumber;
            receive();
        }

        void send(Message message) {
            Runnable runnable = () -> {
                try {
                    OutputStream outputStream = socket.getOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                    objectOutputStream.writeObject(message);

                    objectOutputStream.flush();

                } catch (Exception e) {
                    try {
                        System.out.println("[send 에러, 클라이언트 통신 안됨]");
                        connections.remove(SocketInServer.this);
                        socket.close();
                    } catch (IOException e2) {
                    }

                }
            };
            executorService.submit(runnable);
        }

        void receive() {
            Runnable runnable = () -> {
                try {
                    while (true) {
                        InputStream inputStream = socket.getInputStream();
                        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

                        Message message = (Message) objectInputStream.readObject();

                        System.out.println(message.getType());

                        messageProcess(message);
                    }

                } catch (Exception e) {
                    try {
                        e.printStackTrace();
                        connections.remove(SocketInServer.this);
                        System.out.println("[receive 에러, 클라이언트 통신 안됨]");
                        System.out.println("[" + socketNumber + "번 소켓 종료]");
                        studentList.forEach(student -> {
                            if (student.getUser().getId().equals(this.user.getId())) {
                                delStudent = student;
                            }
                        });
                        studentList.remove(delStudent);
                        delStudent = null;
                        socket.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            };
            executorService.submit(runnable);
        }

        void removeProblem(List<Problem> problems) {
            String delTitle;
            String delContext;
            boolean check = false;
            int i;
            for (i = 0; i < problems.size(); i++) {
                delTitle = problems.get(i).getTitle();
                delContext = problems.get(i).getContext();
                for (int j = 0; j < problemsArrayList.size(); j++) {
                    if (delTitle.equals(problemsArrayList.get(j).getTitle()) &&
                            delContext.equals(problemsArrayList.get(j).getContext())) {
                        check = true;
                        break;
                    } else {
                        check = false;
                    }
                }
                if (!check) {
                    break;
                }
            }

            try {
                problems.remove(i);
            } catch (Exception e) {
                problems.remove(i-1);
            }

        }

        void addProblem(List<Problem> problems) {
            String addTitle = null;
            String addContext = null;
            boolean check = true;
            int i;
            for (i = 0; i < problemsArrayList.size(); i++) {
                addTitle = problemsArrayList.get(i).getTitle();
                addContext = problemsArrayList.get(i).getContext();
                for (int j = 0; j < problems.size(); j++) {
                    if (addTitle.equals(problems.get(j).getTitle()) &&
                            addContext.equals(problems.get(j).getContext())) {
                        check = false;
                        break;
                    } else {
                        check = true;
                    }
                }
                if (check) {
                    break;
                }
            }
            Problem problem = new Problem(addTitle, addContext);
            problems.add(i, problem);
        }

        void messageProcess(Message message) {
            Result result;
            switch (message.getType()) {
                case REQUEST_CHECKLIST:
                    // TODO 체크 리스트 요청
                    break;

                case REQUEST_STUDENTS:
                    message.setStudents(studentList);
                    send(message);
                    break;

                case REQUEST_PROBLEMS:
                    List<Problem> problems = message.getProblems();

                    if (problems.size() > problemsArrayList.size()) {
                        removeProblem(problems);
                    } else if (problems.size() < problemsArrayList.size()) {
                        addProblem(problems);
                    } else {
                        if (problemsArrayList.size() != 0) {
                            removeProblem(problems);
                            addProblem(problems);
                        }
                    }

                    studentList.forEach(student -> {
                        if (student.getUser().getId().equals(message.getUser().getId())) {
                            student.setProblemList(message.getProblems());
                        }
                    });

                    message.setProblems(problems);
                    send(message);
                    break;

                case SEND_PROBLEMS:
                    problemsArrayList = message.getProblems();
                    break;

                case SEND_CODE_AND_RESULT:
                    problemsForCheck = message.getProblems();
                    studentList.forEach(student -> {
                        if (student.getUser().getId().equals(message.getUser().getId())) {
                            student.setProblemList(message.getProblems());
                        }
                    });
                    break;

                case SIGNIN:
                    result = database.checkUser(message);
                    if (result == Result.EQUALS_PASSWORD) {
                        this.user = message.getUser();
                        if (user.getIdentity() == Identity.STUDENT) {
                            Student student = new Student(this.problemsForCheck, this.user);
                            studentList.add(student);
                        }
                    }
                    Database.check = null;
                    System.out.println(result);
                    message.setResult(result);
                    send(message);
                    break;

                case SIGNUP:
                    result = database.signUpMember(message);
                    message.setResult(result);
                    send(message);
                    break;

                default:
                    break;
            }
        }

    }

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }

}
