package com.example.spe_logistic.ui.collect;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spe_logistic.R;

import java.util.ArrayList;

public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.ViewHolderCollect> {

    ArrayList<CollectVo> collect_list;

    public CollectAdapter(ArrayList<CollectVo> collect_list) {
        this.collect_list = collect_list;
    }

    @NonNull
    @Override
    public CollectAdapter.ViewHolderCollect onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.collect_item_list,null,false);
        return new ViewHolderCollect(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectAdapter.ViewHolderCollect viewHolderCollect, int i) {

        viewHolderCollect.id_adress.setText(collect_list.get(i).getId()+" - "+collect_list.get(i).getAddress());
        viewHolderCollect.date.setText("Programado para "+collect_list.get(i).getDate());

        switch (collect_list.get(i).getStatus()){
            case "1":
                viewHolderCollect.state.setText("Pendiente");
                viewHolderCollect.state.setTextColor(0xAAFF8000);
                break;
            case "2":
                viewHolderCollect.state.setText("Aceptado");
                viewHolderCollect.state.setTextColor(0xAA2FD6E9);
                break;
            case "3":
                viewHolderCollect.state.setText("Recogido");
                viewHolderCollect.state.setTextColor(0xAA43ED26);
                viewHolderCollect.edit.setAlpha(20);
                viewHolderCollect.delete.setAlpha(20);
                break;
            default:
                viewHolderCollect.state.setText("");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return collect_list.size();
    }

    public class ViewHolderCollect extends RecyclerView.ViewHolder {

        TextView  id_adress,date,state;
        ImageView edit,delete;

        public ViewHolderCollect(@NonNull View itemView) {
            super(itemView);

            id_adress = (TextView)  itemView.findViewById(R.id.collect_card_id_address);
            date      = (TextView)  itemView.findViewById(R.id.collect_card_date);
            state     = (TextView)  itemView.findViewById(R.id.collect_card_state);
            edit      = (ImageView) itemView.findViewById(R.id.collect_card_edit);
            delete    = (ImageView) itemView.findViewById(R.id.collect_card_delete);

        }
    }
}
