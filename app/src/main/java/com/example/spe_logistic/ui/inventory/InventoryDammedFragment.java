package com.example.spe_logistic.ui.inventory;


import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spe_logistic.R;
import com.example.spe_logistic.SQLiteConnectionHelper;
import com.example.spe_logistic.TemplatePDF;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.spe_logistic.R.color.colorOrangeSpe;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryDammedFragment extends Fragment implements PermissionListener {

    private SQLiteConnectionHelper con;
    private int                    user_id;
    private ArrayList<String[]>    table_inventory_array_list;
    private View                   root;
    private TableLayout            tableDammedFull;
    private String[]               header = {"Referencia","Cantidad","Fecha de ingreso"};

    public InventoryDammedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_inventory_dammed, container, false);

        SharedPreferences preferences = getContext().getSharedPreferences("credentials", getContext().MODE_PRIVATE);
        user_id = preferences.getInt("user_id",0);

        con = new SQLiteConnectionHelper(getContext(),"SPEDB",null,1);

        getDataTable();

        fillDataTable();

        return root;
    }

    private void getDataTable(){
        SQLiteDatabase db = con.getReadableDatabase();

        table_inventory_array_list = new ArrayList<>();

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
                "ORDER BY   Datetime(fecha_ingreso)";

        Cursor cursor = db.rawQuery(queryInventoryDammed,parameters);

        while (cursor.moveToNext()){

            table_inventory_array_list.add(new String[]{cursor.getString(0),cursor.getString(1),cursor.getString(2)});
        }

        db.close();
    }

    private void fillDataTable(){

        tableDammedFull = (TableLayout) root.findViewById(R.id.tableDammedFull);

        TableRow tableRow;
        TextView textCell;

        int indexRow;
        int indexCell;

        for (indexRow = 0; indexRow < table_inventory_array_list.size(); indexRow++){
            tableRow = new TableRow(getContext());
            indexCell = 0;
            while (indexCell<3) {

                textCell = new TextView(getContext());

                textCell.setGravity(Gravity.CENTER);
                textCell.setText(table_inventory_array_list.get(indexRow)[indexCell]);
                textCell.setBackgroundColor(Color.WHITE);
                textCell.setTextSize(15);

                tableRow.addView(textCell, newTableRowParams());

                indexCell++;
            }

            tableRow.setBackgroundColor(getResources().getColor(colorOrangeSpe));

            tableDammedFull.addView(tableRow);
        }
    }

    private TableRow.LayoutParams newTableRowParams() {
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight = 1;
        return params;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Inventario Represado");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar_menu_options, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_pdf:
                if (table_inventory_array_list.size() > 0){
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        export();
                    }else {
                        requestPermission();
                    }
                }else {
                    Toast.makeText(getContext(),"No hay datos para exportar",Toast.LENGTH_LONG).show();
                }

                break;

        }
        return true;
    }

    private void export() {

        TemplatePDF templatePDF = new TemplatePDF(getContext(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        templatePDF.openDocument();
        templatePDF.addMetaData("SPE Logistica","Inventario represado en bodega","SPE");
        templatePDF.addTitles("Servicios Postales Especializados SAS","Inventario represado en bodega",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        templatePDF.createTable(header,table_inventory_array_list);
        templatePDF.closeDocument();
        templatePDF.appViewPdf(getActivity());

    }

    private void requestPermission() {
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(this)
                .check();
    }

    @Override
    public void onPermissionGranted(PermissionGrantedResponse response) {
        export();
    }

    @Override
    public void onPermissionDenied(PermissionDeniedResponse response) {

    }

    @Override
    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
        token.continuePermissionRequest();
    }

}
