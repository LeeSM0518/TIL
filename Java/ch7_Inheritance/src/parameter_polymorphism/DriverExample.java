package parameter_polymorphism;

public class DriverExample {
    public static void main(String[] args) {
        Driver driver = new Driver();

        Bus bus = new Bus();
        Taxi taxi = new Taxi();

        // 자동 타입 변환
        // Vehicle vehicle = bus;
        driver.drive(bus);

        // 자동 타입 변환
        // Vehicle vehicle = taxi;
        driver.drive(taxi);
    }
}
