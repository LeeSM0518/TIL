package abstract_method_overriding;

// Animal 상속
public class Dog extends Animal {
    public Dog(){
        this.kind = "포유류";
    }

    // 추상 메소드 오버라이딩
    public void sound(){
        System.out.println("멍멍");
    }
}
