package br.bsi.sd.udpchat.entity.server.register;

import java.net.InetAddress;

public class ConnectionByServerRegister {
    public String port;
    public InetAddress ip;

    public ConnectionByServerRegister(String port, InetAddress ip) {
        this.port = port;
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }
}
