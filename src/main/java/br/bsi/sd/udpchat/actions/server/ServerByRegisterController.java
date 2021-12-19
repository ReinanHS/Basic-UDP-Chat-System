package br.bsi.sd.udpchat.actions.server;

import br.bsi.sd.udpchat.actions.ControllerBase;
import br.bsi.sd.udpchat.entity.Client;
import br.bsi.sd.udpchat.entity.server.data.Request;
import br.bsi.sd.udpchat.entity.server.data.Response;
import br.bsi.sd.udpchat.interfaces.IRequest;
import br.bsi.sd.udpchat.interfaces.IUser;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class ServerByRegisterController extends ControllerBase {
    private DatagramSocket datagramSocket;
    private ArrayList<IUser> userList;

    @Override
    public void command(String[] args) throws Exception {
        this.datagramSocket = new DatagramSocket(Integer.parseInt(args[1]));
        this.userList = new ArrayList<>();

        this.start();
    }

    public void start() throws Exception {

        this.detailsServer();

        do {

            this.showUserList();
            DatagramPacket packet = this.getPacket();
            IRequest request = new Request(packet.getData());
            this.requestHandler(request, packet);
        } while (true);
    }

    private void requestHandler(IRequest request, DatagramPacket packet) {
        switch (request.getAction()) {
            case REGISTER:
                this.register(request, packet);
                break;
            case USERS:
                this.response(this.userList, packet.getAddress(), packet.getPort(), datagramSocket);
                break;
            case EXIT:
                this.unregister(request);
                break;
            default:
                System.out.println("Route not found!");
        }
    }

    private void response(
            ArrayList<IUser> userList,
            InetAddress ip,
            int portTarget,
            DatagramSocket socket
    ) {
        try {
            byte[] buffer = new byte[1024];
            Response response = new Response(userList);
            buffer = response.getBytes();

            DatagramPacket packet = new DatagramPacket(
                    buffer,
                    buffer.length,
                    ip,
                    portTarget
            );

            socket.send(packet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void register(IRequest request, DatagramPacket packet) {
        this.addUser(request.getUser());
    }

    private void unregister(IRequest request) {
        Client client = (Client) request.getUser();
        this.removeUser(client);
    }

    private void addUser(IUser user) {
        this.userList.add(user);
    }

    private void removeUser(IUser user) {
        this.userList.remove(user);
    }

    private void showUserList() {
        System.out.println("########## LIST OF ALL USERS ("+ this.userList.size() +") ##########");
        System.out.println("-------------------------------------------");

        int count = 0;
        for (IUser user : this.userList) {
            System.out.println(count + " -> (" + user.getName() + ", " + user.getIp().getHostAddress() + ":" + user.getListeningPort() + ")");
            count++;
        }

        System.out.println("-------------------------------------------");
    }

    private void detailsServer() {
        System.out.println("########## Details of Server ##########");
        System.out.println("---------------------------------------");
        System.out.println("IP: " + this.getInet4Address() + " | Listening Port: " + this.datagramSocket.getLocalPort());
        System.out.println("---------------------------------------\n\n");
    }

    private String getInet4Address() {
        try {
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "";
        }
    }

    private DatagramPacket getPacket() {
        try {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("Waiting request...");
            this.datagramSocket.receive(packet);

            return packet;

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
