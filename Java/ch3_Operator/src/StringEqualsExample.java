public class StringEqualsExample {
    public static void main(String[] args) {
        String strVar1 = "신민철";
        String strVar2 = "신민철";
        String strvar3 = new String("신민철");

        System.out.println(strVar1 == strVar2);
        System.out.println(strVar1 == strvar3);
        System.out.println();
        System.out.println(strVar1.equals(strVar2));
        System.out.println(strVar1.equals(strvar3));
    }
}
