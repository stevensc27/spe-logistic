package com.example.spe_logistic.ui.perfil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spe_logistic.R;
import com.example.spe_logistic.SQLiteConnectionHelper;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

public class PerfilPqrAdapter extends RecyclerView.Adapter<PerfilPqrAdapter.ViewHolderPerfilPqr> implements View.OnClickListener {
    @NonNull

    ArrayList<PerfilPqrVo> pqr_list;
    private View.OnClickListener listener;

    public PerfilPqrAdapter(ArrayList<PerfilPqrVo> pqr_list) {
        this.pqr_list = pqr_list;
    }

    @Override
    public PerfilPqrAdapter.ViewHolderPerfilPqr onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pqr_item_list, viewGroup, false);
        view.setOnClickListener(this);

        return new ViewHolderPerfilPqr(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PerfilPqrAdapter.ViewHolderPerfilPqr viewHolderPerfilPqr, int position) {
        viewHolderPerfilPqr.id_category.setText(pqr_list.get(position).getId() + " - " + pqr_list.get(position).getCategory());
        viewHolderPerfilPqr.description.setText(pqr_list.get(position).getDescription());
        viewHolderPerfilPqr.date.setText(pqr_list.get(position).getDate());

        switch (pqr_list.get(position).getState()) {
            case "1":
                viewHolderPerfilPqr.state.setText("Abierto");
                viewHolderPerfilPqr.state.setTextColor(0xAAFF8000);
                break;
            case "2":
                viewHolderPerfilPqr.state.setText("En gestión");
                viewHolderPerfilPqr.state.setTextColor(0xAA2FD6E9);
                break;
            case "3":
                viewHolderPerfilPqr.state.setText("Solucionado");
                viewHolderPerfilPqr.state.setTextColor(0xAA43ED26);
                break;
            default:
                viewHolderPerfilPqr.state.setText("");
                break;
        }

    }

    @Override
    public int getItemCount() {
        return pqr_list.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public class ViewHolderPerfilPqr extends RecyclerView.ViewHolder {

        TextView id_category, description, date, state;

        public ViewHolderPerfilPqr(@NonNull View itemView) {
            super(itemView);

            id_category = itemView.findViewById(R.id.pqr_card_id_category);
            description = itemView.findViewById(R.id.pqr_card_description);
            date        = itemView.findViewById(R.id.pqr_card_date);
            state       = itemView.findViewById(R.id.pqr_card_state);
        }
    }

    public void updateList(ArrayList<PerfilPqrVo> newList) {
        pqr_list = new ArrayList<>();
        pqr_list.addAll(newList);
        notifyDataSetChanged();
    }
}
