package hash_set;

public class Member {
    public String name;
    public int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Member) {
            Member member = (Member) obj;
            return member.name.equals(name) && (member.age == age);
        } else {
            return false;
        }
    }

    @Override
    // name 과 age 값이 같으면 동일한
    // hashCode 가 리턴
    public int hashCode() {
        return name.hashCode() + age;
    }
}
