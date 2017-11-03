package com.immymemine.kevin.chatapp.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.immymemine.kevin.chatapp.R;
import com.immymemine.kevin.chatapp.model.User;

public class SignupActivity extends AppCompatActivity {
    EditText edittextId, edittextPw, edittextName, edittextPhone, edittextBirth, edittextEmail;
    RadioButton radioButton2, radioButton;
    Button signupButton;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        initiateView();
    }

    private void initiateView() {
        edittextId = findViewById(R.id.edittext_id);
        edittextPw = findViewById(R.id.edittext_pw);
        edittextName = findViewById(R.id.edittext_name);
        edittextBirth = findViewById(R.id.edittext_birth);
        edittextPhone = findViewById(R.id.edittext_phone);
        edittextEmail = findViewById(R.id.edittext_email);

        radioButton2 = findViewById(R.id.radioButton2); // 남
        radioButton = findViewById(R.id.radioButton); // 여

        // 회원가입 버튼
        signupButton = findViewById(R.id.signup_button);
    }

    public void signup(View view) {
        setUser();

        mAuth.createUserWithEmailAndPassword(id, pw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {

                        }

                        // ...
                    }
                });
    }

    User user;
    String id = "";
    String pw = "";
    private void setUser() {
        id = edittextId.getText().toString();
        if(!isValid(id)) {
            Toast.makeText(this, "아이디를 입력해주세요", Toast.LENGTH_LONG).show();
            return;
        }

        pw = edittextPw.getText().toString();
        if(!isValid(pw)) {
            Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_LONG).show();
            return;
        }

        String name = edittextName.getText().toString();
        if(!isValid(name)) {
            Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_LONG).show();
            return;
        }

        String gender = "";
        if(radioButton2.isChecked()) {
            gender = "남";
        } else {
            gender = "여";
        }

        String phone_num = edittextPhone.getText().toString();
        if(!isValid(phone_num)) {
            Toast.makeText(this, "휴대폰 번호를 입력해주세요", Toast.LENGTH_LONG).show();
            return;
        }

        String birth = edittextBirth.getText().toString();
        if(!isValid(birth)) {
            Toast.makeText(this, "생년월일을 입력해주세요", Toast.LENGTH_LONG).show();
            return;
        }

        String email = edittextEmail.getText().toString();
        if(!isValid(email)) {
            Toast.makeText(this, "email 을 입력해주세요", Toast.LENGTH_LONG).show();
            return;
        }

        user = new User(id, pw, name, gender,  birth, phone_num, email);
    }

    private boolean isValid(String s) {
        if(s == null || "".equals(s))
            return false;
        else
            return true;
    }
}
