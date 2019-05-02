package chapter08_recursive_calls;

public class FactorialClass {

    // 재귀 호출 방식의 팩토리얼
    static int recursionFactorial(int n) {
        int ret;

        if (n <= 1) ret = 1;
        else ret = n * recursionFactorial(n-1);

        return ret;
    }

    // 반복 호출 방식의 팩토리얼
    static int repeatFactorial(int n) {
        int ret = 1;

        for(int i = n; i > 1; i--)
            ret = ret * i;

        return ret;
    }

    public static void main(String[] args) {
        int inputValue = 3;

        System.out.println(recursionFactorial(inputValue));
        System.out.println(repeatFactorial(inputValue));
    }

}