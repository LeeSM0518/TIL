package anonymous_implement;

import static_method_declare.RemoteControl;

public class RemoteControlExample {
    public static void main(String[] args) {
        RemoteControl rc = new RemoteControl() {
            @Override
            public void turnOn() {
                // 실행문
            }
            @Override
            public void turnOff() {
                // 실행문
            }

            @Override
            public void setVolume(int volume) {
                // 실행문
            }
        };
    }
}
