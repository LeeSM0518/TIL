package scheduler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SchedulerUI extends Scheduler {
    Dimension dimension = new Dimension(600, 600);

    private String header[] = getDays();
    private String contents[][] = new String[24][8];
    private String times[] = getTimes();

    public SchedulerUI() {

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

        JButton addBtn = new JButton("일정 추가");
        JButton searchBtn = new JButton("일정 검색");
        JButton adjustBtn = new JButton("일정 수정");
        JButton deleteBtn = new JButton("일정 삭제");

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
    public String[][] getSchedule() {
        return super.getSchedule();
    }

    @Override
    public void setSchedule(String[][] schedul) {
        super.setSchedule(schedul);
    }

    public static void main(String[] args) {
        Scheduler scheduler = new SchedulerUI();
    }
}
