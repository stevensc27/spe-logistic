package com.example.spe_logistic.ui.references;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class ReferencesFragment extends Fragment implements SearchView.OnQueryTextListener {

    private ReferencesViewModel referencesViewModel;
    private ReferencesAdapter adapter;
    private ArrayList<ReferencesVo> referencesVos = new ArrayList<>();

    RecyclerView references_list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

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
                referencesVos = references_array_list;
                adapter       = new ReferencesAdapter(referencesVos);

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Mis Referencias");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        String userInput = s.toLowerCase();
        ArrayList<ReferencesVo> newList = new ArrayList<>();

        for(int i=0;i<referencesVos.size();i++) {
            ReferencesVo referencesVo = referencesVos.get(i);

            if (referencesVo.getId().contains(userInput) || referencesVo.getCode_bar().toLowerCase().contains(userInput)){
                newList.add(referencesVo);
            }
        }

        if (newList.size() == 0){
            adapter.updateList(referencesVos);
        }else {
            adapter.updateList(newList);
        }

        return true;
    }
}