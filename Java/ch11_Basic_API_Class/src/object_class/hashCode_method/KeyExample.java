package object_class.hashCode_method;

import java.util.HashMap;

public class KeyExample {
    public static void main(String[] args) {
        // Key 객체를 식별키로 사용해서 String 값을 저장하는 HashMap 객체 생성
        System.out.println("HashMap 객체 생성");
        HashMap<Key, String> hashMap = new HashMap<Key, String>();

        // 식별키 값이 1 로 "홍길동" 저장
        System.out.println("HashMap 객체 Key,Value 저장");
        hashMap.put(new Key(1), "홍길동");

        // 식별키 값이 1인 객체의 "홍길동"을 읽어온다
        System.out.println("HashMap Key 값으로 Value 읽어옴");
        String value = hashMap.get(new Key(1));
        System.out.println(value);
    }
}
