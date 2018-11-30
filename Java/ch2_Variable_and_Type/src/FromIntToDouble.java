public class FromIntToDouble {
    public static void main(String[] args) {
        int num1 = 123456780;
        int num2 = 123456780;

        double num3 = num2;  // double <-- int
        num2 = (int) num3;  //  int    <-- float

        int result = num1 - num2;
        System.out.println(result);
    }
}
