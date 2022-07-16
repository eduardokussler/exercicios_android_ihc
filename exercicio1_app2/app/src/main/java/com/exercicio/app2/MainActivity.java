package com.exercicio.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessageToNextActivity(View view) {
        EditText txtMessage = (EditText) findViewById(R.id.txtMessage);
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("message", txtMessage.getText().toString());
        startActivity(i);
    }
}