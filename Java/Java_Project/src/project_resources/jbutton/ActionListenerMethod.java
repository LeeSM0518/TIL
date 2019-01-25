package project_resources.jbutton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerMethod {
    public static void main(String[] args) {
        Dimension dimension = new Dimension(400, 100);

        JFrame frame = new JFrame("Hello");
        frame.setLocation(200, 400);
        frame.setPreferredSize(dimension);

        // Grid 레이아웃 객체 1열, 2행으로 추가
        GridLayout layout = new GridLayout(1, 2);
        frame.setLayout(layout);

        // JLabel 객체의 텍스트를 0으로 생성
        JLabel label = new JLabel("0");
        frame.add(label);

        // JButton 객체의 텍스트를 Click Counter로 생성
        JButton button = new JButton("Click Counter");
        frame.add(button);

        // 버튼 이벤트 처리 메소드
        ActionListener listener = new ActionListener() {
            @Override
            // setText로 글씨를 바꾼다.
            public void actionPerformed(ActionEvent e) {
                label.setText(String.valueOf(Integer.valueOf(label.getText()) + 1));
            }
        };
        button.addActionListener(listener);

        frame.add(button);
        frame.pack();
        frame.setVisible(true);
    }
}
