package br.bsi.sd.udpchat.entity.server.data;

import br.bsi.sd.udpchat.enums.RotaEnum;
import br.bsi.sd.udpchat.interfaces.IRequest;
import br.bsi.sd.udpchat.interfaces.IUser;

import java.io.*;

public class Request implements IRequest, Serializable {
    
    private RotaEnum action;
    private IUser user;

    public Request(RotaEnum action, IUser client) {
        this.action = action;
        this.user = client;
    }

    @Override
    public RotaEnum getAction() {
        return action;
    }

    public void setAction(RotaEnum action) {
        this.action = action;
    }

    @Override
    public IUser getUser() {
        return user;
    }

    public void setUser(IUser user) {
        this.user = user;
    }

    public Request(byte[] bytes) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        try (ObjectInput in = new ObjectInputStream(bis)) {
            Request request = (Request) in.readObject();
            this.action = request.action;
            this.user = request.user;
        }
    }

    public byte[] getBytes() throws Exception {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ObjectOutputStream out = null;
            out = new ObjectOutputStream(bos);
            out.writeObject(this);
            out.flush();
            return bos.toByteArray();
        }
    }
}
