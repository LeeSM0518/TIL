package thread_group;

import daemon_thread.AutoSaveThread;

import java.util.Map;
import java.util.Set;

public class ThreadInfoExample {
    public static void main(String[] args) {
        AutoSaveThread autoSaveThread = new AutoSaveThread();
        autoSaveThread.setName("AutoSaveThread");
        autoSaveThread.setDaemon(true);
        autoSaveThread.start();

        // 프로세스에서 실행하는 모든 Thread 를 가져오는 코드
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        Set<Thread> threads = map.keySet();

        // Thread 를 하나씩 가져와서 루핑시킨다.
        for(Thread thread : threads) {
            System.out.println("Name: " + thread.getName() +
                    ((thread.isDaemon()) ? "(데몬)" : "(주)"));
            System.out.println("\t" + "소속그룹: " +
                    thread.getThreadGroup().getName());
            System.out.println();
        }
    }
}