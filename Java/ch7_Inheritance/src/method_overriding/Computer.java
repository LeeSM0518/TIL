package method_overriding;

public class Computer extends Calculator{
    // 어노테이션을 사용하면 메소드가 정확히 오버라이딩된 것인지
    // 컴파일러가 체크하기 때문에 실수를 줄일 수 있다.
    // @Override
    double areaCircle(double r){
        System.out.println("Computer 객체의 areaCircle() 실행");
        return Math.PI * r * r;
    }
}