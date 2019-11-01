package com.example.spe_logistic.ui.inventory;


import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.spe_logistic.R;
import com.example.spe_logistic.SQLiteConnectionHelper;
import com.example.spe_logistic.TemplatePDF;
import com.example.spe_logistic.utilities.Utilities;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.spe_logistic.R.color.colorOrangeSpe;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryInventoryFragment extends Fragment implements PermissionListener {

    private HorizontalBarChart  horizontalBarChartInventory;
    private BarDataSet          horizontalBarDataSet;
    private BarData             horizontalBarData;
    private XAxis               horizontalBarChartXAxis;
    private ArrayList<BarEntry> barEntryArrayList;
    private ArrayList<String>   barEntryLabelArrayList;

    private SQLiteConnectionHelper con;
    private int user_id;
    private String[] header = {"Código de barras","Cantidad"};
    private ArrayList<String[]> generalData;

    public InventoryInventoryFragment() {
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
        View root = inflater.inflate(R.layout.fragment_inventory_inventory, container, false);

        con = new SQLiteConnectionHelper(getContext(),"SPEDB",null,1);

        SharedPreferences preferences = getContext().getSharedPreferences("credentials", getContext().MODE_PRIVATE);
        user_id = preferences.getInt("user_id",0);

        getDataInventory(root);

        horizontalBarDataSet = new BarDataSet(barEntryArrayList,"");
        horizontalBarDataSet.setColor(getResources().getColor(colorOrangeSpe));
        horizontalBarData = new BarData(horizontalBarDataSet);
        horizontalBarChartInventory.setData(horizontalBarData);

        horizontalBarChartInventory.getAxisRight().setEnabled(false);
        horizontalBarChartInventory.getAxisLeft().setEnabled(true);
        horizontalBarChartInventory.getAxisLeft().setAxisMinimum(0);
        horizontalBarChartInventory.getAxisLeft().setTextSize(10);
        horizontalBarChartInventory.getLegend().setEnabled(false);
        horizontalBarChartInventory.getDescription().setEnabled(false);
        horizontalBarChartInventory.animateY(Utilities.ANIMATION);

        horizontalBarChartXAxis = horizontalBarChartInventory.getXAxis();
        horizontalBarChartXAxis.setValueFormatter(new IndexAxisValueFormatter(barEntryLabelArrayList));
        horizontalBarChartXAxis.setPosition(XAxis.XAxisPosition.TOP);
        horizontalBarChartXAxis.setGranularity(1f);
        horizontalBarChartXAxis.setDrawLabels(true);
        horizontalBarChartXAxis.setDrawAxisLine(false);
        horizontalBarChartXAxis.setDrawGridLines(false);

        return root;
    }

    private void getDataInventory(View root) {
        horizontalBarChartInventory = (HorizontalBarChart) root.findViewById(R.id.horizontalBarChartInventoryFull);

        barEntryArrayList = new ArrayList<>();
        barEntryLabelArrayList = new ArrayList<>();

        SQLiteDatabase db = con.getReadableDatabase();

        String[] parameters = {String.valueOf(user_id)};
        String queryReferences =    "SELECT     count(*) AS amount, "                       +
                "           referencias.codigo_barras "                 +
                "FROM       inventario "                                +
                "INNER JOIN referencias "                               +
                "ON         referencias.id = inventario.referencia_id " +
                "WHERE      referencias.cliente_id = ? AND "            +
                "           inventario.estado_id = 1 "                  +
                "GROUP BY   codigo_barras "                             +
                "ORDER BY   amount ";

        Cursor cursor = db.rawQuery(queryReferences,parameters);

        int i = 0;
        // here desc, in chart asc
        // x order
        // y quantities per unit
        generalData = new ArrayList<>();
        while (cursor.moveToNext()){

            barEntryArrayList.add(new BarEntry(i,cursor.getInt(0)));
            barEntryLabelArrayList.add(cursor.getString(1));

            generalData.add(new String[]{cursor.getString(1),cursor.getString(0)});

            i++;
        }

        db.close();

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Unidades Por Referencia");
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
                if (generalData.size() > 0){
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

    private void requestPermission() {
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(this)
                .check();
    }

    private void export() {

        /*horizontalBarChartInventory.setBackgroundColor(Color.WHITE);
        horizontalBarChartXAxis.setTextColor(Color.BLACK);


        if (!horizontalBarChartInventory.saveToPath("file","Pictures"))
            Log.i("APP","NO SAVE");*/

        TemplatePDF templatePDF = new TemplatePDF(getContext(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        templatePDF.openDocument();
        templatePDF.addMetaData("SPE Logistica","Inventario disponible en bodega","SPE");
        templatePDF.addTitles("Servicios Postales Especializados SAS","Inventario disponible en bodega",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        templatePDF.createTable(header,generalData);
        templatePDF.closeDocument();
        templatePDF.appViewPdf(getActivity());

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
