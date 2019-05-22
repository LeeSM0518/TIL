package abstract_method_overriding;

// 추상 클래스
public abstract class Animal {
    public String kind;

    public void breathe() {
        System.out.println("숨을 쉰다.");
    }

    // 추상 메소드 선언
    public abstract void sound();
}
