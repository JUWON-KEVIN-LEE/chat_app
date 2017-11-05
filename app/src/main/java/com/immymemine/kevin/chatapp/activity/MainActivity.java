package com.immymemine.kevin.chatapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.immymemine.kevin.chatapp.R;
import com.immymemine.kevin.chatapp.adapter.MessageListAdapter;
import com.immymemine.kevin.chatapp.model.Message;
import com.immymemine.kevin.chatapp.util.PreferenceUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText editText;

    MessageListAdapter adapter;

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference messageRef, roomRef;

    List<Message> data = new ArrayList<>();
    String uId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String roomId = intent.getStringExtra("id");

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        uId = PreferenceUtil.getInstance(this).getValue("id");
        roomRef = database.getReference("users").child(uId).child("chat_rooms").child(roomId);
        messageRef = database.getReference("users").child(uId).child("chat_rooms").child(roomId).child("messages");

        initiateView();
        initiate();
    }

    private void initiate() {
        messageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data.clear();
                for(DataSnapshot item : dataSnapshot.getChildren()) {
                    Message message = item.getValue(Message.class);
                    message.isMine = message.sender.equals(uId);
                    data.add(message);
                }
                adapter.setDataAndRefresh(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    SimpleDateFormat sdf = new SimpleDateFormat("MM:ss");
    public void send(View view) {
        String msg = editText.getText().toString();
        editText.setText("");

        if(msg == null || "".equals(msg))
            return;

        String mId = messageRef.push().getKey();

        Message message = new Message();
        message.id = mId;
        message.message = msg;
        message.sended_time = sdf.format(new Date(System.currentTimeMillis()));
        message.sender = uId;
        message.length = msg.length();
        message.read_count = 1;

        messageRef.child(mId).setValue(message);

        roomRef.child("last_message").removeValue();
        roomRef.child("last_message").setValue(msg);
    }


    private void initiateView() {
        editText = findViewById(R.id.editText);
        recyclerView = findViewById(R.id.messagesView);

        adapter = new MessageListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
