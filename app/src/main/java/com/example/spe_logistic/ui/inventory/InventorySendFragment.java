package com.example.spe_logistic.ui.inventory;


import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
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
import com.example.spe_logistic.utilities.Utilities;
import com.github.mikephil.charting.charts.BarChart;
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

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.spe_logistic.R.color.colorGreenSpe;
import static com.example.spe_logistic.R.color.colorOrangeSpe;
import static com.example.spe_logistic.R.color.colorPurpleSpe;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventorySendFragment extends Fragment implements PermissionListener {

    private ArrayList<BarEntry> inputs;
    private ArrayList<BarEntry> outputs;
    private ArrayList<String> labels;
    private BarChart barChart;
    private BarDataSet barDataSetIn;
    private BarDataSet barDataSetOut;
    private BarData barData;
    private float barSpace = 0.02f;
    private float groupSpace = 0.37f;

    private TableLayout tableSend;
    private TableRow tableRow;
    private TextView textCell;
    private int indexRow;
    private int indexCell;
    private ArrayList<String[]> data;

    private SQLiteConnectionHelper con;
    private int user_id;

    private String[] header = {"Mes","Entradas (Unidades)","Salidas (Unidades)"};

    public InventorySendFragment() {
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
        View root = inflater.inflate(R.layout.fragment_inventory_send, container, false);

        getDataSend(root);

        fillSendTable(root);

        return root;
    }

    private void getDataSend(View root) {

        barChart = (BarChart) root.findViewById(R.id.barChartSendFull);

        getInventory();

        barDataSetIn = new BarDataSet(inputs, "Entradas");
        barDataSetOut = new BarDataSet(outputs, "Salidas");

        barDataSetIn.setColor(getResources().getColor(colorPurpleSpe));
        barDataSetOut.setColor(getResources().getColor(colorGreenSpe));

        barData = new BarData(barDataSetIn, barDataSetOut);
        barData.setBarWidth(0.3f);

        barChart.setData(barData);
        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(5);
        barChart.getXAxis().setAxisMinimum(0);
        barChart.getXAxis().setAxisMaximum(0+barChart.getBarData().getGroupWidth(groupSpace, barSpace) * 12);
        barChart.groupBars(0, groupSpace, barSpace);
        barChart.invalidate();
        barChart.getAxisRight().setEnabled(false);
        barChart.getDescription().setEnabled(false);

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart.getXAxis().setDrawLabels(true);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.TOP);
        barChart.getXAxis().setCenterAxisLabels(true);
        barChart.getXAxis().setGranularity(1);
        barChart.getXAxis().setGranularityEnabled(true);
        barChart.getXAxis().setDrawAxisLine(false);
        barChart.getXAxis().setDrawGridLines(false);
        //barChart.getXAxis().setLabelCount(12, true);

        barChart.animateY(Utilities.ANIMATION);

    }

    private void getInventory() {

        con = new SQLiteConnectionHelper(getContext(), "SPEDB", null, 1);
        SQLiteDatabase db = con.getReadableDatabase();

        SharedPreferences preferences = getContext().getSharedPreferences("credentials", getContext().MODE_PRIVATE);
        user_id = preferences.getInt("user_id", 0);

        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
        labels = new ArrayList<>();
        data = new ArrayList<>();

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        DecimalFormat formatter = new DecimalFormat("00");

        calendar.add(Calendar.MONTH, -11);

        db.execSQL("DROP TABLE IF EXISTS months");
        db.execSQL("CREATE TABLE months (id TEXT, name TEXT, client TEXT)");

        String[] parameters = null;
        for (int i = 1; i <= 12; i++) {

            String monthBefore = formatter.format(calendar.get(Calendar.MONTH) + 1);
            String dateBefore = calendar.get(Calendar.YEAR) + "" + monthBefore;
            parameters = new String[]{dateBefore, String.valueOf(user_id)};

            String month = "";
            switch (monthBefore) {
                case "01":
                    month = "ENE";
                    break;
                case "02":
                    month = "FEB";
                    break;
                case "03":
                    month = "MAR";
                    break;
                case "04":
                    month = "ABR";
                    break;
                case "05":
                    month = "MAY";
                    break;
                case "06":
                    month = "JUN";
                    break;
                case "07":
                    month = "JUL";
                    break;
                case "08":
                    month = "AGO";
                    break;
                case "09":
                    month = "SEP";
                    break;
                case "10":
                    month = "OCT";
                    break;
                case "11":
                    month = "NOV";
                    break;
                case "12":
                    month = "DIC";
                    break;
            }

            labels.add(month);

            String queryIn = "SELECT     count(*) " +
                    "FROM       inventario " +
                    "INNER JOIN referencias " +
                    "ON         referencias.id = inventario.referencia_id " +
                    "WHERE      strftime('%Y%m',fecha_ingreso) = ? AND " +
                    "           referencias.cliente_id = ? ";

            Cursor cursorIn = db.rawQuery(queryIn, parameters);
            cursorIn.moveToFirst();
            inputs.add(new BarEntry(i, cursorIn.getInt(0)));

            String queryOut =   "SELECT     count(*) " +
                                "FROM       inventario " +
                                "INNER JOIN envios " +
                                "ON         envios.id = inventario.envio_id " +
                                "INNER JOIN referencias " +
                                "ON         referencias.id = inventario.referencia_id " +
                                "WHERE      strftime('%Y%m',envios.fecha_reservado) = ? AND " +
                                "           referencias.cliente_id = ? ";

            Cursor cursorOut = db.rawQuery(queryOut, parameters);
            cursorOut.moveToFirst();
            outputs.add(new BarEntry(i, cursorOut.getInt(0)));


            data.add(new String[]{month, cursorIn.getString(0), cursorOut.getString(0)});
            calendar.add(Calendar.MONTH, +1);
        }

        db.close();

    }

    private void fillSendTable(View root) {

        tableSend = (TableLayout) root.findViewById(R.id.tableSend);

        for (indexRow = 0; indexRow < data.size(); indexRow++) {
            tableRow = new TableRow(getContext());
            indexCell = 0;
            while (indexCell < 3) {

                textCell = new TextView(getContext());

                textCell.setGravity(Gravity.CENTER);
                textCell.setText(data.get(indexRow)[indexCell]);
                textCell.setBackgroundColor(Color.WHITE);
                textCell.setTextSize(15);

                tableRow.addView(textCell, newTableRowParams());

                indexCell++;
            }

            tableRow.setBackgroundColor(getResources().getColor(colorOrangeSpe));

            tableSend.addView(tableRow);
        }
    }

    private TableRow.LayoutParams newTableRowParams() {
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1, 1, 1, 1);
        params.weight = 1;
        return params;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Entradas Y Salidas Por Mes");
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
                if (data.size() > 0){
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
        templatePDF.addMetaData("SPE Logistica","Entradas y salidas de inventario","SPE");
        templatePDF.addTitles("Servicios Postales Especializados SAS","Entradas y salidas de inventario",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        templatePDF.createTable(header,data);
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
