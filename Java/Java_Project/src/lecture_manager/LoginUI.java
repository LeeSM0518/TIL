package lecture_manager;

import javax.swing.*;
import java.awt.*;

public class LoginUI {
    JFrame mainFrame = new JFrame("Lecture Manager");
    JPanel mainPanel = new JPanel();
    JPanel mainTitlePanel = new JPanel(new BorderLayout());
    JPanel mainIdPanel = new JPanel(new BorderLayout());
    JPanel mainPasswordPanel = new JPanel(new BorderLayout());
    JPanel mainBtnPanel = new JPanel(new GridLayout(1, 2));

    JLabel titleLabel = new JLabel("Lecture Manager");
    JLabel idLabel = new JLabel(" 학번        : ");
    JTextField idTextField = new JTextField();
    JLabel passwordLabel = new JLabel(" 비밀 번호 : ");
    JTextField passwordTextField = new JTextField();

    JButton signUpButton = new JButton("회원가입");
    JButton loginInButton = new JButton("로그인");

    JFrame successLoginFrame = new JFrame("로그인 완료");
    JPanel successLoginPanel = new JPanel();
    JLabel successLoginLabel = new JLabel("로그인이 완료되었습니다.");

    JFrame failLoginFrame = new JFrame("로그인 실패");
    JPanel failLoginPanel = new JPanel();
    JLabel failLoginLabel = new JLabel("로그인을 실패했습니다.");

    JFrame signUpFrame = new JFrame("회원가입");

    void showLoginFrame() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainTitlePanel.add(titleLabel, BorderLayout.CENTER);

        mainIdPanel.add(idLabel, BorderLayout.WEST);
        mainIdPanel.add(idTextField, BorderLayout.CENTER);

        mainPasswordPanel.add(passwordLabel, BorderLayout.WEST);
        mainPasswordPanel.add(passwordTextField, BorderLayout.CENTER);

        mainBtnPanel.add(signUpButton);
        mainBtnPanel.add(loginInButton);

        mainPanel.add(mainTitlePanel, BoxLayout.X_AXIS);
        mainPanel.add(mainIdPanel);
        mainPanel.add(mainPasswordPanel);
        mainPanel.add(mainBtnPanel);

        mainFrame.add(mainPanel);
        mainFrame.setSize(300, 200);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        LoginUI loginUI = new LoginUI();
        loginUI.showLoginFrame();
    }

}
