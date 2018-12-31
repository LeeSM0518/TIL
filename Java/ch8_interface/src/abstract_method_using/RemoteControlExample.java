package abstract_method_using;

import implement_class.Audio;
import implement_class.Television;
import static_method_declare.RemoteControl;

public class RemoteControlExample {
    public static void main(String[] args) {
        // 인터페이스 변수 선언
        RemoteControl rc = null;

        // Television 객체를 인터페이스 타입에 대입
        rc = new Television();

        // 인터페이스의 turnOn(), turnOff() 호출
        rc.turnOn();
        rc.turnOff();

        // Audio 객체를 인터페이스 타입에 대입
        rc = new Audio();

        // 인터페이스의 turnOn(), turnOff() 호출
        rc.turnOn();
        rc.turnOff();
    }
}