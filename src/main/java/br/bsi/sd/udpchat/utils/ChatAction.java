package br.bsi.sd.udpchat.utils;

import br.bsi.sd.udpchat.enums.ConnectionType;
import br.bsi.sd.udpchat.models.Connection;
import br.bsi.sd.udpchat.models.Message;
import br.bsi.sd.udpchat.models.User;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ChatAction {
    protected final User user;
    protected final Connection connection;

    private Scanner scanner = new Scanner(System.in);
    private DatagramSocket datagramSocket;

    private byte[] pullBuffer = new byte[1024];
    private byte[] pushBuffer = new byte[1024];

    public ChatAction(Connection connection, User user) throws UnknownHostException, SocketException {
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

        this.pushBuffer = (new Message(this.user, "A new user has joined the chat")).getBytes();
        DatagramPacket sendBufferStart = new DatagramPacket(
                this.pushBuffer,
                this.pushBuffer.length,
                this.connection.ipConnection,
                this.connection.portConnection
        );

        this.datagramSocket.send(sendBufferStart);

        do {

            System.out.print(this.user.username+"@"+this.user.ip+": ");
            output = this.scanner.nextLine();

            try {

                DatagramPacket pullPacket = new DatagramPacket(this.pullBuffer, this.pullBuffer.length);
                this.datagramSocket.receive(pullPacket);

                Message message = new Message(pullPacket.getData());
                System.out.println(message);

                this.pushBuffer = (new Message(this.user, output)).getBytes();
                DatagramPacket sendBuffer = new DatagramPacket(
                        this.pushBuffer,
                        this.pushBuffer.length,
                        pullPacket.getAddress(),
                        pullPacket.getPort()
                );

                this.datagramSocket.send(sendBuffer);


            } catch (Exception e) {
                System.out.print("system@local: "+e.getMessage());
            }

        } while (!output.toLowerCase(Locale.ROOT).equals("\\q"));

        this.datagramSocket.close();
        System.out.println("Bye...");
    }
}
