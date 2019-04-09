package week5;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class quiz {

    public abstract static class KBKeyPad extends Frame implements ActionListener, WindowListener {
        Panel pbtn,ptf;
        Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,b_cfrm,b_clear,b_del;

        TextField tf;
        StringBuffer sb = new StringBuffer(20);
        private String String;

        public KBKeyPad() {
            Frame f = new Frame("KeyPad");
            Label l = new Label("암호: ");

            pbtn = new Panel();
            tf = new TextField(20);
            ptf = new Panel();
            b1 = new Button();
            b2 = new Button();
            b3 = new Button();
            b4 = new Button();
            b5 = new Button();
            b6 = new Button();
            b7 = new Button();
            b8 = new Button();
            b9 = new Button();
            b0 = new Button();
            b_cfrm = new Button();
            b_clear = new Button();
            b_del = new Button();


            b1.setLabel("1");
            b2.setLabel("2");
            b3.setLabel("3");
            b4.setLabel("4");
            b5.setLabel("5");
            b6.setLabel("6");
            b7.setLabel("7");
            b8.setLabel("8");
            b9.setLabel("9");
            b0.setLabel("0");
            b_del.setLabel("하나지움");
            b_clear.setLabel("전체지움");
            b_cfrm.setLabel("확인");

            pbtn.add(l);
            pbtn.add(tf);

            ptf.add(b1);
            ptf.add(b2);
            ptf.add(b3);
            ptf.add(b4);
            ptf.add(b5);
            ptf.add(b6);
            ptf.add(b7);
            ptf.add(b8);
            ptf.add(b9);
            ptf.add(b_del);
            ptf.add(b0);
            ptf.add(b_clear);

            b1.addActionListener(this);
            b2.addActionListener(this);
            b3.addActionListener(this);
            b4.addActionListener(this);
            b5.addActionListener(this);
            b6.addActionListener(this);
            b7.addActionListener(this);
            b8.addActionListener(this);
            b9.addActionListener(this);
            b_del.addActionListener(this);
            b0.addActionListener(this);
            b_clear.addActionListener(this);
            b_cfrm.addActionListener(this);

            f.add(pbtn,BorderLayout.NORTH);
            f.add(ptf, BorderLayout.CENTER);
            f.add(b_cfrm, BorderLayout.SOUTH);

            ptf.setLayout(new GridLayout(4,3));

            f.setVisible(true);
            f.setSize(300,300);

            f.addWindowListener(new WindowEventHandler());
        }


        public static void main(String[] args) {
            new KBKeyPad() {
                @Override
                public void windowOpened(WindowEvent e) {

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
            };
        }

        public void resetNumber() {
            Button[] button = new Button[] {b0, b1, b2, b3, b4, b5, b6, b7, b8, b9};
            String[] num = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
            ArrayList<String> number = new ArrayList<>(Arrays.asList(num));

            Collections.shuffle(number);

            for (int i = 0; i < 10; i++) {
                button[i].setLabel(number.get(i));
            }
        }

        public void actionPerformed(ActionEvent ae) {
            String name;
            name = ae.getActionCommand();
            if(name.equals("하나지움")) {
                sb.delete(sb.length()-1,sb.length());
                tf.setText(sb.toString());
            }

            else if(name.equals("전체지움")) {
                sb.delete(0,sb.length());
                tf.setText("");
            }

            else if(name.equals("확인"))
            {
                String pawd = "1234";
                if(pawd.equals(sb.toString())) {
                    sb.append(": Correct!");
                    tf.setText(sb.toString());
                }
                else
                {
                    sb.append(": Incorrect!");
                    tf.setText(sb.toString());
                }
            }

            else {
                sb.append(name);
                tf.setText(sb.toString());
                resetNumber();
            }
        }

        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }

        class WindowEventHandler extends WindowAdapter {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }

    }


}
