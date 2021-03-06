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
import java.util.ArrayList;
import java.util.List;

public class ProfessorSubmissionUI extends JFrame {
    private JPanel mainPanel;
    private JPanel inPanel;
    private JPanel problemPanel;
    private JPanel problemInputPanel;
    private JLabel problemLabel;
    private JTextField problemTitleTextField;
    private JTextArea problemContextTextArea;
    private JLabel problemContextLabel;
    private JScrollPane problemContextScrollPane;
    private JPanel buttonPanel;
    private JButton submissionButton;
    private JLabel problemTableLabel;
    private JButton removeButton;
    private JList problemList;
    private JScrollPane problemListScrollPane;
    private DefaultListModel model;

    private Client client;

    private List<Problem> problemArrayList = new ArrayList<>();

    public ProfessorSubmissionUI(Client client) {
        this.client = client;
        add(mainPanel);

        setTitle("Professor Client");
        setSize(1600, 1000);

        Dimension frameSize = getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

        model = new DefaultListModel();
        problemList.setModel(model);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                problemArrayList.remove(problemList.getSelectedIndex());
                model.remove(problemList.getSelectedIndex());
                sendProblems();
            }
        });

        submissionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(problemTitleTextField.getText().equals("") ||
                        problemContextTextArea.getText().equals(""))) {

                    Problem problem = new Problem(problemTitleTextField.getText(),
                            problemContextTextArea.getText());

                    problemArrayList.add(problem);
                    model.addElement(problemTitleTextField.getText());
                    problemTitleTextField.setText("");
                    problemContextTextArea.setText("");

                    sendProblems();

                    JOptionPane.showMessageDialog(null,
                            "문제 제출이 완료되었습니다.",
                            "제출",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "제목이나 내용을 입력해주세요.",
                            "경고",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        problemList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Problem problem = problemArrayList.get(problemList.getSelectedIndex());

                    problemTitleTextField.setText(problem.getTitle());
                    problemContextTextArea.setText(problem.getContext());
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
    }

    public void visibleUI() {
        setVisible(true);
    }

    public void invisibleUI() {
        setVisible(false);
    }

    private void sendProblems() {
        Message message = new Message();
        message.setSendProblems(this.problemArrayList);
        client.sendProblems(message);
    }

}
