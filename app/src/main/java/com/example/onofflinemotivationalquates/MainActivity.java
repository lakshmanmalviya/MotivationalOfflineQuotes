package com.example.onofflinemotivationalquates;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.onofflinemotivationalquates.Adapters.QuoteAdapter;
import com.example.onofflinemotivationalquates.Modals.QuoteModal;
import com.example.onofflinemotivationalquates.SQliteFiles.DBHelper;
import com.example.onofflinemotivationalquates.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding bnd;
    ArrayList<QuoteModal> list;
    QuoteAdapter adapter;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnd = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bnd.getRoot());
        final DBHelper helper = new DBHelper(this);
        list = helper.getQuotes();
        Collections.shuffle(list);
        adapter = new QuoteAdapter(list,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        bnd.quoteRec.setAdapter(adapter);
        bnd.quoteRec.setLayoutManager(layoutManager);
//        String html  = "<p style='color:green'> <b>Hello</b> sab kaam kar rha hai </p>";
                 //How to add CSS & HTML to an android tag
//        bnd.totalQuote.setText(HtmlCompat.fromHtml(html,1));
        bnd.totalQuote.setText("Total Quotes are "+ helper.totalQuoate());
    }
}