package project_resources.jtextfield;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerMethod {
    public static void main(String[] args) {
        Dimension dimension = new Dimension(400, 100);

        JFrame frame = new JFrame("Hello");
        frame.setLocation(400, 200);
        frame.setPreferredSize(dimension);

        JTextField textField = new JTextField();
        textField.setToolTipText("도움말");

        // 텍스트 박스 이벤트 처리
        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // 키를 눌렀을 시 호출됨(단 문자킹만 반응)
                System.out.println("KeyTyped: " + e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // 키를 떼었을 시 호출됨
                System.out.println("KeyPressed: " + e.getKeyChar());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // 키를 눌었을 시 호출됨
                System.out.println("KeyReleased: " + e.getKeyChar());
            }
        };

        // 텍스트에 이벤트 처리 추가
        textField.addKeyListener(listener);

        frame.add(textField, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
