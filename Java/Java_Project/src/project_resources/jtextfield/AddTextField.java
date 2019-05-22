package project_resources.jtextfield;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTextField {
    public static void main(String[] args) {
        // 윈도우 창 크기 값 저장
        Dimension dimension = new Dimension(400, 100);

        // 창의 타이틀 저장 및 프레임 생성
        JFrame frame = new JFrame("Hello!");
        frame.setLocation(200, 400);    // 윈도우 생성 위치
        frame.setPreferredSize(dimension);    // 윈도우 창 크기 설정

        // 텍스트 상자 생성
        JTextField textField = new JTextField();

        // 라벨 텍스트 저장 및 생성
        JLabel label = new JLabel("입력");
        // 라벨 내부 텍스트 위치 설정
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        // 버튼 텍스트 저장 및 생성
        JButton button = new JButton("OK");
        // 버튼의 이벤트 처리
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 텍스트 상자의 텍스트를 가져와서 라벨에 출력시킨다.
                label.setText(textField.getText());
            }
        });

        // 텍스트 상자 가운데 위치
        // 라벨 북쪽 위치
        // 버튼 남쪽 위치
        frame.add(textField, BorderLayout.CENTER);
        frame.add(label, BorderLayout.NORTH);
        frame.add(button, BorderLayout.SOUTH);
        
        frame.pack();
        frame.setVisible(true);
    }
}
