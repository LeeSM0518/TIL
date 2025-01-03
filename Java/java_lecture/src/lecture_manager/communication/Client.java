package lecture_manager.communication;

import lecture_manager.database.Result;
import lecture_manager.database.User;
import lecture_manager.information.Student;
import lecture_manager.message.Message;
import lecture_manager.information.Problem;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private Socket socket;
    private int socketNumber;
    private static Result result = null;
    private User user;
    private List<Problem> problems = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    protected InetSocketAddress inetSocketAddress = null;

    public void ipAssign(InetSocketAddress inetSocketAddress) {
        // TODO UI에서 주소 할당하는 기능 구현, UI에서 할당을 마친 뒤 클라이언트로 전달
        this.inetSocketAddress = inetSocketAddress;
    }

    public void startClient() {
        Thread thread = new Thread(() -> {
            try {
                socket = new Socket();
                socket.connect(inetSocketAddress);
                System.out.println("[연결 완료]");
            } catch (Exception e) {
                System.out.println("[startClient Error]");
                System.out.println("[서버 통신 안됨]");
                if (!socket.isClosed()) {
                    stopClient();
                }
                return;
            }
            Message message = returnMessage();
            messageProcess(message);
        });
        thread.start();
    }

    public void stopClient() {
        try {
            System.out.println("[연결 끊음]");
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Message returnMessage() {
        Message message = null;
        try {
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            message = (Message) objectInputStream.readObject();

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (EOFException e) {

        } catch (Exception e2) {
            e2.printStackTrace();
            System.out.println("[continueReceive Error]");
            System.out.println("[서버 통신 안됨]");

            stopClient();
        }

        return message;
    }

    public void send(Message message) {
        Thread thread = new Thread(() -> {
            try {
                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                objectOutputStream.writeObject(message);

                objectOutputStream.flush();

            } catch (Exception e) {
                System.out.println("[서버 통신 안됨]");
            }
        });

        thread.start();
    }

    public List<Problem> requestProblems(Message message) {
        message.setUser(this.user);
        send(message);
        Message receiveMessage = returnMessage();
        this.problems = receiveMessage.getProblems();
        return problems;
    }

    public Result signUpRequest(Message message) {
        send(message);
        Message receiveMessage = returnMessage();
        return receiveMessage.getResult();
    }

    public Result signInRequest(Message message) {
        send(message);
        Message receiveMessage = returnMessage();
        if (receiveMessage.getResult() == Result.EQUALS_PASSWORD) {
            user = message.getUser();
        }
        return receiveMessage.getResult();
    }

    private Result messageProcess(Message message) {
        switch (message.getType()) {
            case CONNECT:
                socketNumber = message.getTargetNumber();
                return null;
            case SEND_PROBLEMS:
                this.problems = message.getProblems();
                return null;
            default:
                return null;
        }
    }

    public List<Student> requestStudents(Message message) {
        send(message);
        Message receiveMessage = returnMessage();
        try {
            students = receiveMessage.getStudents();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }

        return this.students;
    }

    public void sendCodeAndRunResult(Message message) {
        message.setUser(this.user);
        this.problems = message.getProblems();
        send(message);
    }

    public void sendProblems(Message message) {
        this.problems = message.getProblems();
        send(message);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public int getSocketNumber() {
        return socketNumber;
    }

    public void setSocketNumber(int socketNumber) {
        this.socketNumber = socketNumber;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }
}
