package com.immymemine.kevin.chatapp.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.immymemine.kevin.chatapp.R;
import com.immymemine.kevin.chatapp.model.User;
import com.immymemine.kevin.chatapp.util.DialogUtil;
import com.immymemine.kevin.chatapp.util.ValidationUtil;

public class SignupActivity extends AppCompatActivity {
    EditText edittextRePw, edittextPw, edittextName, edittextPhone, edittextBirth, edittextEmail;
    TextView warn1, warn2, warn3;
    RadioButton radioButton2, radioButton;
    Button signupButton;

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference userRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("users");

        initiateView();
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if( !"".equals(charSequence) &&
                    !"".equals(edittextEmail.getText().toString()) &&
                    !"".equals(edittextPw.getText().toString()) &&
                    !"".equals(edittextRePw.getText().toString()) ) {
                signupButton.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void initiateView() {
        edittextEmail = findViewById(R.id.edittext_email);
        edittextEmail.addTextChangedListener(watcher);

        edittextPw = findViewById(R.id.edittext_pw);
        edittextPw.addTextChangedListener(watcher);

        edittextRePw = findViewById(R.id.edittext_repw);
        edittextRePw.addTextChangedListener(watcher);

        edittextName = findViewById(R.id.edittext_name);
        edittextName.addTextChangedListener(watcher);

        edittextBirth = findViewById(R.id.edittext_birth);
        edittextPhone = findViewById(R.id.edittext_phone);


        radioButton2 = findViewById(R.id.radioButton2); // 남
        radioButton = findViewById(R.id.radioButton); // 여

        // 회원가입 버튼
        signupButton = findViewById(R.id.signup_button);

        warn1 = findViewById(R.id.warn1);
        warn2 = findViewById(R.id.warn2);
        warn3 = findViewById(R.id.warn3);
    }



    public void signup(View view) {
        if(!setUser())
            return;

        mAuth.createUserWithEmailAndPassword(email, pw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("task","successful");
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser fUser = mAuth.getCurrentUser();
                            fUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    DialogUtil.showDialog("이메일을 발송하였습니다. 인증해주세요.", SignupActivity.this, true);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("onFailure",e.toString());
                                }
                            });
                            userRef.child(fUser.getUid()).setValue(user);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        DialogUtil.showDialog(e.toString(), SignupActivity.this, false);
                    }
                });
    }

    User user;
    String email = "";
    String pw = "";
    private boolean setUser() {
        email = edittextEmail.getText().toString();
        if(!ValidationUtil.isValidEmail(email)) {
            warn1.setVisibility(View.VISIBLE);
            signupButton.setEnabled(false);
            return false;
        } else {
            warn1.setVisibility(View.INVISIBLE);
        }

        pw = edittextPw.getText().toString();
        String repw = edittextRePw.getText().toString();
        if(!pw.equals(repw) || !ValidationUtil.isValidPassword(pw)) {
            warn2.setVisibility(View.VISIBLE);
            signupButton.setEnabled(false);
            return false;
        } else {
            warn2.setVisibility(View.INVISIBLE);
        }

        String name = edittextName.getText().toString();
        if(!ValidationUtil.isValidName(name)) { // null 이거나 name 형식이 잘못되었다면
            warn3.setVisibility(View.VISIBLE);
            signupButton.setEnabled(false);
            return false;
        } else {
            warn3.setVisibility(View.INVISIBLE);
        }

        String gender = "male";
        if(radioButton.isChecked())
            gender = "female";

        user = new User(email, pw, name, gender);

        String phone_num = edittextPhone.getText().toString();
        if(!(phone_num == null || "".equals(phone_num))) {
            if(!ValidationUtil.isValidPhoneNumber(phone_num)) {
                Toast.makeText(this, "Check Phone Number", Toast.LENGTH_LONG).show();
                return false;
            } else
                user.phoneNumber = phone_num;
        }

        String birth = edittextBirth.getText().toString();
        if(!((birth == null) || "".equals(birth))) {
            if(!ValidationUtil.isValidBirth(birth)) {
                Toast.makeText(this, "please input yyyyMMdd format", Toast.LENGTH_LONG).show();
                return false;
            } else
                user.birth = birth;
        }

        user.token = FirebaseInstanceId.getInstance().getToken();

        return true;
    }
}

