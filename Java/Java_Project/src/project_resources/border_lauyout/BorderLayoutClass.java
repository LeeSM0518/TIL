package project_resources.border_lauyout;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutClass {
    public static void main(String[] args) {
        Dimension dimension = new Dimension(400, 100);

        JFrame frame = new JFrame("Hello");
        frame.setLocation(200, 400);
        frame.setPreferredSize(dimension);

        // 라벨 객체 "북" 내용물 중앙으로 생성, 베경 노란색
        JLabel label1 = new JLabel("북");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setOpaque(true);
        label1.setBackground(Color.YELLOW);

        JLabel label2 = new JLabel("서서서서");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setVerticalAlignment(SwingConstants.CENTER);
        label2.setOpaque(true);
        label2.setBackground(Color.RED);

        JLabel label3 = new JLabel("동동");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setVerticalAlignment(SwingConstants.CENTER);
        label3.setOpaque(true);
        label3.setBackground(Color.BLUE);

        JButton button = new JButton("남");

        JLabel label5 = new JLabel("중앙");

        label5.setHorizontalAlignment(SwingConstants.CENTER);
        label5.setVerticalAlignment(SwingConstants.CENTER);
        label5.setOpaque(true);
        label5.setBackground(Color.WHITE);

        // 동, 서, 남, 북. 가운데 추가
        frame.add(label1, BorderLayout.NORTH);
        frame.add(label2, BorderLayout.WEST);
        frame.add(label3, BorderLayout.EAST);
        frame.add(button, BorderLayout.SOUTH);
        frame.add(label5, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }
}