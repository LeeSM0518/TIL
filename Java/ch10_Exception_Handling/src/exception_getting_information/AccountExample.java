package exception_getting_information;

import application_exception.BalanceInsufficientException;
import exception_generate.Account;

public class AccountExample {
    public static void main(String[] args) {
        Account account = new Account();

        // 예금하기
        account.deposit(10000);
        System.out.println("예금액: " + account.getBalance());

        // 출금하기
        try {
            account.withdraw(30000);
        } catch (BalanceInsufficientException e) {  // 예외 메시지 얻기
            // 예외 메시지를 getMessage() 메소드의 리턴값으로 얻는다.
            String message = e.getMessage();
            System.out.println(message);
            System.out.println();

            // 예외 발생 코드를 추적해서 모두 콘솔에 출력
            e.printStackTrace();
        }
    }
}