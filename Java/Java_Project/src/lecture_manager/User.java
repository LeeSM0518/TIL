package lecture_manager;

import java.io.Serializable;

public class User implements Serializable {
    String identity;
    String id;
    String password;

    User(){}

    User(String identity, String id, String password) {
        this.identity = identity;
        this.id = id;
        this.password = password;
    }
}