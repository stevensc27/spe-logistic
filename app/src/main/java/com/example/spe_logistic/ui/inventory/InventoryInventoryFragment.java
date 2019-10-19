package com.example.spe_logistic.ui.inventory;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spe_logistic.R;
import com.github.mikephil.charting.charts.BarChart;
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

    public InventoryInventoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_inventory_inventory, container, false);

        getDataInventory(root);

        horizontalBarDataSet = new BarDataSet(barEntryArrayList,"");
        horizontalBarDataSet.setColor(getResources().getColor(colorOrangeSpe));
        horizontalBarData = new BarData(horizontalBarDataSet);
        horizontalBarChartInventory.setData(horizontalBarData);

        horizontalBarChartInventory.getAxisRight().setEnabled(false);
        //horizontalBarChartInventory.getAxisLeft().setEnabled(false);
        horizontalBarChartInventory.getLegend().setEnabled(false);
        horizontalBarChartInventory.getDescription().setEnabled(false);
        horizontalBarChartInventory.animateY(1500);
        horizontalBarChartInventory.setVisibleXRangeMaximum(20);
        //horizontalBarChartInventory.getAxisLeft().setAxisMinimum(0);

        horizontalBarChartXAxis = horizontalBarChartInventory.getXAxis();
        horizontalBarChartXAxis.setValueFormatter(new IndexAxisValueFormatter(barEntryLabelArrayList));
        horizontalBarChartXAxis.setDrawGridLines(false);
        horizontalBarChartXAxis.setPosition(XAxis.XAxisPosition.TOP);
        horizontalBarChartXAxis.setGranularity(1f);
        horizontalBarChartXAxis.setDrawLabels(true);
        horizontalBarChartXAxis.setDrawAxisLine(true);
        horizontalBarChartXAxis.setDrawGridLines(false);
        horizontalBarChartXAxis.setLabelCount(33);


        return root;
    }

    private void getDataInventory(View root) {
        horizontalBarChartInventory = (HorizontalBarChart) root.findViewById(R.id.horizontalBarChartInventoryFull);

        barEntryArrayList = new ArrayList<>();
        barEntryLabelArrayList = new ArrayList<>();

        barEntryArrayList.add(new BarEntry(1,5));
        barEntryArrayList.add(new BarEntry(2,10));
        barEntryArrayList.add(new BarEntry(3,15));
        barEntryArrayList.add(new BarEntry(4,20));
        barEntryArrayList.add(new BarEntry(5,25));
        barEntryArrayList.add(new BarEntry(6,30));
        barEntryArrayList.add(new BarEntry(7,35));
        barEntryArrayList.add(new BarEntry(8,40));
        barEntryArrayList.add(new BarEntry(9,45));
        barEntryArrayList.add(new BarEntry(10,50));
        barEntryArrayList.add(new BarEntry(11,55));
        barEntryArrayList.add(new BarEntry(12,60));
        barEntryArrayList.add(new BarEntry(13,65));
        barEntryArrayList.add(new BarEntry(14,70));
        barEntryArrayList.add(new BarEntry(15,75));
        barEntryArrayList.add(new BarEntry(16,80));
        barEntryArrayList.add(new BarEntry(17,85));
        barEntryArrayList.add(new BarEntry(18,90));
        barEntryArrayList.add(new BarEntry(19,95));
        barEntryArrayList.add(new BarEntry(20,100));
        barEntryArrayList.add(new BarEntry(21,105));
        barEntryArrayList.add(new BarEntry(22,110));
        barEntryArrayList.add(new BarEntry(23,115));
        barEntryArrayList.add(new BarEntry(24,120));
        barEntryArrayList.add(new BarEntry(25,125));
        barEntryArrayList.add(new BarEntry(26,130));
        barEntryArrayList.add(new BarEntry(27,135));
        barEntryArrayList.add(new BarEntry(28,140));
        barEntryArrayList.add(new BarEntry(29,145));
        barEntryArrayList.add(new BarEntry(30,150));
        barEntryArrayList.add(new BarEntry(31,155));
        barEntryArrayList.add(new BarEntry(32,160));
        barEntryArrayList.add(new BarEntry(33,165));

        barEntryLabelArrayList.add("");
        barEntryLabelArrayList.add("223355114466");
        barEntryLabelArrayList.add("222299995555");
        barEntryLabelArrayList.add("556622884400");
        barEntryLabelArrayList.add("335558889999");
        barEntryLabelArrayList.add("995588774466");
        barEntryLabelArrayList.add("223355114466");
        barEntryLabelArrayList.add("222299995555");
        barEntryLabelArrayList.add("556622884400");
        barEntryLabelArrayList.add("335558889999");
        barEntryLabelArrayList.add("995588774466");
        barEntryLabelArrayList.add("223355114466");
        barEntryLabelArrayList.add("222299995555");
        barEntryLabelArrayList.add("556622884400");
        barEntryLabelArrayList.add("335558889999");
        barEntryLabelArrayList.add("995588774466");
        barEntryLabelArrayList.add("223355114466");
        barEntryLabelArrayList.add("222299995555");
        barEntryLabelArrayList.add("556622884400");
        barEntryLabelArrayList.add("335558889999");
        barEntryLabelArrayList.add("995588774466");
        barEntryLabelArrayList.add("223355114466");
        barEntryLabelArrayList.add("222299995555");
        barEntryLabelArrayList.add("556622884400");
        barEntryLabelArrayList.add("335558889999");
        barEntryLabelArrayList.add("995588774466");
        barEntryLabelArrayList.add("223355114466");
        barEntryLabelArrayList.add("222299995555");
        barEntryLabelArrayList.add("556622884400");
        barEntryLabelArrayList.add("335558889999");
        barEntryLabelArrayList.add("995588774466");
        barEntryLabelArrayList.add("223355114466");
        barEntryLabelArrayList.add("222299995555");
        barEntryLabelArrayList.add("556622884400");

    }
}
