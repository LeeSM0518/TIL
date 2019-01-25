package thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ResultByRunnableExample {
    public static void main(String[] args) {
        // CPU 코어 수만큼 최대 스레드를 사용하는 스레드풀 생성
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        System.out.println("[작업 처리 요청]");
        // Runnable 익명 객체 Task 구현
        class Task implements Runnable {
            // 외부 Result 객체를 필드에 저장
            Result result;
            Task(Result result) {
                this.result = result;
            }

            @Override
            public void run() {
                int sum = 0;
                for(int i=1; i<=10; i++) {
                    sum += i;
                }
                // Result 객체에 작업 결과 저장
                result.addValue(sum);
            }
        }

        // 작업 결과 Result 객체 생성
        Result result = new Result();
        // 두 가지 스레드 생성
        Runnable task1 = new Task(result);
        Runnable task2 = new Task(result);
        // 두 가지 작업 처리 요청
        Future<Result> future1 = executorService.submit(task1, result);
        Future<Result> future2 = executorService.submit(task2, result);

        try {
            // 두 가지 작업 결과 취합
            result = future1.get();
            result = future2.get();
            System.out.println("[처리 결과] " + result.accuValue);
            System.out.println("[작업 처리 완료]");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[실행 예외 발생함] " + e.getMessage());
        }

        executorService.shutdown();
    }
}

class Result {
    int accuValue;
    // 처리 결과를 저장
    synchronized void addValue(int value) {
        accuValue += value;
    }
}