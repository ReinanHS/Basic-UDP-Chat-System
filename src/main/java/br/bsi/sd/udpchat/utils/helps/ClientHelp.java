package br.bsi.sd.udpchat.utils.helps;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientHelp {
    public static String getHostAddress() {
        try {
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "";
        }
    }

    public static InetAddress getLocalHost() {
        try {
            return Inet4Address.getLocalHost();
        } catch (UnknownHostException e) {
            return null;
        }
    }
}
