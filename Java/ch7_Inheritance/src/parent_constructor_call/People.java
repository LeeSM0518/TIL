package parent_constructor_call;

public class People {
    public String name;
    public String ssn;

    // 기본 생성자가 없고 두 개의 매개값을 받아 객체를 생성하는 생성자
    public People(String name, String ssn){
        this.name = name;
        this.ssn = ssn;
    }
}
