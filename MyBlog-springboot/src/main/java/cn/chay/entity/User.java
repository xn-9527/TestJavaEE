package cn.chay.entity;

import javax.persistence.Table;

/**
 * Created by sang on 17-3-10.
 */
@Table(name = "user")
public class User {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
