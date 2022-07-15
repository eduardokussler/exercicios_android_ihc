package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText tx1 = (EditText) findViewById(R.id.txt1);
        EditText tx2 = (EditText) findViewById(R.id.txt2);;
        Button btn = (Button) findViewById(R.id.button);
        TextView txtView = (TextView) findViewById(R.id.result);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                long num1 = Long.parseLong(tx1.getText().toString());
                long num2 = Long.parseLong(tx2.getText().toString());
                txtView.setText(Long.toString(num1 + num2));
            }
        });
    }
}