package br.bsi.sd.udpchat.utils;

import br.bsi.sd.udpchat.enums.ConnectionType;
import br.bsi.sd.udpchat.models.Connection;
import br.bsi.sd.udpchat.models.User;

import java.io.IOException;
import java.net.*;

public class ChatAction {
    protected final User user;
    protected final Connection connection;

    private DatagramSocket datagramSocket;

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
        System.out.println("Start server");
    }
}
