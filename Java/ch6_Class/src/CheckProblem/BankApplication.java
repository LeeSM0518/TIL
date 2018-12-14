package CheckProblem;

import java.util.Scanner;

public class BankApplication {
    private static Account1[] accountArray = new Account1[100];
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            System.out.println("----------------------------------");
            System.out.println("1.계좌생성 | 2.계좌목록 | 3.예금 | 4. 출금 | 5.종료 |");
            System.out.println("----------------------------------");
            System.out.print("선택> ");

            int selectNo = scanner.nextInt();

            if(selectNo == 1){
                createAccount();
            } else if (selectNo == 2){
                accountList();
            } else if (selectNo == 3){
                deposit();
            } else if (selectNo == 4) {
                withdraw();
            } else if (selectNo == 5) {
                run = false;
            }


        }
        System.out.println("프로그램 종료");
    }

    // 계좌생성하기
    private static void createAccount(){
        System.out.println("---------------");
        System.out.println("계좌생성");
        System.out.println("---------------");
        System.out.print("계좌번호: ");
        String ano = scanner.next();
        System.out.print("계좌주: ");
        String owner = scanner.next();
        System.out.print("초기입금액: ");
        int balance = scanner.nextInt();

        for(int i=0; i<100; i++){
            if(accountArray[i] == null){
                accountArray[i] = new Account1(ano, owner, balance);
                break;
            }
        }
    }

    // 계좌목록보기
    private static void accountList(){
        System.out.println("---------------");
        System.out.println("계좌목록");
        System.out.println("---------------");
        for(int i=0; i<100; i++){
            if(accountArray[i] == null){
                break;
            }
            System.out.println(accountArray[i].getAno() + '\t' +
                    accountArray[i].getOwner() + '\t' +
                    accountArray[i].getBalance());
        }
    }

    // 예금하기
    private static void deposit(){
        System.out.print("계좌번호: ");
        String ano = scanner.next();
        Account1 acc = findAccount(ano);
        if(acc == null){
            return;
        } else {
            System.out.print("예금액: ");
            int balance = scanner.nextInt();
            acc.setBalance( acc.getBalance() + balance);
        }
    }

    // 출금하기
    private static void withdraw(){
        System.out.print("계좌번호: ");
        String ano = scanner.next();
        Account1 acc = findAccount(ano);
        if(acc == null){
            return;
        } else {
            System.out.print("출금액: ");
            int balance = scanner.nextInt();
            acc.setBalance( acc.getBalance() - balance);
        }
    }

    // Account 배열에서 ano와 동일한 Account 객체 찾기
    private static Account1 findAccount(String ano){
        for(int i=0; i<100; i++){
            if(accountArray[i] != null && accountArray[i].getAno().equals(ano)){
                return accountArray[i];
            }
        }
        System.out.println("다시 입력해주세요.");
        return null;
    }
}
