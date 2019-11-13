package com.example.spe_logistic.ui.perfil;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spe_logistic.TemplatePDF;
import com.example.spe_logistic.R;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.spe_logistic.R.color.colorOrangeSpe;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment implements View.OnClickListener, PermissionListener {

    private PerfilViewModel perfilViewModel;

    private TextView name;
    private TextView nit;

    private BarChart barChartPqr;
    private BarDataSet barDataSet;
    private BarData barData;
    private XAxis barChartXAxis;

    private Button perfilSend;
    private Button perfilCollect;
    private Button perfilReferences;
    private Button perfilExportPdf;
    private Button perfilIndicators;
    private Button perfilSeeMore;

    private ArrayList<String[]> send_history;
    private ArrayList<String[]> collect_history;
    private ArrayList<String[]> references_history;

    private ArrayList<String[]> generalData;

    private NavController navController;

    private TableLayout tableHistory;
    String[] header = {"ID", "Fecha", "Descripción"};


    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        perfilViewModel =
                ViewModelProviders.of(this).get(PerfilViewModel.class);
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        tableHistory = (TableLayout) root.findViewById(R.id.tableHistory);

        name = root.findViewById(R.id.perfil_name);
        nit = root.findViewById(R.id.perfil_nit);

        perfilViewModel.getPerfil().observe(this, perfil_array_list -> {
            name.setText(perfil_array_list.get(0));
            nit.setText(perfil_array_list.get(1));
        });

        perfilViewModel.getSend_history().observe(this, send_history_array_list -> {
            send_history = send_history_array_list;
        });
        perfilViewModel.getCollect_history().observe(this, collect_history_array_list -> {
            collect_history = collect_history_array_list;
        });
        perfilViewModel.getReferences_history().observe(this, references_history_array_list -> {
            references_history = references_history_array_list;
        });

        fillBarChart(root);

        perfilSend       = root.findViewById(R.id.perfil_send_history);
        perfilCollect    = root.findViewById(R.id.perfil_collect_history);
        perfilReferences = root.findViewById(R.id.perfil_references_history);
        perfilExportPdf  = root.findViewById(R.id.perfil_export_history);
        perfilIndicators = root.findViewById(R.id.perfil_indicators);
        perfilSeeMore    = root.findViewById(R.id.perfil_see_more);

        perfilSend.setOnClickListener(this);
        perfilCollect.setOnClickListener(this);
        perfilReferences.setOnClickListener(this);
        perfilExportPdf.setOnClickListener(this);
        perfilIndicators.setOnClickListener(this);
        perfilSeeMore.setOnClickListener(this);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Mi perfil");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.perfil_indicators:
                seeIndicators();
                break;
            case R.id.perfil_see_more:
                navController.navigate(R.id.perfilPqrFragment);
                break;
            case R.id.perfil_send_history:
                perfilSend.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_rounded_orange));
                perfilCollect.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_rounded));
                perfilReferences.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_rounded));
                setHistory(send_history);
                break;
            case R.id.perfil_collect_history:
                perfilSend.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_rounded));
                perfilCollect.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_rounded_orange));
                perfilReferences.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_rounded));
                setHistory(collect_history);
                break;
            case R.id.perfil_references_history:
                perfilSend.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_rounded));
                perfilCollect.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_rounded));
                perfilReferences.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_rounded_orange));
                setHistory(references_history);
                break;
            case R.id.perfil_export_history:
                if (tableHistory.getChildCount() != 1) {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        generatePDF();
                    } else {
                        requestPermission();
                    }
                } else {
                    Toast.makeText(getContext(), "No hay datos para exportar", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    private void seeIndicators() {
        Log.i("APP", "Indicators");
        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_message_multiple, null, false);

        Button btnOk = view.findViewById(R.id.dialog_ok);
        TextView avgEnlistment = view.findViewById(R.id.avg_enlistment);
        TextView avgDispatch = view.findViewById(R.id.avg_dispatch);
        TextView avgTotal = view.findViewById(R.id.avg_total);

        alert.setView(view);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(true);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        perfilViewModel.getIndicators().observe(this, new Observer<ArrayList<String[]>>() {
            @Override
            public void onChanged(ArrayList<String[]> indicators_array_list) {
                avgEnlistment.setText(indicators_array_list.get(0)[0]);
                avgDispatch.setText(indicators_array_list.get(0)[1]);
                avgTotal.setText(indicators_array_list.get(0)[2]);
            }
        });

        alertDialog.show();
    }

    private void fillBarChart(View root) {
        barChartPqr = root.findViewById(R.id.barChartPqr);

        perfilViewModel.getBarPqrList().observe(this, pqr_array_list -> {
            barDataSet = new BarDataSet(pqr_array_list, "");
            barDataSet.setColor(getResources().getColor(colorOrangeSpe));
            barData = new BarData(barDataSet);
            barChartPqr.setData(barData);

            barChartPqr.getAxisRight().setEnabled(false);
            barChartPqr.getAxisLeft().setEnabled(false);
            barChartPqr.getAxisLeft().setAxisMinimum(0);
            barChartPqr.getAxisLeft().setTextSize(4);
            barChartPqr.getLegend().setEnabled(false);
            barChartPqr.getDescription().setEnabled(false);
            barChartPqr.animateY(Utilities.ANIMATION);
        });

        perfilViewModel.getBarPqrListLabel().observe(this, pqr_array_list_label -> {
            barChartXAxis = barChartPqr.getXAxis();
            barChartXAxis.setValueFormatter(new IndexAxisValueFormatter(pqr_array_list_label));
            barChartXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            barChartXAxis.setGranularity(1f);
            barChartXAxis.setDrawLabels(true);
            barChartXAxis.setDrawAxisLine(false);
            barChartXAxis.setDrawGridLines(false);
        });
    }

    private void generatePDF() {
        TemplatePDF templatePDF = new TemplatePDF(getContext(), new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()));
        templatePDF.openDocument();
        templatePDF.addMetaData("SPE Logistica", "Historial de cambios", "SPE");
        templatePDF.addTitles("Servicios Postales Especializados SAS", "Historial de cambios", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        templatePDF.createTable(header, generalData);
        templatePDF.closeDocument();
        templatePDF.appViewPdf(getActivity());
    }

    private void requestPermission() {
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(this)
                .check();
    }

    private void setHistory(ArrayList<String[]> data) {

        generalData = data;

        TableRow tableRow;
        TextView textCell;

        int indexRow;
        int indexCell;

        //ArrayList<String[]> data = new ArrayList<>();

        //data.add(new String[]{"13625","2019-02-02 66:22:11","'steven suarez' por 'steven suarez cano' steven  suarez'steven suarez' por 'steven suarez cano' steven  suarez'steven suarez' por 'steven suarez cano' steven  suarezcanoca   nocano nocano "});


        tableHistory.removeViews(1, tableHistory.getChildCount() - 1);

        for (indexRow = 0; indexRow < data.size(); indexRow++) {
            tableRow = new TableRow(getContext());
            indexCell = 0;
            while (indexCell < 3) {

                //Log.i("APP","data: "+data.get(indexRow)[indexCell]);

                textCell = new TextView(getContext());

                textCell.setGravity(Gravity.CENTER);

                if (indexCell == 0) {
                    textCell.setText(data.get(indexRow)[indexCell] + "\n");
                } else if (indexCell == 1) {
                    textCell.setText(data.get(indexRow)[indexCell].replace(" ", "\n"));
                } else {
                    if (data.get(indexRow)[indexCell].length() > 55)
                        textCell.setText(data.get(indexRow)[indexCell].replaceAll("(.{55})", "$1\n"));
                    else
                        textCell.setText(data.get(indexRow)[indexCell]);
                }

                textCell.setBackgroundColor(Color.WHITE);
                textCell.setTextSize(12);

                tableRow.addView(textCell, newTableRowParams(indexCell));

                indexCell++;
            }

            tableRow.setBackgroundColor(getResources().getColor(colorOrangeSpe));

            tableHistory.addView(tableRow);
        }
    }

    private TableRow.LayoutParams newTableRowParams(int i) {
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1, 1, 1, 1);
        if (i == 0) {
            params.weight = 0.1f;
        } else if (i == 1) {
            params.weight = 0.1f;
        } else {
            params.weight = 0.8f;
        }

        return params;
    }

    @Override
    public void onPermissionGranted(PermissionGrantedResponse response) {
        generatePDF();
    }

    @Override
    public void onPermissionDenied(PermissionDeniedResponse response) {

    }

    @Override
    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
        token.continuePermissionRequest();
    }
}
