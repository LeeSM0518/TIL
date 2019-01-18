package string_class.string_method.replace_method;

public class StringReplaceExample {
    public static void main(String[] args) {
        // String 타입 변수 초기화
        String oldStr = "자바는 객체지향언어 입니다.";

        // oldStr 의 자바를 JAVA 로 바꾸고 객체를 생성한다.
        String newStr = oldStr.replace("자바", "JAVA");

        // newStr 과 같은 문자열을 참조하게 한다.
        String newStr2 = "JAVA는 객체지향언어 입니다.";

        System.out.println(oldStr);
        System.out.println(newStr);
        System.out.println("oldStr's address: " + oldStr.hashCode());
        System.out.println("newStr's address: " + newStr.hashCode());
        System.out.println("newStr2's address: " + newStr2.hashCode());
    }
}
