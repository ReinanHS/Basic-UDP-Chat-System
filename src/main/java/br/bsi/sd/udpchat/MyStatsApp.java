package br.bsi.sd.udpchat;

import java.net.InetAddress;
import java.util.Scanner;

public class MyStatsApp {
    /**
     * Method to launch the application
     * @param args Application parameters
     */
    public static void main(String[] args) {
        UdpChatApp app = new UdpChatApp();
        app.run(new String[]{"connect", readIp()});
    }

    /**
     * Method to read the ip for connection
     * @return ip to connection
     */
    public static String readIp() {
        try {

            Scanner scanner = new Scanner(System.in);
            System.out.print("system@app: Enter the ip address for connection: ");

            InetAddress inetAddress = InetAddress.getByName(scanner.nextLine());

            return inetAddress.getHostAddress();

        } catch (Exception exception) {
            return readIp();
        }
    }
}
