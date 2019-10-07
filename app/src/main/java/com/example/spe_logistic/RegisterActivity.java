package com.example.spe_logistic;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.spe_logistic.entities.Tipos_Vias;
import com.example.spe_logistic.utilities.Utilities;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    Spinner road_type;
    Spinner orientation_road;
    Spinner orientation_road2;

    SQLiteConnectionHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        con = new SQLiteConnectionHelper(getApplicationContext(),"SPEDB",null,1);

        road_type = (Spinner) findViewById(R.id.road_type);
        ArrayAdapter<CharSequence> adapter_address_options = ArrayAdapter.createFromResource(this,R.array.address_options,android.R.layout.simple_spinner_item);
        road_type.setAdapter(adapter_address_options);

        orientation_road = (Spinner) findViewById(R.id.orientation_road);
        ArrayAdapter<CharSequence> orientation_road_options = ArrayAdapter.createFromResource(this,R.array.orientation_road_options,android.R.layout.simple_spinner_item);
        orientation_road.setAdapter(orientation_road_options);

        orientation_road2 = (Spinner) findViewById(R.id.orientation_road2);
        ArrayAdapter<CharSequence> orientation_road_options2 = ArrayAdapter.createFromResource(this,R.array.orientation_road_options,android.R.layout.simple_spinner_item);
        orientation_road2.setAdapter(orientation_road_options2);


    }





}
