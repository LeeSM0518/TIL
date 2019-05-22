package string_class.string_method.get_bytes_method;

import java.io.UnsupportedEncodingException;

public class StringGetBytesExample {
    public static void main(String[] args) {
        String str = "안녕하세요";

        // 기본 문자셋으로 인코딩과 디코딩
        byte[] bytes1 = str.getBytes();
        System.out.println("bytes1.length: " + bytes1.length);
        String str1 = new String(bytes1);
        System.out.println("bytes1->String: " + str1);

        try {
            // EUC-KR을 이용해서 인코딩 및 디코딩
            byte[] bytes2 = str.getBytes("EUC-KR");
            System.out.println("bytes2.length: " + bytes2.length);
            String str2 = new String(bytes2, "EUC-KR");
            System.out.println("bytes2->String: " + str2);

            // UTF-8을 이용해서 인코딩 및 디코딩
            byte[] bytes3 = str.getBytes("UTF-8");
            System.out.println("bytes3.length: " + bytes3.length);
            String str3 = new String(bytes3, "UTF-8");
            System.out.println("bytes3->String: " + str3);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
