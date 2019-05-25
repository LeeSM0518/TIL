package lecture_manager.userinterface;

import lecture_manager.communication.Client;
import lecture_manager.message.Message;

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
    private JScrollPane problemScrollPane;

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

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message message = new Message();
                message.setSendCode(codeInputTextArea.getText(), resultTextArea.getText());
                client.sendCodeAndRunResult(message);
                JOptionPane.showMessageDialog(null, "Code & RunResult 전달이 완료되었습니다.", "제출", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        resultTextArea.setEditable(false);
    }

    private void runCode(String code) {
        String[] codeArr = code.split(" ");
        String className = null;

        for (int i = 0; i < codeArr.length; i++) {
            if (codeArr[i].equals("class")) {
                className = codeArr[i+1];
                break;
            }
        }

        if (className == null) {
            resultTextArea.setText("컴파일 오류 입니다.");
            return;
        }

        try {
            String current = new java.io.File(".").getCanonicalPath();
            File file = new File(current + "/" + className + ".java");
            FileWriter fileWriter = new FileWriter(file, false);

            fileWriter.write(code);
            fileWriter.flush();
            fileWriter.close();

            try {
                String[] cmdArray = {"javac " + className + ".java", "java " + className};
                Runtime.getRuntime().exec(cmdArray[0]);

                Process process = Runtime.getRuntime().exec(cmdArray[1]);

                InputStreamReader reader = new InputStreamReader(process.getInputStream());

                int readData;
                char[] cbuf = new char[100];
                String str = "";

                while ((readData = reader.read(cbuf)) != -1) {
                    String data = new String(cbuf, 0, readData);
                    str += data;
                }

                resultTextArea.setText(str);

                reader.close();

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