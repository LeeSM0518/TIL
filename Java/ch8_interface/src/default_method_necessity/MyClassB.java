package default_method_necessity;

public class MyClassB implements MyInterface {
    public void method1() {
        System.out.println("MyClassB-method1() 실행");
    }

    // 디폴트 메소드 재정의
    public void method2() {
        System.out.println("MyClassB-method2() 실행");
    }
}
