package thread_pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ResultByCallableExample {
    public static void main(String[] args) {
        // CPU 코어 수만큼 최대 스레드를 사용하는 스레드풀 생성
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        System.out.println("[작업 처리 요청]");
        // Callable 객체 생성 및 구현
        Callable<Integer> task = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for(int i=1; i<=10; i++) {
                    sum += i;
                }
                return sum;
            }
        };
        // Callable 작업 처리 요청
        Future<Integer> future = executorService.submit(task);

        // 작업 처리의 예외 처리
        try {
            // future 작업이 완료될 때까지 블로킹되었다가 처리 결과 V 리턴
            // 예외가 발생하지 않아서 null 리턴
            int sum = future.get();
            System.out.println("[처리 결과] " + sum);
            System.out.println("[작업 처리 완료]");
        } catch (Exception e) {
            System.out.println("[실행 예외 발생함] " + e.getMessage());
        }

        //
        executorService.shutdown();
    }
}