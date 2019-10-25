package com.example.spe_logistic.ui.references;


import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spe_logistic.R;
import com.example.spe_logistic.SQLiteConnectionHelper;
import com.example.spe_logistic.utilities.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReferencesItemFragment extends Fragment implements View.OnClickListener {

    private String reference_id;
    private int    user_id;

    private EditText name;
    private EditText codebar;
    private EditText value;
    private EditText amount;

    private Button   save;
    private NavController navController;

    private String nameValidation;

    SQLiteConnectionHelper con;

    public ReferencesItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_references_item, container, false);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("credentials",this.getActivity().MODE_PRIVATE);
        user_id = preferences.getInt("user_id",0);

        reference_id = (getArguments().getString("referenceId","-1"));

        //Log.i("APP",reference_id);

        name    = root.findViewById(R.id.reference_item_description);
        codebar = root.findViewById(R.id.reference_item_bar_code);
        value   = root.findViewById(R.id.reference_item_value);
        amount  = root.findViewById(R.id.reference_item_amount);

        save = root.findViewById(R.id.reference_item_save_button);
        save.setOnClickListener(this);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        if (!reference_id.equals("-1")){
            getReferenceData();
        }

        return root;
    }



    @Override
    public void onClick(View v) {
        if (reference_id.equals("-1")){
            saveReference();
        }else {
            updateReference();
        }
    }

    private void getReferenceData() {
        con = new SQLiteConnectionHelper(this.getContext(),"SPEDB",null,1);
        SQLiteDatabase db = con.getReadableDatabase();

        String[] parameters = {reference_id, String.valueOf(user_id)};
        String queryReferenceData = "SELECT nombre,"                +
                                    "       valor,"                 +
                                    "       codigo_barras,"         +
                                    "       unidades_empaque "      +
                                    "FROM   referencias "           +
                                    "WHERE  id =?           AND "   +
                                    "       cliente_id = ?";

        Cursor cursor = db.rawQuery(queryReferenceData,parameters);

        cursor.moveToFirst();

        nameValidation = cursor.getString(0);

        name.setText(cursor.getString(0));
        codebar.setText(cursor.getString(1));
        value.setText(cursor.getString(2));
        amount.setText(cursor.getString(3));

        codebar.setEnabled(false);
        codebar.setTextColor(0xAA8F84A1);

        value.setEnabled(false);
        value.setTextColor(0xAA8F84A1);

        amount.setEnabled(false);
        amount.setTextColor(0xAA8F84A1);

        db.close();
    }

    private void saveReference() {

        con = new SQLiteConnectionHelper(this.getContext(), "SPEDB", null, 1);
        SQLiteDatabase db = con.getWritableDatabase();

        String isNumber = "[0-9]+";

        if (name.getText().toString().length() < 5) {
            Toast.makeText(this.getActivity(), "La descripción debe tener al menos 5 caracteres", Toast.LENGTH_LONG).show();
            return;
        }

        if (codebar.getText().toString().length() < 11) {
            Toast.makeText(this.getActivity(), "El código de barras debe tener al menos 12 caracteres", Toast.LENGTH_LONG).show();
            return;
        }

        if (!codebar.getText().toString().matches(isNumber)){
            Toast.makeText(this.getActivity(),"El código de barras debe ser numérico",Toast.LENGTH_LONG).show();
            return;
        }

        if (!value.getText().toString().matches(isNumber) || value.getText().toString().length() == 0){
            Toast.makeText(this.getActivity(),"El valor es incorrecto",Toast.LENGTH_LONG).show();
            return;
        }

        if (!amount.getText().toString().matches(isNumber) || amount.getText().toString().length() == 0){
            Toast.makeText(this.getActivity(),"Las unidades por empaque son incorrecta",Toast.LENGTH_LONG).show();
            return;
        }

        ContentValues values = new ContentValues();

        values.put(Utilities.REFERENCIAS_NOMBRE,name.getText().toString());
        values.put(Utilities.REFERENCIAS_VALOR,value.getText().toString());
        values.put(Utilities.REFERENCIAS_CODIGO_BARRAS,codebar.getText().toString());
        values.put(Utilities.REFERENCIAS_UNIDADES_EMPAQUE,amount.getText().toString());
        values.put(Utilities.REFERENCIAS_CLIENTE_ID,String.valueOf(user_id));

        Long idResult = db.insert(Utilities.REFERENCIAS,Utilities.RECOGIDAS_ID,values);

        Toast.makeText(this.getActivity(),"Referencia con id "+idResult+" creada",Toast.LENGTH_LONG).show();
        db.close();
        navController.navigate(R.id.navigation_references);
    }

    private void updateReference() {

        if (name.getText().toString().length() > 5) {

            con = new SQLiteConnectionHelper(this.getContext(), "SPEDB", null, 1);
            SQLiteDatabase db = con.getWritableDatabase();

            saveHistory();

            String[] parameters = {reference_id, String.valueOf(user_id)};
            ContentValues values = new ContentValues();
            values.put(Utilities.REFERENCIAS_NOMBRE, name.getText().toString());

            db.update(Utilities.REFERENCIAS, values, Utilities.REFERENCIAS_ID + " = ? AND " + Utilities.REFERENCIAS_CLIENTE_ID + " =?", parameters);

            Toast.makeText(this.getActivity(), "Referencia actualizada", Toast.LENGTH_LONG).show();
            db.close();
            navController.navigate(R.id.navigation_references);

        } else {
            Toast.makeText(this.getActivity(), "La descripción debe tener más de 5 caracteres", Toast.LENGTH_LONG).show();
        }
    }

    private void saveHistory() {




        if (!nameValidation.equals(name.getText().toString())){
            SQLiteDatabase db = con.getWritableDatabase();

            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String description = "Modificación de referencia. Se cambia nombre '"+nameValidation+"' por '"+name.getText().toString()+"'.";

            ContentValues values = new ContentValues();

            values.put(Utilities.HISTORIAL_REFERENCIAS_FECHA,date);
            values.put(Utilities.HISTORIAL_REFERENCIAS_DESCRIPCION,description);
            values.put(Utilities.HISTORIAL_REFERENCIAS_REFERENCIA_ID,reference_id);

            Long idResult = db.insert(Utilities.HISTORIAL_REFERENCIAS, Utilities.HISTORIAL_REFERENCIAS_ID,values);

            Log.i("APP","ID change history reference: "+ idResult+" "+description);
        }

    }
}
