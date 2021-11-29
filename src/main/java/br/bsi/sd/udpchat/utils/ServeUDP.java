package br.bsi.sd.udpchat.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServeUDP {
    private final DatagramSocket datagramSocket;

    public ServeUDP() throws SocketException {
        this.datagramSocket = new DatagramSocket(5000);
    }

    public ServeUDP(int port) throws SocketException {
        this.datagramSocket = new DatagramSocket(port);
    }

    public void onText() throws IOException {
        byte[] cartaAReceber = new byte[100];
        DatagramPacket envelopeAReceber = new DatagramPacket(cartaAReceber, cartaAReceber.length);

        this.datagramSocket.receive(envelopeAReceber);
        String textoRecebido = new String(envelopeAReceber.getData());
        System.out.println(textoRecebido);
    }

    public void close() {
        this.datagramSocket.close();
    }
}
