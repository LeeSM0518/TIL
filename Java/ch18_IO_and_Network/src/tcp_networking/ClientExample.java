package tcp_networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientExample {
    public static void main(String[] args) {
        Socket socket = null;

        try {
            // Socket 생성
            socket = new Socket();
            System.out.println("[연결 요청]");

            // 연결 요청
            socket.connect(new InetSocketAddress("localhost", 5001));
            System.out.println("[연결 성공]");

        } catch (IOException e) {
        }

        // 연결이 되어 있을 경우
        if (!socket.isClosed()) {
            try {
                // 연결 끊기
                socket.close();
            } catch (IOException e1) {}
        }
    }
}