public class Car8 {
    int speed;

    void run(){
        System.out.println(speed + "으로 달립니다.");
    }

    public static void main(String[] args) {
        Car8 myCar = new Car8();
        myCar.speed = 60;
        myCar.run();
    }
}
