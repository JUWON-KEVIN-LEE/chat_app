package com.immymemine.kevin.chatapp.model;

import java.util.List;

/**
 * Created by quf93 on 2017-11-02.
 */

public class User {
    public User(String email, String pw, String name, String gender) {
        this.email = email; this.pw = pw; this.name = name; this.gender = gender;
    }

    public String pw;
    public String name;
    public String gender;
    public String birth;
    public String phoneNumber;
    public String email;
    public String token;

    public List<ChatRoom> roomList;
    public List<User> friends;
}
