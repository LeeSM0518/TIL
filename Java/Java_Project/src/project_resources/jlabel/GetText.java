package project_resources.jlabel;

import javax.swing.*;
import java.awt.*;

public class GetText {

    public static void main(String[] args) {
        Dimension dim = new Dimension(200, 100);

        JFrame frame = new JFrame("Hello!");
        frame.setLocation(200, 400);

        frame.setPreferredSize(dim);

        JLabel label = new JLabel();

        // 텍스트를 저장한다.
        label.setText("set Text Test");

        // 텍스트를 띄운다.
        frame.add(label);

        frame.pack();
        frame.setVisible(true);

        System.out.println(label.getText());
    }
}