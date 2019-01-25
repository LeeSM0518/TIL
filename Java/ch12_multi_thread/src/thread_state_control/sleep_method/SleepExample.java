package thread_state_control.sleep_method;

import java.awt.*;

public class SleepExample {
    public static void main(String[] args) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for(int i=0; i<10; i++) {
            toolkit.beep();
            try {
                // 3초 동안 메인 스레드를 일시 정지 상태를 만듬
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
        }
    }
}
