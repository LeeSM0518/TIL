package thread_state;

public class StatePrintThread extends Thread{
    private Thread targetThread;

    // targetThread: 상태를 조사할 스레드
    public StatePrintThread(Thread targetThread) {
        this.targetThread = targetThread;
    }

    public void run() {
        while(true) {
            // 스레드 상태 얻기
            Thread.State state = targetThread.getState();
            System.out.println("타겟 스레드 상태: " + state);

            // 객체 생성 상태일 경우 실행 대기 상태로 만듬
            if(state == Thread.State.NEW) {
                targetThread.start();
            }

            // 종료 상태일 경우 while문을 종료함
            if(state == Thread.State.TERMINATED) {
                break;
            }

            try {
                Thread.sleep(500);
            } catch (Exception e) {}
        }
    }
}
