package lecture_manager2.communication;

import lecture_manager2.database.Database;
import lecture_manager2.database.Result;
import lecture_manager2.message.Message;

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
            case CONNECT:
                socketCount++;
                break;
            case SIGNIN:
                break;
            case SIGNUP:
                break;
            default:
                System.out.println("잘못된 메시지 타입 입니다.");
                break;
        }
        connections.forEach(connection -> {
            if (connection.socketNumber == message.getTargetNumber()) {
                connection.send(message);
            }
        });
    }

    class SocketInServer {
        Socket socket;
        int socketNumber;

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
                            connections.remove(SocketInServer.this);
                            System.out.println("[receive 에러, 클라이언트 통신 안됨]");
                            System.out.println("[" + socketNumber + "번 소켓 종료]");
                            socket.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
            };
            executorService.submit(runnable);
        }

        void messageProcess(Message message) {
            switch (message.getType()) {
                case SIGNIN:
                    break;
                case SIGNUP:
                    // TODO 회원가입 구현
                    Result result = database.signUpMember(message);
//                    send()
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
