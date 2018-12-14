package CheckProblem;

public class Account {
    static final int MIN_BALANCE = 0;
    static final int MAX_BALANCE = 1000000;

    private int balance;

    void setBalance(int money){
        if(money > MAX_BALANCE || money < MIN_BALANCE){
            return;
        } else {
            this.balance = money;
        }
    }

    int getBalance(){
        return balance;
    }
}
