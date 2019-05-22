package scheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class SchedulerAdd extends SchedulerMainUI {
    private Dimension dimensionAdd = new Dimension(400, 400);
    private JFrame addFrame = new JFrame("Add Schedule");
    private JLabel dayLabel = new JLabel("잘못된 요일입니다.");
    private JLabel timeLabel = new JLabel("잘못된 시간입니다.");
    private JLabel scheduleLabel = new JLabel("할일을 입력해주세요.");
    private JTextField textDayField = new JTextField();
    private JTextField textTimeField = new JTextField();
    private JTextField textScheduleField = new JTextField();
    private JPanel dayPanel = new JPanel();
    private JPanel timePanel = new JPanel();
    private JPanel schedulePanel = new JPanel();
    private JButton okBtn = new JButton("확인");
    private JButton cancelBtn = new JButton("취소");
    private String[][] schedule = new String[24][7];

    public SchedulerAdd(String[][] contents) {
        schedule = contents;
    }

    public void schedulerAddView() {
        addFrame.setLocation(600, 200);
        addFrame.setPreferredSize(dimensionAdd);

        dayLabel.setVerticalAlignment(SwingConstants.CENTER);
        dayLabel.setHorizontalAlignment(SwingConstants.CENTER);

        timeLabel.setVerticalAlignment(SwingConstants.CENTER);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        scheduleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scheduleLabel.setVerticalAlignment(SwingConstants.CENTER);

        textEvent();

        dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.X_AXIS));
        dayPanel.add(new JLabel("요일 : "));
        dayPanel.add(textDayField);

        timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.X_AXIS));
        timePanel.add(new JLabel("시간 : "));
        timePanel.add(textTimeField);

        schedulePanel.setLayout(new BoxLayout(schedulePanel, BoxLayout.X_AXIS));
        schedulePanel.add(new JLabel("할일 : "));
        schedulePanel.add(textScheduleField);

        okButtonEvent();
        cancelButtonEvent();

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.add(okBtn);
        btnPanel.add(cancelBtn);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(dayLabel);
        mainPanel.add(dayPanel);
        mainPanel.add(timeLabel);
        mainPanel.add(timePanel);
        mainPanel.add(scheduleLabel);
        mainPanel.add(schedulePanel);
        mainPanel.add(btnPanel);

        addFrame.add(mainPanel, BorderLayout.CENTER);
        addFrame.pack();
        addFrame.setVisible(true);
    }

    public void textEvent() {
        KeyListener dayListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                int check = daySearch(textDayField.getText());
                if (check == -1) {
                    dayLabel.setText("잘못된 요일입니다.");
                } else {
                    dayLabel.setText(textDayField.getText());
                }
            }
        };
        textDayField.addKeyListener(dayListener);

        KeyListener timeListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                int[] check = timeSearch(textTimeField.getText());
                if (check[0] == -1 || check[1] == -1) {
                    timeLabel.setText("잘못된 시간입니다.");
                } else {
                    timeLabel.setText(textTimeField.getText());
                }
            }
        };
        textTimeField.addKeyListener(timeListener);


        KeyListener scheduleListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (textScheduleField.getText().equals("")) {
                    scheduleLabel.setText("할일을 입력해주세요.");
                } else {
                    scheduleLabel.setText("저장하세요");
                }
            }
        };
        textScheduleField.addKeyListener(scheduleListener);
    }

    public void okButtonEvent() {
        ActionListener okListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dayLabel.getText().equals("잘못된 요일입니다.") ||
                        timeLabel.getText().equals("잘못된 시간입니다.") ||
                        scheduleLabel.getText().equals("할일을 입력해주세요.")) {

                } else {

                    int updataDay = daySearch(textDayField.getText());
                    int[] updataTime = timeSearch(textTimeField.getText());

                    for (int i = updataTime[0]; i < updataTime[1]; i++) {
                        schedule[i][updataDay] = textScheduleField.getText();
                    }
                    addFrame.setVisible(false);
                    setContents(schedule);
                    viewOn();
                }

            }
        };
        okBtn.addActionListener(okListener);
    }

    public void cancelButtonEvent() {
        ActionListener cancelListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFrame.setVisible(false);
                setContents(schedule);
                viewOn();
            }
        };
        cancelBtn.addActionListener(cancelListener);
    }

}