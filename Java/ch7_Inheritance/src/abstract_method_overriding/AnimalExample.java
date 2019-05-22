package abstract_method_overriding;

public class AnimalExample {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Cat cat = new Cat();

        dog.sound();
        cat.sound();
        System.out.println("-----");

        // 변수의 자동 타입 변환
        Animal animal = null;

        // Dog로 타입 변환
        animal = new Dog();
        animal.sound();

        // Cat로 타입 변환
        animal = new Cat();
        animal.sound();
        System.out.println("-----");

        // 메소드의 다형성
        animalSound(new Dog()); // Animal animal = new Dog();
        animalSound(new Cat()); // Animal animal = new Cat();
    }

    // 자동 타입 변환
    public static void animalSound(Animal animal) {
        animal.sound();
    }
}
