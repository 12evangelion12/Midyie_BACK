/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 02/03/2023 20:49
 */

package fr.tom.midyie.model;

public class Account {

    private int id;
    private String username;
    private String privileges;
    private String password;

    public Account() {
    }

    public Account(int id, String username, String password, String privileges) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.privileges = privileges;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivileges() {
        return privileges;
    }

    @Override
    public String toString() {
        return "{user:" + username + ", privileges:" + privileges + "}";
    }
}
