package com.example.spe_logistic.ui.references;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.spe_logistic.SQLiteConnectionHelper;

import java.util.ArrayList;

public class ReferencesViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<ReferencesVo>> references_list;

    private SQLiteConnectionHelper con;

    public ReferencesViewModel(@NonNull Application application) {
        super(application);

        references_list = new MutableLiveData<>();

        ArrayList<ReferencesVo> references_array_list = new ArrayList<>();

        SharedPreferences preferences = getApplication().getSharedPreferences("credentials", getApplication().MODE_PRIVATE);
        Integer user_id = preferences.getInt("user_id",0);

        con = new SQLiteConnectionHelper(getApplication(),"SPEDB",null,1);
        SQLiteDatabase db = con.getReadableDatabase();
        String search = "SELECT     id,codigo_barras,nombre " +
                        "FROM       referencias " +
                        "WHERE      cliente_id ="+user_id +" "+
                        "ORDER BY   id DESC";

        Cursor cursor = db.rawQuery(search,null);
        try {
            while (cursor.moveToNext()) {
                Log.i("APP","Nombre: "+cursor.getString(2)+" ID: "+cursor.getString(0));

                references_array_list.add(new ReferencesVo(cursor.getString(0),"",cursor.getString(1),cursor.getString(2)));
            }
        } finally {
            cursor.close();
        }

        references_list.setValue(references_array_list);
    }

    public LiveData<ArrayList<ReferencesVo>> getReferencesList() {
        return references_list;
    }
}
