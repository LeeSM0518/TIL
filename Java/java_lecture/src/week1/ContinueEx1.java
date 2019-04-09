package week1;

public class ContinueEx1 {
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            if(i%4 == 0) {
                continue;
            }
            System.out.println("i의 값: " + i);
        }
    }
}