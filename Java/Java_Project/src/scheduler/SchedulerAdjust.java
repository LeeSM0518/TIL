package scheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SchedulerAdjust extends SchedulerMainUI {
    private Dimension dimension = new Dimension(400, 200);
    private JFrame adjustFrame = new JFrame("Adjust Schedule");
    private JLabel scheduleLabel = new JLabel("찾을 수 없는 일정입니다.");
    private JLabel scheduleInputLabel = new JLabel("수정할 일정 : ");
    private JTextField textScheduleField = new JTextField();
    private JLabel adjustLabel = new JLabel("일정을 입력해주세요.");
    private JLabel adjustInputLabel = new JLabel("일정 : ");
    private JTextField textAdjustField = new JTextField();
    private JPanel adjustPanel = new JPanel();
    private JPanel schedulePanel = new JPanel();
    private JPanel btnPanel = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JButton okBtn = new JButton("확인");
    private JButton cancelBtn = new JButton("취소");
    private String [][] schedule;

    public SchedulerAdjust(String[][] contents) {
        this.schedule = contents;
    }

    public void schedulerAdjustUiView() {
        adjustFrame.setLocation(600, 200);
        adjustFrame.setPreferredSize(dimension);

        schedulePanel.setLayout(new BoxLayout(schedulePanel,
                BoxLayout.X_AXIS));
        schedulePanel.add(scheduleInputLabel);
        schedulePanel.add(textScheduleField);

        scheduleTextEvent();

        adjustPanel.setLayout(new BoxLayout(adjustPanel,
                BoxLayout.X_AXIS));
        adjustPanel.add(adjustInputLabel);
        adjustPanel.add(textAdjustField);

        btnPanel.setLayout(new BoxLayout(btnPanel,
                BoxLayout.X_AXIS));
        btnPanel.add(okBtn);
        btnPanel.add(cancelBtn);

        okButtonEvent();
        cancelButtonEvent();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(scheduleLabel);
        mainPanel.add(schedulePanel);
        mainPanel.add(adjustLabel);
        mainPanel.add(adjustPanel);
        mainPanel.add(btnPanel);

        adjustFrame.add(mainPanel, BorderLayout.CENTER);
        adjustFrame.pack();
        adjustFrame.setVisible(true);
    }

    private void scheduleTextEvent() {
        KeyListener scheduleTextListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int check[] = scheduleSearch(textScheduleField.getText(),
                        schedule);
                if (check[0] == -1 || check[1] == -1) {
                    scheduleLabel.setText("찾을 수 없는 일정입니다.");
                } else {
                    scheduleLabel.setText(textScheduleField.getText());
                }
            }
        };
        textScheduleField.addKeyListener(scheduleTextListener);

        KeyListener adjustTextListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(textAdjustField.getText().length() <= 0) {
                    adjustLabel.setText("일정을 입력해주세요.");
                } else {
                    adjustLabel.setText(textAdjustField.getText());
                }
            }
        };
        textAdjustField.addKeyListener(adjustTextListener);
    }

    private void okButtonEvent() {
        ActionListener okListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scheduleLabel.getText().equals("찾을 수 없는 일정입니다.") ||
                adjustLabel.getText().equals("일정을 입력해주세요.")) {

                } else {
                    for (int i=0; i<24; i++) {
                        for(int j=1; j<8; j++) {
                            try {
                                if (schedule[i][j].equals(scheduleLabel.getText())) {
                                    schedule[i][j] = adjustLabel.getText();
                                }
                            } catch (Exception ignored) {}
                        }
                    }
                    adjustFrame.setVisible(false);
                    setContents(schedule);
                    viewOn();
                }
            }
        };
        okBtn.addActionListener(okListener);
    }

    private void cancelButtonEvent() {
        ActionListener cancelListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adjustFrame.setVisible(false);
                setContents(schedule);
                viewOn();
            }
        };
        cancelBtn.addActionListener(cancelListener);
    }

}