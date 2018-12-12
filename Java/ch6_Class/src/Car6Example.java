public class Car6Example {
    public static void main(String[] args) {
        Car6 myCar = new Car6();
        myCar.keyTurnOn();
        myCar.run();
        int speed = myCar.getSpeed();
        System.out.println("현재 속도: " + speed + "km/h");
    }
}
