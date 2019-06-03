package lecture_manager.userinterface;

import lecture_manager.communication.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetSocketAddress;
import java.net.Socket;

public class IpAssignUI extends JFrame {

    private JPanel mainPanel;
    private JButton assignButton;
    private JPanel inPanel;
    private JLabel ipAssignLabel;
    private JTextField ipAssignTextField;

    private Client client;

    public IpAssignUI(Client client) {
        this.client = client;
        add(mainPanel);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        assignButton.addActionListener(action -> {
            try {
                Socket socket = new Socket();
                InetSocketAddress inetSocketAddress = new InetSocketAddress(ipAssignTextField.getText(), 5001);
                socket.connect(inetSocketAddress);
                client.ipAssign(inetSocketAddress);
                SignIn signIn = new SignIn(client);
                signIn.visibleSignIn();
                this.invisibleUI();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "잘못된 IP 입니다.\nIP 를 입력해주세요.",
                        "경고",
                        JOptionPane.WARNING_MESSAGE);
            }

        });

        setTitle("IP 할당");
        setSize(400, 200);
        Dimension frameSize = getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

    public void visibleUI() {
        setVisible(true);
    }

    public void invisibleUI() {
        setVisible(false);
    }
}
