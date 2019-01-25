package thread_down_class_production;

public class BeepPrintExample {
    public static void main(String[] args) {
        Thread thread = new BeepThread();
        thread.start();

        for(int i=0; i<5; i++) {
            System.out.println("띵");
            try { Thread.sleep(500); } catch (Exception e) {}
        }
    }
}
