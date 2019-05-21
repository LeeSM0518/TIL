package lecture_manager2.database;

import lecture_manager2.message.*;

import java.io.*;

import static lecture_manager2.database.Result.*;

public class Database {

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
    public Result signUpMember(Message message) {
        checkUser(message);

        if (check != NONEXISTENCE) {
            check = 0;
            return EXISTENCE;
        }

        User newUser = new User(message.identity, message.id, message.password);

        if (newUser.identity.equals(lecture_manager.Message.STUDENT)) {
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

    public Result checkUser(Message message) {

        if (message.getUser().getIdentity().equals(Message.STUDENT)) {
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