package com.example.apodapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
String info ;
TextView textView ;
Typewriter tw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tw = findViewById(R.id.tw);
        tw.setText("");
        tw.setCharacterDelay(150);
        tw.animateText("About");
        Intent intent = getIntent();
        info = intent.getStringExtra("inf");
        textView = findViewById(R.id.textView);
        textView.setText(info);

    }
}