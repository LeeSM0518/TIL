package lecture_manager2.message;

<<<<<<< HEAD
import lecture_manager2.database.Result;
import lecture_manager2.database.User;

=======
>>>>>>> 153eec044e360cb8b8b000eb13508702c2c89613
import java.io.Serializable;

public class Message implements Serializable {

    private Type type;
    private int targetNumber;
<<<<<<< HEAD
    private Result result;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
=======
>>>>>>> 153eec044e360cb8b8b000eb13508702c2c89613

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
