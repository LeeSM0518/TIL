package thread_state_control.join_method;

public class JoinExample {
    public static void main(String[] args) {
        SumThread sumThread = new SumThread();
        sumThread.start();

        try {
            // sumThread가 종료할 때까지 메인 스레드를 일시 정지 시킴
            sumThread.join();
        } catch (InterruptedException e) { }

        System.out.println("1~100 합: " + sumThread.getSum());
    }
}
