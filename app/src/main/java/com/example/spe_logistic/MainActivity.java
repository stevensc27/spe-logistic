package com.example.spe_logistic;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
//import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button login_button, register_button;

    SQLiteConnectionHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(this);

        register_button = findViewById(R.id.register_button);
        register_button.setOnClickListener(this);


    }

    /*public void onClick(View v){

        Intent intent = null;

        switch (v.getId()){
            case R.id.login_button:
                userValidation();
                break;
            case R.id.register_button:
                intent = new Intent(MainActivity.this,RegisterActivity.class);
                break;
        }

        startActivity(intent);
    }*/


    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()){
            case R.id.login_button:
                Log.i("LOG","Entrar");
                if (userValidation()) {
                    intent = new Intent(MainActivity.this, HomeActivity.class);
                }
                break;
            case R.id.register_button:
                Log.i("LOG","Registrar");
                intent = new Intent(MainActivity.this,RegisterActivity.class);
                break;
        }
        Log.i("LOG","OnClick");

        startActivity(intent);
    }

    private boolean userValidation() {
        con = new SQLiteConnectionHelper(getApplicationContext(),"SPEDB",null,1);
        SQLiteDatabase db = con.getReadableDatabase();
        //Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilities.CLIENTES+" WHERE "+Utilities.CLIENTES_NIT+" = "+ +" AND "+Utilities.CLIENTES_PASSWORD+" = "+,null);
        return true;
    }
}
