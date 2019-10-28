package com.example.spe_logistic.ui.perfil;


import android.content.res.Resources;
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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.spe_logistic.R;

import java.util.ArrayList;

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

        name = root.findViewById(R.id.perfil_name);
        nit  = root.findViewById(R.id.perfil_nit);

        perfilViewModel.getPerfil().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> perfil_array_list) {
                name.setText(perfil_array_list.get(0));
                nit.setText(perfil_array_list.get(1));
            }
        });

        perfilSend       = root.findViewById(R.id.perfil_send_history);
        perfilCollect    = root.findViewById(R.id.perfil_collect_history);
        perfilReferences = root.findViewById(R.id.perfil_references_history);

        perfilSend.setOnClickListener(this);
        perfilCollect.setOnClickListener(this);
        perfilReferences.setOnClickListener(this);


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
                getHistory(1);
                break;
            case R.id.perfil_collect_history:
                perfilSend.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_rounded));
                perfilCollect.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_rounded_orange));
                perfilReferences.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_rounded));
                getHistory(2);
                break;
            case R.id.perfil_references_history:
                perfilSend.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_rounded));
                perfilCollect.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_rounded));
                perfilReferences.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_rounded_orange));
                getHistory(3);
                break;

        }
    }

    private void getHistory(int table) {

    }
}
