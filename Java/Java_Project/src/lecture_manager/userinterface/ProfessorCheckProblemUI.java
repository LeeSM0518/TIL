package lecture_manager.userinterface;

import lecture_manager.communication.Client;
import lecture_manager.message.Message;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProfessorCheckProblemUI extends JFrame {

    private Client client;
    private JPanel mainPanel;
    private JPanel inPanel;
    private JPanel studentPanel;
    private JButton refreshButton;
    private JPanel codeAndResultPanel;
    private JLabel studentCount;
    private JLabel codeLabel;
    private JLabel resultLabel;
    private JPanel codePanel;
    private JPanel resultPanel;
    private JScrollPane codeScrollPane;
    private JScrollPane resultScrollPane;
    private JTextArea codeTextArea;
    private JTextArea resultTextArea;
    private JButton denialButton;
    private JButton runButton;
    private JButton permissionButton;

    private JComboBox comboBox1;
    private JList studentList;
    private List<Student> studentArrayList;

    public ProfessorCheckProblemUI(Client client) {
        this.client = client;
        add(mainPanel);

        setTitle("Professor Client");
        setSize(1600, 1000);

        Dimension frameSize = getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

    private void settingStudentList() {
        try {
            Message message = new Message();
            message.setRequestStudents();
            // TODO 학생들 정보 요청
//            studentArrayList = client.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void visible() {
        setVisible(true);
    }

    public void invisible() {
        setVisible(false);
    }

}