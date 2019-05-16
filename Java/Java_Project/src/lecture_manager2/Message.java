package lecture_manager2;

import java.io.Serializable;

public class Message implements Serializable {

    Type type;
    private int targetNumber;

    public void setConnectMessage(int targetNumber) {
        this.type = lecture_manager2.Type.CONNECT;
        this.targetNumber = targetNumber;
    }
}
