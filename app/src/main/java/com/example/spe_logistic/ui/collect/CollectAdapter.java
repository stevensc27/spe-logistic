package com.example.spe_logistic.ui.collect;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spe_logistic.MyApp;
import com.example.spe_logistic.R;
import com.example.spe_logistic.SQLiteConnectionHelper;
import com.example.spe_logistic.utilities.Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.ViewHolderCollect> {

    ArrayList<CollectVo> collect_list;

    private SQLiteConnectionHelper con;

    private NavController navController;

    private Context context;

    private EditText justify;
    private String   collect_id;

    public CollectAdapter(ArrayList<CollectVo> collect_list) {
        this.collect_list = collect_list;
    }

    @NonNull
    @Override
    public CollectAdapter.ViewHolderCollect onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.collect_item_list,null,false);

        navController = Navigation.findNavController((Activity) viewGroup.getContext(), R.id.nav_host_fragment);

        return new ViewHolderCollect(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CollectAdapter.ViewHolderCollect viewHolderCollect, final int i) {



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
                //viewHolderCollect.edit.setAlpha(20);
                //viewHolderCollect.delete.setAlpha(20);
                break;
            default:
                viewHolderCollect.state.setText("");
                break;
        }

        viewHolderCollect.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (viewHolderCollect.state.getText().toString().equals("Recogido")){
                    Toast.makeText(viewHolderCollect.itemView.getContext(),"La recogida ya se ha realizado",Toast.LENGTH_LONG).show();
                }else {
                    Bundle bundle = new Bundle();
                    bundle.putString("collectId",collect_list.get(i).getId());
                    navController.navigate(R.id.collectItemFragment,bundle);
                }
            }
        });


        viewHolderCollect.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (viewHolderCollect.state.getText().toString().equals("Recogido")){
                    Toast.makeText(viewHolderCollect.itemView.getContext(),"La recogida ya se ha realizado",Toast.LENGTH_LONG).show();
                }else {
                    collect_id = viewHolderCollect.id_adress.getText().toString().split(" - ")[0];
                    confirmDelete(v, i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return collect_list.size();
    }

    public class ViewHolderCollect extends RecyclerView.ViewHolder {

        TextView  id_adress,date,state;
        ImageView edit,delete;

        public ViewHolderCollect(@NonNull final View itemView) {
            super(itemView);

            id_adress = (TextView)  itemView.findViewById(R.id.collect_card_id_address);
            date      = (TextView)  itemView.findViewById(R.id.collect_card_date);
            state     = (TextView)  itemView.findViewById(R.id.collect_card_state);
            edit      = (ImageView) itemView.findViewById(R.id.collect_card_edit);
            delete    = (ImageView) itemView.findViewById(R.id.collect_card_delete);

        }
    }

    public void updateList (ArrayList<CollectVo> newList){
        collect_list = new ArrayList<>();
        collect_list.addAll(newList);
        notifyDataSetChanged();
    }

    private void confirmDelete(View v, final int i){
        final AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
        View view = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_text,null,false);

        justify          = view.findViewById(R.id.dialog_justify);
        Button   confirm = view.findViewById(R.id.dialog_confirm);
        Button   cancel  = view.findViewById(R.id.dialog_cancel);

        alert.setView(view);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(true);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (justify.getText().toString().length() > 15){
                    deleteCollect(v, i);
                    alertDialog.dismiss();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void deleteCollect(View v, int i){
        /*SharedPreferences preferences = v.getContext().getSharedPreferences("credentials", v.getContext().MODE_PRIVATE);
        Integer user_id = preferences.getInt("user_id",0);*/

        con = new SQLiteConnectionHelper(v.getContext(),"SPEDB",null,1);
        SQLiteDatabase db = con.getWritableDatabase();

        saveHistory();

        String[] parameters = {collect_id};
        db.delete(Utilities.RECOGIDAS,Utilities.RECOGIDAS_ID+"=?",parameters);

        collect_list.remove(i);
        notifyDataSetChanged();
        Toast.makeText(v.getContext(),"Se ha cancelado la recogida",Toast.LENGTH_LONG).show();
        db.close();
    }

    private void saveHistory() {

        SQLiteDatabase db = con.getWritableDatabase();

        String date        = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String description = "Cancelación de recogida. " + justify;

        ContentValues values = new ContentValues();

        values.put(Utilities.HISTORIAL_RECOGIDAS_FECHA,date);
        values.put(Utilities.HISTORIAL_RECOGIDAS_DESCRIPCION,description);
        values.put(Utilities.HISTORIAL_RECOGIDAS_RECOGIDA_ID,collect_id);

        Long idResult = db.insert(Utilities.HISTORIAL_RECOGIDAS, Utilities.HISTORIAL_RECOGIDAS_ID,values);

        Log.i("APP","ID delete history collect: "+ idResult);

    }
}
