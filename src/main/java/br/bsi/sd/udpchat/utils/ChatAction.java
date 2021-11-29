package br.bsi.sd.udpchat.utils;

import br.bsi.sd.udpchat.enums.ConnectionType;
import br.bsi.sd.udpchat.models.Connection;
import br.bsi.sd.udpchat.models.Message;
import br.bsi.sd.udpchat.models.User;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
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
        this.datagramSocket = new DatagramSocket(this.connection.portConnection);
    }

    public void start() throws IOException {

        if (this.connection.connectionType == ConnectionType.CLIENT) {
            this.startClient();
            return;
        }

        this.startServer();
    }

    public void startClient() {
        System.out.println("Start Client");
    }

    public void startServer() {
        System.out.println("system@local: use \\q to exit chat");
        String output = "\\q";

        do {

            System.out.print(this.user.username+"@"+this.user.ip+": ");
            output = this.scanner.nextLine();

            this.pushBuffer = SerializationUtils.serialize(new Message(this.user, output));

            DatagramPacket sendBuffer = new DatagramPacket(
                    this.pushBuffer,
                    this.pushBuffer.length,
                    this.connection.ipConnection,
                    this.connection.portConnection
            );

            DatagramPacket pullPacket = new DatagramPacket(this.pullBuffer, this.pullBuffer.length);

            try {

                this.datagramSocket.send(sendBuffer);
                this.datagramSocket.receive(pullPacket);

                Message message = (Message) SerializationUtils.deserialize(pullPacket.getData());

                if(!message.user.equals(this.user)) {
                    System.out.println(message);
                }

            } catch (IOException e) {
                System.out.print("system@local: "+e.getMessage());
            }

        } while (!output.toLowerCase(Locale.ROOT).equals("\\q"));

        this.datagramSocket.close();
        System.out.println("Bye...");
    }
}
