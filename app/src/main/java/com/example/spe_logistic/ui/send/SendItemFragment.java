package com.example.spe_logistic.ui.send;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

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

    Button onDelete,onAdd;

    View root;

    ViewGroup group;

    private LinearLayout parentLinearLayout;

    public SendItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        /*SharedPreferences preferences = this.getActivity().getSharedPreferences("credentials",this.getActivity().MODE_PRIVATE);
        Integer user_id = preferences.getInt("user_id",0);
        Log.i("APP","USER FRAGMENT VIEW MODEL NEW SEND: "+user_id);*/






        root = inflater.inflate(R.layout.fragment_send_item, container, false);

        parentLinearLayout = (LinearLayout) root.findViewById(R.id.parent_linear_layout);


        onAdd = (Button) root.findViewById(R.id.add_field_button);
        onDelete = (Button) root.findViewById(R.id.delete_button);

        onAdd.setOnClickListener(this);
        onDelete.setOnClickListener(this);


        name              = (EditText) root.findViewById(R.id.send_item_name);
        phone             = (EditText) root.findViewById(R.id.send_item_phone);
        email             = (EditText) root.findViewById(R.id.send_item_email);
        city              = (Spinner)  root.findViewById(R.id.send_item_city);
        road_type         = (Spinner)  root.findViewById(R.id.send_item_road_type);
        number1           = (EditText) root.findViewById(R.id.send_item_number1);
        chart1            = (EditText) root.findViewById(R.id.send_item_chart1);
        orientation_road1 = (Spinner)  root.findViewById(R.id.send_item_orientation_road1);
        number2           = (EditText) root.findViewById(R.id.send_item_number2);
        chart2            = (EditText) root.findViewById(R.id.send_item_chart2);
        orientation_road2 = (Spinner)  root.findViewById(R.id.send_item_orientation_road2);
        number3           = (EditText) root.findViewById(R.id.send_item_number3);

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

    @SuppressLint("WrongViewCast")
    public void onAddField(View v) {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(this.getActivity().LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.reference_field, null);


        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);


        onDelete = (Button) root.findViewById(R.id.delete_button);
        onDelete.setOnClickListener(this);

/*
        group = (ViewGroup)root.findViewById(R.id.);
        View view2 = null;
        for(int i = 0; i < group.getChildCount(); i++) {
            view2 = group.getChildAt(i);
            if(view2 instanceof Button) view2.setOnClickListener(this);
        }
*/
    }

    public void onDeleteField(View v) {

        if (parentLinearLayout.getChildCount() > 3) {
            parentLinearLayout.removeViewAt(parentLinearLayout.getChildCount() - 2);
        }
        //parentLinearLayout.removeView((View) v.getParent());
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
        }
    }
}
