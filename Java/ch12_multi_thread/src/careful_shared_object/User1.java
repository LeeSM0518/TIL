package careful_shared_object;

public class User1 extends Thread{
    private Calculator calculator;

    public void setCalculator(Calculator calculator) {
        this.setName("User1");          // 스레드 이름 User1로 설정
        this.calculator = calculator;   // 공유 객체인 Calculator 을 필드에 저장
    }

    @Override
    public void run() {
        calculator.setMemory(100);      // 공유 객체인 Calculator 의 메모리에 100을 저장
    }
}
