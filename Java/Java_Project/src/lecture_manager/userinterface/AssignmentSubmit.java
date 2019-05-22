package lecture_manager.userinterface;

import java.awt.*;
import javax.swing.*;

public class AssignmentSubmit extends JFrame {

    public AssignmentSubmit() {
        JFrame frame = new JFrame("Lecture Manager - Submit");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel bt_pn = new JPanel(new BorderLayout());

        JButton submit_bt = new JButton("제출");
        JTextField input = new JTextField(700);

        bt_pn.add(submit_bt,BorderLayout.EAST);
        panel.add(input, BorderLayout.CENTER);
        panel.add(bt_pn, BorderLayout.SOUTH);

        frame.add(panel);

        frame.setSize(1000,1000);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AssignmentSubmit();
    }

}
