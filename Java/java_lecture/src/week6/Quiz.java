package week6;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class Quiz extends JFrame{
//    static int progressBar1 = 20;
//    static int progressBar2 = 30;
//    static int progressBar3 = 50;
//
//    public static void main(String[] args) {
//
//        JFrame frame = new JFrame("퀴즈");
//        JPanel mainPanel = new JPanel();
//        JPanel panel1 = new JPanel();
//        JPanel panel2 = new JPanel();
//        JPanel panel3 = new JPanel();
//
//        JProgressBar bar1 = new JProgressBar(0, 100);
//        bar1.setValue(20);
//        JProgressBar bar2 = new JProgressBar(0, 100);
//        bar2.setValue(30);
//        JProgressBar bar3 = new JProgressBar(0, 100);
//        bar3.setValue(50);
//
//        JButton btn1 = new JButton("투표");
//        btn1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                progressBar1 += 10;
//                progressBar2 -= 5;
//                progressBar3 -= 5;
//                bar1.setValue(progressBar1);
//                bar2.setValue(progressBar2);
//                bar3.setValue(progressBar3);
//            }
//        });
//
//        JButton btn2 = new JButton("투표");
//        btn2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                progressBar1 -= 5;
//                progressBar2 += 10;
//                progressBar3 -= 5;
//                bar1.setValue(progressBar1);
//                bar2.setValue(progressBar2);
//                bar3.setValue(progressBar3);
//            }
//        });
//
//        JButton btn3 = new JButton("투표");
//        btn3.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                progressBar1 -= 5;
//                progressBar2 -= 5;
//                progressBar3 += 10;
//                bar1.setValue(progressBar1);
//                bar2.setValue(progressBar2);
//                bar3.setValue(progressBar3);
//            }
//        });
//
//        frame.setSize(500, 150);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocation(600, 200);
//
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
//        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
//        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
//
//        panel1.add(bar1);
//        panel1.add(btn1);
//
//        panel2.add(bar2);
//        panel2.add(btn2);
//
//        panel3.add(bar3);
//        panel3.add(btn3);
//
//        mainPanel.add(panel1);
//        mainPanel.add(panel2);
//        mainPanel.add(panel3);
//
//        frame.add(mainPanel);
//
//        frame.setVisible(true);
//    }
//
//}

import java.awt.event.*;
import javax.swing.*;


class Quiz7 extends JFrame implements ActionListener{

    private JProgressBar progressBar1;
    private JProgressBar progressBar2;
    private JProgressBar progressBar3;
    private JButton startButton1;
    private JButton startButton2;
    private JButton startButton3;
    private int currentValue1;
    private int currentValue2;
    private int currentValue3;
    private int total;

    public Quiz7() {

        super("JProgressBar 테스트");
        startButton1 = new JButton("민주당");
        progressBar1 = new JProgressBar(0,1000);
        startButton2 = new JButton("자유당");
        progressBar2 = new JProgressBar(0,1000);
        startButton3 = new JButton("바미당");
        progressBar3 = new JProgressBar(0,1000);

        progressBar1.setValue(0);
        progressBar1.setStringPainted(true);
        progressBar2.setValue(0);
        progressBar2.setStringPainted(true);
        progressBar3.setValue(0);
        progressBar3.setStringPainted(true);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        panel1.add(startButton1);
        panel1.add(progressBar1);
        panel2.add(startButton2);
        panel2.add(progressBar2);
        panel3.add(startButton3);
        panel3.add(progressBar3);

        add("North",panel1);
        add("Center",panel2);
        add("South", panel3);



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        startButton1.addActionListener(this);
        startButton2.addActionListener(this);
        startButton3.addActionListener(this);

    }
    public void actionPerformed(ActionEvent evt){

        if( evt.getSource() == startButton1 )
            currentValue1++;
        if( evt.getSource() == startButton2 )
            currentValue2++;
        if( evt.getSource() == startButton3 )
            currentValue3++;
        total = currentValue1+currentValue2+currentValue3;
        progressBar1.setValue(1000*currentValue1/total);
        progressBar2.setValue(1000*currentValue2/total);
        progressBar3.setValue(1000*currentValue3/total);
        //if(currentValue1 == 1000) currentValue1=0;
        //if(currentValue2 == 1000) currentValue2=0;
        //if(currentValue3 == 1000) currentValue3=0;
    }

    public static void main(String[] args) {
        new Quiz7();
    }

}