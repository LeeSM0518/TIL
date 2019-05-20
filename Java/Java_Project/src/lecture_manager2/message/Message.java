package lecture_manager2.message;

import java.io.Serializable;

public class Message implements Serializable {

    private Type type;
    private int targetNumber;

    public void setConnectMessage(int targetNumber) {
        this.type = Type.CONNECT;
        this.targetNumber = targetNumber;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(int targetNumber) {
        this.targetNumber = targetNumber;
    }
}
