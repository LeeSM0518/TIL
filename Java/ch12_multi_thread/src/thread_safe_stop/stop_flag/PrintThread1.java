package thread_safe_stop;

public class PrintThread1 extends Thread{
    private boolean stop;

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void run() {
        // stop 이 true 가 되면 자원을 정리한 후 실행을 종료시킨다.
        while(!stop) {
            System.out.println("실행 중");
        }
        System.out.println("자원 정리");
        System.out.println("실행 종료");
    }
}
