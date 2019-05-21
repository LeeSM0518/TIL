package lecture_manager2.userinterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class SignIn extends JFrame {

    static int option;

    JPanel button = new JPanel(new GridLayout(1,2));
    JPanel id_panel = new JPanel(new BorderLayout());
    JPanel pw_panel = new JPanel(new BorderLayout());
    JPanel check_panel = new JPanel();
    JFrame frame = new JFrame("Lecture Manager - 로그인");
    JPanel all_panel = new JPanel();
    JButton login = new JButton("로그인");
    JButton signup = new JButton("회원가입");
    ButtonGroup check_box_group = new ButtonGroup();
    JRadioButton pro_check = new JRadioButton("교수용");
    JRadioButton stu_check = new JRadioButton("학생용");
    JLabel id_label = new JLabel("     학    번     ");
    JLabel pw_label = new JLabel("    비밀번호     ");
    JTextField id_field = new JTextField();
    JPasswordField pw_field = new JPasswordField();

    SignIn() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        all_panel.setLayout(new BoxLayout(all_panel, BoxLayout.Y_AXIS));
        check_panel.setBorder(new TitledBorder(null,null,TitledBorder.LEADING,TitledBorder.TOP,null,null));

        pro_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                option = 1;
            }
        });
        stu_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                option = 2;
            }
        });

        check_box_group.add(pro_check);
        check_box_group.add(stu_check);

        check_panel.add(pro_check);
        check_panel.add(stu_check);

        id_panel.add(id_label,BorderLayout.WEST);
        id_panel.add(id_field,BorderLayout.CENTER);

        pw_panel.add(pw_label,BorderLayout.WEST);
        pw_panel.add(pw_field,BorderLayout.CENTER);

        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUp();
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(option == 1) {
                    new AssignmentCheck();

                } else if (option == 2) {
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

        frame.setSize(300,200);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SignIn();
    }

}