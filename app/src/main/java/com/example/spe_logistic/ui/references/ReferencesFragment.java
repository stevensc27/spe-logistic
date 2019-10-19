package com.example.spe_logistic.ui.references;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.widget.Toast;

import com.example.spe_logistic.MyApp;
import com.example.spe_logistic.R;

import java.util.ArrayList;

public class ReferencesFragment extends Fragment {

    private ReferencesViewModel referencesViewModel;
    ReferencesAdapter adapter;

    RecyclerView references_list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        referencesViewModel =
                ViewModelProviders.of(this).get(ReferencesViewModel.class);

        View root = inflater.inflate(R.layout.fragment_references, container, false);

        references_list = root.findViewById(R.id.references_list);
        references_list.setLayoutManager(new LinearLayoutManager(MyApp.getContext()));

        referencesViewModel.getReferencesList().observe(this, new Observer<ArrayList<ReferencesVo>>() {
            @Override
            public void onChanged(@Nullable ArrayList<ReferencesVo> references_array_list) {
                adapter = new ReferencesAdapter(references_array_list);

                adapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),""+(references_list.getChildAdapterPosition(v)),Toast.LENGTH_LONG).show();
                    }
                });

                references_list.setAdapter(adapter);
            }
        });




        return root;
    }
}