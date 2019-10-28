package com.example.spe_logistic.ui.inventory;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.spe_logistic.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import static com.example.spe_logistic.R.color.colorGreenSpe;
import static com.example.spe_logistic.R.color.colorOrangeSpe;
import static com.example.spe_logistic.R.color.colorPurpleSpe;

public class InventoryFragment extends Fragment {

    private NavController navController;

    private InventoryViewModel inventoryViewModel;

    HorizontalBarChart horizontalBarChartInventory;
    BarDataSet horizontalBarDataSet;
    BarData horizontalBarData;
    XAxis horizontalBarChartXAxis;

    BarChart barChartSend;
    BarDataSet barDataSetIn;
    BarDataSet barDataSetOut;
    BarData barData;
    XAxis barChartXAxis;
    float barSpace = 0.01f;
    float groupSpace = 0.08f;

    PieChart pieChartInventory;
    PieDataSet pieDataSet;
    PieData pieData;
    TableLayout tableDammed;
    Description pieDes;

    Button buttomHorizontalBar;
    Button buttonBar;
    Button buttonPie;
    Button buttonDammed;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        inventoryViewModel =
                ViewModelProviders.of(this).get(InventoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_inventory, container, false);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        buttomHorizontalBar = root.findViewById(R.id.buttonHorizontalBar);
        buttonBar = root.findViewById(R.id.buttonBar);
        buttonPie = root.findViewById(R.id.buttonPie);
        buttonDammed = root.findViewById(R.id.buttonDammed);

        buttomHorizontalBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.inventoryInventoryFragment);
            }
        });

        buttonBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.inventorySendFragment);
            }
        });

        buttonPie.setOnClickListener(v -> {
            navController.navigate(R.id.inventoryRotationFragment);
        });

        buttonDammed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.inventoryDammedFragment);
            }
        });

        fillBarChartInventory(root);
        fillBarChartSend(root);
        fillPieChartInventory(root);
        fillTableDammed(root);


        return root;
    }

    private void fillBarChartInventory(View root) {

        horizontalBarChartInventory = (HorizontalBarChart) root.findViewById(R.id.horizontalBarChartInventory);

        // find labels for bar chart of inventory
        inventoryViewModel.getBarInventoryListLabel().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(@Nullable final ArrayList<String> bar_inventory_array_list_label) {
                horizontalBarChartXAxis = horizontalBarChartInventory.getXAxis();
                horizontalBarChartXAxis.setValueFormatter(new IndexAxisValueFormatter(bar_inventory_array_list_label));
                horizontalBarChartXAxis.setPosition(XAxis.XAxisPosition.TOP);
                horizontalBarChartXAxis.setGranularity(1f);
                horizontalBarChartXAxis.setDrawLabels(true);
                horizontalBarChartXAxis.setDrawAxisLine(false);
                horizontalBarChartXAxis.setDrawGridLines(false);
            }
        });

        // find values for bar chart of inventory
        inventoryViewModel.getBarInventoryList().observe(this, new Observer<ArrayList<BarEntry>>() {
            @Override
            public void onChanged(@Nullable ArrayList<BarEntry> bar_inventory_array_list) {
                horizontalBarDataSet = new BarDataSet(bar_inventory_array_list, "");
                horizontalBarDataSet.setColor(getResources().getColor(colorOrangeSpe));
                horizontalBarData = new BarData(horizontalBarDataSet);
                horizontalBarChartInventory.setData(horizontalBarData);
            }
        });


        horizontalBarChartInventory.getAxisRight().setEnabled(false);
        horizontalBarChartInventory.getAxisLeft().setEnabled(true);
        horizontalBarChartInventory.getAxisLeft().setAxisMinimum(0);
        horizontalBarChartInventory.getAxisLeft().setTextSize(4);
        horizontalBarChartInventory.getLegend().setEnabled(false);
        horizontalBarChartInventory.getDescription().setEnabled(false);
        horizontalBarChartInventory.animateY(1500);

    }

    private void fillBarChartSend(View root) {

        barChartSend = (BarChart) root.findViewById(R.id.barChartSend);


        // find labels for bar chart of sends
        inventoryViewModel.getBarSendListLabel().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(@Nullable ArrayList<String> bar_send_array_list_label) {
                barChartXAxis = barChartSend.getXAxis();
                barChartXAxis.setValueFormatter(new IndexAxisValueFormatter(bar_send_array_list_label));
                barChartXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                barChartXAxis.setDrawLabels(false);
                barChartXAxis.setLabelCount(6, true);
                barChartXAxis.setDrawAxisLine(false);
                barChartXAxis.setDrawGridLines(false);
                barChartXAxis.setCenterAxisLabels(true);
            }
        });


        // find values for bar chart of sends
        inventoryViewModel.getBarSendListIn().observe(this, new Observer<ArrayList<BarEntry>>() {
            @Override
            public void onChanged(@Nullable ArrayList<BarEntry> bar_send_array_list_in) {
                if (bar_send_array_list_in != null) {
                    barDataSetIn = new BarDataSet(bar_send_array_list_in, "Entradas de inventario");
                    barDataSetIn.setColor(getResources().getColor(colorPurpleSpe));
                }
            }
        });

        inventoryViewModel.getBarSendListOut().observe(this, new Observer<ArrayList<BarEntry>>() {
            @Override
            public void onChanged(@Nullable ArrayList<BarEntry> bar_send_array_list_out) {
                barDataSetOut = new BarDataSet(bar_send_array_list_out, "Salidas de inventario");
                barDataSetOut.setColor(getResources().getColor(colorGreenSpe));
                barData = new BarData(barDataSetIn, barDataSetOut);
                barChartSend.setData(barData);
                barChartSend.setDragEnabled(true);
                barChartSend.setVisibleXRangeMaximum(6);
                barData.setBarWidth(0.2f);
                barChartSend.getXAxis().setAxisMinimum(0);
                barChartSend.getXAxis().setAxisMaximum(barChartSend.getBarData().getGroupWidth(groupSpace, barSpace) * 6);
                barChartSend.getAxisLeft().setAxisMinimum(0);
                barChartSend.groupBars(0, groupSpace, barSpace);
                barChartSend.invalidate();
            }
        });

        barChartSend.getAxisRight().setEnabled(false);
        barChartSend.getAxisLeft().setEnabled(false);
        //barChartSend.getLegend().setEnabled(false);
        barChartSend.getDescription().setEnabled(false);
        barChartSend.animateY(1500);
    }

    private void fillPieChartInventory(View root) {
        pieChartInventory = (PieChart) root.findViewById(R.id.pieChartInventory);

        inventoryViewModel.getPieInventoryList().observe(this, new Observer<ArrayList<PieEntry>>() {
            @Override
            public void onChanged(@Nullable ArrayList<PieEntry> pie_inventory_array_list) {
                pieDataSet = new PieDataSet(pie_inventory_array_list, "");
                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                pieData = new PieData(pieDataSet);
                pieData.setValueTextSize(9f);
                pieData.setValueTextColor(Color.WHITE);

                pieChartInventory.setData(pieData);

                pieDes = new Description();
                pieDes.setText("Rotación de inventario");
                pieChartInventory.setDescription(pieDes);
            }

        });


        pieChartInventory.getLegend().setTextSize(9);
        pieChartInventory.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        pieChartInventory.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        pieChartInventory.getLegend().setOrientation(Legend.LegendOrientation.VERTICAL);
        pieChartInventory.getLegend().setDrawInside(false);

        pieChartInventory.setDrawSliceText(false);
        pieChartInventory.setDrawHoleEnabled(false);
        pieChartInventory.animateY(1500);


    }

    private void fillTableDammed(View root){

        tableDammed = (TableLayout) root.findViewById(R.id.tableDammed);

        inventoryViewModel.getTableInventoryList().observe(this, new Observer<ArrayList<String[]>>() {
            @Override
            public void onChanged(ArrayList<String[]> table_inventory_array_list) {

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

                    tableDammed.addView(tableRow);
                }

            }
        });
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Resumen");
    }

}