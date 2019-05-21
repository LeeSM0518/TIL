package lecture_manager2.database;

import java.io.Serializable;

public class User implements Serializable {

    private String identity;
    private String id;
    private String password;

    public User(){}

    public User(String identity, String id, String password) {
        this.identity = identity;
        this.id = id;
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}