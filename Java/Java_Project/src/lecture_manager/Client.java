package lecture_manager;

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

public class Client {

    Socket socket;
    User user;
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
        Client client = new Client();

        JFrame jFrame = new JFrame("Client");

        JPanel subPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());
        subPanel.setLayout(new BorderLayout());

        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(jTextArea);

        JPanel subPanel2 = new JPanel();
        subPanel2.setLayout(new GridLayout(2, 1));

        JButton startBtn = new JButton("Start");
        JTextField idField = new JTextField();
        JTextField pwField = new JTextField();
        JButton sendBtn = new JButton("Send");
        JButton signUpBtn = new JButton("SignUp");

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
                Message message = new Message(Message.LOGIN);
                message.setLoginMessage(idField.getText(), pwField.getText());
                client.send(message.loginRequest());
                idField.setText("");
                pwField.setText("");
            }
        });

        signUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message message = new Message(Message.SIGN_UP);
                message.setSignUpMessage("nalsm98", "이상민", "0518", "student");
                client.send(message.signUpRequest());
                idField.setText("");
                pwField.setText("");
            }
        });

        subPanel2.add(idField);
        subPanel2.add(pwField);

        subPanel.add(startBtn, BorderLayout.WEST);
        subPanel.add(subPanel2, BorderLayout.CENTER);
        subPanel.add(sendBtn, BorderLayout.EAST);
        subPanel.add(signUpBtn, BorderLayout.EAST);

        mainPanel.add(jTextArea, BorderLayout.CENTER);
        mainPanel.add(subPanel, BorderLayout.SOUTH);

        jFrame.add(mainPanel);
        jFrame.setSize(500, 300);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}