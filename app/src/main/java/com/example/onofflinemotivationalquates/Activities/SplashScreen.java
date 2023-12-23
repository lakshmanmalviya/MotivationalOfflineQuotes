package com.example.onofflinemotivationalquates.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.onofflinemotivationalquates.MainActivity;
import com.example.onofflinemotivationalquates.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ImageView starImageView = findViewById(R.id.blink_star);
        ImageView starImageView2 = findViewById(R.id.blink_star2);
        Animation blinkAnimation = AnimationUtils.loadAnimation(this, R.anim.blink_animation);
        starImageView.setVisibility(View.VISIBLE);
        starImageView.startAnimation(blinkAnimation);
        starImageView2.startAnimation(blinkAnimation);
        new Thread(){
            public void run(){
                try{
                   sleep(5000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                   startActivity(new Intent(getApplicationContext(),AllPanel.class));
                   finish();
                }
            }

        }.start();
    }
}