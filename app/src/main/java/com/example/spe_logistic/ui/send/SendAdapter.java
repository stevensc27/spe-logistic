package com.example.spe_logistic.ui.send;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spe_logistic.R;

import java.util.ArrayList;

public class SendAdapter extends RecyclerView.Adapter<SendAdapter.ViewHolderSends> {

    ArrayList<SendVo> send_list;

    public SendAdapter(ArrayList<SendVo> send_list) {
        this.send_list = send_list;
    }

    @NonNull
    @Override
    public SendAdapter.ViewHolderSends onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.send_item_list,null,false);
        return new ViewHolderSends(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SendAdapter.ViewHolderSends viewHolderSends, int i) {

        viewHolderSends.id.setText(send_list.get(i).getId());
        viewHolderSends.address.setText(send_list.get(i).getAddress());

        switch (send_list.get(i).getStatus()){
            case "1":
                viewHolderSends.state.setText("Pendiente");
                viewHolderSends.state.setTextColor(0xAAFF8000);
                break;
            case "2":
                viewHolderSends.state.setText("En Alistamiento");
                viewHolderSends.state.setTextColor(0xAA2FD6E9);
                viewHolderSends.edit.setAlpha(80);
                viewHolderSends.delete.setAlpha(80);
                break;
            case "3":
                viewHolderSends.state.setText("Despachado");
                viewHolderSends.state.setTextColor(0xAA43ED26);
                viewHolderSends.edit.setAlpha(80);
                viewHolderSends.delete.setAlpha(80);
                break;
            default:
                viewHolderSends.state.setText("");
                break;
        }

    }

    @Override
    public int getItemCount() {
        return send_list.size();
    }

    public class ViewHolderSends extends RecyclerView.ViewHolder {

        TextView  id,address,state;
        ImageView edit,delete;


        public ViewHolderSends(@NonNull View itemView) {
            super(itemView);
            id      = (TextView)  itemView.findViewById(R.id.send_card_id);
            address = (TextView)  itemView.findViewById(R.id.send_card_address);
            state   = (TextView)  itemView.findViewById(R.id.send_card_state);
            edit    = (ImageView) itemView.findViewById(R.id.send_card_edit);
            delete  = (ImageView) itemView.findViewById(R.id.send_card_delete);
        }
    }
}
