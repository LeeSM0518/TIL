package lecture_manager.communication;

import lecture_manager.database.Result;
import lecture_manager.database.User;
import lecture_manager.message.Message;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class Client {

    private Socket socket;
    private int socketNumber;
    private static Result result = null;
    private User user;

    public void startClient() {
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
            Message message = receive();
            messageProcess(message);
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

    private void continueReceive() {
        while (true) {
            receive();
        }
    }

    private Message receive() {
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

    public Result signUpRequest(Message message) {
        send(message);
        Message receiveMessage = receive();
        return receiveMessage.getResult();
    }

    public Result signInRequest(Message message) {
        send(message);
        Message receiveMessage = receive();
        return receiveMessage.getResult();
    }

    // TODO 회원가입 구현
    private Result messageProcess(Message message) {
        Result result;
        switch (message.getType()) {
            case CONNECT:
                socketNumber = message.getTargetNumber();
                return null;
            default:
                return null;
        }
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
}