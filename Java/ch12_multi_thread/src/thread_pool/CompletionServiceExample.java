package thread_pool;

import java.util.concurrent.*;

public class CompletionServiceExample extends Thread {
    public static void main(String[] args) {
        // CPU 코어 수만큼 최대 스래드를 사용하는 스레드풀 생성
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        // 스레드풀에서 작업 처리가 완료된 것만 통보받는 완료 서비스 객체 생성
        // 생성자 매개값으로 executorService 제공
        CompletionService<Integer> completionService =
                new ExecutorCompletionService<Integer>(executorService);

        System.out.println("[작업 처리 요청]");
        for(int i=0; i<3; i++) {
            // submit 으로 작업 처리 요청
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int sum = 0;
                    for(int i=1; i<=10; i++) {
                        sum += i;
                    }
                    return sum;
                }
            });
        }

        System.out.println("[처리 완료된 작업 확인]");
        // 스레드풀의 스레드에서 실행하도록 한다
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        // 완료된 작업 가져오기
                        Future<Integer> future = completionService.take();
                        int value = future.get();
                        System.out.println("[처리 결과] " + value);
                    } catch (Exception e) {
                        break;
                    }
                }
            }
        });

        // 3초 후 스레드풀 종료
        try { Thread.sleep(3000); }
        catch (InterruptedException e) {}
        executorService.shutdownNow();
    }
}
