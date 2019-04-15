package midterm_exam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignIn {
    // 윈도우
    Frame frame = new Frame("Adapter 테스트");

    Label idLabel = new Label("회원ID");
    TextField idTextField = new TextField();
    Button yesButton = new Button("처리");

    Label nameLabel = new Label("회원 이름");
    TextField nameTextField = new TextField();
    Button deleteButton = new Button("삭제");

    Label passwordLabel = new Label("패스워드");
    TextField passwordTextField = new TextField();
    Button exitButton = new Button("종료");

    Label birthLabel = new Label("주민등록번호");
    TextField birthTextField = new TextField();

    TextField freeTextField = new TextField(10);
    TextArea outScrollPane = new TextArea(10, 30);

    Button calculateButton = new Button("빈도수계산");
    Button resetButton = new Button("Reset");

    SignIn() {
        frame.setSize(500, 380);

        GridLayout gridLayout = new GridLayout(4, 3);
        Panel signEditPanel = new Panel();
        signEditPanel.setLayout(gridLayout);
        idLabel.setAlignment(Label.CENTER);
        nameLabel.setAlignment(Label.CENTER);
        passwordLabel.setAlignment(Label.CENTER);
        birthLabel.setAlignment(Label.CENTER);

        signEditPanel.add(idLabel);
        signEditPanel.add(idTextField);
        signEditPanel.add(yesButton);
        signEditPanel.add(nameLabel);
        signEditPanel.add(nameTextField);
        signEditPanel.add(deleteButton);
        signEditPanel.add(passwordLabel);
        signEditPanel.add(passwordTextField);
        signEditPanel.add(exitButton);
        signEditPanel.add(birthLabel);
        signEditPanel.add(birthTextField);

        Label blankLabel = new Label();

        Panel editTextPanel = new Panel();
        editTextPanel.setLayout(new BoxLayout(editTextPanel, BoxLayout.Y_AXIS));

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                freeTextField.setText("");
                outScrollPane.setText(freeTextField.getText());
            }
        });

        deleteButton.addActionListener(s -> {
            freeTextField.setText("");
            outScrollPane.setText("");
            idTextField.setText("");
            nameTextField.setText("");
            passwordTextField.setText("");
            birthTextField.setText("");
        });

        editTextPanel.add(freeTextField);

        idTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (idTextField.getText().equals("숫자 또는 영문자만 허용됨!")) {
                    idTextField.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

                int key = e.getKeyChar();

                if ((key < 95 || key > 122) && (key < 65 || key > 90) && (key < 48 || key > 57) &&
                        key != 8 && key != 10) {
                    idTextField.setText("숫자 또는 영문자만 허용됨!");
                }

            }
        });

        nameTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (nameTextField.getText().equals("영문자와 공백만 허용됨!")) {
                    nameTextField.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyChar();

                if ((key < 95 || key > 122) && (key < 65 || key > 90) && key != 32 &&
                        key != 8 && key != 10) {
                    nameTextField.setText("영문자와 공백만 허용됨!");
                }
            }
        });

        passwordTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (passwordTextField.getText().equals("Space, Enter, Back space 는 허용되지 않음!")) {
                    passwordTextField.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyChar();

                if ( key == 32 || key == 10 || key == 8) {
                    passwordTextField.setText("Space, Enter, Back space 는 허용되지 않음!");
                }
            }
        });

        birthTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (birthTextField.getText().equals("숫자나 -만 허용됨!")) {
                    birthTextField.setText("");
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyChar();

                if ((key < 48 || key > 57) && key != 45 && key != 8 && key != 10) {
                    birthTextField.setText("숫자나 -만 허용됨!");
                }
            }
        });

        exitButton.addActionListener(s -> {
            System.exit(0);
        });

        editTextPanel.add(blankLabel);
        editTextPanel.add(outScrollPane);

        Panel elseButtons = new Panel();
        elseButtons.setLayout(new BorderLayout());
        elseButtons.add(calculateButton, BorderLayout.WEST);
        elseButtons.add(resetButton, BorderLayout.EAST);

        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.add(signEditPanel, BorderLayout.NORTH);
        frame.add(editTextPanel, BorderLayout.CENTER);
        frame.add(elseButtons);

        frame.setVisible(true);
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        new SignIn();
    }

}