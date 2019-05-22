package socket_data_communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerExample {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            // 서버소켓 생성
            serverSocket = new ServerSocket();

            // 서버소켓의 IP와 포트 바인딩
            serverSocket.bind(new InetSocketAddress("localhost", 5001));

            while (true) {
                // 클라이언트 연결 수락
                System.out.println("[연결 기다림]");
                Socket socket = serverSocket.accept();

                // 연결된 클라이언트 정보 얻기
                InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                System.out.println("[연결 수락함] " + isa.getHostName());

                // 입력받을 데이터 공간
                byte[] bytes = null;
                String message = null;

                // 입력 스트림 생성
                InputStream is = socket.getInputStream();

                // 데이터를 입력받을 바이트 배열 생성
                bytes = new byte[100];

                // 데이터를 저장하고, 바이트 개수 저장
                int readByteCount = is.read(bytes);

                // 바이트 배열을 String 으로 변환한다.
                message = new String(bytes, 0, readByteCount, StandardCharsets.UTF_8);
                System.out.println("[데이터 받기 성공]: " + message);

                // 출력 스트림 생성
                OutputStream os = socket.getOutputStream();

                // 출력할 문자열
                message = "Hello Client";

                // 바이트 배열로 변환
                bytes = message.getBytes(StandardCharsets.UTF_8);

                // 바이트 배열 출력
                os.write(bytes);
                os.flush();
                System.out.println("[데이터 보내기 성공]");

                // 종료
                is.close();
                os.close();
                socket.close();
            }

        } catch (Exception e) {}

        if (!serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e1) {}
        }
    }
}
