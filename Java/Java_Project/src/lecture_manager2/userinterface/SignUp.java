package lecture_manager2.userinterface;

import lecture_manager2.database.Identity;
import lecture_manager2.database.User;
import lecture_manager2.message.Message;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class SignUp {

    private static Identity signUpOption = null;

    private JFrame frame = new JFrame("Lecture Manager - 회원가입");

    private JPanel all_pn = new JPanel();
    private JPanel name_pn = new JPanel(new BorderLayout());
    private JPanel number_pn = new JPanel(new BorderLayout());
    private JPanel pw_pn = new JPanel(new BorderLayout());
    private JPanel pw_check_pn = new JPanel(new BorderLayout());
    private JPanel button_pn = new JPanel(new GridLayout(1, 1));
    private JPanel check_pn = new JPanel();

    private JTextField name_fd = new JTextField();
    private JTextField number_fd = new JTextField();

    private JPasswordField pw_fd = new JPasswordField();
    private JPasswordField pw_check_fd = new JPasswordField();

    private JLabel name_lb = new JLabel("       이름      ");
    private JLabel number_lb = new JLabel("       학번      ");
    private JLabel pw_lb = new JLabel("     비밀번호    ");
    private JLabel pw_check_lb = new JLabel("비밀번호 재확인");

    private JButton overlap_bt = new JButton("중복확인");
    private JButton signup_bt = new JButton("회원가입");

    private ButtonGroup check_box_group = new ButtonGroup();

    private JRadioButton pro_check = new JRadioButton("교수용");
    private JRadioButton stu_check = new JRadioButton("학생용");

    public void viewSignUpUI() {
        all_pn.setLayout(new BoxLayout(all_pn, BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        check_pn.setBorder(new TitledBorder(null, null, TitledBorder.LEADING, TitledBorder.TOP, null, null));

        pro_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUpOption = Identity.PROFESSOR;
            }
        });

        stu_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUpOption = Identity.STUDENT;
            }
        });

        overlap_bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        signup_bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pw_fd.getText() != pw_check_fd.getText()) {

                } else {
//                    User user = new User(signUpOption, name_fd.getText(), );
//                    Message message = new Message();
//                    message.setSignUpMessage();
                }
            }
        });

        name_pn.add(name_lb, BorderLayout.WEST);
        name_pn.add(name_fd, BorderLayout.CENTER);

        number_pn.add(number_lb, BorderLayout.WEST);
        number_pn.add(number_fd, BorderLayout.CENTER);
        number_pn.add(overlap_bt, BorderLayout.EAST);

        pw_pn.add(pw_lb, BorderLayout.WEST);
        pw_pn.add(pw_fd, BorderLayout.CENTER);

        pw_check_pn.add(pw_check_lb, BorderLayout.WEST);
        pw_check_pn.add(pw_check_fd, BorderLayout.CENTER);

        button_pn.add(signup_bt);

        //overlab_pn.add(overlap_bt);

        check_box_group.add(pro_check);
        check_box_group.add(stu_check);

        check_pn.add(pro_check);
        check_pn.add(stu_check);

        all_pn.add(check_pn);
        all_pn.add(name_pn);
        all_pn.add(number_pn);
        //all_pn.add(overlab_pn);
        all_pn.add(pw_pn);
        all_pn.add(pw_check_pn);
        all_pn.add(button_pn);

        frame.setContentPane(all_pn);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }

}