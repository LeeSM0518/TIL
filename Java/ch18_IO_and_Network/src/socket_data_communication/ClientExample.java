package socket_data_communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientExample {
    public static void main(String[] args) {
        Socket socket = null;

        try {
            // 소켓 생성
            socket = new Socket();
            System.out.println("[연결 요청]");

            // 서버에 연결을 요청하기 위해 IP 주소와 바인딩 포트 제공하여 연결 요청
            socket.connect(new InetSocketAddress("localhost", 5001));
            System.out.println("[연결 성공]");

            // 보낼 데이터를 저장할 공간
            byte[] bytes = null;
            String message = null;

            // 출력 스트림 생성
            OutputStream os = socket.getOutputStream();
            message = "Hello Server";

            // "Hello Server"를 바이트 배열로 바꾼다.
            bytes = message.getBytes(StandardCharsets.UTF_8);

            // 서버로 바이트 배열을 보낸다.
            os.write(bytes);
            os.flush();
            System.out.println("[데이터 보내기 성공]");

            // 입력 스트림 생성
            InputStream is = socket.getInputStream();

            // 입력 받을 데이터 배열 생성
            bytes = new byte[100];

            // 바이트 배열에 데이터를 저장하고 입력받은 바이트 개수를 저장
            int readByteCount = is.read(bytes);

            // 바이트 배열을 String 형으로 바꾼다.
            message = new String(bytes, 0, readByteCount, StandardCharsets.UTF_8);
            System.out.println("[데이터 받기 성공]: " + message);

            os.close();
            is.close();

        } catch (Exception e) {}

        if (!socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e1) {}
        }

    }
}
