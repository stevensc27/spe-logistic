package com.example.spe_logistic.ui.collect;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.example.spe_logistic.MyApp;
import com.example.spe_logistic.R;

import java.util.ArrayList;

public class CollectFragment extends Fragment {

    private CollectViewModel collectViewModel;

    RecyclerView collect_list;
    FloatingActionButton new_collect;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        collectViewModel =
                ViewModelProviders.of(this).get(CollectViewModel.class);
        View root = inflater.inflate(R.layout.fragment_collect, container, false);

        /*
        new_collect = (FloatingActionButton) root.findViewById(R.id.new_collect);
        new_collect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(MyApp.getContext(),"Hola desde boton flotante",Toast.LENGTH_LONG).show();
                //Snackbar.make(v,"AAAAAAAAAAA",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                CollectItemFragment collect_item = new CollectItemFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,collect_item);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
         */

        collect_list = root.findViewById(R.id.collect_list);
        collect_list.setLayoutManager(new LinearLayoutManager(MyApp.getContext()));

        collectViewModel.getCollectList().observe(this, new Observer<ArrayList<CollectVo>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CollectVo> collect_array_list) {
                CollectAdapter adapter = new CollectAdapter(collect_array_list);
                collect_list.setAdapter(adapter);
            }
        });


        return root;
    }
}