package com.example.spe_logistic.ui.collect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.example.spe_logistic.R;

public class CollectFragment extends Fragment {

    private CollectViewModel collectViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        collectViewModel =
                ViewModelProviders.of(this).get(CollectViewModel.class);
        View root = inflater.inflate(R.layout.fragment_collect, container, false);
        final TextView textView = root.findViewById(R.id.text_references);
        collectViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}