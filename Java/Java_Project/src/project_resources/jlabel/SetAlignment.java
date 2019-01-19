package project_resources.jlabel;

import javax.swing.*;
import java.awt.*;

public class SetAlignment {
    public static void main(String[] args) {
        Dimension dimension = new Dimension(200, 100);

        JFrame frame = new JFrame("Hello!");
        frame.setLocation(200, 400);
        frame.setPreferredSize(dimension);

        JLabel label = new JLabel();
        label.setText("setText Test");

        // 세로 아래 정렬
        label.setVerticalAlignment(SwingConstants.BOTTOM);

        // 가로 오른쪽 정렬
        label.setHorizontalAlignment(SwingConstants.RIGHT);

        frame.add(label);
        frame.pack();
        frame.setVisible(true);
    }
}
