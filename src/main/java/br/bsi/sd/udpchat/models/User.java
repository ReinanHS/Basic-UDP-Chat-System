package br.bsi.sd.udpchat.models;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    public String username;
    public String ip;

    public User(String username, String ip) {
        this.username = username;
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(ip, user.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, ip);
    }
}
