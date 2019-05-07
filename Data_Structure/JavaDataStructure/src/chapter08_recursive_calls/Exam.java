package chapter08_recursive_calls;

public class Exam {

    static void downNum(int num) {

        if (num == 1) {
            System.out.println(num);
            return;
        } else {
            downNum(num - 1);
            System.out.println(num + " ");
        }

    }

    public static void main(String[] args) {
        downNum(10);
    }

}
