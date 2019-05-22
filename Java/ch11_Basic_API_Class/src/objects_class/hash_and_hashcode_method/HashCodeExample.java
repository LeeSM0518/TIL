package objects_class.hash_and_hashcode_method;

import java.util.Objects;

public class HashCodeExample {
    public static void main(String[] args) {
        // 필드 값을 동일하게 객체를 생성해준다.
        Student s1 = new Student(1, "홍길동");
        Student s2 = new Student(1, "홍길동");

        // s1과 s2의 해시 코드를 출력한다.
        System.out.println(s1.hashCode());
        System.out.println(Objects.hashCode(s2));
    }

    // Student 클래스
    static class Student {
        // 필드
        int sno;
        String name;

        // 생성자
        Student(int sno, String name) {
            this.sno = sno;
            this.name = name;
        }

        // hashCode 메소드를 재정의해서
        // 필드 값이 동일하면 같은 해시 코드를 가지도록 한다.
        @Override
        public int hashCode() {
            return Objects.hash(sno, name);
        }
    }
}
