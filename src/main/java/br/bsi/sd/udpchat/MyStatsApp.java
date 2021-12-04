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
        app.run(new String[]{"connect", readIp(), readSendingPort(), readListeningPort()});
    }

    /**
     * Method to read the IP for connection
     * @return ip to connection
     */
    public static String readIp() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("system@app: Enter the IP address for connection: ");
            InetAddress inetAddress = InetAddress.getByName(scanner.nextLine());

            return inetAddress.getHostAddress();

        } catch (Exception exception) {
            System.out.println("Connection error: " + exception.getMessage());
            return readIp();
        }
    }
    
    /**
     * Method to read the SENDNING PORT for connection
     * @return PORT
     */
    public static String readSendingPort() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("system@app: Enter the SENDING PORT: ");
        
        return scanner.nextLine();
    }
    
    /**
     * Method to read the LISTENING PORT for connection
     * @return PORT
     */
    public static String readListeningPort() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("system@app: Enter your program's LISTENING PORT: ");
        
        return scanner.nextLine();
    }
}