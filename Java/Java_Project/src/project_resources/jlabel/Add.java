package project_resources.jlabel;

import javax.swing.*;
import java.awt.*;

public class Add {
    public static void main(String[] args) {
        Dimension dim = new Dimension(200, 100);

        JFrame frame = new JFrame("Hello!");
        frame.setLocation(200, 400);
        frame.setPreferredSize(dim);

        // JLabel 객체에 메시지를 넣어 생성한다.
        JLabel label = new JLabel("Welcome to Java");

        // 라벨을 윈도우에 추가한다.
        frame.add(label);
        frame.pack();
        frame.setVisible(true);
    }
}
