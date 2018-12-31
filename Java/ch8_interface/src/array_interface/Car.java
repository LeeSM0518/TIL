package array_interface;

import field_polymorphism.HankookTire;
import field_polymorphism.Tire;

public class Car {
    // 4개의 타이어 필드를 인터페이스를 이용해 배열로 선언
    Tire[] tires = {
            new HankookTire(),
            new HankookTire(),
            new HankookTire(),
            new HankookTire()
    };

    // 전체 타이어의 roll() 메소드를 호출하는 for문
    void run() {
        for(Tire tire : tires){
            tire.roll();
        }
    }
}