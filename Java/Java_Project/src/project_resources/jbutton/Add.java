package project_resources.jbutton;

import javax.swing.*;
import java.awt.*;

public class Add {
    public static void main(String[] args) {
        Dimension dimension = new Dimension(400, 100);

        JFrame frame = new JFrame("Hello");
        frame.setLocation(200, 400);
        frame.setPreferredSize(dimension);

        // 버튼 객체 생성
        JButton button = new JButton("Test");
        // 버튼 추가
        frame.add(button);

        frame.pack();
        frame.setVisible(true);
    }
}