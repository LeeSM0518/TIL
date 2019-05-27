package lecture_manager.userinterface;

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
}
