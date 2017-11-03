package com.immymemine.kevin.chatapp.model;

import java.util.List;

/**
 * Created by quf93 on 2017-11-02.
 */

public class User {
    public User(String id, String pw, String name, String gender, String birth, String phone_num, String email) {
        this.id = id; this.pw = pw; this.name = name; this.gender = gender; this.birth = birth;
        this.phoneNumber = phone_num; this.email = email;
    }

    String id;

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    String pw;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<ChatRoom> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<ChatRoom> roomList) {
        this.roomList = roomList;
    }

    public Friends getFriends() {
        return friends;
    }

    public void setFriends(Friends friends) {
        this.friends = friends;
    }

    String name;
    String gender;
    String birth;
    String phoneNumber;
    String email;
    String token;

    List<ChatRoom> roomList;
    Friends friends;
}
