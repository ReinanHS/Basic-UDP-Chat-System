package br.bsi.sd.udpchat.actions;

import br.bsi.sd.udpchat.UdpChatApp;
import br.bsi.sd.udpchat.utils.ClienteUDP;
import br.bsi.sd.udpchat.utils.ServeUDP;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.Scanner;

public class ConnectionController extends ControllerBase{
    private String username;
    private String ipConnection;

    private String myInet4Address;

    @Override
    public void command(String[] args) {
        if(args.length <= 1) {
            System.out.println(UdpChatApp.APP_NAME + ": To connect it is necessary to enter an ip address. See "+ UdpChatApp.APP_NAME + " connect 127.0.0.1");
        }

        this.ipConnection = args[1];
        this.username = System.getProperty("user.name");
        this.myInet4Address = this.getInet4Address();

        if(args.length <= 2) {
            this.getConfigUserName();
        }

        System.out.println(this.username + " we will connect to IP "+ this.ipConnection);
        System.out.println("Your ip address is "+ this.myInet4Address);

        try {
            this.runChat();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to get the customer's ip address
     * @return ip
     */
    private String getInet4Address() {
        try {
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "";
        }
    }

    public void runChat() throws Exception {
        boolean isExitChat = false;
        Scanner scanner = new Scanner(System.in);
        ClienteUDP clienteUDP = new ClienteUDP();

        Thread threadServidor = new Thread() {
            @Override
            public void run() {
                try {

                    ServeUDP serveUDP = new ServeUDP();
                    
                    while (true){
                        serveUDP.onText();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        threadServidor.start();

        while (!isExitChat) {
            System.out.print(this.username+"@"+this.myInet4Address+": ");
            String text = scanner.next();

            if(text.toLowerCase(Locale.ROOT).equals("\\sair") || text.toLowerCase(Locale.ROOT).equals("\\exit")) {
                isExitChat = true;
            } else {
                clienteUDP.sendText(this.ipConnection, this.username+"@"+this.myInet4Address+": "+text);
            }
        }

        if(isExitChat) {
            threadServidor.stop();
        }
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
