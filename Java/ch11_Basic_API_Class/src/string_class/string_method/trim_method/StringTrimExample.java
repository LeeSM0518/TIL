package string_class.string_method.trim_method;

public class StringTrimExample {
    public static void main(String[] args) {
        String tel1 = "  02";
        String tel2 = "123   ";
        String tel3 = "   1234   ";

        // trim() 메소드로 문자열의 양 옆 공백을 제거한 뒤 저장
        String tel = tel1.trim() + tel2.trim() + tel3.trim();
        System.out.println(tel);
    }
}
