package thread_cooperation;

public class ThreadB extends Thread{
    private WorkObject workObjecct;

    public ThreadB(WorkObject workObjecct) {
        // 공유 객체를 매개값으로 받아 필드에 저장
        this.workObjecct = workObjecct;
    }

    @Override
    public void run() {
        // 공유 객체의 methodB()를 10번 반복 호출
        for(int i=0; i<10; i++) {
            workObjecct.methodB();
        }
    }
}
