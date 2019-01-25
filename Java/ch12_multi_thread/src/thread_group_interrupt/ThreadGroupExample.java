package thread_group_interrupt;

public class ThreadGroupExample {
    public static void main(String[] args) {
        // 스레드 그룹 생성
        ThreadGroup myGroup = new ThreadGroup("myGroup");

        // myGroup 스레드 그룹에 두 스레드를 포함
        WorkThread workThreadA = new WorkThread(myGroup, "workThreadA");
        WorkThread workThreadB = new WorkThread(myGroup, "workThreadB");

        workThreadA.start();
        workThreadB.start();

        System.out.println("[ main 스레드 그룹의 list() 메소드 출력 내용 ]");
        // mainGroup 에 현재 스레드의 그룹을 가져온다.
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        // mainGroup 의 포함된 스레드와 하위 그룹에 대한 정보를 출력 시킨다.
        mainGroup.list();
        System.out.println();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {}

        System.out.println("[ myGroup 스레드 그룹의 interrupt() 메소드 호출 ]");
        myGroup.interrupt();
    }
}