package string_class.string_method.substring_method;

public class StringSubstringExample {
    public static void main(String[] args) {
        String ssn = "111111-2222222";

        // 0부터 6까지 문자열 추출
        String firstNum = ssn.substring(0, 6);
        System.out.println(firstNum);

        // 7부터 끝까지 문자열 추출
        String secondNum = ssn.substring(7);
        System.out.println(secondNum);
    }
}
