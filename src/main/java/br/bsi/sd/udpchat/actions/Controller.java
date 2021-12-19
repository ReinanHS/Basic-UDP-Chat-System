package br.bsi.sd.udpchat.actions;

import java.net.SocketException;

public interface Controller {
    void command();
    void command(String[] args) throws SocketException, Exception;
}
