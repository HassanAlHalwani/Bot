package com.example.bot;

public class DatabaseUsers {

    private String password;
    private int id;


    public int getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }


    public void setId(int id) {
        this.id = id;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public DatabaseUsers(int id, String password) {
        this.id = id;
        this.password = password;
    }
}
