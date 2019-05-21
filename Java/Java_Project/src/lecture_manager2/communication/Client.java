package lecture_manager2.communication;

import lecture_manager2.message.Message;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class Client {

    private Socket socket;
    private int socketNumber;

    private void startClient() {
        Thread thread = new Thread(() -> {
            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress("localhost", 5001));

                System.out.println("[연결 완료]");
            } catch (Exception e) {
                System.out.println("[startClient Error]");
                System.out.println("[서버 통신 안됨]");
                if (!socket.isClosed()) {
                    stopClient();
                }
                return;
            }
            receive();
        });
        thread.start();
    }

    private void stopClient() {
        try {
            System.out.println("[연결 끊음]");
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receive() {
        while (true) {
            try {
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

                Message message = (Message) objectInputStream.readObject();

                messageProcess(message);

            } catch (SocketException e) {
                e.printStackTrace();
            } catch (EOFException e) {

            } catch (Exception e2) {
                e2.printStackTrace();
                System.out.println("[receive Error]");
                System.out.println("[서버 통신 안됨]");

                stopClient();
                break;
            }
        }
    }

    void send(Message message) {
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

    void signUpMessageSend() {

    }

    private void messageProcess(Message message) {
        switch (message.getType()) {
            case SIGNUP:
                break;
            case CONNECT:
                socketNumber = message.getTargetNumber();
                System.out.println("소켓 번호: " + socketNumber);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.startClient();
    }
}
