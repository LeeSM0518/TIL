package lecture_manager.userinterface;

import javax.swing.*;
import java.awt.*;

public class ProfessorCheckProblemUI extends JFrame {

//    private Client client;
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
    private JScrollPane studentScrollPane;
    private JTable StudentTable;
    private JComboBox comboBox1;

    public ProfessorCheckProblemUI() {
//        this.client = client;
        add(mainPanel);

        setTitle("Professor Client");
        setSize(1600, 1000);

        Dimension frameSize = getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ProfessorCheckProblemUI();
    }

}
