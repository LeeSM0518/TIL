package lecture_manager.userinterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Choice extends JFrame {

    Choice() {
        JFrame frame = new JFrame("Professor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton submit = new JButton("문제제출");
        JButton check = new JButton("문제채점");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AssignmentSubmit();
            }
        });

        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AssignmentCheck();
            }
        });

        JPanel panel = new JPanel(new GridLayout(1,2));
        panel.setBorder(BorderFactory.createEmptyBorder(70,10,70,10));
        panel.add(submit);
        panel.add(check);

        frame.setContentPane(panel);

        frame.setSize(300,200);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Choice();
    }

}