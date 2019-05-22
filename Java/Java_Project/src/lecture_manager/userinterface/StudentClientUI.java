package lecture_manager.userinterface;

import javax.swing.*;

public class StudentClientUI extends JFrame {

    private JPanel mainPanel;
    private JTable problemTable;
    private JPanel TablePanel;
    private JPanel buttonMainPanel;
    private JPanel buttonPanel;
    private JButton button1;
    private JButton button2;
    private JPanel buttonPanel2;
    private JPanel codeMainPanel;

    public StudentClientUI() {
        add(mainPanel);

        setTitle("Student Client");
        setSize(1600, 1000);
    }

    public static void main(String[] args) {
        StudentClientUI studentClientUI = new StudentClientUI();
        studentClientUI.setVisible(true);
    }

}
