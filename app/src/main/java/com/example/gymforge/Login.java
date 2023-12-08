package com.example.gymforge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private EditText loginEmail, loginPass;
    private Button loginbtn;
    boolean passVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        loginEmail = findViewById(R.id.Login_Email);
        loginPass = findViewById(R.id.Login_Pass);
        loginbtn = findViewById(R.id.btn_login);

        loginPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX() >= loginPass.getRight() - loginPass.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=loginPass.getSelectionEnd();
                        if (passVisible){
                            loginPass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_off,0);
                            loginPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passVisible=false;
                        }else {
                            loginPass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.password_icon,0);
                            loginPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passVisible=true;
                        }
                        loginPass.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (loginEmail.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter your Email",Toast.LENGTH_SHORT).show();
                }
                if (loginPass.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter your Password",Toast.LENGTH_SHORT).show();
                }
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                if (!(loginEmail.getText().toString().isEmpty() && loginPass.getText().toString().isEmpty())){
                    firebaseAuth.signInWithEmailAndPassword(loginEmail.getText().toString(),loginPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                checkUserRole();


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

    private void checkUserRole() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference userRef = db.collection("Users").document(userId);

        userRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String userRole = documentSnapshot.getString("role");

                            // Verificar el rol y permitir o denegar acceso
                            if ("admin".equals(userRole)) {
                                Intent in = new Intent(Login.this,DietasActivity.class);
                                startActivity(in);
                                // El usuario tiene permisos de administrador
                            }else
                            if ("couch".equals(userRole)) {
                                Intent in = new Intent(Login.this,Add_Diets.class);
                                startActivity(in);
                                // El usuario tiene permisos de administrador
                            }
                            else {
                                Intent in = new Intent(Login.this,NavBotDialog.class);
                                startActivity(in);
                                // El usuario no tiene permisos de administrador
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Manejar errores al obtener el rol
                        Toast.makeText(getApplicationContext(), "Error al obtener el rol del usuario", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void Forgot(View view){
        Intent forgot = new Intent(this, forgotpassword.class);
        startActivity(forgot);
    }
    public void SingUP(View view){
        Intent Sign = new Intent(this, SignUp.class);
        startActivity(Sign);
    }
    //public void Home(View view){
       // Intent Homee = new Intent(this, NavBotDialog.class);
       // startActivity(Homee);
    //}
}