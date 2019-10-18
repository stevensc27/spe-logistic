package com.example.spe_logistic.ui.inventory;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.example.spe_logistic.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

import static com.example.spe_logistic.R.color.colorGreenSpe;
import static com.example.spe_logistic.R.color.colorOrangeSpe;
import static com.example.spe_logistic.R.color.colorPurpleSpe;

public class InventoryFragment extends Fragment {

    private InventoryViewModel inventoryViewModel;

    HorizontalBarChart horizontalBarChartInventory;
    BarDataSet         horizontalBarDataSet;
    BarData            horizontalBarData;
    XAxis              horizontalBarChartXAxis;
    
    BarChart   barChartSend;
    BarDataSet barDataSetIn;
    BarDataSet barDataSetOut;
    BarData    barData;
    XAxis      barChartXAxis;

    ArrayList<BarEntry> in  = new ArrayList<>();
    ArrayList<BarEntry> out = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        inventoryViewModel =
                ViewModelProviders.of(this).get(InventoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_inventory, container, false);

        fillBarChartInventory(root);
        
        fillBarChartSend(root);

        return root;
    }


    private void fillBarChartInventory(View root) {

        horizontalBarChartInventory = (HorizontalBarChart) root.findViewById(R.id.horizontalBarChartInventory);
        
        // find labels for bar chart of inventory
        inventoryViewModel.getBarInventoryListLabel().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(@Nullable final ArrayList<String> bar_inventory_array_list_label) {
                horizontalBarChartXAxis = horizontalBarChartInventory.getXAxis();
                horizontalBarChartXAxis.setDrawGridLines(false);
                horizontalBarChartXAxis.setPosition(XAxis.XAxisPosition.TOP);
                horizontalBarChartXAxis.setGranularity(1f);
                horizontalBarChartXAxis.setDrawLabels(true);
                horizontalBarChartXAxis.setDrawAxisLine(false);
                horizontalBarChartXAxis.setDrawGridLines(false);
                horizontalBarChartXAxis.setValueFormatter(new IndexAxisValueFormatter(bar_inventory_array_list_label));
            }
        });
        
        // find values for bar chart of inventory
        inventoryViewModel.getBarInventoryList().observe(this, new Observer<ArrayList<BarEntry>>() {
            @Override
            public void onChanged(@Nullable ArrayList<BarEntry> bar_inventory_array_list) {
                horizontalBarDataSet = new BarDataSet(bar_inventory_array_list,"");
                horizontalBarDataSet.setColor(getResources().getColor(colorOrangeSpe));
                horizontalBarData = new BarData(horizontalBarDataSet);
                horizontalBarChartInventory.setData(horizontalBarData);
            }
        });
        
        horizontalBarChartInventory.getAxisRight().setEnabled(false);
        horizontalBarChartInventory.getAxisLeft().setEnabled(false);
        horizontalBarChartInventory.getLegend().setEnabled(false);
        horizontalBarChartInventory.getDescription().setEnabled(false);
        horizontalBarChartInventory.animateY(3000);
        
    }

    private void fillBarChartSend(View root) {
        
        barChartSend = (BarChart) root.findViewById(R.id.barChartSend);



        // find labels for bar chart of sends
        inventoryViewModel.getBarSendListLabel().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(@Nullable ArrayList<String> bar_send_array_list_label) {
                barChartXAxis = barChartSend.getXAxis();
                barChartXAxis.setDrawGridLines(false);
                barChartXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                barChartXAxis.setDrawLabels(true);
                barChartXAxis.setDrawAxisLine(false);
                barChartXAxis.setDrawGridLines(false);
                barChartXAxis.setCenterAxisLabels(true);
                barChartXAxis.setValueFormatter(new IndexAxisValueFormatter(bar_send_array_list_label));
            }
        });


        // find values for bar chart of sends
        inventoryViewModel.getBarSendListIn().observe(this, new Observer<ArrayList<BarEntry>>() {
            @Override
            public void onChanged(@Nullable ArrayList<BarEntry> bar_send_array_list_in) {

                barDataSetIn = new BarDataSet(bar_send_array_list_in,"Entradas");
                barDataSetIn.setColor(getResources().getColor(colorPurpleSpe));

            }
        });

        inventoryViewModel.getBarSendListOut().observe(this, new Observer<ArrayList<BarEntry>>() {
            @Override
            public void onChanged(@Nullable ArrayList<BarEntry> bar_send_array_list_out) {

                barDataSetOut = new BarDataSet(bar_send_array_list_out,"Salidas");
                barDataSetOut.setColor(getResources().getColor(colorGreenSpe));

                barData = new BarData(barDataSetIn,barDataSetOut);
                barChartSend.setData(barData);

                barChartSend.setDragEnabled(true);
                barChartSend.setVisibleXRangeMaximum(4);

                float barSpace = 0.01f;
                float groupSpace = 0.08f;

                barData.setBarWidth(0.2f);
                barChartSend.getXAxis().setAxisMinimum(0);
                barChartSend.getXAxis().setAxisMaximum(barChartSend.getBarData().getGroupWidth(groupSpace,barSpace)*6);

                barChartSend.getAxisLeft().setAxisMinimum(0);

                barChartSend.groupBars(0,groupSpace,barSpace);
                barChartSend.invalidate();

            }
        });




        barChartSend.getAxisRight().setEnabled(false);
        barChartSend.getAxisLeft().setEnabled(false);
        barChartSend.getLegend().setEnabled(false);
        barChartSend.getDescription().setEnabled(false);
        barChartSend.animateY(3000);
    }
}