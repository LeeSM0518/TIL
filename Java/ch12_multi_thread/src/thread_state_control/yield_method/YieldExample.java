package thread_state_control.yield_method;

public class YieldExample {
    public static void main(String[] args) {
        // 스레드 A, B 생성
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();

        // 스레드 A, B 실행
        threadA.start();
        threadB.start();

        try { Thread.sleep(3000); } catch (InterruptedException e) {}
        threadA.work = false;   // 스레드 B만 실행

        try { Thread.sleep(3000); } catch (InterruptedException e) {}
        threadA.work = true;    // 스레드 A, B 모두 실행

        try { Thread.sleep(3000); } catch (InterruptedException e) {}
        // 스레드 A, B 모두 종료
        threadA.stop = true;
        threadB.stop = true;
    }
}
