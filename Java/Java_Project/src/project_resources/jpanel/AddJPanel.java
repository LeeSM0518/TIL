package project_resources.jpanel;

import javax.swing.*;
import java.awt.*;

public class AddJPanel {
    public static void main(String[] args) {
        Dimension dimension = new Dimension(800, 150);

        JFrame frame = new JFrame("Hello");
        frame.setLocation(200, 400);

        // 1 패널에 레이아웃과 라벨, 텍스트 상자 추가
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel1.add(new JLabel("이름: "));
        panel1.add(new JTextField());

        // 2 패널에 레이아웃과 라벨, 텍스트 상자 추가
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel2.add(new JLabel("나이: "));
        panel2.add(new JTextField());

        // 3 패널에 레이아웃과 라벨, 텍스트 상자 추가
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
        panel3.add(new JLabel("주소: "));
        panel3.add(new JTextField());

        // 4 패널에 레이아웃과 패널들을 추가
        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
        panel4.add(panel1);
        panel4.add(panel2);
        panel4.add(panel3);

        // 프레임에 패널을 추가하고 버튼 남쪽에 추가
        frame.add(panel4, BorderLayout.CENTER);
        frame.add(new JButton("입력하기"), BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
}
