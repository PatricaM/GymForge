package com.example.gymforge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation up, down;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.logo_app);
        up= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation_up);
        imageView.setAnimation(up);

        TextView textView = findViewById(R.id.app_name);
        down = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation_up);
        textView.setAnimation(down);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        },3500);
    }

}