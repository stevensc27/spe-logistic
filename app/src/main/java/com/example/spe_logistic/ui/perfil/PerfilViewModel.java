package com.example.spe_logistic.ui.perfil;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.spe_logistic.SQLiteConnectionHelper;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<String>> perfil_list;
    private SQLiteConnectionHelper con;
    private SQLiteDatabase db;
    private int user_id;

    public PerfilViewModel(@NonNull Application application) {
        super(application);

        perfil_list = new MutableLiveData<>();

        ArrayList<String> perfil_array_list = new ArrayList<>();

        con = new SQLiteConnectionHelper(getApplication(), "SPEDB", null, 1);
        db = con.getReadableDatabase();

        SharedPreferences preferences = getApplication().getSharedPreferences("credentials", getApplication().MODE_PRIVATE);
        user_id = preferences.getInt("user_id", 0);

        String[] parameters = {String.valueOf(user_id)};
        String queryPerfil =    "SELECT razon_social,nit " +
                                "FROM   clientes " +
                                "WHERE  id = ? ";

        Cursor cursor = db.rawQuery(queryPerfil,parameters);
        cursor.moveToFirst();

        perfil_array_list.add(cursor.getString(0));
        perfil_array_list.add(cursor.getString(1));

        perfil_list.setValue(perfil_array_list);

        getDataHistorySend();
        getDataHistoryCollect();
        getDataHistoryReferences();

        db.close();

    }

    private void getDataHistorySend() {
        String[] parameters = {String.valueOf(user_id)};
        Cursor cursor = db.rawQuery("SELECT     historial_envios.fecha,descripcion " +
                                         "FROM       historial_envios " +
                                         "INNER JOIN envios " +
                                         "ON         envios.id = historial_envios.envio_id " +
                                         "WHERE      envios.cliente_id = ?", parameters);
    }

    private void getDataHistoryCollect() {
        String[] parameters = {String.valueOf(user_id)};
        Cursor cursor = db.rawQuery("SELECT     historial_recogidas.fecha,descripcion " +
                                         "FROM       historial_recogidas " +
                                         "INNER JOIN recogidas " +
                                         "ON         recogidas.id = historial_recogidas.recogida_id " +
                                         "WHERE      recogidas.cliente_id = ?", parameters);
    }

    private void getDataHistoryReferences() {
        String[] parameters = {String.valueOf(user_id)};
        Cursor cursor = db.rawQuery("SELECT     historial_referencias.fecha,descripcion " +
                                         "FROM       historial_referencias " +
                                         "INNER JOIN referencias " +
                                         "ON         referencias.id = historial_referencias.referencia_id " +
                                         "WHERE      referencias.cliente_id = ?", parameters);
    }

    public LiveData<ArrayList<String>> getPerfil() {
        return perfil_list;
    }
}
