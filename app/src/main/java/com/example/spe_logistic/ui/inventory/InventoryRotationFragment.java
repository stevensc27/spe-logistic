package com.example.spe_logistic.ui.inventory;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.spe_logistic.R;

import java.util.ArrayList;

import static com.example.spe_logistic.R.color.colorOrangeSpe;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryRotationFragment extends Fragment {

    private TableLayout         tableRotation;
    private TableRow            tableRow;
    private TextView            textCell;
    private int                 indexRow;
    private int                 indexCell;
    private ArrayList<String[]> data;

    public InventoryRotationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_inventory_rotation, container, false);

        fillRotationTable(root);

        return root;
    }

    private void fillRotationTable(View root) {

        tableRotation = (TableLayout) root.findViewById(R.id.tableRotation);

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

            tableRotation.addView(tableRow);
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

        data.add(new String[]{"1122334455667","1.85","4"});
        data.add(new String[]{"9966558877442","5.77","5"});
        data.add(new String[]{"6633225588221","4.58","2"});
        data.add(new String[]{"9955441122665","3.25","13"});
        data.add(new String[]{"6633225599884","9.35","6"});
        data.add(new String[]{"9988772200220","5.68","8"});
        data.add(new String[]{"1122334455667","1.85","4"});
        data.add(new String[]{"9966558877442","5.77","5"});
        data.add(new String[]{"6633225588221","4.58","2"});
        data.add(new String[]{"9955441122665","3.25","13"});
        data.add(new String[]{"6633225599884","9.35","6"});
        data.add(new String[]{"9988772200220","5.68","8"});
        data.add(new String[]{"1122334455667","1.85","4"});
        data.add(new String[]{"9966558877442","5.77","5"});
        data.add(new String[]{"6633225588221","4.58","2"});
        data.add(new String[]{"9955441122665","3.25","13"});
        data.add(new String[]{"6633225599884","9.35","6"});
        data.add(new String[]{"9988772200220","5.68","8"});
        data.add(new String[]{"1122334455667","1.85","4"});
        data.add(new String[]{"9966558877442","5.77","5"});
        data.add(new String[]{"6633225588221","4.58","2"});
        data.add(new String[]{"9955441122665","3.25","13"});
        data.add(new String[]{"6633225599884","9.35","6"});
        data.add(new String[]{"9988772200220","5.68","8"});
        data.add(new String[]{"1122334455667","1.85","4"});
        data.add(new String[]{"9966558877442","5.77","5"});
        data.add(new String[]{"6633225588221","4.58","2"});
        data.add(new String[]{"9955441122665","3.25","13"});
        data.add(new String[]{"6633225599884","9.35","6"});
        data.add(new String[]{"9988772200220","5.68","8"});
        data.add(new String[]{"1122334455667","1.85","4"});
        data.add(new String[]{"9966558877442","5.77","5"});
        data.add(new String[]{"6633225588221","4.58","2"});
        data.add(new String[]{"9955441122665","3.25","13"});
        data.add(new String[]{"6633225599884","9.35","6"});
        data.add(new String[]{"9988772200220","5.68","8"});
        data.add(new String[]{"1122334455667","1.85","4"});
        data.add(new String[]{"9966558877442","5.77","5"});
        data.add(new String[]{"6633225588221","4.58","2"});
        data.add(new String[]{"9955441122665","3.25","13"});
        data.add(new String[]{"6633225599884","9.35","6"});
        data.add(new String[]{"9988772200220","5.68","8"});
        data.add(new String[]{"1122334455667","1.85","4"});
        data.add(new String[]{"9966558877442","5.77","5"});
        data.add(new String[]{"6633225588221","4.58","2"});
        data.add(new String[]{"9955441122665","3.25","13"});
        data.add(new String[]{"6633225599884","9.35","6"});
        data.add(new String[]{"9988772200220","5.68","8"});


        return data;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Rotación De inventario");
    }
    
}
