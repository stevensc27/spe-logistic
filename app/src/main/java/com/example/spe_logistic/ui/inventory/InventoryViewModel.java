package com.example.spe_logistic.ui.inventory;

import android.app.Application;
import android.content.ContentUris;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spe_logistic.SQLiteConnectionHelper;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class InventoryViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<BarEntry>> bar_inventory_list;
    private MutableLiveData<ArrayList<String>>   bar_inventory_list_label;

    private MutableLiveData<ArrayList<BarEntry>> bar_send_list_in;
    private MutableLiveData<ArrayList<BarEntry>> bar_send_list_out;
    private MutableLiveData<ArrayList<String>>   bar_send_list_label;

    private MutableLiveData<ArrayList<PieEntry>> pie_inventory_list;

    private MutableLiveData<ArrayList<String[]>>   table_inventory_list;

    private SQLiteConnectionHelper con;

    private int user_id;

    public InventoryViewModel(@NonNull Application application) {
        super(application);

        SharedPreferences preferences = getApplication().getSharedPreferences("credentials", getApplication().MODE_PRIVATE);
        user_id = preferences.getInt("user_id",0);

        bar_inventory_list       = new MutableLiveData<>();
        bar_inventory_list_label = new MutableLiveData<>();

        bar_send_list_in         = new MutableLiveData<>();
        bar_send_list_out        = new MutableLiveData<>();
        bar_send_list_label      = new MutableLiveData<>();

        pie_inventory_list       = new MutableLiveData<>();

        table_inventory_list     = new MutableLiveData<>();

        con = new SQLiteConnectionHelper(getApplication(),"SPEDB",null,1);

        getDataInventory();
        getDataSend();
        getDataPie();
        getDataTable();

    }

    private void getDataInventory() {

        SQLiteDatabase db = con.getReadableDatabase();

        ArrayList<BarEntry> bar_inventory_array_list       = new ArrayList<>();
        ArrayList<String>   bar_inventory_array_list_label = new ArrayList<>();
        
        String[] parameters = {String.valueOf(user_id)};
        String queryReferences =    "SELECT     count(*) AS amount, "                       +
                                    "           referencias.codigo_barras "                 +
                                    "FROM       inventario "                                +
                                    "INNER JOIN referencias "                               +
                                    "ON         referencias.id = inventario.referencia_id " +
                                    "WHERE      referencias.cliente_id = ? AND "            +
                                    "           inventario.estado_id = 1 "                  +
                                    "GROUP BY   codigo_barras "                             +
                                    "ORDER BY   amount DESC "                               +
                                    "LIMIT      6";

        Cursor cursor = db.rawQuery(queryReferences,parameters);

        int i = 0;
        // here desc, in chart asc
        // x order
        // y quantities per unit
        cursor.moveToLast();
        while (cursor.moveToPrevious()){

            bar_inventory_array_list.add(new BarEntry(i,cursor.getFloat(0)));
            bar_inventory_array_list_label.add(cursor.getString(1));
            i++;
        }

        db.close();

        bar_inventory_list.setValue(bar_inventory_array_list);
        bar_inventory_list_label.setValue(bar_inventory_array_list_label);
    }

    private void getDataSend() {

        SQLiteDatabase db = con.getReadableDatabase();

        ArrayList<BarEntry> bar_send_array_list_in    = new ArrayList<>();
        ArrayList<BarEntry> bar_send_array_list_out   = new ArrayList<>();
        ArrayList<String>   bar_send_array_list_label = new ArrayList<>();
        

        Date afterSixMonth = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(afterSixMonth);
        calendar.add(Calendar.MONTH, -5);


        DecimalFormat formatter = new DecimalFormat("00");
        String monthAfterSix = formatter.format(calendar.get(Calendar.MONTH)+1);
        String dateAfterSix  = calendar.get(Calendar.YEAR)+""+monthAfterSix;

        String[] parameters = {dateAfterSix,String.valueOf(user_id)};
        String queryIn =    "SELECT     count(*), " +
                            "           strftime('%Y%m', fecha_ingreso) as month " +
                            "FROM       inventario " +
                            "INNER JOIN referencias " +
                            "ON         referencias.id = inventario.referencia_id " +
                            "WHERE      strftime('%Y%m', fecha_ingreso) >= ? AND" +
                            "           referencias.cliente_id = ? " +
                            "GROUP BY   month " +
                            "ORDER BY   month";

        Cursor cursor = db.rawQuery(queryIn,parameters);

        int i = 1;
        while (cursor.moveToNext()){

            bar_send_array_list_in.add(new BarEntry(i,cursor.getInt(0)));
        }

        /*bar_send_array_list_in.add(new BarEntry(1,12));
        bar_send_array_list_in.add(new BarEntry(2,35));
        bar_send_array_list_in.add(new BarEntry(3,51));
        bar_send_array_list_in.add(new BarEntry(4,13));
        bar_send_array_list_in.add(new BarEntry(5,68));
        bar_send_array_list_in.add(new BarEntry(6,22));
         */
        

        String queryOut =   "SELECT     count(*), " +
                            "           strftime('%Y_%m', despachos.fecha) as month, " +
                            "           CASE strftime('%m', despachos.fecha) " +
                            "               WHEN '01' then 'ENE' " +
                            "               WHEN '02' then 'FEB' " +
                            "               WHEN '03' then 'MAR' " +
                            "               WHEN '04' then 'ABR' " +
                            "               WHEN '05' then 'MAY' " +
                            "               WHEN '06' then 'JUN' " +
                            "               WHEN '07' then 'JUL' " +
                            "               WHEN '08' then 'AGO' " +
                            "               WHEN '09' then 'SEP' " +
                            "               WHEN '10' then 'OCT' " +
                            "               WHEN '11' then 'NOV' " +
                            "               WHEN '12' then 'DIC' " +
                            "           END " +
                            "FROM       inventario " +
                            "INNER JOIN referencias " +
                            "ON         referencias.id = inventario.referencia_id " +
                            "INNER JOIN envios " +
                            "ON         envios.id = inventario.envio_id " +
                            "INNER JOIN despachos " +
                            "ON         despachos.id = envios.despacho_id " +
                            "WHERE      strftime('%Y%m', fecha_ingreso) >= ? AND " +
                            "           referencias.cliente_id = ? " +
                            "GROUP BY   month " +
                            "ORDER BY   month";

        Cursor cursor1 = db.rawQuery(queryOut,parameters);

        i = 1;
        //Log.i("APP","in: "+cursor1.getString(0));
        while (cursor1.moveToNext()){
            Log.i("APP","out: "+cursor1.getString(0));
            bar_send_array_list_out.add(new BarEntry(i,cursor1.getInt(0)));
            bar_send_array_list_label.add(cursor1.getString(2));
        }

        /*
        bar_send_array_list_out.add(new BarEntry(1,93));
        bar_send_array_list_out.add(new BarEntry(2,62));
        bar_send_array_list_out.add(new BarEntry(3,30));
        bar_send_array_list_out.add(new BarEntry(4,71));
        bar_send_array_list_out.add(new BarEntry(5,33));
        bar_send_array_list_out.add(new BarEntry(6,11));

        //bar_send_array_list_label.add("");
        bar_send_array_list_label.add("JUN");
        bar_send_array_list_label.add("JUL");
        bar_send_array_list_label.add("AGO");
        bar_send_array_list_label.add("SEP");
        bar_send_array_list_label.add("OCT");
        bar_send_array_list_label.add("NOV");
        */

        db.close();

        bar_send_list_in.setValue(bar_send_array_list_in);
        bar_send_list_out.setValue(bar_send_array_list_out);
        bar_send_list_label.setValue(bar_send_array_list_label);

    }

    private void getDataPie() {

        SQLiteDatabase db = con.getReadableDatabase();

        ArrayList<PieEntry> pie_inventory_arrat_list = new ArrayList<>();
        ArrayList<String[]> data = new ArrayList<>();

        /*
        Date date = new Date();
        Calendar calendar  = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String firstDay   = format.format(calendar.getTime());

        
        String[] parameters = {String.valueOf(user_id)};
        String queryReferences =    "SELECT id,codigo_barras " +
                                    "FROM   referencias " +
                                    "WHERE  cliente_id = ?";

        Cursor cursor = db.rawQuery(queryReferences, parameters);

        while (cursor.moveToNext()){

            String[] parametersInitial = {firstDay,firstDay,cursor.getString(0)};
            String queryInventoryInitial =  "SELECT     count(*) AS amountFirstDay " +
                                            "FROM       inventario " +
                                            "INNER JOIN envios " +
                                            "ON         envios.id = inventario.envio_id " +
                                            "WHERE      Datetime(fecha_ingreso) <= Datetime(?)                  AND " +
                                            "               (Datetime(envios.fecha_alistado) = null             OR " +
                                            "                Datetime(envios.fecha_alistados) > Datetime(?))    AND " +
                                            "           referencia_id = ?";
            Cursor cursorInitial = db.rawQuery(queryInventoryInitial,parametersInitial);
            cursorInitial.moveToFirst();
            Float amountFirstDay = cursorInitial.getFloat(0);



            String[] parametersCurrent = {"1",cursor.getString(0)};
            String queryInventoryCurrent =  "SELECT count(*) AS amountCurrentDay " +
                                            "FROM   inventario " +
                                            "WHERE  estado_id = ?       AND " +
                                            "       referencia_id = ?";
            Cursor cursorCurrent = db.rawQuery(queryInventoryCurrent,parametersCurrent);
            cursorCurrent.moveToFirst();
            Float amountCurrentDay = cursorCurrent.getFloat(0);



            Float averageInventory = ( amountFirstDay + amountCurrentDay ) / 2;



            String[] parametersDispatched = {firstDay,cursor.getString(0)};
            String queryDispatched =    "SELECT     count(*) AS amountDispatched " +
                                        "FROM       inventario " +
                                        "INNER JOIN envios " +
                                        "ON         envios.id = inventario.envio_id " +
                                        "WHERE      Datetime(envios.fecha_alistamiento) > Datatime(?) AND " +
                                        "           referencia_id = ?";
            Cursor cursorDispatched = db.rawQuery(queryDispatched,parametersDispatched);
            cursorDispatched.moveToFirst();
            Float amountDispatched = cursorDispatched.getFloat(0);



            Float rotationIndicator = amountDispatched / averageInventory;
            DecimalFormat decimalFormat = new DecimalFormat("#.00");


            String rotationIndicatorString = decimalFormat.format(rotationIndicator);
            String barCode = cursor.getString(1);



            data.add(new String[]{rotationIndicatorString,barCode});

        }
*/

        pie_inventory_arrat_list.add(new PieEntry(6, "1232365985645"));
        pie_inventory_arrat_list.add(new PieEntry(5, "6688522994477"));
        pie_inventory_arrat_list.add(new PieEntry(3, "9325365984561"));
        pie_inventory_arrat_list.add(new PieEntry(2, "3366885544223"));
        pie_inventory_arrat_list.add(new PieEntry(2, "3366885534223"));


        db.close();

        pie_inventory_list.setValue(pie_inventory_arrat_list);

    }

    private void getDataTable(){
        SQLiteDatabase db = con.getReadableDatabase();

        ArrayList<String[]> table_inventory_array_list = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-15);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

        String[] parameters = {String.valueOf(user_id),date};
        String queryInventoryDammed =   "SELECT     referencias.codigo_barras, " +
                                        "           count(*), " +
                                        "           fecha_ingreso " +
                                        "FROM       inventario " +
                                        "INNER JOIN referencias " +
                                        "ON         referencias.id = inventario.referencia_id " +
                                        "WHERE      referencias.cliente_id = ? AND " +
                                        "           Datetime(fecha_ingreso) <  Datetime(?) " +
                                        "GROUP BY   referencias.codigo_barras," +
                                        "           fecha_ingreso " +
                                        "LIMIT      7";

        Cursor cursor = db.rawQuery(queryInventoryDammed,parameters);

        while (cursor.moveToNext()){

            table_inventory_array_list.add(new String[]{cursor.getString(0),cursor.getString(1),cursor.getString(2)});
        }

        db.close();

        table_inventory_list.setValue(table_inventory_array_list);
    }

    public LiveData<ArrayList<BarEntry>> getBarInventoryList() {
        return bar_inventory_list;
    }

    public LiveData<ArrayList<String>> getBarInventoryListLabel() {
        return bar_inventory_list_label;
    }

    public LiveData<ArrayList<BarEntry>> getBarSendListIn() {
        return bar_send_list_in;
    }

    public LiveData<ArrayList<BarEntry>> getBarSendListOut() {
        return bar_send_list_out;
    }

    public LiveData<ArrayList<String>> getBarSendListLabel() {
        return bar_send_list_label;
    }

    public LiveData<ArrayList<PieEntry>> getPieInventoryList() {
        return pie_inventory_list;
    }

    public LiveData<ArrayList<String[]>> getTableInventoryList() {
        return table_inventory_list;
    }
}
