package promotion;

public class ChildExample {
    public static void main(String[] args) {
        Child child = new Child();

        child.method1();
        child.method2();
        child.method3();

        Parent parent1 = new Parent();
        parent1.method1();
        parent1.method2();

        // 자동 타입 변환
        Parent parent2 = child;

        parent2.method1();
        parent2.method2(); // 오버라이딩된 함수 호출
        ((Child)parent2).method3();

        parent2 = ((Child)parent2);
        //parent2.method3();
        System.out.println(parent2.hashCode());
        System.out.println(((Child)parent2).hashCode());
    }
}