package com.example.spe_logistic.ui.send;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.spe_logistic.R;
import com.example.spe_logistic.entities.Envios;

import java.util.ArrayList;

public class SendAdapter extends RecyclerView.Adapter<SendAdapter.ViewHolderSends> {

    ArrayList<SendVo> send_list;

    private Object AssetManager;

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
        viewHolderSends.state.setText(send_list.get(i).getStatus());
        viewHolderSends.state.setTextColor(0xAAFF0000);

    }

    @Override
    public int getItemCount() {
        return send_list.size();
    }

    public class ViewHolderSends extends RecyclerView.ViewHolder {

        TextView id,address,state;


        public ViewHolderSends(@NonNull View itemView) {
            super(itemView);
            id      = (TextView) itemView.findViewById(R.id.id);
            address = (TextView) itemView.findViewById(R.id.address);
            state   = (TextView) itemView.findViewById(R.id.state);
        }
    }
}
