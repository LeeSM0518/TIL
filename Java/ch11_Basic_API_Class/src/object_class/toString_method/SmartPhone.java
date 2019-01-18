package object_class.toString_method;

public class SmartPhone {
    private String company;
    private String os;

    public SmartPhone(String company, String os) {
        this.company = company;
        this.os = os;
    }

    // 객체 문자 정보를 전달해주는 toString 을 오버라이딩해서
    // 회사와 운영체제를 반환한다.
    @Override
    public String toString() {
        return company + ", " + os;
    }
}
