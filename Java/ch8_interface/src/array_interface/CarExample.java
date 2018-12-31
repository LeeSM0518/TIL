package array_interface;

import field_polymorphism.KumhoTire;

public class CarExample {
    public static void main(String[] args) {
        Car myCar = new Car();

        myCar.run();

        // 인덱스를 통해 앞쪽 타이어를 KumhoTire로 교체
        myCar.tires[0] = new KumhoTire();
        myCar.tires[1] = new KumhoTire();

        myCar.run();
    }
}