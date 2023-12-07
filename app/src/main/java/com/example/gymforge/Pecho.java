package com.example.gymforge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class Pecho extends AppCompatActivity {

    private ViewFlipper vFlipper;
    private MaterialButton btBack, btNext, btHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pecho);

        //OCULTAR BARRA DE ESTADO//
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setUpButton();
        setUpViewFlipper();
    }
    private void setUpButton(){
        btBack=findViewById(R.id.btBack);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vFlipper.setInAnimation(getApplicationContext(),android.R.anim.slide_in_left);
                vFlipper.setOutAnimation(getApplicationContext(),android.R.anim.slide_out_right);
                vFlipper.showPrevious();
            }
        });

        btNext=findViewById(R.id.btNext);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vFlipper.setInAnimation(getApplicationContext(),R.anim.view_flipper_slide_in_right);
                vFlipper.setOutAnimation(getApplicationContext(),R.anim.view_flipper_slide_in_left);
                vFlipper.showNext();
            }
        });

        btHome=findViewById(R.id.btHome);
        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pecho.this, HomeFragment.class);
                startActivity(intent);
            }
        });
    }

    private void setUpViewFlipper(){
        vFlipper=findViewById(R.id.vFlipper);
    }
}