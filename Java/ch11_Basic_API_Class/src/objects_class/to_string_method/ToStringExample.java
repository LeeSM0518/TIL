package objects_class.to_string_method;

import java.util.Objects;

public class ToStringExample {
    public static void main(String[] args) {
        // String 객체 선언
        String str1 = "홍길동";
        String str2 = null;

        // str1의 문자열 리턴
        System.out.println(Objects.toString(str1));

        // str2는 문자열이 없으므로 null 리턴
        System.out.println(Objects.toString(str2));

        // str2는 문자열이 없으므로 nullDefault 메시지 리턴
        System.out.println(Objects.toString(str2, "이름이 없습니다."));
    }
}
