package br.bsi.sd.udpchat.actions;

import br.bsi.sd.udpchat.entity.Client;
import br.bsi.sd.udpchat.entity.server.data.Request;
import br.bsi.sd.udpchat.entity.server.register.ConnectionByServerRegister;
import br.bsi.sd.udpchat.enums.RotaEnum;
import br.bsi.sd.udpchat.utils.RequestManager;
import br.bsi.sd.udpchat.utils.input.InputClient;
import br.bsi.sd.udpchat.utils.input.InputMenuRegisterServe;
import br.bsi.sd.udpchat.utils.input.InputRegisterServe;

import java.net.DatagramSocket;
import java.util.Scanner;

public class ConnectionByRegisterController extends ControllerBase {
    public Scanner scanner = new Scanner(System.in);

    @Override
    public void command(String[] args) {
        ConnectionByServerRegister connection = InputRegisterServe.read();
        Client serveClient = new Client(connection.port, "Server", connection.ip);

        Client client = InputClient.read();

        try {
            DatagramSocket datagramSocket = new DatagramSocket(client.getListeningPort());
            RequestManager.sendRequest(new Request(RotaEnum.REGISTER, client), serveClient, datagramSocket);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMenu() {
        switch (InputMenuRegisterServe.read()) {
            case ENVIAR_MENSAGENS -> {
                System.out.println("Parceiros registrados no servidor:");
            }
            case ESPERAR_MENSAGENS -> {
                System.out.println("Aguardando conexão dos parceiros...");
            }
            case SAIR -> {
                System.out.println("Bye...");
                System.exit(1);
            }
        }
    }
}
