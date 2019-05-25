package lecture_manager.userinterface;

import lecture_manager.communication.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfessorSubmissionUI extends JFrame {
    private JPanel mainPanel;
    private JPanel inPanel;
    private JPanel problemPanel;
    private JScrollPane problemScrollPane;
    private JTable problemTable;
    private JPanel problemInputPanel;
    private JLabel problemLabel;
    private JTextField problemTitleTextField;
    private JTextArea problemContextTextArea;
    private JLabel problemContextLabel;
    private JScrollPane problemContextScrollPane;
    private JPanel buttonPanel;
    private JButton submissionButton;
    private JLabel problemTableLabel;

    private Client client;

    public ProfessorSubmissionUI(Client client) {
        add(mainPanel);

        setTitle("Professor Client");
        setSize(1600, 1000);

        Dimension frameSize = getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

        submissionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO problemTable 에 추가가 되고 table의 위치를 눌렀을 때 이벤트 처리 구현
                // TODO 문제 제목, 내용이 들어갈 클래스 구현
                // TODO 메시지에 문제 객체 추가
            }
        });
    }

    public void visibleUI() {
        setVisible(true);
    }

    public void invisibleUI() {
        setVisible(false);
    }

    public static void main(String[] args) {
        Client client = new Client();
        ProfessorSubmissionUI professorSubmissionUI =  new ProfessorSubmissionUI(client);
        professorSubmissionUI.visibleUI();
    }
}
