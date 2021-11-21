package br.bsi.sd.udpchat.actions;

import br.bsi.sd.udpchat.UdpChatApp;

import java.util.Locale;
import java.util.Scanner;

public class ConnectionController extends ControllerBase{
    private String username;
    private String ipConnection;

    @Override
    public void command(String[] args) {
        if(args.length <= 1) {
            System.out.println(UdpChatApp.APP_NAME + ": To connect it is necessary to enter an ip address. See "+ UdpChatApp.APP_NAME + " connect 127.0.0.1");
        }

        this.ipConnection = args[1];
        this.username = System.getProperty("user.name");

        if(args.length <= 2) {
            this.getConfigUserName();
        }

        System.out.println(this.username + " we will connect to IP "+ this.ipConnection);
    }

    /**
     * Method to get username settings
     */
    private void getConfigUserName() {
        System.out.print("The name you want to use is "+ this.username + " [y/n]: ");

        try {
            Scanner scanner = new Scanner(System.in);
            String resultChangeName = scanner.next();

            if (!resultChangeName.toLowerCase(Locale.ROOT).equals("y")) {
                System.out.print("Enter a username: ");
                this.username = scanner.next();
            }

        } catch (Exception exception) {
            System.out.println("An unexpected error has occurred: " + exception.getMessage());
            System.out.println("Please try typing again!!!");
            this.getConfigUserName();
        }
    }
}
