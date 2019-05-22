package synchronized_method;

public class Calculator {
    private int memory;

    public int getMemory() {
        return memory;
    }

    // 계산기 메모리에 값을 저장하는 메소드
    public synchronized void setMemory(int memory) {
        // 매개값을 memory 필드에 저장
        this.memory = memory;

        try {
            // 스레드를 2초간 일시 정지
            Thread.sleep(2000);
        } catch(InterruptedException e) {}
        System.out.println(Thread.currentThread().getName() + ": " + this.memory);
    }
}
