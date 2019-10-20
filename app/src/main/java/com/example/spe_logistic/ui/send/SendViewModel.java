package com.example.spe_logistic.ui.send;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.spe_logistic.MyApp;
import com.example.spe_logistic.SQLiteConnectionHelper;
import com.example.spe_logistic.entities.Envios;

import java.util.ArrayList;

public class SendViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<SendVo>> send_list;

    public SendViewModel(@NonNull Application application){
        super(application);
        //SQLiteConnectionHelper con = new SQLiteConnectionHelper(MyApp.getContext(),"SPEDB",null,1);
        //SQLiteDatabase db = con.getWritableDatabase();

        /*SharedPreferences preferences = getApplication().getSharedPreferences("credentials", getApplication().MODE_PRIVATE);
        Integer user_id = preferences.getInt("user_id",0);
        Log.i("APP","USER FRAGMENT VIEW MODEL: "+user_id);*/

        send_list = new MutableLiveData<>();

        ArrayList<SendVo> send_array_list = new ArrayList<>();
        
        /*
        SELECT      id,direccion_destinatario||' '||ciudades.nombre,estado_id
        FROM        envios
        INNER JOIN  ciudades
                ON  ciudades.id = envios.ciudad_destinatario_id
        WHERE       cliente_id = ***
        */
        
        send_array_list.add(new SendVo("123","carrera 213","1"));
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
        send_array_list.add(new SendVo("789","circular 1651","3"));

        send_list.setValue(send_array_list);
    }


    public LiveData<ArrayList<SendVo>> getSendList() {
        return send_list;
    }

}
