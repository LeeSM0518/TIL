package lecture_manager.userinterface;

import lecture_manager.communication.Client;

public class Sub2UI {
    static Client client = new Client();
    static SignIn signIn = new SignIn(client);

    public static void main(String[] args) {
        signIn.visibleSignIn();
    }
}
