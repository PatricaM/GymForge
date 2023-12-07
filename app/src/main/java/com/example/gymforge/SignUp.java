package com.example.gymforge;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {



    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth;
    private EditText signupEmail, signupPass, signupName,signupPhone;
    private Button signupbtn;
    boolean passVisible;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.Register_Email);
        signupPass = findViewById(R.id.Register_pass);
        signupbtn = findViewById(R.id.btn_register);
        signupName = findViewById(R.id.Register_Name);
        signupPhone = findViewById(R.id.Register_Phone);

        signupPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX() >= signupPass.getRight() - signupPass.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=signupPass.getSelectionEnd();
                        if (passVisible){
                            signupPass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_off,0);
                            signupPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passVisible=false;
                        }else {
                            signupPass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.password_icon,0);
                            signupPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passVisible=true;
                        }
                        signupPass.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signupName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please enter your name",Toast.LENGTH_SHORT).show();
                }
                if (signupPhone.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please enter your phone",Toast.LENGTH_SHORT).show();
                }
                if (signupEmail.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please enter your email",Toast.LENGTH_SHORT).show();
                }
                if (signupPass.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please enter your password",Toast.LENGTH_SHORT).show();
                }
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

                if(!(signupName.getText().toString().isEmpty() && signupPhone.getText().toString().isEmpty() && signupEmail.getText().toString().isEmpty() && signupPass.getText().toString().isEmpty())){

                    firebaseAuth.createUserWithEmailAndPassword(signupEmail.getText().toString(),signupPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){

                                String uid=task.getResult().getUser().getUid();

                                Users user=new Users(uid,signupName.getText().toString(),signupPhone.getText().toString(),signupEmail.getText().toString(),signupPass.getText().toString(),0);

                                firebaseDatabase.getReference().child("users").child(uid).setValue(user);

                                Intent in=new Intent(SignUp.this,NavBotDialog.class);
                                startActivity(in);
                            }

                            else {
                                Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
    public void Login(View view){
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }
}
