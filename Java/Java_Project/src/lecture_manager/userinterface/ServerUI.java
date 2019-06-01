package lecture_manager.userinterface;

import lecture_manager.communication.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerUI extends Server {
    private JPanel mainPanel;
    private JPanel inPanel;
    private JPanel profilePanel;
    private JLabel profileLabel;
    private JPanel serverStatePanel;
    private JPanel profileButtonPanel;
    private JPanel profileInformationPanel;
    private JList ProfileList;
    private JScrollPane profileScrollPane;
    private JButton addProfileButton;
    private JButton removeProfileButton;
    private JLabel serverStateLabel;
    private JPanel serverStateInformationPanel;
    private JPanel serverStateButtonPanel;
    private JList ServerStateList;
    private JScrollPane serverStateScrollPane;
    private JButton serverIpAssignButton;
    private JButton serverStartButton;

    public ServerUI() {
        JFrame mainFrame = new JFrame("서버");
        mainFrame.add(mainPanel);

        serverIpAssignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        mainFrame.setSize(1600, 800);

        Dimension frameSize = mainFrame.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

        mainFrame.setVisible(true);
    }

    private void serverIpAssign() {
        // TODO 현재 사용 중인 PC로 서버 IP 지정 기능 구현
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
        // TODO 큐에서 기다리고 있는 메시지 꺼내서 serverStateList 에 추가 기능 구현 (스레드로 구현)
    }



    public static void main(String[] args) {
        new ServerUI();
    }

}
