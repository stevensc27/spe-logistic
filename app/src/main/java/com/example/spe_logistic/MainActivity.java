package com.example.spe_logistic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//import android.widget.Button;

public class MainActivity extends AppCompatActivity  {

    //Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //login_button = findViewById(R.id.login_button);

        //login_button.setOnClickListener(this);


    }

    public void onClick(View v){

        Intent intent = null;

        switch (v.getId()){
            case R.id.login_button:

                break;
            case R.id.register_button:
                intent = new Intent(MainActivity.this,RegisterActivity.class);
                break;
        }

        startActivity(intent);
    }
}
