package final_exam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Scanner;

public class Problem2 {

    JFrame mainFrame = new JFrame("문자 글자 수, 세기");

    JPanel mainPanel = new JPanel(new BorderLayout());
    JLabel northLabel = new JLabel("아래 텍스트 영역에 문자를 넣으시오.");
    JTextArea centerTextArea = new JTextArea();

    JPanel underPanel = new JPanel(new BorderLayout());
    JButton calButton = new JButton("글자 수 계산");
    JLabel countLabel = new JLabel("총 단어수 및 공백 포함 총 글자 수: 0개 문자 0개 단어");

    Problem2() {
        mainFrame.setSize(800, 600);
        mainFrame.add(mainPanel);

        mainFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        underPanel.add(calButton, BorderLayout.WEST);
        underPanel.add(countLabel, BorderLayout.CENTER);

        mainPanel.add(northLabel, BorderLayout.NORTH);
        mainPanel.add(centerTextArea, BorderLayout.CENTER);
        mainPanel.add(underPanel, BorderLayout.SOUTH);

        mainFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        calButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scanner wordScanner = new Scanner(centerTextArea.getText());
                Scanner letterScanner = new Scanner(centerTextArea.getText());
                int wordCount = 0;
                int letterCount = 0;

                String str = "";

                while (true) {
                    try {
                        wordScanner.next();
                        wordCount++;
                    } catch (Exception e2) {
                        break;
                    }
                }

                while (true) {
                    try {
                        str += letterScanner.nextLine();
                    } catch (Exception e2) {
                        break;
                    }
                }

                letterCount = str.length();

                countLabel.setText("총 단어수 및 공백 포함 총 글자 수: " + letterCount + "개 문자 " + wordCount + "개 단어");
            }
        });

        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Problem2();
    }
}
