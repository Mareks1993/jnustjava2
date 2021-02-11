package com.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Interval extends AppCompatActivity {

    Button b;
    EditText et_walk;
    EditText et_run;
    TextView walk;
    TextView run;
    int val = 0;
    int valRun = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval);
        b = (Button) findViewById(R.id.bclick);
        et_walk = (EditText) findViewById(R.id.et_walk);
        et_run = (EditText) findViewById(R.id.et_run);
        walk = (TextView) findViewById(R.id.walk_tv);
        run = (TextView) findViewById(R.id.run_tv);

        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String value = et_walk.getText().toString();
                val = Integer.parseInt(value);
                String value2 = et_run.getText().toString();
                valRun = Integer.parseInt(value2);
                walk.setText(value);
                run.setText(value2);

                Intent intent = new Intent(Interval.this,MainActivity.class);
                intent.putExtra("keywalk",val);
                intent.putExtra("keyRun",valRun);
                startActivity(intent);
            }
        });
    }

}