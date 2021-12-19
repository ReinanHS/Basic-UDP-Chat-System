/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.bsi.sd.udpchat.interfaces;

import java.net.InetAddress;

/**
 *
 * @author Eduardo
 */
public interface IUser {
    public String getName();
    public InetAddress getIp();
    public int getListeningPort();
}
