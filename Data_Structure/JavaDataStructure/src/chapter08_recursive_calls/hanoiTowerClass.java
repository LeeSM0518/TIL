package chapter08_recursive_calls;

public class hanoiTowerClass {

    static void hanoiTower(int n, String from, String to, String temp) {

        if (n == 1) {
            System.out.println("원판 1 을 " + from + " 에서 " + to + " 로 옮겼습니다.");
        } else {
            hanoiTower(n - 1, from, to, temp);
            System.out.println("원판 " + n + " 를 " + from + " 에서 " + to + " 로 옮겼습니다.");
            hanoiTower(n - 1, temp, from, to);
        }

    }

    public static void main(String[] args) {
        String[] abc = new String[3];
        abc[0] = "A";
        abc[1] = "B";
        abc[2] = "C";

        hanoiTower(4, abc[0], abc[1], abc[2]);
    }

}
