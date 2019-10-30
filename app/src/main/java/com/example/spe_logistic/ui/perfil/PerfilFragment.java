package com.example.spe_logistic.ui.perfil;


import android.content.res.Resources;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spe_logistic.HistoryChangesPDF;
import com.example.spe_logistic.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.spe_logistic.R.color.colorOrangeSpe;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment implements View.OnClickListener {

    private PerfilViewModel perfilViewModel;

    private TextView name;
    private TextView nit;

    private Button perfilSend;
    private Button perfilCollect;
    private Button perfilReferences;
    private Button perfilExportPdf;

    private ArrayList<String[]> send_history;
    private ArrayList<String[]> collect_history;
    private ArrayList<String[]> references_history;

    private ArrayList<String[]> generalData;

    private TableLayout tableHistory;



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

        tableHistory = (TableLayout) root.findViewById(R.id.tableHistory);

        name = root.findViewById(R.id.perfil_name);
        nit  = root.findViewById(R.id.perfil_nit);

        perfilViewModel.getPerfil().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> perfil_array_list) {
                name.setText(perfil_array_list.get(0));
                nit.setText(perfil_array_list.get(1));
            }
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

        perfilSend       = root.findViewById(R.id.perfil_send_history);
        perfilCollect    = root.findViewById(R.id.perfil_collect_history);
        perfilReferences = root.findViewById(R.id.perfil_references_history);
        perfilExportPdf  = root.findViewById(R.id.perfil_export_history);

        perfilSend.setOnClickListener(this);
        perfilCollect.setOnClickListener(this);
        perfilReferences.setOnClickListener(this);
        perfilExportPdf.setOnClickListener(this);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Mi Perfil");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.perfil_send_history:
                perfilSend.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_rounded_orange));
                perfilCollect.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_rounded));
                perfilReferences.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_rounded));
                setHistory(send_history);
                break;
            case R.id.perfil_collect_history:
                perfilSend.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_rounded));
                perfilCollect.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_rounded_orange));
                perfilReferences.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_rounded));
                setHistory(collect_history);
                break;
            case R.id.perfil_references_history:
                perfilSend.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_rounded));
                perfilCollect.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_rounded));
                perfilReferences.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_rounded_orange));
                setHistory(references_history);
                break;
            case R.id.perfil_export_history:
                if (tableHistory.getChildCount() != 1){
                    String[] header = {"ID","Fecha","Descripción"};
                    HistoryChangesPDF historyChangesPDF = new HistoryChangesPDF(getContext());
                    historyChangesPDF.openDocument();
                    historyChangesPDF.addMetaData("SPE Logistica","Historial de cambios","SPE");
                    historyChangesPDF.addTitles("SPE Logistica","Historial de cambios",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    historyChangesPDF.createTable(header,generalData);
                    historyChangesPDF.closeDocument();
                    historyChangesPDF.appViewPdf(getActivity());

                }else {
                    Toast.makeText(getContext(),"No hay datos para exportar",Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    private void setHistory(ArrayList<String[]> data) {

        generalData = data;

        TableRow tableRow;
        TextView textCell;

        int indexRow;
        int indexCell;

        //ArrayList<String[]> data = new ArrayList<>();

        //data.add(new String[]{"13625","2019-02-02 66:22:11","'steven suarez' por 'steven suarez cano' steven  suarez'steven suarez' por 'steven suarez cano' steven  suarez'steven suarez' por 'steven suarez cano' steven  suarezcanoca   nocano nocano "});


        tableHistory.removeViews(1,tableHistory.getChildCount()-1);

        for (indexRow = 0; indexRow < data.size(); indexRow++){
            tableRow = new TableRow(getContext());
            indexCell = 0;
            while (indexCell<3) {

                Log.i("APP","data: "+data.get(indexRow)[indexCell]);

                textCell = new TextView(getContext());

                textCell.setGravity(Gravity.CENTER);

                if (indexCell==0){
                    textCell.setText(data.get(indexRow)[indexCell]+"\n");
                }else if (indexCell==1){
                    textCell.setText(data.get(indexRow)[indexCell].replace(" ","\n"));
                }else {
                    if (data.get(indexRow)[indexCell].length()>60)
                    textCell.setText(data.get(indexRow)[indexCell].replaceAll("(.{60})", "$1\n"));
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
        params.setMargins(1,1,1,1);
        if (i == 0){
            params.weight = 0.1f;
        }else if (i == 1){
            params.weight = 0.1f;
        }else {
            params.weight = 0.8f;
        }

        return params;
    }
}
