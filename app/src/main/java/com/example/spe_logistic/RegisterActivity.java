package com.example.spe_logistic;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.spe_logistic.utilities.Utilities;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    SQLiteConnectionHelper con;

    // activity vars

    private EditText name;
    private EditText nit;
    private EditText password;
    private EditText address;
    private Spinner city;
    private Spinner road_type;
    /*private EditText number1;
    private EditText chart1;
    private Spinner  orientation_road1;
    private EditText number2;
    private EditText chart2;
    private Spinner  orientation_road2;
    private EditText number3;*/

    private Button save_button;

    // aux vars
    private String[] road_type_id;
    private String[] city_id;
    private ArrayList<String> citys;
    private ArrayAdapter<String> adapter_city;
    /*private String[] orientation_road1_id;
    private String[] orientation_road2_id;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        nit = findViewById(R.id.nit);
        password = findViewById(R.id.password);
        city = findViewById(R.id.city);
        address = findViewById(R.id.address);
        /*road_type         = (Spinner)  findViewById(R.id.road_type);
        number1           = (EditText) findViewById(R.id.number1);
        chart1            = (EditText) findViewById(R.id.chart1);
        orientation_road1 = (Spinner)  findViewById(R.id.orientation_road1);
        number2           = (EditText) findViewById(R.id.number2);
        chart2            = (EditText) findViewById(R.id.chart2);
        orientation_road2 = (Spinner)  findViewById(R.id.orientation_road2);
        number3           = (EditText) findViewById(R.id.number3);*/

        save_button = findViewById(R.id.save_button);
        save_button.setOnClickListener(this);


        // spinners
        getCitys();
        adapter_city = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, citys);
        city.setAdapter(adapter_city);

        /*road_type.setAdapter(adapter_address_options);
        ArrayAdapter<CharSequence> orientation_road_options = ArrayAdapter.createFromResource(this,R.array.orientation_road_options,android.R.layout.simple_spinner_item);
        orientation_road1.setAdapter(orientation_road_options);
        ArrayAdapter<CharSequence> orientation_road_options2 = ArrayAdapter.createFromResource(this,R.array.orientation_road_options,android.R.layout.simple_spinner_item);
        orientation_road2.setAdapter(orientation_road_options2);*/

    }

    private void getCitys() {
        con = new SQLiteConnectionHelper(getApplicationContext(), "SPEDB", null, 1);
        SQLiteDatabase db = con.getReadableDatabase();

        citys = new ArrayList<String>();

        String queryCitys = "SELECT     id,nombre " +
                "FROM       ciudades " +
                "ORDER BY   nombre";

        Cursor cursor = db.rawQuery(queryCitys, null);

        while (cursor.moveToNext()) {
            String city;
            city = cursor.getString(1) + " " + cursor.getString(0);
            citys.add(city);
        }
    }


    @Override
    public void onClick(View v) {
        if (userRegister()) {
            Toast.makeText(this, "NIT registrado correctamente", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private boolean userRegister() {
        con = new SQLiteConnectionHelper(getApplicationContext(), "SPEDB", null, 1);
        SQLiteDatabase db = con.getWritableDatabase();

        ContentValues values_cliente = new ContentValues();
        ContentValues values_direccion = new ContentValues();

        String city_id = city.getSelectedItem().toString().split(" ")[1];
/*
        road_type_id         = road_type.getSelectedItem().toString().split("-");
        orientation_road1_id = orientation_road1.getSelectedItem().toString().split("-");
        orientation_road2_id = orientation_road2.getSelectedItem().toString().split("-");*/

        /*if (addressValidation()) {*/

            /*values_direccion.put(Utilities.DIRECCIONES_TIPO_VIA_ID, road_type_id[0]);
            values_direccion.put(Utilities.DIRECCIONES_NUMERO_1, number1.getText().toString());
            values_direccion.put(Utilities.DIRECCIONES_LETRA_1, chart1.getText().toString());
            values_direccion.put(Utilities.DIRECCIONES_ORIENTACION_1_ID, orientation_road1_id[0]);
            values_direccion.put(Utilities.DIRECCIONES_NUMERO_2, number2.getText().toString());
            values_direccion.put(Utilities.DIRECCIONES_LETRA_2, chart2.getText().toString());
            values_direccion.put(Utilities.DIRECCIONES_ORIENTACION_2_ID, orientation_road2_id[0]);
            values_direccion.put(Utilities.DIRECCIONES_NUMERO_3, number3.getText().toString());
            values_direccion.put(Utilities.DIRECCIONES_CIUDAD_ID, city_id[0]);

            Integer direccion_id = (int) (long) db.insert(Utilities.DIRECCIONES, Utilities.DIRECCIONES_ID, values_direccion);*/

        if (userValidation()) {
            values_cliente.put(Utilities.CLIENTES_RAZON_SOCIAL, name.getText().toString());
            values_cliente.put(Utilities.CLIENTES_NIT, nit.getText().toString());
            values_cliente.put(Utilities.CLIENTES_PASSWORD, password.getText().toString());
            values_cliente.put(Utilities.CLIENTES_DIRECCION,address.getText().toString());
            values_cliente.put(Utilities.CLIENTES_CIUDAD_ID,city_id);

            Long cliente_id = db.insert(Utilities.CLIENTES, Utilities.CLIENTES_ID, values_cliente);

            //Toast.makeText(this,"CLIENTE ID"+cliente_id,Toast.LENGTH_LONG).show();


            return true;

        } else {
            return false;
        }
        /*}else {
            return false;
        }*/

    }


    /*private boolean addressValidation() {
        if (number1.getText().toString().length() == 0 || number2.getText().toString().length() == 0) {
            Toast.makeText(this, "Ingrese una dirección válida", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!road_type_id[0].matches("\\d+(?:\\.\\d+)?")) {
            Toast.makeText(this, "Ingrese tipo de vía", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!city_id[0].matches("\\d+(?:\\.\\d+)?")) {
            Toast.makeText(this, "Ingrese ciudad", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
*/

    private boolean userValidation() {
        String name_validate = name.getText().toString();
        String nit_validate = nit.getText().toString();
        String password_valdiate = password.getText().toString();
        String address_valdiate = address.getText().toString();

        if (name_validate.length() == 0 || nit_validate.length() == 0 || password_valdiate.length() == 0 || address_valdiate.length() == 0) {
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_LONG).show();
            return false;
        }

        if (name_validate.length() < 6) {
            Toast.makeText(this, "Razón social muy corta", Toast.LENGTH_LONG).show();
            return false;
        }
        if (nit_validate.length() < 8) {
            Toast.makeText(this, "NIT muy corto", Toast.LENGTH_LONG).show();
            return false;
        }
        if (password_valdiate.length() < 4) {
            Toast.makeText(this, "Contraseña muy corta", Toast.LENGTH_LONG).show();
            return false;
        }
        if (address_valdiate.length() < 6) {
            Toast.makeText(this, "Dirección muy corta", Toast.LENGTH_LONG).show();
            return false;
        }

        con = new SQLiteConnectionHelper(getApplicationContext(), "SPEDB", null, 1);
        SQLiteDatabase db = con.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT id FROM " + Utilities.CLIENTES + " WHERE " + Utilities.CLIENTES_NIT + "='" + nit_validate + "'", null);

        if (cursor.moveToFirst()) {
            Toast.makeText(this, "NIT ya existe", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
