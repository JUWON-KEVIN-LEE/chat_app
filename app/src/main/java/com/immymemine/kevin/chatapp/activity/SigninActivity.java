package com.immymemine.kevin.chatapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.immymemine.kevin.chatapp.R;

public class SigninActivity extends AppCompatActivity {

    TextView textView2;
    EditText edittextId2, edittextPw2;
    Button signupButton2, signinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        initiateView();
    }

    private void initiateView() {
        textView2 = findViewById(R.id.textView2);
        edittextId2 = findViewById(R.id.edittext_id2);
        edittextPw2 = findViewById(R.id.edittext_pw2);
        signupButton2 = findViewById(R.id.signup_button2);
        signupButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        signinButton = findViewById(R.id.signin_button);
    }
}
