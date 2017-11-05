package com.immymemine.kevin.chatapp.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.immymemine.kevin.chatapp.R;
import com.immymemine.kevin.chatapp.adapter.RoomListAdapter;
import com.immymemine.kevin.chatapp.model.ChatRoom;
import com.immymemine.kevin.chatapp.util.PreferenceUtil;

import java.util.ArrayList;
import java.util.List;

public class RoomListActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference userRef, roomRef;

    RecyclerView recyclerView;
    RoomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        initiateView();
        initiate();
    }

    List<ChatRoom> data = new ArrayList<>();
    private void initiate() {
        PreferenceUtil preferenceUtil = PreferenceUtil.getInstance(this);
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("users");
        roomRef = userRef.child(preferenceUtil.getValue("id")).child("chat_rooms");

        // Read from the database
        roomRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    Log.d("Snapshot", snapshot.toString());
                    ChatRoom chatRoom = snapshot.getValue(ChatRoom.class);
                    data.add(chatRoom);
                }
                adapter.setDataAndRefresh(data);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    public void showDialog(View view) {
        final EditText edittext = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("대화방 생성");
        builder.setMessage("대화방 제목을 입력해주세요.");
        builder.setView(edittext);
        builder.setPositiveButton("생성",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getApplicationContext(),edittext.getText().toString() ,Toast.LENGTH_LONG).show();
                        String title = edittext.getText().toString();
                        if(title == null || "".equals(title))
                            Toast.makeText(getApplicationContext(), "제목을 입력해주세요.", Toast.LENGTH_LONG).show();
                        else {
                            String roomKey = roomRef.push().getKey();

                            ChatRoom chatRoom = new ChatRoom();
                            chatRoom.id = roomKey;
                            chatRoom.title = title;
                            chatRoom.created_time = System.currentTimeMillis();
                            // chatRoom.members =

                            roomRef.child(roomKey).setValue(chatRoom);
                        }
                    }
                });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }

    private void initiateView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RoomListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
