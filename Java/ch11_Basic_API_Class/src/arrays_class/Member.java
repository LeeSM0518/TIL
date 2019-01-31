package arrays_class;

// Comparable<Member> : Member 타입만 비교하기 위해 제네릭 <>을 사용
public class Member implements Comparable<Member> {
    String name;
    Member(String name) {
        this.name = name;
    }

    @Override
    // compareTo() 메소드는 비교값을 리턴하도록 오버라이딩
    public int compareTo(Member o) {
        return name.compareTo(o.name);
    }
}