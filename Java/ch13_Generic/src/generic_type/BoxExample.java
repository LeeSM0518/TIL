package generic_type;

public class BoxExample {
    public static void main(String[] args) {
        Box box = new Box();

        // String -> Object (자동 타입 변환)
        box.set("홍길동");

        // Object -> String (강제 타입 변환)
        String name = (String) box.get();

        // Apple -> Object (자동 타입 변환)
        box.set(new Apple());

        // Object -> Apple (강제 타입 변환)
        Apple apple = (Apple) box.get();
    }
}
