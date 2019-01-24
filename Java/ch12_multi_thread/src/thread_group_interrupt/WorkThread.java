package thread_group_interrupt;

public class WorkThread extends Thread{
    public WorkThread(ThreadGroup threadGroup, String threadName) {
        // 스레드 그룹과 스레드 이름을 설정
        super(threadGroup, threadName);
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 예외 발생시 스레드 종료
                System.out.println(getName() + " interrupted");
                break;
            }
        }
        System.out.println(getName() + " 종료됨");
    }
}