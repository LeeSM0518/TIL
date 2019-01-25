package string_class.string_method.value_of_method;

public class StringValueOfExample {
    public static void main(String[] args) {
        // 정수, 소수, 부울을 모두 String 타입으로 변환한다.
        String str1 = String.valueOf(10);
        String str2 = String.valueOf(10.5);
        String str3 = String.valueOf(true);

        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);

        // 모두 같은 String 객체를 참조하고 있음을 알 수 있다.
        System.out.println(str1.getClass());
        System.out.println(str2.getClass());
        System.out.println(str3.getClass());
    }
}
