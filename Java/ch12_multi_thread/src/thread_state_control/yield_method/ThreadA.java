package thread_state_control.yield_method;

public class ThreadA extends Thread {
    public boolean stop = false;
    public boolean work = true;

    public void run() {
        // stop이 true가 되면 while문 종료
        while(!stop) {
            if(work) {
                System.out.println("ThreadA 작업 내용");
            } else {
                // work가 false가 되면 다른 스레드에게 실행 양보
                Thread.yield();
            }
        }
        System.out.println("ThreadA 종료");
    }
}
