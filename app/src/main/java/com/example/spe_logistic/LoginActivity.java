package com.example.spe_logistic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spe_logistic.utilities.Utilities;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText user_login, password_login;
    Button   login_button, register_button;
    Integer  user_id;

    SQLiteConnectionHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                if (userValidation()) {
                    intent = new Intent(LoginActivity.this, HomeActivity.class);
                    savePreferences();
                }
                break;
            case R.id.register_button:
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }

    private void savePreferences() {
        /*SharedPreferences mPreferences;
        SharedPreferences.Editor mEditor;

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        mEditor.putInt()*/


        SharedPreferences preferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("user_id",user_id);
        editor.commit();





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

            String[] parameters = {user,pass};
            String[] field      = {Utilities.CLIENTES_ID};

            Cursor cursor = db.query(Utilities.CLIENTES,field,Utilities.CLIENTES_NIT+"=? AND "+Utilities.CLIENTES_PASSWORD+"=?",parameters,null,null,null);

            if(cursor.moveToFirst()){
                user_id =  cursor.getInt(0);
                Log.i("APP","USER IN: "+cursor.getString(0));
                return true;
            }else {
                Toast.makeText(this,"NIT o contraseña incorrectos",Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }
}
