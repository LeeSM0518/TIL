package lecture_manager.information;

import lecture_manager.database.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
    private List<Problem> problemList;
    private User user;

    public Student(User user) {
        this.user = user;
        this.problemList = new ArrayList<>();
    }

    public Student(List<Problem> problemList, User user) {
        this.problemList = problemList;
        this.user = user;
    }

    public List<Problem> getProblemList() {
        return problemList;
    }

    public void setProblemList(List<Problem> problemList) {
        this.problemList = problemList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
