package application_exception;

public class BalanceInsufficientException extends Exception{
    public BalanceInsufficientException(){}

    // 상위 클래스의 생성자를 호출하여 예외 메시지를 넘겨준다.
    public BalanceInsufficientException(String message) {
        super(message);
    }
}
