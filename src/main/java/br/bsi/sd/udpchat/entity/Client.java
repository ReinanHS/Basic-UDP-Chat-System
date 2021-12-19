package br.bsi.sd.udpchat.entity;

import br.bsi.sd.udpchat.interfaces.IUser;

import java.io.Serializable;
import java.net.InetAddress;

public class Client implements IUser, Serializable {
    public int port;
    public String name;
    public InetAddress ip;

    public Client(int port, String name, InetAddress ip) {
        this.port = port;
        this.name = name;
        this.ip = ip;
    }

    public Client(String port, String name, InetAddress ip) {
        this.port = Integer.parseInt(port);
        this.name = name;
        this.ip = ip;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public InetAddress getIp() {
        return this.ip;
    }

    @Override
    public int getListeningPort() {
        return this.port;
    }
}
