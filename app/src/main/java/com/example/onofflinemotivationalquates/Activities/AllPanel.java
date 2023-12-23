package com.example.onofflinemotivationalquates.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.onofflinemotivationalquates.MainActivity;
import com.example.onofflinemotivationalquates.R;
import com.example.onofflinemotivationalquates.databinding.ActivityAllPanelBinding;

public class AllPanel extends AppCompatActivity {
   ActivityAllPanelBinding bnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnd = ActivityAllPanelBinding.inflate(getLayoutInflater());
        setContentView(bnd.getRoot());
        bnd.admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminWork.class));
            }
        });
        bnd.user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}