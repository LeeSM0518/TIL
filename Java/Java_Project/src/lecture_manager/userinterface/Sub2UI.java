package lecture_manager.userinterface;

import lecture_manager.communication.Client;

public class Sub2UI {
    static Client client = new Client();
    static IpAssignUI ipAssignUI = new IpAssignUI(client);

    public static void main(String[] args) {
        ipAssignUI.visibleUI();
    }
}
