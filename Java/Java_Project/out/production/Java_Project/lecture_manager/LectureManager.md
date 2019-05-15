# Lecture Manager (Java Project)

# UI

* **계획**

  1. 로그인 및 회원 가입 UI

     <img src="../../../capture/스크린샷 2019-05-13 오전 12.05.57.png">

  2. 학생 UI

     <img src="../../../capture/스크린샷 2019-05-13 오전 12.07.42.png">

  3. 교수님 UI

     <img src="../../../capture/스크린샷 2019-05-13 오전 12.08.43.png">

     <img src="../../../capture/스크린샷 2019-05-13 오전 12.09.44.png">



# Class

* **Server(서버)**
* **Client(클라이언트)**
  * 교수 Client
  * 학생 Client
* **교수 Client UI**
* **학생 Client UI**
* **회원가입 UI**



# FlowChart

* **Server(서버) 순서도(로그인 및 회원가입)**

  ```mermaid
  graph TB
  
  start(서버 시작) --> newServer[서버 소켓 생성-ip,port]
  newServer --> newSocekt[클라이언트 소켓 생성]
  newSocekt --> checkSocket{로그인을 요청했는가?}
  checkSocket -->|Yes| checkId{아이디와 비밀번호가 존재하고 맞는가?}
  checkSocket -->|No| checkSign{회원가입 인가?}
  checkSign -->|Yes| inputInf[회원가입]
  checkSign -->|No| checkSocket
  checkId -->|Yes| checkStudent{학생인가 교수인가?}
  checkStudent -->|Student| newStudentClient[학생 클라이언트 생성]
  checkStudent -->|Professor| newProfessorClient[교수 클라이언트 생성]
  checkId -->|No| showError[에러]
  showError --> checkSocket
  ```

  * **Client 클래스가 필요하다 **

    ```java
    public class Client {
      String identity;			// 학생인지 교수인지 판별
      String id;				// 학번
      String password;	// 비밀번호
    }
    ```

  * **Message 클래스 필요**

    ```java
    public class Message {
      String type;		// 로그인인지, 회원가입인지 등등 어떤 내용을 가진 메시지인지 알기 위함.
      String context;	// 내용
      final static char SPLIT_UNIT = '@';
      
      String[] splitContext(String subContext) {
        String[] splits = new String[subContext.length];
        
        // subContext를 SPLIT_UNIT 단위로 자른 후 splits에 저장
        
        return splits;
      }
      
    }
    ```

  * **파일 입출력 필요**

    * 객체 입출력으로 회원정보를 입력 및 출력 할 수 있는지 확인 필요.
    * 객체 입출력이 되지 않는다면 Writer, Reader 을 사용해서 20171687,1234,student 와 같이 입출력을 파일에 한다.



# 소켓 통신 예제

## Server(서버)

```java
package chat_server_implement;

import com.sun.security.ntlm.Client;
import sun.lwawt.PlatformEventNotifier;

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
```



## Client(클라이언트)

```java
package chat_server_implement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientExample {

    Socket socket;
    JPanel mainPanel = new JPanel();
    static JTextArea jTextArea = new JTextArea();

    void startClient() {
        // 스레드 생성
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    // 소켓 생성 및 연결 요청
                    socket = new Socket();
                    socket.connect(new InetSocketAddress("localhost", 5001));

                    String message = "[연결 완료: " + socket.getRemoteSocketAddress() + "]";
                    System.out.println(message);

                    jTextArea.append(message + "\n");
                } catch (Exception e) {
                    String message = "[서버 통신 안됨]";
                    System.out.println(message);

                    jTextArea.append(message + "\n");
                    if (!socket.isClosed()) {
                        stopClient();
                    }
                    return;
                }
                // 서버에서 보낸 데이터 받기
                receive();
            }
        };
        // 스레드 시작
        thread.start();
    }

    void stopClient() {
        try {
            String message = "[연결 끊음]";
            System.out.println(message);

            jTextArea.append(message + "\n");

            // 연결 끊기
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }

        } catch (IOException e) {

        }
    }

    void receive() {
        while (true) {
            try {
                byte[] byteArr = new byte[100];
                InputStream inputStream = socket.getInputStream();

                // 서버가 비정상적으로 종료했을 경우 IOException 발생
                int readByteCount = inputStream.read(byteArr);          // 데이터 받기

                // 서버가 정상적으로 Socket 의 close() 를 호출했을 경우
                if (readByteCount == -1) {
                    throw new IOException();
                }

                // 문자열로 변환
                String data = new String (byteArr, 0, readByteCount, StandardCharsets.UTF_8);

                String message = "[받기 완료] " + data;

                System.out.println(message);
                jTextArea.append(message + "\n");

            } catch (Exception e) {
                String message = "[서버 통신 안됨]";
                System.out.println(message);
                jTextArea.append(message + "\n");

                stopClient();
                break;
            }
        }
    }

    void send(String data) {
        // 스레드 생성
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    byte[] byteArr = data.getBytes(StandardCharsets.UTF_8);

                    // 서버로 데이터 보내기
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(byteArr);
                    outputStream.flush();

                    String message = "[보내기 완료]";
                    System.out.println(message);

                    jTextArea.append(message + "\n");

                } catch (Exception e) {
                    String message = "[서버 통신 안됨]";
                    System.out.println(message);
                    jTextArea.append(message + "\n");
                    stopClient();
                }
            }
        };
        // 스레드 시작
        thread.start();
    }

    void start() {
        Client3Example client = new Client3Example();

        JFrame jFrame = new JFrame("Client");

        JPanel subPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());
        subPanel.setLayout(new BorderLayout());

        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(jTextArea);

        JButton startBtn = new JButton("Start");
        JTextField textField = new JTextField();
        JButton sendBtn = new JButton("Send");

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startBtn.getLabel().equals("Start")) {
                    jTextArea.setText("Start" + "\n");
                    client.startClient();
                    startBtn.setLabel("Stop");
                } else {
                    client.stopClient();
                    startBtn.setLabel("Start");
                }
            }
        });

        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send(textField.getText());
                textField.setText("");
            }
        });

        subPanel.add(startBtn, BorderLayout.WEST);
        subPanel.add(textField, BorderLayout.CENTER);
        subPanel.add(sendBtn, BorderLayout.EAST);

        mainPanel.add(jTextArea, BorderLayout.CENTER);
        mainPanel.add(subPanel, BorderLayout.SOUTH);

        jFrame.add(mainPanel);
        jFrame.setSize(500, 300);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        Client3Example client = new Client3Example();
        client.start();
    }
}
```