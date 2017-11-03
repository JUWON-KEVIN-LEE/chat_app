package com.immymemine.kevin.chatapp.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.immymemine.kevin.chatapp.R;

public class RoomListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
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
                    }
                });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }

}
