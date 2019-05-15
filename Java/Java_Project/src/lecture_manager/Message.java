package lecture_manager;

public class Message {

    final static String LOGIN = "LOGIN";
    final static String SIGN_UP = "SIGN_UP";
    final static String PROFESSOR = "PROFESSOR";
    final static String STUDENT = "STUDENT";

    final static String SPLIT_UNIT = "@";

    String type;
    String context;
    String id;
    String name;
    String password;
    String identity;

    public Message() {
    }

    public Message(String type) {
        this.type = type;
    }

    public void setSignUpMessage(String id, String name, String password, String identity) {
        this.type = SIGN_UP;
        this.id = id;
        this.name = name;
        this.password = password;
        this.identity = identity;
    }

    public void setLoginMessage(String id, String password) {
        this.type = LOGIN;
        this.id = id;
        this.password = password;
    }

    static public String[] splitMessage(String message) {
        return message.split(SPLIT_UNIT);
    }

    public String signUpRequest() {
        return this.type + SPLIT_UNIT + this.id + SPLIT_UNIT + this.name + SPLIT_UNIT + this.password + SPLIT_UNIT + this.identity;
    }

    public String loginRequest() {
        return this.type + SPLIT_UNIT + this.id + SPLIT_UNIT + this.password;
    }

    // TODO 메시지 진행
    int messageProcess(Message message, Database database) {
        int check = 0;

        switch (message.type) {
            case Message.LOGIN:
                check = database.checkUser(message);
                break;
            case Message.SIGN_UP:
                check = database.signUpMember(message);
                break;
        }

        return check;
    }

    // TODO 메시지 해석 메소드
    static public Message translateMessage(String str) {
        String[] messageArr = Message.splitMessage(str);
        Message message = new Message();

        switch (messageArr[0]) {
            case Message.LOGIN :
                message.type = Message.LOGIN;
                message.setLoginMessage(messageArr[1], messageArr[2]);
                break;
            case Message.SIGN_UP :
                message.type = Message.SIGN_UP;
                message.setSignUpMessage(messageArr[1], messageArr[2], messageArr[3], messageArr[4]);
                break;
        }

        return message;
    }
}
