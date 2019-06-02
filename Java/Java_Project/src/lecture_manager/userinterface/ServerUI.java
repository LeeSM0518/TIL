package lecture_manager.userinterface;

import lecture_manager.communication.Server;
import lecture_manager.message.Message;
import lecture_manager.message.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class ServerUI extends Server {
    private JPanel mainPanel;
    private JPanel inPanel;
    private JPanel serverStatePanel;
    private JLabel serverStateLabel;
    private JPanel serverStateInformationPanel;
    private JPanel serverStateButtonPanel;
    private JList serverStateList;
    private JScrollPane serverStateScrollPane;
    private JButton serverIpAssignButton;
    private JButton serverStartButton;

    private static DefaultListModel serverStateListModel;

    public ServerUI() {
        JFrame mainFrame = new JFrame("서버");
        mainFrame.add(mainPanel);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                executorService.shutdownNow();
                stopServer();
                System.exit(1);
            }
        });

        serverStateListModel = new DefaultListModel<java.io.Serializable>();

        serverStateList.setModel(serverStateListModel);

        serverIpAssignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverIpAssign();
                serverStateListModel.addElement("Server IP : " + inetSocketAddress.getAddress());
                serverStateListModel.addElement("Server Port : " + inetSocketAddress.getPort());
            }
        });

        serverStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServerInUI();
            }
        });

        mainFrame.setSize(800, 800);

        Dimension frameSize = mainFrame.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

        mainFrame.setVisible(true);
    }

    private void serverIpAssign() {
        try {
            InetAddress local = InetAddress.getLocalHost();
            inetSocketAddress = new InetSocketAddress(local.getHostAddress(), 5001);
            JOptionPane.showMessageDialog(null,
                    "IP 할당을 완료했습니다.",
                    "완료",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null,
                    "IP 할당을 실패했습니다.",
                    "실패",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void startServerInUI() {
        if (inetSocketAddress != null) {
            startServer();
            JOptionPane.showMessageDialog(null,
                    "서버가 실행되었습니다.",
                    "성공",
                    JOptionPane.INFORMATION_MESSAGE);
            pollWaitingMessage();
        } else {
            JOptionPane.showMessageDialog(null,
                    "서버 실행을 실패했습니다.",
                    "실패",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void loadingUserInf() {
        // TODO 데이터 베이스로 부터 회원 정보들을 가져와서 profileList 에 추가 기능 구현
    }

    private void addUser() {
        // TODO 회원 추가 버튼 클릭시 회원 가입 창이 뜨고 회원 가입 완료시 데이터 베이스에 추가하고
        //  profileList 에 추가
    }

    private void removeUser() {
        // TODO profileList 에 선택되어있는 user의 정보를 데이터 베이스에서 지운다.
    }

    private void viewUserInformation() {
        // TODO profileList 에서 user 를 더블 클릭시 회원 정보를 확인할 수 있다.
    }

    private void pollWaitingMessage() {
        Runnable runnable = () -> {
            while (true) {
                try {
                    Thread.sleep(1);
                    if (waitingMessages.size() != 0) {
                        System.out.println(waitingMessages.size());
                        Message message = waitingMessages.poll();
                        serverStateListModel.addElement(message.getType());
                        if (message.getType() == Type.STOP_SERVER) {
                            JOptionPane.showMessageDialog(null,
                                    "서버가 종료되었습니다.",
                                    "서버 종료",
                                    JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        executorService.submit(runnable);
    }

    public static void main(String[] args){
        new ServerUI();
    }
}
