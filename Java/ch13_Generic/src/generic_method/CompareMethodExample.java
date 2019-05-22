package generic_method;

public class CompareMethodExample {
    public static void main(String[] args) {
        Pair<Integer, String> p1 = new Pair<Integer, String>(1, "사과");
        Pair<Integer, String> p2 = new Pair<Integer, String>(1, "사과");

        // 구체적인 제네릭 타입을 명시적으로 지정
        boolean result1 = Util2.<Integer, String>compare(p1, p2);

        if(result1) {
            System.out.println("논리적으로 동등한 객체입니다.");
        } else {
            System.out.println("논리적으로 동등하지 않은 객체입니다.");
        }

        Pair<String, String> p3 = new Pair<String, String>("user1", "홍길동");
        Pair<String, String> p4 = new Pair<String, String>("user2", "홍길동");

        // 구체적인 타입을 추정
        boolean result2 = Util2.compare(p3, p4);

        if(result2) {
            System.out.println("논리적으로 동등한 객체입니다.");
        } else {
            System.out.println("논리적으로 동등하지 않은 객체입니다.");
        }
    }
}