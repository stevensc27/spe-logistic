package com.example.spe_logistic.ui.inventory;


import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spe_logistic.R;
import com.example.spe_logistic.SQLiteConnectionHelper;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

import static com.example.spe_logistic.R.color.colorOrangeSpe;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryInventoryFragment extends Fragment {

    HorizontalBarChart  horizontalBarChartInventory;
    BarDataSet          horizontalBarDataSet;
    BarData             horizontalBarData;
    XAxis               horizontalBarChartXAxis;
    ArrayList<BarEntry> barEntryArrayList;
    ArrayList<String>   barEntryLabelArrayList;

    private SQLiteConnectionHelper con;
    private int user_id;

    public InventoryInventoryFragment() {
        // Required empty public constructor
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
        horizontalBarChartInventory.animateY(1500);

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
        while (cursor.moveToNext()){

            barEntryArrayList.add(new BarEntry(i,cursor.getFloat(0)));
            barEntryLabelArrayList.add(cursor.getString(1));
            i++;
        }

        db.close();

    }
}
