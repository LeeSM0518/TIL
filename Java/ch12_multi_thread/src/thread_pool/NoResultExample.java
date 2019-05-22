 package thread_pool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NoResultExample {
    public static void main(String[] args) {
        // CPU 코어의 수만큼 최대 스레드를 사용하는 스레드풀 생성
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
         );

        System.out.println("[작업 처리 요청]");
        // Runnable 익명 객체 생성
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for(int i=1; i<=10; i++) {
                    sum += i;
                }
                System.out.println("[처리 결과] " + sum);
            }
        };

        // 스레드풀의 작업 큐에 Runnable 객체를 작업 처리 요청
        Future future = executorService.submit(runnable);

        try {
            // future 작업이 완료될 때까지 블로킹되었다가 처리 결과 V 리턴
            // 예외가 발생하지 않아서 null 리턴
            future.get();
            System.out.println("[작업 처리 완료]");
        } catch (Exception e) {
            System.out.println("[실행 예외 발생함] " + e.getMessage());
        }

        // 작업 큐에 있는 모든 작업을 처리한 뒤에 스레드풀을 종료시킨다.
        executorService.shutdown();
    }
}