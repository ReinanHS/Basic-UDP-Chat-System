package br.bsi.sd.udpchat.utils;

import br.bsi.sd.udpchat.entity.Client;
import br.bsi.sd.udpchat.entity.server.data.Request;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class RequestManager {
    public static void sendRequest(Request request, Client client, DatagramSocket socket) {
        try {
            byte[] requestBytes = request.getBytes();

            DatagramPacket envelopeAEnviar = new DatagramPacket(
                    requestBytes,
                    requestBytes.length,
                    client.getIp(),
                    client.getListeningPort()
            );

            socket.send(envelopeAEnviar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
