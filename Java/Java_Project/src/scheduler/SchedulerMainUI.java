package scheduler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchedulerMainUI extends Scheduler {
    Dimension dimension = new Dimension(600, 600);

    private String header[] = getDays();
    private String contents[][] = new String[24][8];
    private String times[] = getTimes();

    public SchedulerMainUI() {

        JFrame frame = new JFrame("Scheduler");
        frame.setLocation(600, 200);
        frame.setPreferredSize(dimension);

        for (int i=0; i < times.length; i++) {
            contents[i][0] = times[i];
        }

        DefaultTableModel model = new DefaultTableModel(contents, header);
        JTable table = new JTable(model);
        table.setRowHeight(50);

        JScrollPane scrollPane = new JScrollPane(table);

        JLabel currentTime = new JLabel("현재 시간 : ");
        currentTime.setHorizontalAlignment(SwingConstants.LEFT);

        JButton addBtn = new JButton(getAddSchedule());
        ActionListener addListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSchedule();
            }
        };

        JButton searchBtn = new JButton(getSearchSchedule());
        ActionListener searchListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchSchedule();
            }
        };

        JButton adjustBtn = new JButton(getDeleteSchedule());
        ActionListener adjustListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adjustSchedule();
            }
        };

        JButton deleteBtn = new JButton(getAdjustSchedule());
        ActionListener deleteListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSchedule();
            }
        };

        JButton exitBtn = new JButton(getExit());
        ActionListener exitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitScheduler();
            }
        };

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(currentTime, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 4));
        panel1.add(addBtn);
        panel1.add(searchBtn);
        panel1.add(adjustBtn);
        panel1.add(deleteBtn);
        panel1.add(exitBtn);
        panel.add(panel1, BorderLayout.SOUTH);

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public void setContents(String[][] contents) {
        this.contents = contents;
    }

    public String[][] getContents() {
        return contents;
    }

    @Override
    public void currentTimeUpdate(){

    }

    @Override
    public void addSchedule() {

    }

    @Override
    public void searchSchedule() {

    }

    @Override
    public void adjustSchedule() {

    }

    @Override
    public void deleteSchedule() {

    }

    @Override
    public void exitScheduler() {

    }

    @Override
    public String[][] getSchedule() {
        return super.getSchedule();
    }

    @Override
    public void setSchedule(String[][] schedul) {
        super.setSchedule(schedul);
    }

    public static void main(String[] args) {
        SchedulerMainUI schedulerMainUI = new SchedulerMainUI();
    }
}
