package lecture_manager.userinterface;

import java.io.Serializable;

public class Problem implements Serializable {
    private String title;
    private String context;
    private String code;
    private String runResult;
    private boolean check;

    public Problem() {}

    public Problem(String title, String context) {
        this.title = title;
        this.context = context;
    }

    public Problem(String title, String code, String runResult) {
        this.title = title;
        this.code = code;
        this.runResult = runResult;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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

    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
