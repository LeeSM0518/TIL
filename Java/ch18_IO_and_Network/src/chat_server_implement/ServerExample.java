package chat_server_implement;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class ServerExample {

    ExecutorService executorService;
    ServerSocket serverSocket;
    List<Client> connections = new Vector<>();

    JPanel mainPanel = new JPanel();
    static JTextArea jTextArea = new JTextArea();

    void startServer() {
        // ExecutorService 객체 호출
        executorService = Executors.newFixedThreadPool(
                // 사용가능한 CPU 코어 수 호출
                Runtime.getRuntime().availableProcessors()
        );

        try {
            // ServerSocket 객체 생성
            serverSocket = new ServerSocket();
            // ServerSocket 을 로컬로 IP를 잡고 5001 포트와 바인딩한다.
            serverSocket.bind(new InetSocketAddress("localhost", 5001));
        } catch (Exception e) {
            // 예외가 발생할 경우 서버를 닫고 메소드를 종료한다.
            if (!serverSocket.isClosed()) {
                stopServer();
                return;
            }
        }

        // 수락 작업 생성
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("[서버 시작]");
                jTextArea.append("[서버 시작]\n");

                while (true) {
                    try {
                        // 연결 수락
                        Socket socket = serverSocket.accept();
                        String message = "[연결 수락: " +
                                socket.getRemoteSocketAddress() +
                                ": " + Thread.currentThread().getName() +
                                "]";
                        System.out.println(message);
                        jTextArea.append(message + "\n");

                        // Client 객체 저장
                        Client client = new Client(socket);
                        connections.add(client);

                        jTextArea.append("[연결 개수: " + connections.size() + "]" + "\n");
                    } catch (Exception e) {
                        if (!serverSocket.isClosed()) {
                            stopServer();
                            break;
                        }
                    }
                }
            }
        };
        // 스레드풀에서 처리
        executorService.submit(runnable);
    }

    void stopServer() {
        try {
//            (원래 방법) 모든 Socket 닫기
//            Iterator<Client> iterator = connections.iterator();
//            while (iterator.hasNext()) {
//                Client client = iterator.next();
//                client.socket.close();
//                iterator.remove();
//            }

            // (스트림 이용 방법) 모든 Socket 닫기
            connections.forEach(client -> {
                try {
                    client.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // ServerSocket 닫기
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

            // ExecutorService 종료
            if (executorService != null && !executorService.isShutdown()) {
                executorService.isShutdown();
            }

            System.out.println("[서버 멈춤]");
            jTextArea.append("[서버 멈춤]\n");

        } catch (Exception e) {
        }
    }

    // Client 를 내부 클래스로 선언
    class Client {
        Socket socket;

        // 매개값으로 socket 을 받는 생성자
        Client(Socket socket) {
            this.socket = socket;
            receive();
        }

        void receive() {
            // 데이터 받기 작업 생성
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            byte[] byteArr = new byte[100];
                            InputStream inputStream = socket.getInputStream();

                            // 클라이언트가 비정상 종료를 했을 경우 IOException 발생
                            int readByteCount = inputStream.read(byteArr);  // 데이터 받기

                            // 클라이언트가 정상적으로 Socket 의 close() 를 호출했을 경우
                            if (readByteCount == -1) {
                                throw new IOException();
                            }

                            String message = "[요청 처리: " + socket.getRemoteSocketAddress() + ": " +
                                    Thread.currentThread().getName() + "]";
                            System.out.println(message);
                            jTextArea.append(message + "\n");

                            // 문자열로 변환
                            String data = new String(byteArr, 0, readByteCount, StandardCharsets.UTF_8);

                            // 모든 클라이언트에게 보냄 (선택적으로도 보낼 수 있다)
                            for (Client client : connections) {
                                client.send(data);
                            }
                        }
                    } catch (Exception e) {
                        try {
                            connections.remove(Client.this);

                            String message = "[클라이언트 통신 안됨: " +
                                    socket.getRemoteSocketAddress() +
                                    ": " + Thread.currentThread().getName() + "]";
                            System.out.println(message);
                            jTextArea.append(message + "\n");

                            socket.close();
                        } catch (IOException e2) {
                        }
                    }

                }
            };

            // 스레드풀에서 처리
            executorService.submit(runnable);
        }

        void send(String data) {
            // 데이터 보내기 작업 생성
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // 클라이언트로 데이터 보내기
                    try {
                        byte[] byteArr = data.getBytes(StandardCharsets.UTF_8);
                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write(byteArr);
                        outputStream.flush();
                    } catch (Exception e) {
                        try {
                            String message = "[클라이언트 통신 안됨: " +
                                    socket.getRemoteSocketAddress() + ": " +
                                    Thread.currentThread().getName() + "]";
                            System.out.println(message);
                            jTextArea.append(message + "\n");

                            connections.remove(Client.this);
                            socket.close();
                        } catch (IOException e2) {
                        }
                    }
                }
            };
            // 스레드풀에서 처리
            executorService.submit(runnable);
        }
    }

    void start() {
        ServerExample server = new ServerExample();

        JFrame jFrame = new JFrame("Server");

        mainPanel.setLayout(new BorderLayout());
        JButton jButton = new JButton("START");

        jTextArea.setEditable(false);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jButton.getLabel().equals("START")) {
                    server.startServer();
                    jButton.setLabel("STOP");
                } else {
                    server.stopServer();
                    jButton.setLabel("START");
                }
            }
        });

        mainPanel.add(jTextArea, BorderLayout.CENTER);
        mainPanel.add(jButton, BorderLayout.SOUTH);

        jFrame.add(mainPanel);

        jFrame.setSize(500, 300);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        ServerExample serverExample = new ServerExample();
        serverExample.start();
    }
}
