package lecture_manager.userinterface;

import lecture_manager.communication.Client;
import lecture_manager.information.Problem;
import lecture_manager.message.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentClientUI extends JFrame {

    private JPanel mainPanel;

    private JPanel TablePanel;

    private JPanel rightSideMainPanel;
    private JPanel buttonMainPanel;
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
    private JLabel codeLabel;
    private JScrollPane codeInputScrollPane;
    private JScrollPane resultScrollPane;
    private JScrollPane problemScrollPane;
    private DefaultListModel model;

    private JList problemList;
    private JPanel listPanel;
    private JList checkList;
    private JScrollPane checkScrollPane;

    private List<Problem> problemsInf = new ArrayList<>();

    private Client client;

    private static DefaultListModel newModel;
    private static DefaultListModel newModel2;
    private static int yes = 0;

    public StudentClientUI(Client client) {
        this.client = client;
        add(mainPanel);

        setTitle("Student Client");
        setSize(1600, 1000);
        Dimension frameSize = getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

        problemList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Problem problem = problemsInf.get(problemList.getSelectedIndex());

                    if (problem.getCode() != null) {
                        codeInputTextArea.setText(problem.getCode());
                        resultTextArea.setText(problem.getRunResult());
                    } else {
                        codeInputTextArea.setText("");
                        resultTextArea.setText("");
                    }

                    new ProblemViewDetail(problem.getTitle(), problem.getContext());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Problem problem = problemsInf.get(problemList.getSelectedIndex());

                problem.setCode(codeInputTextArea.getText());
                problem.setRunResult(resultTextArea.getText());

                problemsInf.set(problemList.getSelectedIndex(), problem);
                runCode(codeInputTextArea.getText());
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message message = new Message();
                Problem problem = problemsInf.get(problemList.getSelectedIndex());

                problem.setCode(codeInputTextArea.getText());
                problem.setRunResult(resultTextArea.getText());

                problemsInf.set(problemList.getSelectedIndex(), problem);

                message.setSendCode(problemsInf);
                client.sendCodeAndRunResult(message);
                JOptionPane.showMessageDialog(null,
                        "Code & RunResult 전달이 완료되었습니다.",
                        "제출",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingProblemListModel();
                settingCheckListModel();
                yes = 0;
                problemsInf.forEach(problem -> {
                    if (problem.getCheck()) {
                        yes++;
                    }
                });
                problemCountLabel.setText(" 문제 개수 : " + yes + " / " + problemsInf.size() + " ");
            }
        });

        resultTextArea.setEditable(false);
    }

    private void settingProblemListModel() {
        try {
            Message message = new Message();
            message.setProblemsRequest(problemsInf);
            problemsInf = client.requestProblems(message);
            newModel = new DefaultListModel();

            problemsInf.forEach(problem -> {
                newModel.addElement(problem.getTitle());
            });

            problemList.setModel(newModel);

        } catch (Exception e) {

        }
    }

    private void settingCheckListModel() {
        newModel2 = new DefaultListModel();
        problemsInf.forEach(check -> {
            if (!check.getCheck()) {
                newModel2.addElement("X");
            } else {
                newModel2.addElement("O");
            }
        });
        checkList.setModel(newModel2);
    }

    public void runCode(String code) {
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

}