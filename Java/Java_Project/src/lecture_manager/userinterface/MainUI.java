package lecture_manager.userinterface;

import lecture_manager.communication.Client;

public class MainUI {
    static Client client = new Client();
    static SignIn signIn = new SignIn(client);

    public static void main(String[] args) {
        signIn.visibleSignIn();
    }

}