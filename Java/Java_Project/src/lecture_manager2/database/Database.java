package lecture_manager2.database;

import lecture_manager2.message.*;

import java.io.*;

import static lecture_manager2.database.Result.*;

public class Database {

    static Result check;
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

            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Database.newDatabase 오류");
            e.printStackTrace();
        }

    }

    // TODO 회원가입
    public Result signUpMember(Message message) {
        checkUser(message);

        if (check != NONEXISTENCE) {
            check = null;
            return EXISTENCE;
        }

        User newUser = new User(message.getUser().getIdentity(), message.getUser().getName(),
                message.getUser().getId(), message.getUser().getPassword());

        if (newUser.getIdentity().equals(Identity.STUDENT)) {
            users.student.add(newUser);
        } else {
            users.professor.add(newUser);
        }

        try {
            FileOutputStream fos = new FileOutputStream(currentDir + "/UsersDatabase.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(users);

            oos.flush();
            oos.close();
            fos.close();

        } catch (IOException e) {
            System.out.println("Database.signUpMember 오류");
            e.printStackTrace();
        }

        return SUCCESS_SIGNUP;
    }


    public Result checkUser(Message message) {

        if (message.getUser().getIdentity().equals(Identity.STUDENT)) {
            this.users.student.forEach(student -> {
                if (student.getId().equals(message.getUser().getId())) {
                    if (student.getPassword().equals(message.getUser().getPassword())) {
                        check = Result.EQUALS_PASSWORD;
                    } else {
                        check = Result.NOT_EQUALS_PASSWORD;
                    }
                }
            });
        } else if (message.getUser().getIdentity().equals(Identity.PROFESSOR)) {
            this.users.professor.forEach(professor -> {
                if (professor.getId().equals(message.getUser().getId())) {
                    if (professor.getPassword().equals(message.getUser().getPassword())) {
                        check = Result.EQUALS_PASSWORD;
                    } else {
                        check = Result.NOT_EQUALS_PASSWORD;
                    }
                }
            });
        }

        if (check == null) check = NONEXISTENCE;

        return check;

    }
}