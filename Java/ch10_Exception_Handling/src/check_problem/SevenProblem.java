package check_problem;

class NotExistIDException extends Exception {
    public NotExistIDException() {}
    public NotExistIDException(String message) {
        // 상위 클래스인 Exception의 생산자에게 예외 메시지를 전달
        super("아이디가 존재하지 않습니다.");
    }
}

class WrongPasswordException extends Exception {
    public WrongPasswordException() {}
    public WrongPasswordException(String message) {
        // 상위 클래스인 Exception의 생산자에게 예외 메시지를 전달
        super("패스워드가 틀립니다.");
    }
}

class LoginExample {
    public static void main(String[] args) {
        try {
            login("white", "12345");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            login("blue", "54321");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void login(String id, String password) throws NotExistIDException, WrongPasswordException {

        // id가 "blue"가 아니라면 NotExistIDException 발생시킴
        if(!id.equals("blue")) {
            throw new NotExistIDException("blue");
        }

        // password가 "12345"가 아니라면 WrongPasswordException 발생시킴
        if(!password.equals("12345")) {
            throw new WrongPasswordException("12345");
        }
    }
}