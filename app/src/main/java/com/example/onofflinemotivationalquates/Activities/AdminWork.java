package com.example.onofflinemotivationalquates.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.onofflinemotivationalquates.SQliteFiles.DBHelper;
import com.example.onofflinemotivationalquates.databinding.ActivityAdminWorkBinding;

public class AdminWork extends AppCompatActivity {
   ActivityAdminWorkBinding bnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnd = ActivityAdminWorkBinding.inflate(getLayoutInflater());
        setContentView(bnd.getRoot());
        final DBHelper helper = new DBHelper(this);
        bnd.addLayout.setVisibility(View.VISIBLE);
        bnd.deleteLayout.setVisibility(View.GONE);
        bnd.updateLayout.setVisibility(View.GONE);
        bnd.insertQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bnd.name.getText().toString().trim().isEmpty()){
//                    for (int i=0; i<10; i++){
                        if(helper.insertOrder(bnd.name.getText().toString().trim(),bnd.quote.getText().toString().trim())){
                            Toast.makeText(getApplicationContext(), " Quote inserted successfully ", Toast.LENGTH_SHORT).show();
                        }
//                    }
                }
            }
        });
        bnd.insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bnd.addLayout.setVisibility(View.VISIBLE);
                bnd.deleteLayout.setVisibility(View.GONE);
                bnd.updateLayout.setVisibility(View.GONE);
            }
        });
        bnd.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bnd.addLayout.setVisibility(View.GONE);
                bnd.deleteLayout.setVisibility(View.VISIBLE);
                bnd.updateLayout.setVisibility(View.GONE);
            }
        });
        bnd.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bnd.addLayout.setVisibility(View.GONE);
                bnd.deleteLayout.setVisibility(View.GONE);
                bnd.updateLayout.setVisibility(View.VISIBLE);
            }
        });
    }
}