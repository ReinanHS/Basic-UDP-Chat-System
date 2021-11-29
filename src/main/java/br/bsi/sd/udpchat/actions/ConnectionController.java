package br.bsi.sd.udpchat.actions;

import br.bsi.sd.udpchat.UdpChatApp;
import br.bsi.sd.udpchat.enums.ConnectionType;
import br.bsi.sd.udpchat.models.Connection;
import br.bsi.sd.udpchat.models.User;
import br.bsi.sd.udpchat.utils.ChatAction;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.Scanner;

public class ConnectionController extends ControllerBase {
    public Scanner scanner = new Scanner(System.in);

    @Override
    public void command(String[] args) {

        if (args.length <= 1) {
            System.out.println(UdpChatApp.APP_NAME + ": To connect it is necessary to enter an ip address. See " + UdpChatApp.APP_NAME + " connect 127.0.0.1");
        }

        try {

            User user = new User(this.getConfigUserName(), this.getInet4Address());
            //ConnectionType connectionType = getConnectionType();
            ConnectionType connectionType = ConnectionType.SERVE;

            Connection connection = new Connection(args[1], 5000, connectionType);
            ChatAction chatAction = new ChatAction(connection, user);
            chatAction.start();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private ConnectionType getConnectionType() {
        try {

            System.out.println("system@app: Choose an option: ");

            for (ConnectionType connectionType : ConnectionType.values()) {
                System.out.println("[ " + connectionType.ordinal() + " ] - " + connectionType.name());
            }

            return ConnectionType.values()[scanner.nextInt()];

        } catch (Exception exception) {
            System.out.println("system@app: The value you entered is invalid");
            return this.getConnectionType();
        }
    }

    /**
     * Method to get the customer's ip address
     *
     * @return ip
     */
    private String getInet4Address() {
        try {
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "";
        }
    }

    /**
     * Method to get username settings
     */
    private String getConfigUserName() {
        String username = System.getProperty("user.name");
        System.out.print("system@app: The name you want to use is " + username + " [y/n]: ");

        try {

            String resultChangeName = scanner.next();

            if (!resultChangeName.toLowerCase(Locale.ROOT).equals("y")) {
                System.out.print("system@app: Enter a username: ");
                return scanner.next();
            }

            return username;

        } catch (Exception exception) {
            System.out.println("system@app: An unexpected error has occurred: " + exception.getMessage());
            System.out.println("system@app: Please try typing again!!!");
            return this.getConfigUserName();
        }
    }
}
