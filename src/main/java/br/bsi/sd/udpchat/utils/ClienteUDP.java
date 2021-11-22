package br.bsi.sd.udpchat.utils;

import java.net.*;

public class ClienteUDP {
    private DatagramSocket datagramSocket;

    public ClienteUDP() throws SocketException {
        this.datagramSocket = new DatagramSocket();
    }

    public void sendText(String ipNumber, String text) {
        this.sendText(ipNumber, 5000, text);
    }

    public void sendText(String ipNumber, int port, String text) {
        byte[] cartaAEnviar = text.getBytes();

        try {

            InetAddress ip = InetAddress.getByName(ipNumber);
            DatagramPacket envelopeAEnviar = new DatagramPacket(cartaAEnviar, cartaAEnviar.length, ip, port);
            this.datagramSocket.send(envelopeAEnviar);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
