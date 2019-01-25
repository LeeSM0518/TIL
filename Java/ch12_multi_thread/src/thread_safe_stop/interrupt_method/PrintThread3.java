package thread_safe_stop.interrupt_method;

public class PrintThread3 extends Thread{
    @Override
    public void run() {
        while(true) {
            System.out.println("실행 중");
            // interrupt() 가 호출되었는지 확인
            if(Thread.interrupted()) {
                break;
            }
        }
        System.out.println("자원 정리");
        System.out.println("실행 종료");
    }
}
