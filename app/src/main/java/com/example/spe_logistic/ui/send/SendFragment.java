package com.example.spe_logistic.ui.send;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.example.spe_logistic.R;

import java.util.ArrayList;

public class SendFragment extends Fragment {

    private SendViewModel sendViewModel;

    ListView send_list;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);

        View root = inflater.inflate(R.layout.fragment_send, container, false);

        send_list = root.findViewById(R.id.send_list);

        sendViewModel.getSendList().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(@Nullable ArrayList<String> send_list_array) {

                //ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,send_list_array);
                //send_list.setAdapter(adapter);

            }
        });


        return root;


    }
}