package midterm_exam;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class KBKeyPad extends Frame implements ActionListener, WindowListener {
    Panel pbtn, ptf;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, b_cfrm, b_clear, b_del;

    TextField textField;
    StringBuffer sb = new StringBuffer(20);
    private String string;

    public KBKeyPad() {
        // frame 생성(윈도우)
        Frame frame = new Frame("KeyPad");

        Label passwordLabel = new Label("암호: ");

        pbtn = new Panel();
        textField = new TextField(20);

        ptf = new Panel();
        b1 = new Button("1");
        b2 = new Button("2");
        b3 = new Button("3");
        b4 = new Button("4");
        b5 = new Button("5");
        b6 = new Button("6");
        b7 = new Button("7");
        b8 = new Button("8");
        b9 = new Button("9");
        b0 = new Button("0");


        List<Button> list = new Vector<>();



        b_cfrm = new Button("확인");
        b_clear = new Button("전체지움");
        b_del = new Button("하나지움");

        pbtn.add(passwordLabel);
        pbtn.add(textField);

        ptf.add(b1);
        ptf.add(b2);
        ptf.add(b3);
        ptf.add(b4);
        ptf.add(b5);
        ptf.add(b6);
        ptf.add(b7);
        ptf.add(b8);
        ptf.add(b9);
        ptf.add(b0);
        ptf.add(b_del);
        ptf.add(b_clear);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                string.concat("1");
                textField.setText(string);
            }
        });
    }


}
