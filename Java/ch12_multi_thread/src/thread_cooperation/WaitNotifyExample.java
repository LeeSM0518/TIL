package thread_cooperation;

public class WaitNotifyExample {
    public static void main(String[] args) {
        WorkObject sharedObject = new WorkObject();     // 공유 객체

        ThreadA threadA = new ThreadA(sharedObject);    // ThreadA 생성
        ThreadB threadB = new ThreadB(sharedObject);    // ThreadB 생성

        threadA.start();    // ThreadA 실행
        threadB.start();    // ThreadB 실행
    }
}
