package lecture_manager.userinterface;

import lecture_manager.communication.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class StudentClientUI extends JFrame {

    private JPanel mainPanel;

    private JTable problemTable;
    private JPanel TablePanel;

    private JPanel rightSideMainPanel;
    private JPanel buttonMainPanel;
    private JButton problemContextButton;
    private JPanel codeMainPanel;
    private JButton refreshButton;
    private JTextArea codeInputTextArea;
    private JPanel codeInputPanel;
    private JLabel runLabel;
    private JPanel resultPanel;
    private JTextArea resultTextArea;
    private JLabel problemCountLabel;
    private JPanel runOrSubmitPanel;
    private JButton runButton;
    private JButton submitButton;
    private JLabel empty;
    private JLabel codeLabel;
    private JScrollPane codeInputScrollPane;
    private JScrollPane resultScrollPane;
    private JScrollPane problemTablePane;

    private Client client;

    public StudentClientUI(Client client) {
        this.client = client;
        add(mainPanel);

        setTitle("Student Client");
        setSize(1600, 1000);
        Dimension frameSize = getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runCode(codeInputTextArea.getText());
            }
        });

        resultTextArea.setEditable(false);
    }

    private void runCode(String code) {
        String[] codeArr = code.split(" ");
        String className = null;

        for (String s : codeArr) {
            if (s.equals("class")) {
                className = s;
                break;
            }
        }

        try {
            String current = new java.io.File(".").getCanonicalPath();
            File file = new File(current + "/test.java");
            FileWriter fileWriter = new FileWriter(file, false);

            fileWriter.write(code);
            fileWriter.flush();
            fileWriter.close();

            try {
                String[] cmdArray = {"javac test.java", "java " + className};
                Runtime.getRuntime().exec(cmdArray[0]);

                Process process = Runtime.getRuntime().exec(cmdArray[1]);

                BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

                String line = null;

                while ((line = br.readLine()) != null) {
                    line += line;
                }

                // TODO line RunTextArea 에 넣는 기능 구현

            } catch (Exception e2) {
                e2.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void visibleStudentClientUI() {
        setVisible(true);
    }

    public void invisibleStudentClientUI() {
        setVisible(false);
    }

    public static void main(String[] args) {
        Client client = new Client();
        StudentClientUI studentClientUI = new StudentClientUI(client);
        studentClientUI.visibleStudentClientUI();
    }

}