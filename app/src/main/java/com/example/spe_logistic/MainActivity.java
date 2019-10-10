package com.example.spe_logistic;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spe_logistic.utilities.Utilities;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText user_login, password_login;
    Button login_button, register_button;

    SQLiteConnectionHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_login     = (EditText) findViewById(R.id.user_login);
        password_login = (EditText) findViewById(R.id.password_login);

        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(this);

        register_button = findViewById(R.id.register_button);
        register_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.login_button:
                Log.i("LOG", "Entrar");
                if (userValidation()) {
                    intent = new Intent(MainActivity.this, HomeActivity.class);
                }
                break;
            case R.id.register_button:
                Log.i("LOG", "Registrar");
                intent = new Intent(MainActivity.this, RegisterActivity.class);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }

    private boolean userValidation() {

        String user = user_login.getText().toString();
        String pass = password_login.getText().toString();

        if (user.length() == 0 || pass.length() == 0){
            Toast.makeText(this,"Deber ingresar los datos",Toast.LENGTH_LONG).show();
            return false;
        }else{
            con = new SQLiteConnectionHelper(getApplicationContext(),"SPEDB",null,1);
            SQLiteDatabase db = con.getReadableDatabase();

            Cursor cursor = db.rawQuery("SELECT id FROM "+ Utilities.CLIENTES+" WHERE "+Utilities.CLIENTES_NIT+"='"+user+"' AND "+Utilities.CLIENTES_PASSWORD+"='"+pass+"'",null);

            if(cursor.moveToFirst()){
                return true;
            }else {
                Toast.makeText(this,"NIT o contraseña incorrectos",Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }
}
