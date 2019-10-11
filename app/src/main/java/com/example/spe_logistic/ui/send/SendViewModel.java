package com.example.spe_logistic.ui.send;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.database.sqlite.SQLiteDatabase;

import com.example.spe_logistic.MyApp;
import com.example.spe_logistic.SQLiteConnectionHelper;
import com.example.spe_logistic.entities.Envios;

import java.util.ArrayList;

public class SendViewModel extends ViewModel {

    private MutableLiveData<ArrayList<SendVo>> send_list;


    public SendViewModel(){

        //SQLiteConnectionHelper con = new SQLiteConnectionHelper(MyApp.getContext(),"SPEDB",null,1);
        //SQLiteDatabase db = con.getWritableDatabase();

        send_list = new MutableLiveData<>();

        ArrayList<SendVo> send_array_list = new ArrayList<>();


        send_array_list.add(new SendVo("123","carrera 213","en proceso"));
        send_array_list.add(new SendVo("456","calle 4561","en proceso"));
        send_array_list.add(new SendVo("789","circular 1651","en proceso"));



        send_list.setValue(send_array_list);
    }


    public LiveData<ArrayList<SendVo>> getSendList() {
        return send_list;
    }

}