package com.example.spe_logistic.ui.collect;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.spe_logistic.MyApp;
import com.example.spe_logistic.R;
import com.example.spe_logistic.SimpleDividerItemDecoration;

import java.util.ArrayList;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class CollectFragment extends Fragment implements SearchView.OnQueryTextListener {

    private CollectViewModel collectViewModel;
    private CollectAdapter adapter;
    private ArrayList<CollectVo> collectVos = new ArrayList<>();

    private NavController navController;

    RecyclerView collect_list;
    FloatingActionButton new_collect;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        collectViewModel =
                ViewModelProviders.of(this).get(CollectViewModel.class);
        View root = inflater.inflate(R.layout.fragment_collect, container, false);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        new_collect = root.findViewById(R.id.new_collect);
        new_collect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                /*CollectItemFragment collect_item = new CollectItemFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,collect_item);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/

                Bundle bundle = new Bundle();
                navController.navigate(R.id.collectItemFragment,bundle);
            }
        });


        collect_list = root.findViewById(R.id.collect_list);
        collect_list.setLayoutManager(new LinearLayoutManager(MyApp.getContext()));

        collectViewModel.getCollectList().observe(this, new Observer<ArrayList<CollectVo>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CollectVo> collect_array_list) {
                collectVos = collect_array_list;
                adapter    = new CollectAdapter(collectVos);
                collect_list.setAdapter(adapter);
            }
        });

        collect_list.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Mis Recogidas");
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
        ArrayList<CollectVo> newList = new ArrayList<>();

        for(int i=0;i<collectVos.size();i++) {
            CollectVo collectVo = collectVos.get(i);

            if (collectVo.getId().contains(userInput) || collectVo.getAddress().toLowerCase().contains(userInput)){
                newList.add(collectVo);
            }
        }

        if (newList.size() == 0){
            adapter.updateList(collectVos);
        }else {
            adapter.updateList(newList);
        }

        return true;
    }
}