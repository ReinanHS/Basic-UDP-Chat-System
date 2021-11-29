package br.bsi.sd.udpchat.models;

import java.io.*;
import java.util.Objects;

public class Message implements Serializable {
    public User user;
    public String text;

    public Message(User user, String text) {
        this.user = user;
        this.text = text;
    }

    public Message(byte[] bytes) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        try (ObjectInput in = new ObjectInputStream(bis)) {
            Message message = (Message) in.readObject();
            this.user = message.user;
            this.text = message.text;
        }
    }

    @Override
    public String toString() {
        return this.user.username + "@" + this.user.ip + ": " + this.text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(user, message.user) && Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, text);
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
