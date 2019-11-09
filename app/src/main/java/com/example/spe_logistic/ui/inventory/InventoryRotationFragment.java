package com.example.spe_logistic.ui.inventory;


import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.spe_logistic.R;
import com.example.spe_logistic.SQLiteConnectionHelper;
import com.github.mikephil.charting.data.PieEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.spe_logistic.R.color.colorOrangeSpe;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryRotationFragment extends Fragment {

    private TableLayout         tableRotation;
    private TableRow            tableRow;
    private TextView            textCell;
    private int                 indexRow;
    private int                 indexCell;
    private ArrayList<String[]> data;
    private SQLiteConnectionHelper con;
    private int user_id;

    public InventoryRotationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_inventory_rotation, container, false);

        SharedPreferences preferences = getContext().getSharedPreferences("credentials", getContext().MODE_PRIVATE);
        user_id = preferences.getInt("user_id",0);

        fillRotationTable(root);

        return root;
    }

    private void fillRotationTable(View root) {

        tableRotation = (TableLayout) root.findViewById(R.id.tableRotation);

        data = getTableData();

        for (indexRow = 0; indexRow < data.size(); indexRow++){
            tableRow = new TableRow(getContext());
            indexCell = 0;
            while (indexCell<3) {

                textCell = new TextView(getContext());

                textCell.setGravity(Gravity.CENTER);
                textCell.setText(data.get(indexRow)[indexCell]);
                textCell.setBackgroundColor(Color.WHITE);
                textCell.setTextSize(15);

                tableRow.addView(textCell, newTableRowParams());

                indexCell++;
            }

            tableRow.setBackgroundColor(getResources().getColor(colorOrangeSpe));

            tableRotation.addView(tableRow);
        }
    }

    private TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight = 1;
        return params;
    }

    private ArrayList<String[]> getTableData(){

        con = new SQLiteConnectionHelper(getContext(),"SPEDB",null,1);
        SQLiteDatabase db = con.getReadableDatabase();

        ArrayList<String[]>  data = new ArrayList<>();
/*

        data.add(new String[]{"1122334455667","1.85","4"});
        data.add(new String[]{"9966558877442","5.77","5"});
        data.add(new String[]{"6633225588221","4.58","2"});
        data.add(new String[]{"9955441122665","3.25","13"});
        data.add(new String[]{"6633225599884","9.35","6"});
        data.add(new String[]{"9988772200220","5.68","8"});
        data.add(new String[]{"1122334455667","1.85","4"});
        data.add(new String[]{"9966558877442","5.77","5"});
        data.add(new String[]{"6633225588221","4.58","2"});
        data.add(new String[]{"9955441122665","3.25","13"});
        data.add(new String[]{"6633225599884","9.35","6"});
        data.add(new String[]{"9988772200220","5.68","8"});
        data.add(new String[]{"1122334455667","1.85","4"});
        data.add(new String[]{"9966558877442","5.77","5"});
        data.add(new String[]{"6633225588221","4.58","2"});
        data.add(new String[]{"9955441122665","3.25","13"});
        data.add(new String[]{"6633225599884","9.35","6"});
        data.add(new String[]{"9988772200220","5.68","8"});
        data.add(new String[]{"1122334455667","1.85","4"});
        data.add(new String[]{"9966558877442","5.77","5"});
        data.add(new String[]{"6633225588221","4.58","2"});
        data.add(new String[]{"9955441122665","3.25","13"});
        data.add(new String[]{"6633225599884","9.35","6"});
        data.add(new String[]{"9988772200220","5.68","8"});
        data.add(new String[]{"1122334455667","1.85","4"});
        data.add(new String[]{"9966558877442","5.77","5"});
        data.add(new String[]{"6633225588221","4.58","2"});
        data.add(new String[]{"9955441122665","3.25","13"});
        data.add(new String[]{"6633225599884","9.35","6"});
        data.add(new String[]{"9988772200220","5.68","8"});
        data.add(new String[]{"1122334455667","1.85","4"});
        data.add(new String[]{"9966558877442","5.77","5"});
        data.add(new String[]{"6633225588221","4.58","2"});
        data.add(new String[]{"9955441122665","3.25","13"});
        data.add(new String[]{"6633225599884","9.35","6"});
        data.add(new String[]{"9988772200220","5.68","8"});
        data.add(new String[]{"1122334455667","1.85","4"});
        data.add(new String[]{"9966558877442","5.77","5"});
        data.add(new String[]{"6633225588221","4.58","2"});
        data.add(new String[]{"9955441122665","3.25","13"});
        data.add(new String[]{"6633225599884","9.35","6"});
        data.add(new String[]{"9988772200220","5.68","8"});
        data.add(new String[]{"1122334455667","1.85","4"});
        data.add(new String[]{"9966558877442","5.77","5"});
        data.add(new String[]{"6633225588221","4.58","2"});
        data.add(new String[]{"9955441122665","3.25","13"});
        data.add(new String[]{"6633225599884","9.35","6"});
        data.add(new String[]{"9988772200220","5.68","8"});
*/

        Date date = new Date();
        Calendar calendar  = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String firstDay   = format.format(calendar.getTime());

        //Log.i("APP","InitialDay: "+firstDay);


        String[] parameters = {String.valueOf(user_id)};
        String queryReferences =    "SELECT id,codigo_barras " +
                "FROM   referencias " +
                "WHERE  cliente_id = ?";

        Cursor cursor = db.rawQuery(queryReferences, parameters);


        while (cursor.moveToNext()){

            String[] parametersInitial = {firstDay,"1",firstDay,cursor.getString(0)};
            String queryInventoryInitial =  "SELECT     count(*) " +
                    "FROM       inventario " +
                    "LEFT JOIN  envios " +
                    "ON         envios.id = inventario.envio_id " +
                    "WHERE      Datetime(fecha_ingreso) < Datetime(?)                                           AND " +
                    "           (inventario.estado_id = ? OR (Datetime(envios.fecha_reservado > Datetime(?))))  AND " +
                    "           referencia_id = ? ";
            Cursor cursorInitial = db.rawQuery(queryInventoryInitial,parametersInitial);
            Float amountFirstDay = 0f;
            if (cursorInitial.moveToFirst()){
                amountFirstDay = cursorInitial.getFloat(0);
            }
            Log.i("APP","InitialAmount: "+cursor.getString(1)+" "+amountFirstDay);




            String[] parametersCurrent = {"1",cursor.getString(0)};
            String queryInventoryCurrent =  "SELECT count(*) " +
                    "FROM   inventario " +
                    "WHERE  estado_id = ?       AND " +
                    "       referencia_id = ?";
            Cursor cursorCurrent = db.rawQuery(queryInventoryCurrent,parametersCurrent);
            Float amountCurrentDay = 0f;
            if (cursorCurrent.moveToFirst())
                amountCurrentDay = cursorCurrent.getFloat(0);
            Log.i("APP","currentAmount: "+cursor.getString(1)+" "+amountCurrentDay);




            Float averageInventory = ( amountFirstDay + amountCurrentDay ) / 2;
            Log.i("APP","averageAmount: "+cursor.getString(1)+" "+averageInventory);




            String[] parametersDispatched = {firstDay,cursor.getString(0)};
            String queryDispatched =    "SELECT     count(*) " +
                    "FROM       inventario " +
                    "INNER JOIN envios " +
                    "ON         envios.id = inventario.envio_id " +
                    "WHERE      Datetime(envios.fecha_reservado) > Datetime(?) AND " +
                    "           referencia_id = ?";
            Cursor cursorDispatched = db.rawQuery(queryDispatched,parametersDispatched);
            Float amountDispatched = 0f;
            if (cursorDispatched.moveToFirst())
                amountDispatched = cursorDispatched.getFloat(0);
            Log.i("APP","dispatchedAmount: "+cursor.getString(1)+" "+amountDispatched);


            String[] parametersDateOld = {cursor.getString(0)};
            String queryDateOld =   "SELECT     fecha_ingreso " +
                                    "FROM       inventario " +
                                    "WHERE      referencia_id = ? " +
                                    "ORDER BY   datetime(fecha_ingreso) " +
                                    "LIMIT      1 ";
            Cursor cursorDateOld = db.rawQuery(queryDateOld,parametersDateOld);
            String dateOld = "";
            if (cursorDateOld.moveToFirst())
                dateOld = cursorDateOld.getString(0);


            if (averageInventory != 0){

                int rotationIndicator = Math.round (amountDispatched / averageInventory);
                Log.i("APP","rotationIndicator: "+cursor.getString(1)+" "+rotationIndicator);

                data.add(new String[]{cursor.getString(1),String.valueOf(rotationIndicator),dateOld});
            }

        }


        db.close();

        return data;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Rotación De inventario");
    }
    
}
