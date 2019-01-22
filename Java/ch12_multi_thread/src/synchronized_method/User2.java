package synchronized_method;

public class User2 extends Thread {
    private Calculator calculator;

    public void setCalculator(Calculator calculator) {
        this.setName("User2");          // 스레드 이름 User2로 저장
        this.calculator = calculator;   // 공유 객체인 Calculator 을 필드에 저장
    }

    @Override
    public void run() {
        calculator.setMemory(50);       // 공유 객체인 Calculator 의 메모리에 50을 저장
    }
}
