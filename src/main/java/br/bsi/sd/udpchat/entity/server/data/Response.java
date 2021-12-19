
package br.bsi.sd.udpchat.entity.server.data;


import br.bsi.sd.udpchat.interfaces.IResponse;
import br.bsi.sd.udpchat.interfaces.IUser;

import java.io.*;
import java.util.ArrayList;

public class Response implements IResponse, Serializable {
    private ArrayList<IUser> userList;

    public Response(ArrayList<IUser> userList) {
        this.userList = userList;
    }
    
     public Response(byte[] bytes) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        try (ObjectInput in = new ObjectInputStream(bis)) {
            Response response = (Response) in.readObject();
            this.userList = response.userList;
        }
    }

    public void setUserList(ArrayList<IUser> userList) {
        this.userList = userList;
    }

    @Override
    public ArrayList<IUser> getUserList() {
        return this.userList;
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
