package com.example.spe_logistic.ui.send;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.support.v7.widget.Toolbar;

import com.example.spe_logistic.HomeActivity;
import com.example.spe_logistic.MyApp;
import com.example.spe_logistic.R;
import com.example.spe_logistic.SimpleDividerItemDecoration;

import java.util.ArrayList;

public class SendFragment extends Fragment  {

    private SendViewModel sendViewModel;
    private Toolbar toolbar;

    RecyclerView send_list;
    FloatingActionButton new_send;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);



        toolbar = (Toolbar) root.findViewById(R.id.toolBar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);



        new_send = (FloatingActionButton) root.findViewById(R.id.new_send);
        new_send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SendItemFragment send_item = new SendItemFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,send_item);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
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
                SendAdapter adapter = new SendAdapter(send_array_list);
                send_list.setAdapter(adapter);
            }
        });

        send_list.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

        return root;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.toolbar_menu, menu);
    }


}