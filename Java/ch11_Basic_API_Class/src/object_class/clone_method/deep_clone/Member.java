package object_class.clone_method.deep_clone;

import java.util.Arrays;

public class Member implements Cloneable{
    // 기본 타입 필드
    public String name;
    public int age;

    // 참조 타입 필드(깊은 복제 대상)
    public int[] scores;
    public Car car;

    // 생산자
    public Member(String name, int age, int[] scores, Car car) {
        this.name = name;
        this.age = age;
        this.scores = scores;
        this.car = car;
    }

    // 깊은 복제를 위한 clone 메소드 오버라이딩
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // 먼져 얕은 복사를 해서 name, age 를 복제한다.
        Member cloned = (Member) super.clone();

        // scores 를 깊은 복제한다. 복제할 배열과 길이를 파라미터로 입력
        cloned.scores = Arrays.copyOf(this.scores, this.scores.length);

        // car 도 깊은 복제한다. 자신의 Car 객체의 변수를 파라미터로 해서
        // Car 객체를 생성한뒤 cloned 에 저장한다.
        cloned.car = new Car(this.car.model);
        return cloned;
    }

    public Member getMember() {
        Member cloned = null;
        try {
            cloned = (Member) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloned;
    }
}
