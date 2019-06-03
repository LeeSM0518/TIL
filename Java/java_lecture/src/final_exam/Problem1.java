package final_exam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Problem1 {

    JFrame mainFrame = new JFrame("World Clock Window");

    JTabbedPane jTabbedPane = new JTabbedPane();

    JPanel seoulPanel = new JPanel(new BorderLayout());
    JPanel chittagongPanel = new JPanel(new BorderLayout());
    JPanel laPanel = new JPanel(new BorderLayout());
    JPanel newYorkPanel = new JPanel(new BorderLayout());

    JLabel seoulTimeLabel = new JLabel();
    JLabel chittagongTimeLabel = new JLabel();
    JLabel laLabel = new JLabel();
    JLabel newYorkLabel = new JLabel();

    public String currentSeoulTime;
    public String currentChitTime;
    public String currentLATime;
    public String currentNKTIme;
    public String[] currentTimeSplits;

    public void currentTimeUpdate() {
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat(
                "yyyy년 MM월 dd일 HH시 mm분 ss초"
        );
        Date currentSeoulDate = new Date(time);
        Date currentChitDate = new Date(time - 10800000);
        Date currentLADate = new Date(time - 57600000);
        Date currentNKDate = new Date(time - 46800000);

        currentSeoulTime = dayTime.format(currentSeoulDate);
        currentChitTime = dayTime.format(currentChitDate);
        currentLATime = dayTime.format(currentLADate);
        currentNKTIme = dayTime.format(currentNKDate);

        seoulTimeLabel.setText(currentSeoulTime);
        chittagongTimeLabel.setText(currentChitTime);
        laLabel.setText(currentLATime);
        newYorkLabel.setText(currentNKTIme);
    }

    public Problem1() {
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setSize(600,200);

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

        Font font = new Font("Helvetica", Font.BOLD, 27);

        seoulTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        seoulTimeLabel.setVerticalAlignment(SwingConstants.CENTER);
        seoulTimeLabel.setFont(font);

        chittagongTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        chittagongTimeLabel.setVerticalAlignment(SwingConstants.CENTER);
        chittagongTimeLabel.setFont(font);

        newYorkLabel.setHorizontalAlignment(SwingConstants.CENTER);
        newYorkLabel.setVerticalAlignment(SwingConstants.CENTER);
        newYorkLabel.setFont(font);

        laLabel.setHorizontalAlignment(SwingConstants.CENTER);
        laLabel.setVerticalAlignment(SwingConstants.CENTER);
        laLabel.setFont(font);

        seoulPanel.add(seoulTimeLabel, BorderLayout.CENTER);
        chittagongPanel.add(chittagongTimeLabel, BorderLayout.CENTER);
        newYorkPanel.add(newYorkLabel, BorderLayout.CENTER);
        laPanel.add(laLabel, BorderLayout.CENTER);

        jTabbedPane.add("서울", seoulPanel);
        jTabbedPane.add("치타공", chittagongPanel);
        jTabbedPane.add("LA", laPanel);
        jTabbedPane.add("뉴욕", newYorkPanel);

        mainFrame.add(jTabbedPane);

        seoulPanel.setBackground(Color.BLACK);
        chittagongPanel.setBackground(Color.BLACK);
        laPanel.setBackground(Color.BLACK);
        newYorkPanel.setBackground(Color.BLACK);

        mainFrame.setVisible(true);
    }

    public void changeLabelColor() throws InterruptedException {
        Color[] colors = new Color[]{
                Color.RED,
                Color.GREEN,
                Color.BLUE,
                Color.CYAN,
                Color.MAGENTA,
                Color.YELLOW,
                Color.WHITE
        };
        int i = 0;

        while (true) {
            currentTimeUpdate();
            seoulTimeLabel.setForeground(colors[i % colors.length]);
            chittagongTimeLabel.setForeground(colors[(i + 1) % colors.length]);
            newYorkLabel.setForeground(colors[(i + 2) % colors.length]);
            laLabel.setForeground(colors[(i + 3) % colors.length]);
            Thread.sleep(1000);
            i = (i + 1) % colors.length;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Problem1 problem1= new Problem1();
        problem1.currentTimeUpdate();
        problem1.changeLabelColor();
    }
}