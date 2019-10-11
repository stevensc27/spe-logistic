package com.example.spe_logistic;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.spe_logistic.utilities.Utilities;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    SQLiteConnectionHelper con;

    // activity vars

    EditText name;
    EditText nit;
    EditText password;
    Spinner  city;
    Spinner  road_type;
    EditText number1;
    EditText chart1;
    Spinner  orientation_road1;
    EditText number2;
    EditText chart2;
    Spinner  orientation_road2;
    EditText number3;

    Button   save_button;

    // aux vars
    String[] road_type_id;
    String[] city_id;
    String[] orientation_road1_id;
    String[] orientation_road2_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name              = (EditText) findViewById(R.id.name);
        nit               = (EditText) findViewById(R.id.nit);
        password          = (EditText) findViewById(R.id.password);
        city              = (Spinner)  findViewById(R.id.city);
        road_type         = (Spinner)  findViewById(R.id.road_type);
        number1           = (EditText) findViewById(R.id.number1);
        chart1            = (EditText) findViewById(R.id.chart1);
        orientation_road1 = (Spinner)  findViewById(R.id.orientation_road1);
        number2           = (EditText) findViewById(R.id.number2);
        chart2            = (EditText) findViewById(R.id.chart2);
        orientation_road2 = (Spinner)  findViewById(R.id.orientation_road2);
        number3           = (EditText) findViewById(R.id.number3);

        save_button = findViewById(R.id.save_button);
        save_button.setOnClickListener(this);


        // spinners
        ArrayAdapter<CharSequence> adapter_city = ArrayAdapter.createFromResource(this,R.array.city_options,android.R.layout.simple_spinner_item);
        city.setAdapter(adapter_city);
        ArrayAdapter<CharSequence> adapter_address_options = ArrayAdapter.createFromResource(this,R.array.address_options,android.R.layout.simple_spinner_item);
        road_type.setAdapter(adapter_address_options);
        ArrayAdapter<CharSequence> orientation_road_options = ArrayAdapter.createFromResource(this,R.array.orientation_road_options,android.R.layout.simple_spinner_item);
        orientation_road1.setAdapter(orientation_road_options);
        ArrayAdapter<CharSequence> orientation_road_options2 = ArrayAdapter.createFromResource(this,R.array.orientation_road_options,android.R.layout.simple_spinner_item);
        orientation_road2.setAdapter(orientation_road_options2);

    }


    @Override
    public void onClick(View v) {
        if (userRegister()){
            Toast.makeText(this,"NIT registrado correctamente",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private boolean userRegister() {
        con = new SQLiteConnectionHelper(getApplicationContext(),"SPEDB",null,1);
        SQLiteDatabase db = con.getWritableDatabase();

        ContentValues values_cliente   = new ContentValues();
        ContentValues values_direccion = new ContentValues();

        road_type_id         = road_type.getSelectedItem().toString().split("-");
        city_id              = city.getSelectedItem().toString().split("-");
        orientation_road1_id = orientation_road1.getSelectedItem().toString().split("-");
        orientation_road2_id = orientation_road2.getSelectedItem().toString().split("-");

        if (addressValidation()) {

            values_direccion.put(Utilities.DIRECCIONES_TIPO_VIA_ID, road_type_id[0]);
            values_direccion.put(Utilities.DIRECCIONES_NUMERO_1, number1.getText().toString());
            values_direccion.put(Utilities.DIRECCIONES_LETRA_1, chart1.getText().toString());
            values_direccion.put(Utilities.DIRECCIONES_ORIENTACION_1_ID, orientation_road1_id[0]);
            values_direccion.put(Utilities.DIRECCIONES_NUMERO_2, number2.getText().toString());
            values_direccion.put(Utilities.DIRECCIONES_LETRA_2, chart2.getText().toString());
            values_direccion.put(Utilities.DIRECCIONES_ORIENTACION_2_ID, orientation_road2_id[0]);
            values_direccion.put(Utilities.DIRECCIONES_NUMERO_3, number3.getText().toString());
            values_direccion.put(Utilities.DIRECCIONES_CIUDAD_ID, city_id[0]);

            Integer direccion_id = (int) (long) db.insert(Utilities.DIRECCIONES, Utilities.DIRECCIONES_ID, values_direccion);

            if (userValidation()){
                values_cliente.put(Utilities.CLIENTES_RAZON_SOCIAL,name.getText().toString());
                values_cliente.put(Utilities.CLIENTES_NIT,nit.getText().toString());
                values_cliente.put(Utilities.CLIENTES_PASSWORD,password.getText().toString());
                values_cliente.put(Utilities.CLIENTES_DIRECCION_ID,direccion_id);

                Long cliente_id = db.insert(Utilities.CLIENTES, Utilities.CLIENTES_ID, values_cliente);

                return true;

            }else {
                return false;
            }
        }else {
            return false;
        }

    }


    private boolean addressValidation() {
        if (number1.getText().toString().length() == 0 || number2.getText().toString().length() == 0){
            Toast.makeText(this,"Ingrese una dirección válida",Toast.LENGTH_LONG).show();
            return false;
        }

        if(!road_type_id[0].matches("\\d+(?:\\.\\d+)?")){
            Toast.makeText(this,"Ingrese tipo de vía",Toast.LENGTH_LONG).show();
            return false;
        }

        if(!city_id[0].matches("\\d+(?:\\.\\d+)?")){
            Toast.makeText(this,"Ingrese ciudad",Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }


    private boolean userValidation() {
        String name_validate     = name.getText().toString();
        String nit_validate      = nit.getText().toString();
        String password_valdiate = password.getText().toString();
        if (name_validate.length() == 0 || nit_validate.length() == 0 || password_valdiate.length() == 0){
            Toast.makeText(this,"Ingrese todos los datos",Toast.LENGTH_LONG).show();
            return false;
        }

        con = new SQLiteConnectionHelper(getApplicationContext(),"SPEDB",null,1);
        SQLiteDatabase db = con.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT id FROM "+ Utilities.CLIENTES+" WHERE "+Utilities.CLIENTES_NIT+"='"+nit_validate+"'",null);

        if(cursor.moveToFirst()){
            Toast.makeText(this,"NIT ya existe",Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
