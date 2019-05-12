package udp_networking;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class UdpSendExample {
    public static void main(String[] args) throws Exception {
        // DatagramSocket 객체 생성
        DatagramSocket datagramSocket = new DatagramSocket();

        System.out.println("[발신 시작]");

        for (int i = 1; i < 3; i++) {
            String data = "메시지" + i;
            byte[] byteArr = data.getBytes(StandardCharsets.UTF_8);

            // DatagramPacket 생성
            DatagramPacket packet = new DatagramPacket(
                    byteArr, byteArr.length,
                    new InetSocketAddress("localhost", 5001)
            );

            // DatagramPacket 전송
            datagramSocket.send(packet);
            System.out.println("[보낸 바이트 수]: " + byteArr.length + " bytes");
        }

        System.out.println("[발신 종료]");

        // DatagramSocket 닫기
        datagramSocket.close();

    }
}