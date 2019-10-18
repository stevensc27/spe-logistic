package com.example.spe_logistic.ui.references;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spe_logistic.R;

import java.util.ArrayList;

public class ReferencesAdapter extends RecyclerView.Adapter<ReferencesAdapter.ViewHolderReferences> {

    ArrayList<ReferencesVo> references_list;

    public ReferencesAdapter(ArrayList<ReferencesVo> references_list) {
        this.references_list = references_list;
    }

    @NonNull
    @Override
    public ReferencesAdapter.ViewHolderReferences onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.references_item_list,null,false);

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.references_item_list, viewGroup,false);

        return new ViewHolderReferences(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReferencesAdapter.ViewHolderReferences viewHolderReferences, int i) {
        viewHolderReferences.reference_code_bar.setText(references_list.get(i).getReference()+" - "+references_list.get(i).getCode_bar());
        viewHolderReferences.description.setText(references_list.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return references_list.size();
    }

    public class ViewHolderReferences extends RecyclerView.ViewHolder {

        TextView reference_code_bar,description;
        ImageView edit;


        public ViewHolderReferences(@NonNull View itemView) {
            super(itemView);
            reference_code_bar = (TextView)  itemView.findViewById(R.id.references_card_references_code_bar);
            description        = (TextView)  itemView.findViewById(R.id.references_card_description);
            edit               = (ImageView) itemView.findViewById(R.id.references_card_edit);
        }
    }
}
