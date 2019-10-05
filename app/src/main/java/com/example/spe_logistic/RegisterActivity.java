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
    ArrayList<String> road_types;
    ArrayList<Tipos_Vias> road_types_list;

    SQLiteConnectionHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        con = new SQLiteConnectionHelper(getApplicationContext(),"SPEDB",null,1);

        road_type = (Spinner) findViewById(R.id.road_type);

        searchRoadsTypes();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,road_types);

        road_type.setAdapter(adapter);
    }

    private void searchRoadsTypes() {
        SQLiteDatabase db = con.getReadableDatabase();

        Tipos_Vias tipos_vias = null;

        road_types_list = new ArrayList<Tipos_Vias>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilities.TIPOS_VIAS,null);

        Log.i("QUERY","SELECT * FROM "+ Utilities.TIPOS_VIAS+" "+cursor.getCount());

        while (cursor.moveToNext()){
            tipos_vias = new Tipos_Vias();
            tipos_vias.setId(cursor.getInt(0));
            tipos_vias.setNombre(cursor.getString(1));

            road_types_list.add(tipos_vias);
        }

        //generateList();
    }

    private void generateList() {
        road_types = new ArrayList<String>();
        road_types.add("Seleccione");

        for (int i = 0; i<=road_types_list.size();i++){
            road_types.add(road_types_list.get(i).getId()+" - "+road_types_list.get(i).getNombre());
        }
    }


}
