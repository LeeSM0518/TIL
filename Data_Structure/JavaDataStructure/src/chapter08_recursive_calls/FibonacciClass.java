package chapter08_recursive_calls;

public class FibonacciClass {

    static int recFib (int n) {
        int ret;

        if (n == 0)
            ret = 0;
        else  if (n == 1)
            ret = 1;
        else
            ret = recFib(n-1) + recFib(n-2);

        return ret;
    }

    static int repFib (int n) {
        int ret;

        if (n < 2) {
            ret = n;
        } else {
            int temp = 0, current = 1, last = 0;

            for (int i = 2; i <= n; i++) {
                temp = current;
                current += last;
                last = temp;
            }

            ret = current;
        }

        return ret;
    }

    public static void main(String[] args) {
        System.out.println("recFib(3) = " + recFib(3));
        System.out.println("repFib(3) = " + repFib(3));
    }

}
