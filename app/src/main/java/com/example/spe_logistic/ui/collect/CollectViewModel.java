package com.example.spe_logistic.ui.collect;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.spe_logistic.SQLiteConnectionHelper;

import java.util.ArrayList;

public class CollectViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<CollectVo>> collect_list;

    private SQLiteConnectionHelper con;

    public CollectViewModel(@NonNull Application application) {
        super(application);

        collect_list = new MutableLiveData<>();

        ArrayList<CollectVo> collect_array_list = new ArrayList<>();

        SharedPreferences preferences = getApplication().getSharedPreferences("credentials", getApplication().MODE_PRIVATE);
        Integer user_id = preferences.getInt("user_id",0);

        con = new SQLiteConnectionHelper(getApplication(),"SPEDB",null,1);
        SQLiteDatabase db = con.getReadableDatabase();

        String search = "SELECT     id,direccion,fecha,estado_id " +
                        "FROM       recogidas " +
                        "WHERE      cliente_id ="+ user_id +" "+
                        "ORDER BY   id DESC";

        Cursor cursor = db.rawQuery(search,null);
        try {
            while (cursor.moveToNext()) {
                collect_array_list.add(new CollectVo(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            }
        } finally {
            cursor.close();
        }

        collect_list.setValue(collect_array_list);

    }

    public LiveData<ArrayList<CollectVo>> getCollectList() {
        return collect_list;
    }
}
