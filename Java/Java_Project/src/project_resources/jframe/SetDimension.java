package project_resources;

import javax.swing.*;
import java.awt.*;

public class SetDimension {
    public static void main(String[] args) {
        // Dimension 객체에 윈도우 창 크기 저장
        Dimension dim = new Dimension(500, 300);

        JFrame frame = new JFrame("Hello World!");
        frame.setLocation(200, 400);

        // 윈도우 창 크기 저장시켜주기
        frame.setPreferredSize(dim);
        frame.pack();
        frame.setVisible(true);
    }
}
