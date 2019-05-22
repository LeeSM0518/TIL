package tcp_networking;

import java.io.IOException;
import java.net.*;

public class ServerExample {
    public static void main(String[] args) throws UnknownHostException {
        ServerSocket serverSocket = null;
        InetAddress local = InetAddress.getLocalHost();

        try {
            // ServerSocket 생성
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(
                    // 127.0.0.1 로 할당
                    "localhost"
                    // 로컬 IP 주소로 서버 IP를 할당
                    // local.getHostAddress(),
                    ,
                    // 포트 번호 할당
                    5001
            ));

            System.out.println(serverSocket.getInetAddress());

            while (true) {
                System.out.println("[연결 기다림]");

                // 클라이언트 연결 수락
                Socket socket = serverSocket.accept();
                InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                System.out.println("[연결 수락함] " + isa.getHostName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // ServerSocket 이 닫혀있지 않을 경우
        if (!serverSocket.isClosed()) {
            try {
                // ServerSocket 닫기
                serverSocket.close();
            } catch (IOException e1) {}
        }
    }
}
