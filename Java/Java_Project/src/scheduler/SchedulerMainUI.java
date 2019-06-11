package scheduler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SchedulerMainUI extends Scheduler {
    private Dimension dimension = new Dimension(600, 600);
    private JFrame frame = new JFrame("Scheduler");
    private String header[] = getDays();
    private String contents[][] = new String[24][8];
    private String times[] = getTimes();
    private static JLabel currentTime = new JLabel("현재 시간 : ");
    private JButton addBtn = new JButton(getAddSchedule());
    private JButton adjustBtn = new JButton(getAdjustSchedule());
    private JButton deleteBtn = new JButton(getDeleteSchedule());
    private JPanel panel = new JPanel();
    private JPanel panel1 = new JPanel();
    private JButton exitBtn = new JButton(getExit());
    private SchedulerAdd schedulerAdd;
    private SchedulerDelete schedulerDelete;
    private SchedulerAdjust schedulerAdjust;
    private JTable table;

    DefaultTableModel model;
    JScrollPane scrollPane;

    public SchedulerMainUI() {
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    long time = System.currentTimeMillis();
                    String str = dayTime.format(new Date(time));
                    currentTime.setText("현재 시간 : " + str);
                }
            }
        });
        thread.start();
    }



    public void modelUpdate() {
        for (int i=0; i < times.length; i++) {
            contents[i][0] = times[i];
        }

        model = new DefaultTableModel(contents, header);
        table = new JTable(model);
        table.setRowHeight(50);
        scrollPane = new JScrollPane(table);
    }


    public void viewOn() {

        frame.setLocation(600, 200);
        frame.setPreferredSize(dimension);

        modelUpdate();

        currentTime.setHorizontalAlignment(SwingConstants.LEFT);

        addListener();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(currentTime, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel1.setLayout(new GridLayout(1, 4));
        panel1.add(addBtn);
        panel1.add(adjustBtn);
        panel1.add(deleteBtn);
        panel1.add(exitBtn);
        panel.add(panel1, BorderLayout.SOUTH);

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

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
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public void addListener() {
        ActionListener addListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSchedule();
            }
        };
        addBtn.addActionListener(addListener);

        ActionListener adjustListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adjustSchedule();
            }
        };
        adjustBtn.addActionListener(adjustListener);

        ActionListener deleteListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSchedule();
            }
        };
        deleteBtn.addActionListener(deleteListener);

        ActionListener exitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitScheduler();
            }
        };
        exitBtn.addActionListener(exitListener);
    }

    public void setContents(String[][] contents) {
        this.contents = contents;
    }

    public String[][] getContents() {
        return this.contents;
    }

    @Override
    public void currentTimeUpdate(){

    }

    @Override
    public void addSchedule() {
        schedulerAdd = new SchedulerAdd(contents);
        schedulerAdd.schedulerAddView();
        frame.setVisible(false);
    }

    public int daySearch(String day) {

        String[] days = getDays();
        int check = -1;

        for (int i = 1; i < days.length; i++) {
            if (days[i].equals(day)) {
                check = i;
                break;
            }
        }

        return check;
    }

    @Override
    public void adjustSchedule() {
        schedulerAdjust = new SchedulerAdjust(contents);
        schedulerAdjust.schedulerAdjustUiView();
        frame.setVisible(false);
    }

    @Override
    public void deleteSchedule() {
        schedulerDelete = new SchedulerDelete(contents);
        schedulerDelete.schedulerDeleteUiView();
        frame.setVisible(false);
    }

    @Override
    public void exitScheduler() {
        System.exit(0);
    }

    @Override
    public String[][] getSchedule() {
        return super.getSchedule();
    }

    @Override
    public void setSchedule(String[][] schedul) {
        super.setSchedule(schedul);
    }


    public int[] timeSearch(String time) {
        int check[] = new int[]{-1, -1};
        String[] stringTimes = time.split("~");
        String[] timeTokens = getTimesTokens();

        if (stringTimes.length == 2) {
            for (int i = 0; i < timeTokens.length; i++) {
                if (stringTimes[0].equals(timeTokens[i])) {
                    check[0] = i;
                    break;
                }
            }
            for (int i = 0; i < timeTokens.length; i++) {
                if (stringTimes[1].equals(timeTokens[i])) {
                    check[1] = i;
                    break;
                }
            }
        }
        return check;
    }

    public int[] scheduleSearch(String schedule, String[][] contents) {
        int check[] = new int[] {-1, -1};

        for(int i=0; i<24; i++) {
            for(int j=1; j<8; j++) {
                try {
                    if(schedule.equals(contents[i][j])) {
                        check[0] = i;
                        check[1] = j;
                        break;
                    }
                } catch (NullPointerException e) {}

            }
        }
        return check;
    }

}
