package com.example.spe_logistic.ui.inventory;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class InventoryViewModel extends ViewModel {

    private MutableLiveData<ArrayList<BarEntry>> bar_inventory_list;
    private MutableLiveData<ArrayList<String>>   bar_inventory_list_label;

    private MutableLiveData<ArrayList<BarEntry>> bar_send_list_in;
    private MutableLiveData<ArrayList<BarEntry>> bar_send_list_out;
    private MutableLiveData<ArrayList<String>>   bar_send_list_label;

    private MutableLiveData<ArrayList<PieEntry>> pie_inventory_list;


    public InventoryViewModel() {

        bar_inventory_list       = new MutableLiveData<>();
        bar_inventory_list_label = new MutableLiveData<>();

        bar_send_list_in         = new MutableLiveData<>();
        bar_send_list_out        = new MutableLiveData<>();
        bar_send_list_label      = new MutableLiveData<>();

        pie_inventory_list       = new MutableLiveData<>();

        getDataInventory();
        getDataSend();
        getDataPie();


    }

    private void getDataInventory() {
        ArrayList<BarEntry> bar_inventory_array_list       = new ArrayList<>();
        ArrayList<String>   bar_inventory_array_list_label = new ArrayList<>();

        // here desc, in chart asc
        // x order
        // y quantities per unit
        bar_inventory_array_list.add(new BarEntry(1,5));
        bar_inventory_array_list.add(new BarEntry(2,10));
        bar_inventory_array_list.add(new BarEntry(3,30));
        bar_inventory_array_list.add(new BarEntry(4,60));
        bar_inventory_array_list.add(new BarEntry(5,90));

        //there must be a joker item with blank value
        bar_inventory_array_list_label.add("");
        bar_inventory_array_list_label.add("223355114466");
        bar_inventory_array_list_label.add("222299995555");
        bar_inventory_array_list_label.add("556622884400");
        bar_inventory_array_list_label.add("335558889999");
        bar_inventory_array_list_label.add("995588774466");

        bar_inventory_list.setValue(bar_inventory_array_list);
        bar_inventory_list_label.setValue(bar_inventory_array_list_label);
    }

    private void getDataSend() {
        ArrayList<BarEntry> bar_send_array_list_in    = new ArrayList<>();
        ArrayList<BarEntry> bar_send_array_list_out   = new ArrayList<>();
        ArrayList<String>   bar_send_array_list_label = new ArrayList<>();

        bar_send_array_list_in.add(new BarEntry(1,12));
        bar_send_array_list_in.add(new BarEntry(2,35));
        bar_send_array_list_in.add(new BarEntry(3,51));
        bar_send_array_list_in.add(new BarEntry(4,13));
        bar_send_array_list_in.add(new BarEntry(5,68));
        bar_send_array_list_in.add(new BarEntry(6,22));

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

        bar_send_list_in.setValue(bar_send_array_list_in);
        bar_send_list_out.setValue(bar_send_array_list_out);
        bar_send_list_label.setValue(bar_send_array_list_label);

    }

    private void getDataPie() {
        ArrayList<PieEntry> pie_inventory_arrat_list = new ArrayList<>();

        pie_inventory_arrat_list.add(new PieEntry(6, "1232365985645"));
        pie_inventory_arrat_list.add(new PieEntry(5, "6688522994477"));
        pie_inventory_arrat_list.add(new PieEntry(3, "9325365984561"));
        pie_inventory_arrat_list.add(new PieEntry(2, "3366885544223"));
        pie_inventory_arrat_list.add(new PieEntry(2, "3366885534223"));

        pie_inventory_list.setValue(pie_inventory_arrat_list);

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
}