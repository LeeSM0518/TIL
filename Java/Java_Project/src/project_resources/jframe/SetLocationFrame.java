package project_resources;

import javax.swing.*;

public class SetLocationFrame {
    public static void main(String[] args) {
        // 창의 타이틀
        JFrame frame = new JFrame("Hello World!");

        // 창이 나오는 위치
        frame.setLocation(200, 400);

        frame.pack();

        // 보이게 하기
        frame.setVisible(true);
    }
}