package lecture_manager2.userinterface;

import lecture_manager2.communication.Client;
import lecture_manager2.database.Identity;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class SignIn {

    private static Identity signInOption = null;

    private JFrame frame = new JFrame("Lecture Manager - 로그인");

    private JPanel button = new JPanel(new GridLayout(1, 2));
    private JPanel id_panel = new JPanel(new BorderLayout());
    private JPanel pw_panel = new JPanel(new BorderLayout());
    private JPanel check_panel = new JPanel();
    private JPanel all_panel = new JPanel();

    private JButton login = new JButton("로그인");
    private JButton signup = new JButton("회원가입");

    private ButtonGroup check_box_group = new ButtonGroup();

    private JRadioButton pro_check = new JRadioButton("교수용");
    private JRadioButton stu_check = new JRadioButton("학생용");

    private JLabel id_label = new JLabel("     학    번     ");
    private JLabel pw_label = new JLabel("    비밀번호     ");
    private JTextField id_field = new JTextField();
    private JPasswordField pw_field = new JPasswordField();

    protected Client client;
    protected SignUp signUpUI;

    public SignIn(Client client) {
        this.client = client;
        client.startClient();

        signUpUI = new SignUp(client);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        all_panel.setLayout(new BoxLayout(all_panel, BoxLayout.Y_AXIS));
        check_panel.setBorder(new TitledBorder(null, null, TitledBorder.LEADING, TitledBorder.TOP, null, null));

        pro_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signInOption = Identity.PROFESSOR;
            }
        });
        stu_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signInOption = Identity.STUDENT;
            }
        });

        check_box_group.add(pro_check);
        check_box_group.add(stu_check);

        check_panel.add(pro_check);
        check_panel.add(stu_check);

        id_panel.add(id_label, BorderLayout.WEST);
        id_panel.add(id_field, BorderLayout.CENTER);

        pw_panel.add(pw_label, BorderLayout.WEST);
        pw_panel.add(pw_field, BorderLayout.CENTER);

        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUpUI.visibleSignUpUI();
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (signInOption == Identity.PROFESSOR) {
                    new AssignmentCheck();

                } else if (signInOption == Identity.STUDENT) {
                    new Programming();
                }
            }
        });

        button.add(signup);
        button.add(login);

        all_panel.add(check_panel);
        all_panel.add(Box.createVerticalStrut(20));
        all_panel.add(id_panel);
        all_panel.add(Box.createVerticalStrut(10));
        all_panel.add(pw_panel);
        all_panel.add(Box.createVerticalStrut(30));
        all_panel.add(button);

        frame.setContentPane(all_panel);

        frame.setSize(300, 200);
    }

    public void visibleSignIn() {
        frame.setVisible(true);
    }

    public void invisibleSignIn() {
        frame.setVisible(false);
    }

}