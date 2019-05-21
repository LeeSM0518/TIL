package lecture_manager2.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Users implements Serializable {
    List<User> professor = new ArrayList<>();
    List<User> student = new ArrayList<>();
}
