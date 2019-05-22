package thread_callback;

import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallbackExample {
    // ExecutorService 변수 선언
    private ExecutorService executorService;

    // 생산자에 스레드풀 생성문 선언
    public CallbackExample() {
        executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
    }

    // 콜백 메소드를 가진 CompletionHandler 익명 객체 구현
    //                      <결과타입, 첨부타입>
    private CompletionHandler<Integer, Void> callback =

            new CompletionHandler<Integer, Void>() {
                @Override
                //                  결과타입,          첨부타입
                public void completed(Integer result, Void attachment) {
                    System.out.println("completed() 실행: " + result);
                }

                @Override
                //                  예외 타입     , 첨부 타입
                public void failed(Throwable exc, Void attachment) {
                    System.out.println("failed() 실행: " + exc.toString());
                }
            };

    public void doWork(final String x, final String y) {
        // Runnable 익명 객체 선언
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    int intX = Integer.parseInt(x);
                    int intY = Integer.parseInt(y);
                    int result = intX + intY;
                    // 정상 처리했을 경우
                    callback.completed(result, null);
                } catch (NumberFormatException e) {
                    // 예외가 발생했을 경우
                    callback.failed(e, null);
                }
            }
        };
        // 스레드풀에게 작업 처리 요청
        executorService.submit(task);
    }

    public void finish() {
        // 스레드풀 종료
        executorService.shutdown();
    }

    public static void main(String[] args) {
        CallbackExample example = new CallbackExample();
        example.doWork("3", "3");
        example.doWork("3", "심");
        example.finish();
    }
}
