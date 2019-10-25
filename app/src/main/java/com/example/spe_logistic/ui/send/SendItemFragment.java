package com.example.spe_logistic.ui.send;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.spe_logistic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendItemFragment extends Fragment implements View.OnClickListener {

    EditText name;
    EditText phone;
    EditText email;
    Spinner  city;
    Spinner  road_type;
    EditText number1;
    EditText chart1;
    Spinner  orientation_road1;
    EditText number2;
    EditText chart2;
    Spinner  orientation_road2;
    EditText number3;
    EditText codebar;
    EditText amount;

    Button onDelete;
    Button onAdd;
    Button save;

    View root;

    private LinearLayout parentLinearLayout;

    public SendItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        /*SharedPreferences preferences = this.getActivity().getSharedPreferences("credentials",this.getActivity().MODE_PRIVATE);
        Integer user_id = preferences.getInt("user_id",0);
        Log.i("APP","USER FRAGMENT VIEW MODEL NEW SEND: "+user_id);*/


        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_send_item, container, false);

        parentLinearLayout =  root.findViewById(R.id.parent_linear_layout);


        onAdd    = root.findViewById(R.id.add_field_button);
        onDelete = root.findViewById(R.id.delete_button);
        save     = root.findViewById(R.id.send_item_save);

        onAdd.setOnClickListener(this);
        onDelete.setOnClickListener(this);
        save.setOnClickListener(this);

        name              = root.findViewById(R.id.send_item_name);
        phone             = root.findViewById(R.id.send_item_phone);
        email             = root.findViewById(R.id.send_item_email);
        city              = root.findViewById(R.id.send_item_city);
        road_type         = root.findViewById(R.id.send_item_road_type);
        number1           = root.findViewById(R.id.send_item_number1);
        chart1            = root.findViewById(R.id.send_item_chart1);
        orientation_road1 = root.findViewById(R.id.send_item_orientation_road1);
        number2           = root.findViewById(R.id.send_item_number2);
        chart2            = root.findViewById(R.id.send_item_chart2);
        orientation_road2 = root.findViewById(R.id.send_item_orientation_road2);
        number3           = root.findViewById(R.id.send_item_number3);


        ArrayAdapter<CharSequence> adapter_city = ArrayAdapter.createFromResource(this.getActivity(),R.array.city_options,android.R.layout.simple_spinner_item);
        city.setAdapter(adapter_city);
        ArrayAdapter<CharSequence> adapter_address_options = ArrayAdapter.createFromResource(this.getActivity(),R.array.address_options,android.R.layout.simple_spinner_item);
        road_type.setAdapter(adapter_address_options);
        ArrayAdapter<CharSequence> orientation_road_options = ArrayAdapter.createFromResource(this.getActivity(),R.array.orientation_road_options,android.R.layout.simple_spinner_item);
        orientation_road1.setAdapter(orientation_road_options);
        ArrayAdapter<CharSequence> orientation_road_options2 = ArrayAdapter.createFromResource(this.getActivity(),R.array.orientation_road_options,android.R.layout.simple_spinner_item);
        orientation_road2.setAdapter(orientation_road_options2);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_field_button:
                onAddField(v);
                break;

            case R.id.delete_button:
                onDeleteField(v);
                break;

            case R.id.send_item_save:
                if (validateData(v)){
                    saveSend();
                }
                break;
        }
    }

    @SuppressLint("WrongViewCast")
    public void onAddField(View v) {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(this.getActivity().LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.reference_field, null);

        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 2);

        onDelete = root.findViewById(R.id.delete_button);
        onDelete.setOnClickListener(this);

    }

    public void onDeleteField(View v) {

        if (parentLinearLayout.getChildCount() > 4) {
            parentLinearLayout.removeViewAt(parentLinearLayout.getChildCount() - 3);
        }
    }

    private boolean validateData(View v){

        String isNumber = "[0-9]+";

        LinearLayout layout;
        layout = root.findViewById(R.id.parent_linear_layout);

        int count = layout.getChildCount();
        View row = null;

        for(int i=1; i<count-2; i++) {

            row = layout.getChildAt(i);
            View field_codebar = null;
            View field_amount  = null;

            if (row instanceof LinearLayout){
                field_codebar = ((LinearLayout)row).getChildAt(0);
                field_amount = ((LinearLayout)row).getChildAt(1);

                String codebar_unit = null;
                if (field_codebar instanceof EditText);{
                    codebar_unit = ((EditText)field_codebar).getText().toString();
                }

                String amount_unit = null;
                if (field_amount instanceof EditText);{
                    amount_unit = ((EditText)field_amount).getText().toString();
                }

                if (!validateReference(codebar_unit, amount_unit)){
                    return false;
                }
            }
        }

        return true;
    }

    private boolean validateReference(String codebar_unit, String amount_unit){

        String isNumber = "[0-9]+";

        if (!codebar_unit.matches(isNumber)){
            Toast.makeText(this.getActivity(),"Los códigos de barras deben ser numéricos",Toast.LENGTH_LONG).show();
            return false;
        }
        if (codebar_unit.length() < 12){
            Toast.makeText(this.getActivity(),"El codigo de barras debe tener al menos 12 caracteres",Toast.LENGTH_LONG).show();
            return false;
        }
        if (!amount_unit.matches(isNumber) || amount_unit.length() == 0){
            Toast.makeText(this.getActivity(),"Cantidad de unidades inválida",Toast.LENGTH_LONG).show();
            return false;
        }



        return true;
    }

    private void saveSend(){

    }


}
