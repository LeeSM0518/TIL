package lecture_manager.userinterface;

import lecture_manager.communication.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfessorChoiceUI extends JFrame {
    private JPanel mainPanel;
    private JPanel inPanel;
    private JButton submissionButton;
    private JButton checkProblemButton;
    private JLabel collectUILabel;

    private Client client;
    private ProfessorCheckProblemUI professorCheckProblemUI;
    private ProfessorSubmissionUI professorSubmissionUI;

    public ProfessorChoiceUI(Client client) {
        this.client = client;
        professorCheckProblemUI = new ProfessorCheckProblemUI(client);
        professorSubmissionUI = new ProfessorSubmissionUI(client);

        add(mainPanel);
        setTitle("Professor Client");
        setSize(500, 300);
        Dimension frameSize = getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

        submissionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                professorSubmissionUI.visibleUI();
            }
        });

        checkProblemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                professorCheckProblemUI.visible();
            }
        });
    }

    public void visible() {
        setVisible(true);
    }

    public void invisible() {
        setVisible(false);
    }

}
