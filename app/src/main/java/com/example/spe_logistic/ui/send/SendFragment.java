package com.example.spe_logistic.ui.send;

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

public class SendFragment extends Fragment implements SearchView.OnQueryTextListener {

    private SendViewModel sendViewModel;
    private SendAdapter adapter;
    private ArrayList<SendVo> sendVos = new ArrayList<>();

    private NavController navController;

    RecyclerView send_list;
    FloatingActionButton new_send;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        new_send = root.findViewById(R.id.new_send);
        new_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*SendItemFragment send_item = new SendItemFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,send_item);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/

                Bundle bundle = new Bundle();
                navController.navigate(R.id.sendItemFragment, bundle);
            }
        });

        /*
        SharedPreferences preferences = this.getActivity().getSharedPreferences("credentials", Context.MODE_PRIVATE);
        Integer user_id = preferences.getInt("user_id",0);
        Log.i("APP","USER FRAGMENT: "+user_id);
         */

        send_list = root.findViewById(R.id.send_list);
        send_list.setLayoutManager(new LinearLayoutManager(MyApp.getContext()));

        sendViewModel.getSendList().observe(this, new Observer<ArrayList<SendVo>>() {
            @Override
            public void onChanged(@Nullable ArrayList<SendVo> send_array_list) {
                sendVos = send_array_list;
                adapter = new SendAdapter(send_array_list);
                send_list.setAdapter(adapter);
            }
        });

        send_list.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Mis envíos");
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
        ArrayList<SendVo> newList = new ArrayList<>();

        for (int i = 0; i < sendVos.size(); i++) {
            SendVo sendVo = sendVos.get(i);

            //            String status;
            //            if (sendVo.getStatus().equals("1")){
            //                status = "pendiente";
            //            }else if (sendVo.getStatus().equals("2")){
            //                status = "en alistamiento";
            //            }else {
            //                status = "despachado";
            //            }

            if (sendVo.getId().contains(userInput) || sendVo.getName_receiver().toLowerCase().contains(userInput)) {
                newList.add(sendVo);
            }
        }

        if (newList.size() == 0) {
            adapter.updateList(sendVos);
        } else {
            adapter.updateList(newList);
        }

        return true;
    }
}