package br.bsi.sd.udpchat.utils;

import java.net.*;

public class ServeUDP
{    
    public static void receive(String username, DatagramSocket socket)
    {
        try {
            byte[] cartaAReceber = new byte[1024];
            DatagramPacket envelopeAReceber = new DatagramPacket(cartaAReceber, cartaAReceber.length);

            System.out.println("Waiting message...");
            socket.receive(envelopeAReceber);
            
            String clientAddress = envelopeAReceber.getAddress().getHostAddress();
            int clientPort = envelopeAReceber.getPort();
            
            String textoRecebido = new String(envelopeAReceber.getData());
            
            System.out.println(username+"@"+clientAddress+":"+clientPort+": "+textoRecebido.trim());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}