public class IncreaseDecreaseOperatorExample {
    public static void main(String[] args) {
        int x = 10;
        int y = 10;
        int z;

        x++;
        ++x;
        System.out.println("x = " + x + '\n');


        y--;
        --y;
        System.out.println("y = " + y+ '\n');

        z = x++;
        System.out.println("z = " + z);
        System.out.println("x = " + x+ '\n');

        z = ++x;
        System.out.println("z = " + z);
        System.out.println("x = " + x+ '\n');

        z = ++x + y++;
        System.out.println("z = " + z);
        System.out.println("x = " + x);
        System.out.println("y = " + y);
    }
}
