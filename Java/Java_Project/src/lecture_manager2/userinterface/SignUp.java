package lecture_manager2.userinterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class SignUp extends JFrame {

    SignUp() {
        JFrame frame = new JFrame("Lecture Manager - 회원가입");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel all_pn = new JPanel();
        all_pn.setLayout(new BoxLayout(all_pn, BoxLayout.Y_AXIS));
        JPanel name_pn = new JPanel(new BorderLayout());
        JPanel number_pn = new JPanel(new BorderLayout());
        JPanel pw_pn = new JPanel(new BorderLayout());
        JPanel pw_check_pn = new JPanel(new BorderLayout());
        JPanel button_pn = new JPanel(new GridLayout(1,1));
        //JPanel overlab_pn = new JPanel(new GridLayout(1,1));
        JPanel check_pn = new JPanel();
        check_pn.setBorder(new TitledBorder(null,null,TitledBorder.LEADING,TitledBorder.TOP,null,null));

        JTextField name_fd = new JTextField();
        JTextField number_fd = new JTextField();
        JPasswordField pw_fd = new JPasswordField();
        JPasswordField pw_check_fd = new JPasswordField();

        JLabel name_lb = new JLabel("       이름      ");
        JLabel number_lb = new JLabel("       학번      ");
        JLabel pw_lb = new JLabel("     비밀번호    ");
        JLabel pw_check_lb = new JLabel("비밀번호 재확인");

        JButton overlap_bt = new JButton("중복확인");
        JButton signup_bt = new JButton("회원가입");

        overlap_bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        signup_bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pw_fd.getText()!=pw_check_fd.getText()) {

                }
            }
        });



        ButtonGroup check_box_group = new ButtonGroup();
        JRadioButton pro_check = new JRadioButton("교수용");
        JRadioButton stu_check = new JRadioButton("학생용");

        name_pn.add(name_lb,BorderLayout.WEST);
        name_pn.add(name_fd, BorderLayout.CENTER);

        number_pn.add(number_lb,BorderLayout.WEST);
        number_pn.add(number_fd,BorderLayout.CENTER);
        number_pn.add(overlap_bt,BorderLayout.EAST);

        pw_pn.add(pw_lb, BorderLayout.WEST);
        pw_pn.add(pw_fd, BorderLayout.CENTER);

        pw_check_pn.add(pw_check_lb,BorderLayout.WEST);
        pw_check_pn.add(pw_check_fd,BorderLayout.CENTER);

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
        frame.setSize(300,200);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SignUp();
    }

}