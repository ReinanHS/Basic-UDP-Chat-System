/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.bsi.sd.udpchat.interfaces;


import br.bsi.sd.udpchat.enums.RotaEnum;

/**
 *
 * @author Eduardo
 */
public interface IRequest {
    public RotaEnum getAction();
    public IUser getUser();
}
