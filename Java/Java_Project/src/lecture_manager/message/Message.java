package lecture_manager.message;

import lecture_manager.database.Result;
import lecture_manager.database.User;
import lecture_manager.information.Problem;
import lecture_manager.information.Student;

import java.io.Serializable;
import java.util.List;

public class Message implements Serializable {

    private Type type;
    private int targetNumber;
    private Result result;
    private User user;
    private String code;
    private String runResult;
    private List<Problem> problems;
    private List<Student> students;

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

    public void setProblemsRequest(List<Problem> problems) {
        this.type = Type.REQUEST_PROBLEMS;
        this.problems = problems;
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

    public void setSendCode(List<Problem> problems) {
        this.type = Type.SEND_CODE_AND_RESULT;
        this.problems = problems;
    }

    public void setSendProblems(List<Problem> problems) {
        this.type = Type.SEND_PROBLEMS;
        this.problems = problems;
    }

    public void setSendStudentList(List<Student> studentList) {
        this.type = Type.SEND_STUDENTLIST;
        this.students = studentList;
    }

    public void setRequestCheckList(List<Problem> problems) {
        this.type = Type.REQUEST_CHECKLIST;
        this.problems = problems;
    }

    public void setRequestStudents() {
        this.type = Type.REQUEST_STUDENTS;
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

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}
