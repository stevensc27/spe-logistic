package com.example.spe_logistic.ui.inventory;


import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.spe_logistic.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import static com.example.spe_logistic.R.color.colorGreenSpe;
import static com.example.spe_logistic.R.color.colorGrey;
import static com.example.spe_logistic.R.color.colorOrangeSpe;
import static com.example.spe_logistic.R.color.colorPurpleSpe;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventorySendFragment extends Fragment {

    ArrayList<BarEntry> inputs;
    ArrayList<BarEntry> outputs;
    BarChart            barChart;
    BarDataSet          barDataSetIn;
    BarDataSet          barDataSetOut;
    BarData             barData;
    float               barSpace = 0.04f;
    float               groupSpace = 0.15f;

    private TableLayout         tableSend;
    private TableRow            tableRow;
    private TextView            textCell;
    private int                 indexRow;
    private int                 indexCell;
    private ArrayList<String[]> data;

    public InventorySendFragment() {
        // Required empty public constructor
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

        inputs  = new ArrayList<>();
        outputs = new ArrayList<>();

        inputs  = getInventoryIn();
        outputs = getInventoryOut();

        barDataSetIn  = new BarDataSet(inputs,"Entradas");
        barDataSetOut = new BarDataSet(outputs,"Salidas");

        barDataSetIn.setColor(getResources().getColor(colorPurpleSpe));
        barDataSetOut.setColor(getResources().getColor(colorGreenSpe));

        barData  = new BarData(barDataSetIn,barDataSetOut);
        barData.setBarWidth(0.3f);

        barChart.setData(barData);
        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(4);
        barChart.getXAxis().setAxisMinimum(0);
        barChart.getXAxis().setAxisMaximum(barChart.getBarData().getGroupWidth(groupSpace,barSpace)*12);
        //barChart.getAxisLeft().setAxisMinimum(0);
        barChart.groupBars(0,groupSpace,barSpace);
        barChart.invalidate();
        barChart.getAxisRight().setEnabled(false);
        //barChart.getAxisLeft().setEnabled(false);
        barChart.getDescription().setEnabled(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setDrawLabels(false);
        barChart.getXAxis().setLabelCount(12,true);
        barChart.getXAxis().setDrawAxisLine(false);
        barChart.getXAxis().setDrawGridLines(false);


        barChart.animateY(1500);

    }

    private ArrayList<BarEntry> getInventoryIn(){

        ArrayList<BarEntry> data = new ArrayList<>();

        data.add(new BarEntry(1,65));
        data.add(new BarEntry(2,54));
        data.add(new BarEntry(3,65));
        data.add(new BarEntry(4,84));
        data.add(new BarEntry(5,54));
        data.add(new BarEntry(6,54));
        data.add(new BarEntry(7,21));
        data.add(new BarEntry(8,96));
        data.add(new BarEntry(9,15));
        data.add(new BarEntry(10,21));
        data.add(new BarEntry(11,65));
        data.add(new BarEntry(12,54));

        return data;
    }

    private ArrayList<BarEntry> getInventoryOut(){

        ArrayList<BarEntry> data = new ArrayList<>();

        data.add(new BarEntry(1,25));
        data.add(new BarEntry(2,96));
        data.add(new BarEntry(3,63));
        data.add(new BarEntry(4,56));
        data.add(new BarEntry(5,74));
        data.add(new BarEntry(6,85));
        data.add(new BarEntry(7,69));
        data.add(new BarEntry(8,02));
        data.add(new BarEntry(9,65));
        data.add(new BarEntry(10,56));
        data.add(new BarEntry(11,10));
        data.add(new BarEntry(12,92));

        return data;
    }

    private void fillSendTable(View root) {

        tableSend = (TableLayout) root.findViewById(R.id.tableSend);

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

            tableSend.addView(tableRow);
        }
    }

    private TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight = 1;
        return params;
    }

    private ArrayList<String[]> getTableData(){

        ArrayList<String[]>  data = new ArrayList<>();

        data.add(new String[]{"ENE","45","7575"});
        data.add(new String[]{"FEB","5465","9875"});
        data.add(new String[]{"MAR","9863564","75714"});
        data.add(new String[]{"ABR","6254","7575"});
        data.add(new String[]{"MAY","565","867687"});
        data.add(new String[]{"JUN","21212","543543"});
        data.add(new String[]{"JUL","5654","543543"});
        data.add(new String[]{"AGO","6354","54354"});
        data.add(new String[]{"SEP","21321","3543"});
        data.add(new String[]{"OCT","21321","3453"});
        data.add(new String[]{"NOV","212","1221"});
        data.add(new String[]{"DIC","8228","5432543"});

        return data;
    }


}
