package object_class.equals_method;

public class Member {
    public String id;

    public Member(String id) {
        this.id = id;
    }

    // equals 오버라이딩
    @Override
    public boolean equals(Object obj) {
        // 매개값이 Member 타입인지 확인
        if(obj instanceof Member) {
            // 매개값을 Member 타입으로 강제 타입 변환
            Member member = (Member) obj;

            // id 필드값이 동일한지 비교
            if(id.equals(member.id)) {
                return true;
            }
        }
        return false;
    }
}