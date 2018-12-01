public class BinaryStringExample {
    public static String toBinaryString(int value) {
        String str = Integer.toBinaryString(value);
        while(str.length() < 32){
            str = "0" + str;
        }
        return str;
    }
}