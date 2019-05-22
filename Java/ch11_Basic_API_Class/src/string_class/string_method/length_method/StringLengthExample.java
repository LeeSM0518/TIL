package string_class.string_method.length_method;

public class StringLengthExample {
    public static void main(String[] args) {
        // String 타입의 변수에 값 초기화
        String ssn = "111111-2222222";
        // String 변수의 문자열 길이를 호출
        int length = ssn.length();

        // 길이 비교
        if(length == 14) {
            System.out.println("주민번호 자리수가 맞습니다.");
        } else {
            System.out.println("주민번호 자리수가 틀립니다.");
        }
    }
}
