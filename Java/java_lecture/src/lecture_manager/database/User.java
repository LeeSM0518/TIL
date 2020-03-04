package lecture_manager.database;

import java.io.Serializable;

public class User implements Serializable {

    private Identity identity;
    private String name;
    private String id;
    private String password;

    public User(){}

    public User(Identity identity, String id, String password) {
        this.identity = identity;
        this.name = name;
        this.id = id;
        this.password = password;
    }

    public User(Identity identity, String name, String id, String password) {
        this.identity = identity;
        this.name = name;
        this.id = id;
        this.password = password;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}