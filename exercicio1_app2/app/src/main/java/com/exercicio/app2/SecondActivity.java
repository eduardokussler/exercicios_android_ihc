package com.exercicio.app2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        String message = getIntent().getStringExtra("message");
        TextView txt = (TextView) findViewById(R.id.txtMessageReceived);
        txt.setText(message);
    }
}
