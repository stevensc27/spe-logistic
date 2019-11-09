package com.example.spe_logistic.ui.send;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;


import com.example.spe_logistic.SQLiteConnectionHelper;

import java.util.ArrayList;

public class SendViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<SendVo>> send_list;

    private SQLiteConnectionHelper con;

    public SendViewModel(@NonNull Application application) {
        super(application);

        send_list = new MutableLiveData<>();

        ArrayList<SendVo> send_array_list = new ArrayList<>();

        SharedPreferences preferences = getApplication().getSharedPreferences("credentials", getApplication().MODE_PRIVATE);
        Integer user_id = preferences.getInt("user_id", 0);

        con = new SQLiteConnectionHelper(getApplication(), "SPEDB", null, 1);
        SQLiteDatabase db = con.getReadableDatabase();


        String search = "SELECT     envios.id," +
                "           direccion_destinatario||' '||ciudades.nombre," +
                "           estado_id, " +
                "           envios.nombre_destinatario " +
                "FROM       envios " +
                "INNER JOIN ciudades " +
                "ON         ciudades.id = envios.ciudad_destinatario_id " +
                "WHERE      cliente_id =" + user_id + " " +
                "ORDER BY   envios.id DESC";

        Cursor cursor = db.rawQuery(search, null);
        try {
            while (cursor.moveToNext()) {
                send_array_list.add(new SendVo(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            }
        } finally {
            cursor.close();
        }

        
        /*send_array_list.add(new SendVo("123","carrera 213","1"));
        send_array_list.add(new SendVo("456","calle 4561","2"));
        send_array_list.add(new SendVo("856","calle 96 54 26","2"));
        send_array_list.add(new SendVo("789","circular 1651","3"));
        send_array_list.add(new SendVo("123","carrera 213","1"));
        send_array_list.add(new SendVo("456","calle 4561","2"));
        send_array_list.add(new SendVo("856","calle 96 54 26","2"));
        send_array_list.add(new SendVo("789","circular 1651","3"));
        send_array_list.add(new SendVo("123","carrera 213","1"));
        send_array_list.add(new SendVo("456","calle 4561","2"));
        send_array_list.add(new SendVo("856","calle 96 54 26","2"));
        send_array_list.add(new SendVo("789","circular 1651","3"));*/

        send_list.setValue(send_array_list);
    }


    public LiveData<ArrayList<SendVo>> getSendList() {
        return send_list;
    }

}
