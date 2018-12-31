package casting;

public class Bus implements Vehicle{
    public void run() {
        System.out.println("버스가 달린다.");
    }

    public void checkFare() {
        System.out.println("승차요금을 체그합니다.");
    }
}
