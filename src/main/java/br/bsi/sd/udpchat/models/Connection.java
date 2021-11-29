package br.bsi.sd.udpchat.models;

import br.bsi.sd.udpchat.enums.ConnectionType;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Connection {
    public InetAddress ipConnection;
    public int portConnection;
    public ConnectionType connectionType;

    public Connection(InetAddress ipConnection, int portConnection, ConnectionType connectionType) {
        this.connectionType = connectionType;
        this.ipConnection = ipConnection;
        this.portConnection = portConnection;
    }

    public Connection(String ipConnection, int portConnection, ConnectionType connectionType) throws UnknownHostException {
        this.connectionType = connectionType;
        this.ipConnection = InetAddress.getByName(ipConnection);
        this.portConnection = portConnection;
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

    public int getPortConnection() {
        return portConnection;
    }

    public void setPortConnection(int portConnection) {
        this.portConnection = portConnection;
    }
}
