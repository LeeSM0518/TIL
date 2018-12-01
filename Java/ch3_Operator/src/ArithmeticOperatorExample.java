public class ArithmeticOperatorExample {
    public static void main(String[] args) {
        int int1 = 10;
        int int2 = 4;
        int result2 = int1 / int2;
        double result3 = int1 / int2;

        System.out.println(result2);
        System.out.println(result3);

        result3 = (int1 * 1.0) / int2;
        System.out.println(result3);
        result3 = (double) int1 / int2;
        System.out.println(result3);
        result3 = int1 / (double) int2;
        System.out.println(result3);
    }
}
