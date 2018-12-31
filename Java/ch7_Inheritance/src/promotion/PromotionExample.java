package promotion;

class A{}

class B extends A{}
class C extends A{}

class D extends B{}
class E extends C{}

public class PromotionExample {
    public static void main(String[] args) {
        B b = new B();
        C c = new C();
        D d = new D();
        E e = new E();
        A a = new A();

        // 자식이 부모로 타입 변환은 안된다
        // b = a; 컴파일 에러!!

        A a1 = b;
        A a2 = c;
        A a3 = d;
        A a4 = e;

        B b1 = d;
        C c1 = e;

        // 상속 관계가 아니므로 컴파일 에러!!
        // B b3 = e;
        // C c2 = d;
    }
}