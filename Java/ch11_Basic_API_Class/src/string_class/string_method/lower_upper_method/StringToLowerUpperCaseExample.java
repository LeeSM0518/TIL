package string_class.string_method.lower_upper_method;

public class StringToLowerUpperCaseExample {
    public static void main(String[] args) {
        String str1 = "Java Programming";
        String str2 = "JAVA PROGRAMMING";

        // 문자열이 동일한지 확인(false)
        System.out.println(str1.equals(str2));

        // 모든 문자열을 소문자로 바꾸고 새로운 객체 리턴
        String lowerStr1 = str1.toLowerCase();
        String lowerStr2 = str2.toLowerCase();

        // 문자열이 동일한지 확인(true)
        System.out.println(lowerStr1.equals(lowerStr2));

        // 대소문자를 무시하고 문자열을 비교한다.
        System.out.println(str1.equalsIgnoreCase(str2));
    }
}
