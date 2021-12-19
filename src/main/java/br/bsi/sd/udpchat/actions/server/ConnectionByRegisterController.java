package br.bsi.sd.udpchat.actions.server;

import br.bsi.sd.udpchat.actions.ControllerBase;
import br.bsi.sd.udpchat.entity.Client;
import br.bsi.sd.udpchat.entity.Connection;
import br.bsi.sd.udpchat.entity.User;
import br.bsi.sd.udpchat.entity.server.data.Request;
import br.bsi.sd.udpchat.entity.server.data.Response;
import br.bsi.sd.udpchat.entity.server.register.ConnectionByServerRegister;
import br.bsi.sd.udpchat.enums.ConnectionType;
import br.bsi.sd.udpchat.enums.RotaEnum;
import br.bsi.sd.udpchat.interfaces.IUser;
import br.bsi.sd.udpchat.utils.ChatAction;
import br.bsi.sd.udpchat.utils.ClienteUDP;
import br.bsi.sd.udpchat.utils.RequestManager;
import br.bsi.sd.udpchat.utils.ServeUDP;
import br.bsi.sd.udpchat.utils.helps.ClientHelp;
import br.bsi.sd.udpchat.utils.input.InputClient;
import br.bsi.sd.udpchat.utils.input.InputMenuRegisterServe;
import br.bsi.sd.udpchat.utils.input.InputRegisterServe;

import java.net.DatagramSocket;
import java.util.Locale;

public class ConnectionByRegisterController extends ControllerBase {
    private ConnectionByServerRegister connection;
    private Client serveClient;
    private Client client;
    private DatagramSocket datagramSocket;

    @Override
    public void command(String[] args) {
        this.connection = InputRegisterServe.read();
        this.serveClient = new Client(this.connection.port, "Server", this.connection.ip);
        this.client = InputClient.read();

        try {
            this.datagramSocket = new DatagramSocket(this.client.getListeningPort());
            RequestManager.sendRequest(new Request(RotaEnum.REGISTER, this.client), this.serveClient, datagramSocket);

            this.showMenu();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMenu() {
        switch (InputMenuRegisterServe.read()) {
            case ENVIAR_MENSAGENS -> {
                RequestManager.sendRequest(new Request(RotaEnum.USERS, this.client), this.serveClient, datagramSocket);
                Response response = ServeUDP.receiveResponse(datagramSocket);

                System.out.println("Parceiros registrados no servidor:");

                assert response != null;
                int count = 0;
                for (IUser user : response.getUserList()) {
                    System.out.println(count + " -> (" + user.getName() + ", " + user.getIp().getHostAddress() + ":" + user.getListeningPort() + ")");
                    count++;
                }

                System.out.println("Digite o index do parceiro com quem quer falar: ");
                int indexUser = this.scanner.nextInt();

                IUser client = response.getUserList().get(indexUser);
                startChat(client);

            }
            case ESPERAR_MENSAGENS -> {
                startChat(null);
            }
            case SAIR -> {
                this.closeConnection();
            }
        }
    }

    private void startChat(IUser client) {
        System.out.println("system@local: use \\q to exit chat");
        String output = "\\q";

        if(client != null) {
            ClienteUDP.sendMessage(
                    "Starting chat...",
                    client.getIp(),
                    client.getListeningPort(),
                    this.datagramSocket
            );
        }

        do {
            Client clientReceive = ServeUDP.receiveWithClient(this.client.getName(), this.datagramSocket);

            String user = this.client.getName()+"@"+this.client.getIp()+":"+this.client.getListeningPort()+": ";
            System.out.print(user);
            output = this.scanner.nextLine();

            assert clientReceive != null;
            ClienteUDP.sendMessage(
                    user + output,
                    clientReceive.getIp(),
                    clientReceive.getListeningPort(),
                    this.datagramSocket
            );

        } while (!output.toLowerCase(Locale.ROOT).equals("\\q"));

        this.closeConnection();
    }

    private void closeConnection() {
        RequestManager.sendRequest(new Request(RotaEnum.EXIT, this.client), this.serveClient, datagramSocket);
        this.datagramSocket.close();
        System.out.println("Bye...");
        System.exit(1);
    }
}
