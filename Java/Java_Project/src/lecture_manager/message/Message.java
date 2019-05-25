package lecture_manager.message;

import lecture_manager.database.Result;
import lecture_manager.database.User;
import java.io.Serializable;

public class Message implements Serializable {

    private Type type;
    private int targetNumber;
    private Result result;
    private User user;
    private String code;
    private String runResult;

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

    public void setConnectMessage(int targetNumber) {
        this.type = Type.CONNECT;
        this.targetNumber = targetNumber;
    }

    public void setSignUpMessage(int targetNumber, User user) {
        this.type = Type.SIGNUP;
        this.targetNumber = targetNumber;
        this.user = user;
    }

    public void setSignInMessage(int targetNumber, User user) {
        this.type = Type.SIGNIN;
        this.targetNumber = targetNumber;
        this.user = user;
    }

    public void setSendCode(String code, String runResult) {
        this.type = Type.SEND_CODE_AND_RESULT;
        this.code = code;
        this.runResult = runResult;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRunResult() {
        return runResult;
    }

    public void setRunResult(String runResult) {
        this.runResult = runResult;
    }
}
