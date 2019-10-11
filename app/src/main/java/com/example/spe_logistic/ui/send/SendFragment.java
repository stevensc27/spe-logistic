package com.example.spe_logistic.ui.send;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.example.spe_logistic.MyApp;
import com.example.spe_logistic.R;

import java.util.ArrayList;

public class SendFragment extends Fragment {

    private SendViewModel sendViewModel;

    RecyclerView send_list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);

        View root = inflater.inflate(R.layout.fragment_send, container, false);



        send_list = root.findViewById(R.id.send_list);
        send_list.setLayoutManager(new LinearLayoutManager(MyApp.getContext()));

        sendViewModel.getSendList().observe(this, new Observer<ArrayList<SendVo>>() {
            @Override
            public void onChanged(@Nullable ArrayList<SendVo> send_list_array) {
                SendAdapter adapter = new SendAdapter(send_list_array);

                send_list.setAdapter(adapter);

            }
        });

        return root;
    }
}