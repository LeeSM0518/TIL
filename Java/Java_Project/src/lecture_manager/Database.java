package lecture_manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    static int check;
    final static int EXISTENCE = 1;
    final static int NONEXISTENCE = 2;
    final static int NOT_EQUALS_PASSWORD = 3;
    final static int EQUALS_PASSWORD = 4;
    final static int SUCCESS_SIGNUP = 5;

    private Users users = new Users();
    private String currentDir;

    public Database() {
        try {
            currentDir = new java.io.File(".").getCanonicalPath();
            FileInputStream fis = new FileInputStream(currentDir + "/UsersDatabase.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);

            users = (Users) ois.readObject();

        } catch (IOException e) {
            newDatabase();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void newDatabase() {
        try {
            FileOutputStream fos = new FileOutputStream(currentDir + "/UsersDatabase.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(users);

            oos.flush(); oos.close(); fos.close();
        } catch (IOException e) {
            System.out.println("Database.newDatabase 오류");
            e.printStackTrace();
        }

    }

    // TODO 회원가입
    public int signUpMember(Message message) {
        checkUser(message);

        if (check != NONEXISTENCE) {
            check = 0;
            return EXISTENCE;
        }

        User newUser = new User(message.identity, message.id, message.password);

        if (newUser.identity.equals(Message.STUDENT)) {
            users.student.add(newUser);
        } else {
            users.professor.add(newUser);
        }

        try {
            FileOutputStream fos = new FileOutputStream(currentDir + "/UsersDatabase.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(users);

            oos.flush(); oos.close(); fos.close();

        } catch (IOException e) {
            System.out.println("Database.signUpMember 오류");
            e.printStackTrace();
        }

        return SUCCESS_SIGNUP;
    }

    public int checkUser(Message message) {

        if (message.identity.equals(Message.STUDENT)) {
            this.users.student.forEach(student -> {
                if (student.id.equals(message.id)) {
                  if (student.password.equals(message.password)) {
                      check = Database.EQUALS_PASSWORD;
                  } else {
                      check = Database.NOT_EQUALS_PASSWORD;
                  }
                }
            });
        } else if (message.identity.equals(Message.PROFESSOR)){
            this.users.professor.forEach(professor -> {
                if (professor.id.equals(message.id)) {
                    if (professor.password.equals(professor.password)) {
                        check = Database.EQUALS_PASSWORD;
                    } else {
                        check = Database.NOT_EQUALS_PASSWORD;
                    }
                }
            });
        }

        if (check == 0) check = NONEXISTENCE;

        return check;

    }
}