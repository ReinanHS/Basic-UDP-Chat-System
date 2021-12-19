package br.bsi.sd.udpchat.utils;

import br.bsi.sd.udpchat.entity.Client;
import br.bsi.sd.udpchat.entity.server.data.Response;

import java.net.*;

public class ServeUDP
{    
    public static void receive(String username, DatagramSocket socket)
    {
        receiveWithClient(username, socket);
    }

    public static Client receiveWithClient(String username, DatagramSocket socket)
    {
        try {
            byte[] cartaAReceber = new byte[1024];
            DatagramPacket envelopeAReceber = new DatagramPacket(cartaAReceber, cartaAReceber.length);

            System.out.println("Waiting message...");
            socket.receive(envelopeAReceber);

            Client client = new Client(envelopeAReceber.getPort(), "X", envelopeAReceber.getAddress());

            String textoRecebido = new String(envelopeAReceber.getData());
            System.out.println(textoRecebido.trim());

            return client;

        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

    public static Response receiveResponse(DatagramSocket socket)
    {
        try {
            byte[] cartaAReceber = new byte[1024];
            DatagramPacket envelopeAReceber = new DatagramPacket(cartaAReceber, cartaAReceber.length);

            System.out.println("Waiting message...");
            socket.receive(envelopeAReceber);

            return new Response(envelopeAReceber.getData());

        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }
}