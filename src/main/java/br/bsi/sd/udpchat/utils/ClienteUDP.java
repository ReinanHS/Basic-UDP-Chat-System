package udpchat.utils;

import java.net.*;

public class ClienteUDP
{
    public static void sendMessage(
            String message,
            InetAddress ip,
            int portTarget,
            DatagramSocket socket
    ) {
        try {
            byte[] cartaAEnviar = new byte[1024];
            cartaAEnviar = message.getBytes();
            
            DatagramPacket envelopeAEnviar = new DatagramPacket(
                    cartaAEnviar,
                    cartaAEnviar.length,
                    ip,
                    portTarget
            );

            socket.send(envelopeAEnviar);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}