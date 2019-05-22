package thread_safe_stop.interrupt_method;

public class InterruptExample {
    public static void main(String[] args) {
        Thread thread = new PrintThread2();
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        // 스레드를 종료시키기 위해
        // InterruptedException 을 발생시킨다
        thread.interrupt();
    }
}
