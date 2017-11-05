package com.immymemine.kevin.chatapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.immymemine.kevin.chatapp.R;
import com.immymemine.kevin.chatapp.util.DialogUtil;
import com.immymemine.kevin.chatapp.util.PreferenceUtil;

public class SigninActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    TextView textView2;
    EditText edittextId2, edittextPw2;
    Button signupButton2, signinButton;
    PreferenceUtil preferenceUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        preferenceUtil = PreferenceUtil.getInstance(this);
        mAuth = FirebaseAuth.getInstance();
        if(preferenceUtil.getValue("auto_login").equals("true")) {
            Intent intent = new Intent(SigninActivity.this, RoomListActivity.class);
            startActivity(intent);
        }
        initiateView();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
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

    public void signin(View view) {
        final String email = edittextId2.getText().toString();
        final String password = edittextPw2.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser fUser = mAuth.getCurrentUser();
                            if(fUser.isEmailVerified()) {
                                // 로그인이 되면 사용자 정보를 로컬에 저장해 둔다
                                // 1. SharedPreferences 생성
                                // SharedPreferences sPref = getSharedPreferences("ChatApp", Context.MODE_PRIVATE);
                                // 2. Editor 생성
                                // SharedPreferences.Editor editor = sPref.edit();
                                // 3. key - value 저장
                                // editor.putString("id", fUser.getUid());
                                preferenceUtil.setValue("id", fUser.getUid());
                                preferenceUtil.setValue("email", email);
                                preferenceUtil.setValue("pw", password);
                                preferenceUtil.setValue("auto_login", "true");

                                Intent intent = new Intent(SigninActivity.this, RoomListActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                DialogUtil.showDialog("이메일 인증을 진행해주세요.", SigninActivity.this, false);
                            }
                        }
                    }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                DialogUtil.showDialog("Error : " + e.toString(), SigninActivity.this, false);
            }
        });
    }
}
