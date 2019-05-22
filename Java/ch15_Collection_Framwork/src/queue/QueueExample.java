package queue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
        Queue<Message> messageQueue = new LinkedList<Message>();

        // 메시지 저장
        messageQueue.offer(
                new Message("sendMail", "홍길동")
        );
        messageQueue.offer(
                new Message("sendSMS", "신용권")
        );
        messageQueue.offer(
                new Message("sendKakaotalk", "홍두께")
        );

        // 메시지 큐가 비어있는지 확인
        while(!messageQueue.isEmpty()) {
            // 메시지 큐에서 한 개의 메시지 꺼냄
            Message message = messageQueue.poll();
            switch (message.command) {
                case "sendMail":
                    System.out.println(message.to +
                            "님에게 메일을 보냅니다.");
                    break;
                case "sendSMS":
                    System.out.println(message.to +
                            "님에게 SMS를 보냅니다.");
                    break;
                case "sendKakaotalk":
                    System.out.println(message.to +
                            "님에게 카카오톡을 보냅니다.");
                    break;
            }
        }
    }
}