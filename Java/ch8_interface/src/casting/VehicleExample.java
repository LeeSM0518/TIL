package casting;

public class VehicleExample {
    public static void main(String[] args) {
        Vehicle vehicle = new Bus();

        vehicle.run();
        // vehicle 인터페이스에 checkFare()가 없으므로 컴파일 에러
        // vehicle.checkFare();

        Bus bus = (Bus) vehicle;

        bus.run();
        // Bus 클래스에는 checkFare()가 있다.
        bus.checkFare();
    }
}
