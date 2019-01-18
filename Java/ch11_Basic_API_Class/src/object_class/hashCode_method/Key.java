package object_class.hashCode_method;

public class Key {
    public int number;

    public Key(int number) {
        this.number = number;
    }

    // equals 오버라이딩
    @Override
    public boolean equals(Object obj) {
        // 매개값이 Key 타입인지 확인
        if(obj instanceof Key) {
            // Key 타입으로 강제 타입 변환
            Key compareKey = (Key) obj;

            // number 필드 값이 동일한지 비교
            if(this.number == compareKey.number) {
                System.out.println("equals 실행");
                return true;
            }
        }
        System.out.println("equals 실행");
        return false;
    }

    // hashCode 오버라이딩
    // 논리적 동등 객체일 경우 동일한 해시코드가
    // 리턴되도록 하기 위해서
    @Override
    public int hashCode() {
        System.out.println("hashCode 실행");
        return number;
    }
}