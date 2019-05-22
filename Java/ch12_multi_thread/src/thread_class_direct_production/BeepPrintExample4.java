package thread_class_direct_production;

import java.awt.*;

public class BeepPrintExample4 {
    Thread thread = new Thread(() -> {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for(int i=0; i<5; i++) {
            toolkit.beep();
            try { Thread.sleep(500); } catch (Exception e) {}
        }
    });
}
