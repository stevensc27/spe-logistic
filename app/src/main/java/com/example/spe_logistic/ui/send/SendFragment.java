package com.example.spe_logistic.ui.send;

/*import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.ViewGroup;
import android.widget.ListView;
import android.view.LayoutInflater;

import com.example.spe_logistic.R;
import com.example.spe_logistic.SQLiteConnectionHelper;
import com.example.spe_logistic.utilities.Utilities;

import java.util.ArrayList;

public class SendFragment extends Fragment implements View.OnClickListener {

    ListView send_list;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_send, container, false);

        send_list = (ListView) root.findViewById(R.id.send_list);


        ArrayList<String> send_list_array = new ArrayList<>();
        send_list_array.add("Perro");
        send_list_array.add("Gato");
        send_list_array.add("Raton");
        send_list_array.add("Caballo");
        send_list_array.add("Vaca");
        send_list_array.add("Camello");

        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,send_list_array);

        send_list.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {

    }
} */

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.example.spe_logistic.MyApp;
import com.example.spe_logistic.R;

import java.util.ArrayList;

public class SendFragment extends Fragment {

    private SendViewModel sendViewModel;


    RecyclerView send_list;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
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

                //ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,send_list_array);
                //send_list.setAdapter(adapter);

            }
        });


        return root;
    }
}