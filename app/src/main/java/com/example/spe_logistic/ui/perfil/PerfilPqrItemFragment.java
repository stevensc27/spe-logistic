package com.example.spe_logistic.ui.perfil;


import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.spe_logistic.R;
import com.example.spe_logistic.SQLiteConnectionHelper;
import com.example.spe_logistic.utilities.Utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilPqrItemFragment extends Fragment implements View.OnClickListener {

    private SQLiteConnectionHelper con;
    private ArrayList<String> categories;
    private ArrayAdapter<String> adapter_categories;
    private Spinner category;
    private EditText description;
    private Button save;
    private NavController navController;
    private String pqr_id;
    private View root;
    private int user_id;
    private int state_id;

    public PerfilPqrItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SharedPreferences preferences = this.getActivity().getSharedPreferences("credentials",this.getActivity().MODE_PRIVATE);
        user_id = preferences.getInt("user_id",0);

        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_perfil_pqr_item, container, false);

        pqr_id = (getArguments().getString("pqrId", "-1"));

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        category    = root.findViewById(R.id.pqr_item_categories);
        description = root.findViewById(R.id.pqr_item_description);
        save        = root.findViewById(R.id.pqr_item_save_button);

        getCategories();
        adapter_categories = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, categories);
        category.setAdapter(adapter_categories);

        save.setOnClickListener(this);

        if (!pqr_id.equals("-1")) {
            getPqrData();
        }

        return root;
    }

    private void getPqrData() {
        con = new SQLiteConnectionHelper(this.getContext(), "SPEDB", null, 1);
        SQLiteDatabase db = con.getReadableDatabase();

        String[] parameter = {pqr_id};
        String queryPqr =   "SELECT     descripcion, " +
                            "           categorias_pqrs.nombre || ' ' || pqrs.categoria_id," +
                            "           estado_id " +
                            "FROM       pqrs " +
                            "INNER JOIN categorias_pqrs " +
                            "ON         categorias_pqrs.id = pqrs.categoria_id " +
                            "WHERE      pqrs.id = ? ";
        Cursor cursor = db.rawQuery(queryPqr,parameter);
        cursor.moveToFirst();

        Log.i("APP",cursor.getString(1));
        int spinnerPosition = adapter_categories.getPosition(cursor.getString(1));
        category.setSelection(spinnerPosition);
        description.setText(cursor.getString(0));

        state_id = cursor.getInt(2);

        db.close();
    }


    private void getCategories() {
        con = new SQLiteConnectionHelper(this.getContext(), "SPEDB", null, 1);
        SQLiteDatabase db = con.getReadableDatabase();

        categories = new ArrayList<String>();

        String queryCategories = "SELECT     id,nombre " +
                "FROM       categorias_pqrs " +
                "ORDER BY   nombre";

        Cursor cursor = db.rawQuery(queryCategories, null);

        while (cursor.moveToNext()) {
            String category;
            category = cursor.getString(1) + " " + cursor.getString(0);
            categories.add(category);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pqr_item_save_button:
                if (validateData (v)){
                    savePqr();
                    navController.navigate(R.id.perfilPqrFragment);
                }
                break;
        }
    }

    private boolean validateData(View v) {
        if (description.getText().toString().length() < 20) {
            Toast.makeText(this.getActivity(), "La descripción es muy corta", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void savePqr() {
        con = new SQLiteConnectionHelper(this.getContext(), "SPEDB", null, 1);
        SQLiteDatabase db = con.getWritableDatabase();

        String category_id = category.getSelectedItem().toString().split(" ")[1];

        ContentValues values = new ContentValues();

        values.put(Utilities.PQRS_FECHA,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        values.put(Utilities.PQRS_CATEGORIA_ID,category_id);
        values.put(Utilities.PQRS_CLIENTE_ID,user_id);
        values.put(Utilities.PQRS_DESCRIPCION,description.getText().toString());


        if (pqr_id.equals("-1")) {

            values.put(Utilities.PQRS_ESTADO_ID,"1");

            Long idResult = db.insert(Utilities.PQRS, Utilities.PQRS_ID, values);
            Toast.makeText(this.getActivity(), "PQR con id " + idResult + " creado", Toast.LENGTH_LONG).show();

        } else {

            values.put(Utilities.PQRS_ESTADO_ID,state_id);

            String[] parameters = {pqr_id};
            db.update(Utilities.PQRS, values, Utilities.PQRS_ID + " = ?", parameters);
            Toast.makeText(this.getActivity(), "El PQR ha sido actualizado", Toast.LENGTH_LONG).show();
        }

        db.close();
    }
}
