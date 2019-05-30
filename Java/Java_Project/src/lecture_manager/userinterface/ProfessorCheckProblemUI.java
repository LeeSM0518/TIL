package lecture_manager.userinterface;

import lecture_manager.communication.Client;
import lecture_manager.information.Problem;
import lecture_manager.information.Student;
import lecture_manager.message.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    private JButton permissionButton;

    private JComboBox problemComboBox;
    private JList studentList;
    private JScrollPane StudentListScrollPane;
    private JPanel buttonPanel;
    private JList problemCountList;
    private JLabel problemCountLabel;
    private List<Student> studentArrayList;


    public ProfessorCheckProblemUI(Client client) {
        this.client = client;
        add(mainPanel);

        setTitle("Professor Client");
        setSize(1600, 1000);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                invisible();
            }
        });

        // TODO 리스트 더블클릭시 콤보 박스 값 할당
        studentList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    DefaultComboBoxModel model = new DefaultComboBoxModel<>();

                    List<Problem> problems = studentArrayList.get(
                            studentList.getSelectedIndex()).getProblemList();

                    problems.forEach(problem -> {
                        if (problem.getCheck()) {
                            model.addElement(problem.getTitle() + "(승인완료)");
                        } else {
                            model.addElement(problem.getTitle());
                        }
                    });

                    problemComboBox.setModel(model);
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

        permissionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentArrayList.get(studentList.getSelectedIndex())
                        .getProblemList()
                        .get(problemComboBox.getSelectedIndex())
                        .setCheck(true);

                Message message = new Message();
                message.setSendStudentList(studentArrayList);
                client.send(message);
            }
        });

        denialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentArrayList.get(studentList.getSelectedIndex())
                        .getProblemList()
                        .get(problemComboBox.getSelectedIndex())
                        .setCheck(false);

                Message message = new Message();
                message.setSendStudentList(studentArrayList);
                client.send(message);
           }
        });

        codeTextArea.setEditable(false);

        problemComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Problem problem = studentArrayList
                            .get(studentList.getSelectedIndex())
                            .getProblemList()
                            .get(problemComboBox.getSelectedIndex());
                    codeTextArea.setText(problem.getCode());
                    resultTextArea.setText(problem.getRunResult());
                } catch (Exception e2) {
                    e2.printStackTrace();
                    codeTextArea.setText("");
                    resultTextArea.setText("");
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingStudentList();
                settingProblemCountList();
            }
        });

        resultTextArea.setEditable(false);

        Dimension frameSize = getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

    private void settingStudentList() {
        try {
            Message message = new Message();
            message.setRequestStudents();
            studentArrayList = client.requestStudents(message);

            if (studentArrayList == null) {
                JOptionPane.showMessageDialog(null,
                        "학생이 없습니다.",
                        "경고",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            DefaultListModel model = new DefaultListModel();

            studentArrayList.forEach(student -> {
                model.addElement(student.getUser().getId());
            });

            studentCount.setText("학생 수 : " + studentArrayList.size());

            studentList.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void settingProblemCountList() {
        try {
            if (studentArrayList == null) {
                JOptionPane.showMessageDialog(null,
                        "학생이 없습니다.",
                        "경고",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            DefaultListModel model = new DefaultListModel();

            studentArrayList.forEach(student -> {
                List<Problem> problems = student.getProblemList();

                int ok = 0;
                int problemCount = 0;

                if (!(problems == null || problems.size() == 0)) {
                    problemCount = problems.size();

                    ok = (int) problems.stream().filter(Problem::getCheck).count();
                }

                String element = ok + " / " + problemCount;

                model.addElement(element);
            });

            problemCountList.setModel(model);

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