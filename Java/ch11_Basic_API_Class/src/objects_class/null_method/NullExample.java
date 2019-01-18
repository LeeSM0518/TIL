package objects_class.null_method;

import java.util.Objects;

public class NullExample {
    public static void main(String[] args) {
        // String 객체 선언
        String str1 = "홍길동";
        String str2 = null;

        // str1 null 조사
        System.out.println(Objects.requireNonNull(str1));

        // str2 null 조사 후 null 리턴
        try {
            String name = Objects.requireNonNull(str2);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        // str2 null 조사 후 메시지 출력
        try {
            String name = Objects.requireNonNull(str2, "이름이 없습니다.");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        // str2 null 조사 후 메시지 출력
        try {
            // 람다식 이용
            String name = Objects.requireNonNull(str2, ()->"이름이 없다니깐요");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
