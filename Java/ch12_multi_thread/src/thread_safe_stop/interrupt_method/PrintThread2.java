package thread_safe_stop.interrupt_method;

public class PrintThread2 extends Thread{
    @Override
    public void run() {
        try {
            while(true) {
                System.out.println("실행 중");
                Thread.sleep(1);
                // interrupt() 메소드를 실행시키면 catch 문으로 이동한다.
            }
        } catch (InterruptedException e) {}

        System.out.println("자원 정리");
        System.out.println("실행 종료");
    }

}
