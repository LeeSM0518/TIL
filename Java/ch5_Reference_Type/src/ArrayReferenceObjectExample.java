public class ArrayReferenceObjectExample {
    public static void main(String[] args) {
        String[] strArray = new String[3];
        strArray[0] = "Java";
        strArray[1] = "Java";
        strArray[2] = new String("Java");

        System.out.println( strArray[0] == strArray[1] );	// true,  같은 객체 참조
        System.out.println( strArray[0] == strArray[2] );	// flase, 다른 객체 참조
        System.out.println( strArray[0].equals(strArray[1]) );	// true, 문자열 동일
    }
}
