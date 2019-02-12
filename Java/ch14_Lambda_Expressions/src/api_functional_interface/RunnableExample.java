package api_functional_interface;

public class RunnableExample {
    public static void main(String[] args) {

        // 람다식(스레드가 실행하는 코드, run() 메소드)
        Runnable runnable = () -> {
            for(int i=0; i<10; i++) {
                System.out.println(i);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
