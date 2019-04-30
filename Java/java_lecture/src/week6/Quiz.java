package week6;

import javax.swing.*;
import java.awt.*;

public class Quiz extends JFrame{

    public static void main(String[] args) {
        JFrame frame = new JFrame("퀴즈");
        JPanel mainPanel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        JProgressBar bar1 = new JProgressBar(0, 100);
        JProgressBar bar2 = new JProgressBar(0, 100);
        JProgressBar bar3 = new JProgressBar(0, 100);

        JButton btn1 = new JButton("투표");
        JButton btn2 = new JButton("투표");
        JButton btn3 = new JButton("투표");

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(600, 200);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

        panel1.add(bar1);
        panel1.add(btn1);

        panel2.add(bar2);
        panel2.add(btn2);

        panel3.add(bar3);
        panel3.add(btn3);

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);

        frame.add(mainPanel);

        frame.setVisible(true);
    }

}
