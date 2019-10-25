package com.example.spe_logistic.ui.references;

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
