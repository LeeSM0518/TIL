package lecture_manager.userinterface;

import javax.swing.*;
import java.awt.*;

public class ProblemViewDetail extends JFrame {
    private JPanel mainPanel;
    private JPanel inPanel;
    private JPanel titlePanel;
    private JPanel ContextPanel;
    private JLabel titleLabel;
    private JLabel contextLabel;
    private JScrollPane contextScrollPane;
    private JLabel contextLabel2;
    private JLabel titleLabel2;

    public ProblemViewDetail(String title, String context) {
        add(mainPanel);

        titleLabel2.setText(title);
        contextLabel2.setText(context);

        setSize(800, 800);

        Dimension frameSize = getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

        setVisible(true);
    }

}
