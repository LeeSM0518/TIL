package project_resources.jtextfield;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetEnabledMethod {
    public static void main(String[] args) {
        Dimension dimension = new Dimension(400, 100);

        JFrame frame = new JFrame("Hello");
        frame.setLocation(200, 400);
        frame.setPreferredSize(dimension);

        JTextField textField = new JTextField();
        textField.setText("미리 설정");
        textField.setEnabled(false);    // 텍스트 상자를 비활성화 시킨다.

        JLabel jLabel = new JLabel("입력");
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setVerticalAlignment(SwingConstants.CENTER);

        JButton button = new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jLabel.setText(textField.getText());
            }
        });

        frame.add(textField, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
        frame.add(jLabel, BorderLayout.NORTH);

        frame.pack();
        frame.setVisible(true);
    }
}
