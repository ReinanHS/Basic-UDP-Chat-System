package br.bsi.sd.udpchat.utils;

import br.bsi.sd.udpchat.enums.ConnectionType;
import br.bsi.sd.udpchat.models.Connection;
import br.bsi.sd.udpchat.models.Message;
import br.bsi.sd.udpchat.models.User;

import java.net.*;
import java.util.Locale;
import java.util.Scanner;

public class ChatAction {
    protected final User user;
    protected final Connection connection;

    private final Scanner scanner = new Scanner(System.in);
    private DatagramSocket datagramSocket;

    private final byte[] pullBuffer = new byte[1024];
    private byte[] pushBuffer = new byte[1024];

    public ChatAction(Connection connection, User user) throws SocketException {
        this.user = user;
        this.connection = connection;

        try{
            this.datagramSocket = new DatagramSocket(this.connection.portConnection);
        } catch (BindException exception) {
            System.out.println("system@local: There is already an instance of this program running on your machine");
            System.out.println("system@local: Because of this, the ip "+this.connection.ipConnection+" is not available");
            System.exit(0);
        }
    }

    public void start() throws Exception {
        System.out.println("system@local: use \\q to exit chat");
        String output = "\\q";

        this.sendMessageToConnection(this.connection, new Message(this.user, "A new user has joined the chat"));

        do {

            System.out.print(this.user.username+"@"+this.user.ip+": ");
            output = this.scanner.nextLine();

            try {

                DatagramPacket pullPacket = new DatagramPacket(this.pullBuffer, this.pullBuffer.length);
                this.datagramSocket.receive(pullPacket);

                Message message = new Message(pullPacket.getData());
                System.out.println(message);

                this.sendMessageToConnection(new Connection(pullPacket.getAddress(), pullPacket.getPort(), ConnectionType.PEER_TO_PEER), new Message(this.user, output));

            } catch (Exception e) {
                System.out.print("system@local: "+e.getMessage());
            }

        } while (!output.toLowerCase(Locale.ROOT).equals("\\q"));

        this.datagramSocket.close();
        System.out.println("Bye...");
    }

    private void sendMessageToConnection(Connection  connection, Message message) throws Exception {
        this.pushBuffer = message.getBytes();
        DatagramPacket sendBufferStart = new DatagramPacket(
                this.pushBuffer,
                this.pushBuffer.length,
                connection.ipConnection,
                connection.portConnection
        );

        this.datagramSocket.send(sendBufferStart);
    }
}
