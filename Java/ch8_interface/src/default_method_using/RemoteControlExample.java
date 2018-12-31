package default_method_using;

import static_method_declare.RemoteControl;
import implement_class.Television;

public class RemoteControlExample {
    public static void main(String[] args) {
        RemoteControl rc = null;

        rc = new Television();
        rc.turnOn();
        rc.setMute(true);

        rc = new Audio();
        rc.turnOn();
        rc.setMute(true);
    }
}
