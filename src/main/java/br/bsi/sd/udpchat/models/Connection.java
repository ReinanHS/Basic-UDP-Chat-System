package br.bsi.sd.udpchat.models;

import br.bsi.sd.udpchat.enums.ConnectionType;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Connection {
    public InetAddress ipConnection;
    public int sendingPort;
    public int listeningPort;
    public ConnectionType connectionType;

    public Connection(
            InetAddress ipConnection,
            int sendingPort,
            int listeningPort,
            ConnectionType connectionType
    ) {
        this.ipConnection = ipConnection;
        this.sendingPort = sendingPort;
        this.listeningPort = listeningPort;
        this.connectionType = connectionType;
    }

    public Connection(
            String ipConnection,
            int sendingPort,
            int listeningPort,
            ConnectionType connectionType
    ) throws UnknownHostException {
        this.ipConnection = InetAddress.getByName(ipConnection);
        this.sendingPort = sendingPort;
        this.listeningPort = listeningPort;
        this.connectionType = connectionType;
    }

    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    public InetAddress getIpConnection() {
        return ipConnection;
    }

    public void setIpConnection(InetAddress ipConnection) {
        this.ipConnection = ipConnection;
    }

    public int getSendingPort() {
        return sendingPort;
    }

    public void setSendingPort(int sendingPort) {
        this.sendingPort = sendingPort;
    }
}