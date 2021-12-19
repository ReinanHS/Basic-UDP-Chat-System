package br.bsi.sd.udpchat.utils;

import br.bsi.sd.udpchat.entity.Connection;
import br.bsi.sd.udpchat.entity.User;

import java.net.*;
import java.util.Locale;
import java.util.Scanner;



public class ChatAction {
    protected final User user;
    protected final Connection connection;

    private final Scanner scanner = new Scanner(System.in);
    private DatagramSocket datagramSocket;

    public ChatAction(Connection connection, User user) throws SocketException {
        this.user = user;
        this.connection = connection;

        try{
            this.datagramSocket = new DatagramSocket(this.connection.listeningPort);
        } catch (BindException exception) {
            System.out.println("system@local: There is already an instance of this program running on your machine");
            System.out.println("system@local: Because of this, the ip "+this.connection.ipConnection+" is not available");
            System.exit(0);
        }
    }

    public void start() throws Exception {
        System.out.println("system@local: use \\q to exit chat");
        String output = "\\q";
        
        ClienteUDP.sendMessage(
                "Starting chat...",
                this.connection.ipConnection,
                this.connection.sendingPort,
                this.datagramSocket
        );

        do {
            ServeUDP.receive(this.user.username, this.datagramSocket);
            
            String user = this.user.username+"@"+this.user.ip+":"+this.connection.listeningPort+": ";
            System.out.print(user);
            output = this.scanner.nextLine();
            
            ClienteUDP.sendMessage(
                    output,
                    this.connection.ipConnection,
                    this.connection.sendingPort,
                    this.datagramSocket
            );
            
        } while (!output.toLowerCase(Locale.ROOT).equals("\\q"));

        this.datagramSocket.close();
        System.out.println("Bye...");
    }
}