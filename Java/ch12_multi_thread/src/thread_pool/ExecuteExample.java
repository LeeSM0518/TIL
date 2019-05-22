package thread_pool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecuteExample {
    public static void main(String[] args) throws Exception{
        // 최대 스레드 개수가 2인 스레드풀 생성
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for(int i=0; i<10; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // 스레드 총 개수 및 작업 스레드 이름 출력
                    ThreadPoolExecutor threadPoolExecutor =
                            (ThreadPoolExecutor) executorService;
                    int poolSize = threadPoolExecutor.getPoolSize();
                    String threadName = Thread.currentThread().getName();
                    System.out.println("[총 스레드 개수: " + poolSize + "] 작업 스레드 이름: " +
                            threadName);

                    // 예외 발생 시킴
                    int value = Integer.parseInt("삼");
                }
            };

            // 작업 처리 요청
            // 작업 처리 도중 예외가 발생하여 스레드가 종료되고 스레드풀에서 제거
//            executorService.execute(runnable);
            // 작업 처리 도중 예외가 발생하더라도 스레드는 종료되지 않고 다음 작업을 위해 재사용
             executorService.submit(runnable);
            Thread.sleep(10);
        }
        // 스레드풀 종료
        executorService.shutdown();
    }
}