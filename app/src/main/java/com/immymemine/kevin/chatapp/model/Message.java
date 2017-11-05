package com.immymemine.kevin.chatapp.model;

/**
 * Created by quf93 on 2017-11-02.
 */

public class Message {
    public String id;
    public String message;
    public String sender;
    public String type;
    public boolean isMine;
    public String sended_time;
    public int length;
    public int total_count;
    public int read_count;

    @Override
    public boolean equals(Object obj) {
        return this.id.equals( ((Message)obj).id );
    }
}
