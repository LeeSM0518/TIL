package object_class.clone_method;

public class Member implements Cloneable{
    // Member의 멤버 선언
    public String id;
    public String name;
    public String password;
    public int age;
    public boolean adult;

    // Member 생산자 선언
    public Member(String id, String name, String password,
                  int age, boolean adult) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.adult = adult;
    }

    // 얕은 복제를 위한 메소드 선언
    public Member getMember() {
        // Member 타입 변수 선언
        Member cloned = null;

        // 예외 처리를 위한 try-catch 선언
        try {
            // clone() 메소드의 리턴 타입은 Object 이므로
            // Member 타입으로 강제 타입 변환을 한다.
            cloned = (Member) clone();
        } catch (CloneNotSupportedException e) {
            // 예외가 발생
        }
        return cloned;
    }
}
