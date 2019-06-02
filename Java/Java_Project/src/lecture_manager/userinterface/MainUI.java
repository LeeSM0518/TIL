package lecture_manager.userinterface;

import lecture_manager.communication.Client;

public class MainUI {
    static Client client = new Client();
    static IpAssignUI ipAssignUI = new IpAssignUI(client);

    public static void main(String[] args) {
        ipAssignUI.visibleUI();
    }

}