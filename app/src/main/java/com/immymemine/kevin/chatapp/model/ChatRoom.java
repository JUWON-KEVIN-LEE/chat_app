package com.immymemine.kevin.chatapp.model;

import java.util.Map;

/**
 * Created by quf93 on 2017-11-02.
 */

public class ChatRoom {
    public String id;
    public String title;
    public String last_message;
    public long last_message_time;
    public long message_count;
    public long created_time;

    public Map<String, Object> messages;
    public Map members;

    public ChatRoom() {
    }
}
