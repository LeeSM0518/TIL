package lecture_manager2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    static int socketCount = 1;

    ExecutorService executorService;
    ServerSocket serverSocket;
    List<SocketInServer> connections = new ArrayList<>();
    Type type;

    void startServer() {
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
            try {
                Socket socket = serverSocket.accept();
                SocketInServer socketInServer = new SocketInServer(socket, socketCount);

                connections.add(socketInServer);

                Message message = new Message();
                message.setConnectMessage(socketCount);

                sendToTarget(message);

            } catch (Exception e) {
                if (!serverSocket.isClosed()) {
                    stopServer();
                }
            }

        };

        executorService.submit(runnable);
    }

    void stopServer() {
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
        switch (message.type) {
            case CONNECT:
                break;
            case SIGNIN:
                break;
            default:
                System.out.println("잘못된 메시지 타입 입니다.");
                break;
        }
    }

    class SocketInServer {
        Socket socket;
        int socketNumber;

        SocketInServer(Socket socket, int socketNumber) {
            this.socket = socket;
            this.socketNumber = socketNumber;
        }

        void send(Message message) {
            Runnable runnable = () -> {
                try {
                    OutputStream outputStream = socket.getOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                    objectOutputStream.writeObject(message);

                    objectOutputStream.flush();
                    objectOutputStream.close();
                    outputStream.close();

                } catch (Exception e) {

                }
            };

            executorService.submit(runnable);
        }

    }

}
