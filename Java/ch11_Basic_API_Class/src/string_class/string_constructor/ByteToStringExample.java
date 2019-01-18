package string_class.string_constructor;

public class ByteToStringExample {
    public static void main(String[] args) {
        // byte 타입 배열 객체 생성
        byte[] bytes = { 72, 101, 108, 108, 111, 32, 74, 97, 118, 97 };

        // String 생산자로 byte 배열을 문자열로 변환
        String str1 = new String(bytes);
        System.out.println(str1);

        // String 생산자로 byte 배열을 6 번째 위치부터 4개를 문자열로 변환
        String str2 = new String(bytes, 6, 4);
        System.out.println(str2);
    }
}
