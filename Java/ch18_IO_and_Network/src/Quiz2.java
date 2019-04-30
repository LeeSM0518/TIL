import apple.laf.JRSUIConstants;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * ProgressBarDemo.java is a 1.4 application that requires these files:
 *   LongTask.java
 *   SwingWorker.java
 */
class ThreadGUI extends Container {

    int value = 0;


    public ThreadGUI() {

        Frame frame = new Frame("프로그래스바 - 스레드");

        frame.setSize(400, 300);

        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));

        JPanel contentPane = new JPanel(new BorderLayout());

        JProgressBar bar = new JProgressBar(0, 100);

        bar.setValue(0);

        bar.setStringPainted(true);

        bar.setForeground(Color.GREEN);

        JButton btn = new JButton("버튼");

        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                value += 10;
                bar.setValue(value);
            }

        });


        contentPane.add(bar, BorderLayout.CENTER);

        contentPane.add(btn, BorderLayout.EAST);

        mainContainer.add(contentPane);

//        JPanel contentPane2 = new JPanel();
//
//        contentPane2.setLayout(new BorderLayout());
//
//
//        JProgressBar bar2 = new JProgressBar(0, 100);
//
//        bar2.setValue(0);
//
//        bar2.setStringPainted(true);
//
//        bar2.setForeground(Color.GREEN);
//
//        JButton btn2 = new JButton("버튼");
//
//        btn2.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                value += 10;
//                bar2.setValue(value);
//            }
//
//        });
//
//
//        contentPane2.add(bar, BorderLayout.CENTER);
//
//        contentPane2.add(btn, BorderLayout.EAST);
//
//        mainContainer.add(contentPane2);
//
//        JPanel contentPane3 = new JPanel();
//
//        contentPane3.setLayout(new BorderLayout());
//
//
//        JProgressBar bar3 = new JProgressBar(0, 100);
//
//        bar3.setValue(0);
//
//        bar3.setStringPainted(true);
//
//        bar3.setForeground(Color.GREEN);
//
//        JButton btn3 = new JButton("버튼");
//
//        btn3.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                value += 10;
//                bar3.setValue(value);
//            }
//
//        });
//
//
//        contentPane3.add(bar, BorderLayout.CENTER);
//
//        contentPane3.add(btn, BorderLayout.EAST);
//
//        mainContainer.add(contentPane3);

        frame.add(mainContainer);


        frame.setLocationByPlatform(true);


        frame.setVisible(true);

    }


    public static void main(String[] args) {

        new ThreadGUI();

    }


}