package com.example.spe_logistic.ui.send;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.database.sqlite.SQLiteDatabase;

import com.example.spe_logistic.SQLiteConnectionHelper;

import java.util.ArrayList;

public class SendViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> send_list;


    public SendViewModel(){
        //SQLiteConnectionHelper con = new SQLiteConnectionHelper(null,"SPEDB",null,1);
        //SQLiteDatabase db = con.getWritableDatabase();

        send_list = new MutableLiveData<>();

        ArrayList<String> send_array_list = new ArrayList<>();

        send_array_list.add("Prueba1");
        send_array_list.add("Prueba2");
        send_array_list.add("Prueba3");

        send_list.setValue(send_array_list);
    }


    public LiveData<ArrayList<String>> getSendList() {
        return send_list;
    }

}