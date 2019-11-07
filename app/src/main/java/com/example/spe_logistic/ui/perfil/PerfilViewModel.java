package com.example.spe_logistic.ui.perfil;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.spe_logistic.SQLiteConnectionHelper;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


public class PerfilViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<String>> perfil_list;

    private MutableLiveData<ArrayList<String[]>> time_indicators;

    private MutableLiveData<ArrayList<BarEntry>> pqr_list;
    private MutableLiveData<ArrayList<String>> pqr_list_label;

    private MutableLiveData<ArrayList<String[]>> send_history;
    private MutableLiveData<ArrayList<String[]>> collect_history;
    private MutableLiveData<ArrayList<String[]>> references_history;

    private SQLiteConnectionHelper con;
    private SQLiteDatabase db;
    private int user_id;
    private String[] parameters;

    public PerfilViewModel(@NonNull Application application) {
        super(application);

        perfil_list = new MutableLiveData<>();

        time_indicators = new MutableLiveData<>();

        pqr_list = new MutableLiveData<>();
        pqr_list_label = new MutableLiveData<>();

        send_history = new MutableLiveData<>();
        collect_history = new MutableLiveData<>();
        references_history = new MutableLiveData<>();

        ArrayList<String> perfil_array_list = new ArrayList<>();

        con = new SQLiteConnectionHelper(getApplication(), "SPEDB", null, 1);
        db = con.getReadableDatabase();

        SharedPreferences preferences = getApplication().getSharedPreferences("credentials", getApplication().MODE_PRIVATE);
        user_id = preferences.getInt("user_id", 0);

        parameters = new String[]{String.valueOf(user_id)};

        String[] parameters = {String.valueOf(user_id)};
        String queryPerfil = "SELECT razon_social,nit " +
                "FROM   clientes " +
                "WHERE  id = ? ";

        Cursor cursor = db.rawQuery(queryPerfil, parameters);
        cursor.moveToFirst();

        perfil_array_list.add(cursor.getString(0));
        perfil_array_list.add(cursor.getString(1));

        perfil_list.setValue(perfil_array_list);

        getDataIndicators();
        getDataPqr();
        getDataHistorySend();
        getDataHistoryCollect();
        getDataHistoryReferences();

        db.close();

    }

    private void getDataIndicators() {
        SQLiteDatabase db = con.getReadableDatabase();

        String timeEnlis = "00:00";
        String timeDispa = "00:00";
        String timeTotal = "00:00";
        ArrayList<String[]> indicators_array_list = new ArrayList<>();

        String[] parameters = {String.valueOf(user_id)};
        String queryEnlistment =    "SELECT     avg(CAST((julianday(fecha_alistado) - julianday(fecha_reservado))*24 AS real)), " +
                                    "           avg(CAST((julianday(fecha) - julianday(fecha_alistado))*24 AS real)), " +
                                    "           avg(CAST((julianday(fecha) - julianday(fecha_reservado))*24 AS real)) " +
                                    "FROM       envios " +
                                    "INNER JOIN despachos " +
                                    "ON         despachos.id = envios.despacho_id " +
                                    "WHERE      envios.cliente_id = ? ";
        Cursor cursor = db.rawQuery(queryEnlistment, parameters);
        if (cursor.moveToFirst()) {
            float enlis = cursor.getFloat(0);
            timeEnlis = String.format("%02d",(int) enlis) + ":" + String.format("%02d",(int) ((enlis - (int) enlis) * 60));
            float dispa = cursor.getFloat(1);
            timeDispa = String.format("%02d",(int) dispa) + ":" + String.format("%02d",(int) ((dispa - (int) dispa) * 60));
            float total = cursor.getFloat(2);
            timeTotal = String.format("%02d",(int) total) + ":" + String.format("%02d",(int) ((total - (int) total) * 60));
        }


        indicators_array_list.add(new String[]{timeEnlis, timeDispa, timeTotal});

        time_indicators.setValue(indicators_array_list);
    }

    private void getDataPqr() {
        SQLiteDatabase db = con.getReadableDatabase();

        ArrayList<BarEntry> pqr_array_list = new ArrayList<>();
        ArrayList<String> pqr_array_list_label = new ArrayList<>();

        String[] parameters = {String.valueOf(user_id)};
        String queryPqr = "SELECT     estados_pqrs.nombre, " +
                "           count(*) " +
                "FROM       pqrs " +
                "INNER JOIN estados_pqrs " +
                "ON         estados_pqrs.id = pqrs.estado_id " +
                "WHERE      pqrs.cliente_id = ? " +
                "GROUP BY   estados_pqrs.nombre " +
                "ORDER BY   estados_pqrs.nombre DESC";

        Cursor cursor = db.rawQuery(queryPqr, parameters);
        int i = 0;
        while (cursor.moveToNext()) {

            pqr_array_list_label.add(cursor.getString(0));
            pqr_array_list.add(new BarEntry(i, cursor.getInt(1)));
            i++;
        }

        pqr_list.setValue(pqr_array_list);
        pqr_list_label.setValue(pqr_array_list_label);
    }

    private void getDataHistorySend() {
        ArrayList<String[]> data = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT     envio_id,fecha,descripcion " +
                "FROM       historial_envios " +
                "WHERE      cliente_id = ? " +
                "ORDER BY   id DESC", parameters);
        data = getRows(cursor);
        send_history.setValue(data);
    }

    private void getDataHistoryCollect() {
        ArrayList<String[]> data = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT     recogida_id,fecha,descripcion " +
                "FROM       historial_recogidas " +
                "WHERE      cliente_id = ? " +
                "ORDER BY   id DESC", parameters);
        data = getRows(cursor);
        collect_history.setValue(data);
    }

    private void getDataHistoryReferences() {
        ArrayList<String[]> data = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT     referencia_id,historial_referencias.fecha,descripcion " +
                "FROM       historial_referencias " +
                "WHERE      cliente_id = ? " +
                "ORDER BY   id DESC", parameters);
        data = getRows(cursor);
        references_history.setValue(data);
    }

    private ArrayList<String[]> getRows(Cursor cursor) {
        ArrayList<String[]> data = new ArrayList<>();
        while (cursor.moveToNext()) {
            data.add(new String[]{cursor.getString(0), cursor.getString(1), cursor.getString(2)});
        }
        return data;
    }

    public LiveData<ArrayList<String[]>> getIndicators() {
        return time_indicators;
    }

    public LiveData<ArrayList<String>> getPerfil() {
        return perfil_list;
    }

    public LiveData<ArrayList<BarEntry>> getBarPqrList() {
        return pqr_list;
    }

    public LiveData<ArrayList<String>> getBarPqrListLabel() {
        return pqr_list_label;
    }

    public LiveData<ArrayList<String[]>> getSend_history() {
        return send_history;
    }

    public LiveData<ArrayList<String[]>> getCollect_history() {
        return collect_history;
    }

    public LiveData<ArrayList<String[]>> getReferences_history() {
        return references_history;
    }
}
