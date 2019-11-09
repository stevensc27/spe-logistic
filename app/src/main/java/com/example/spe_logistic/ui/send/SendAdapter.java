package com.example.spe_logistic.ui.send;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spe_logistic.R;
import com.example.spe_logistic.SQLiteConnectionHelper;
import com.example.spe_logistic.utilities.Utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SendAdapter extends RecyclerView.Adapter<SendAdapter.ViewHolderSends> {

    ArrayList<SendVo> send_list;

    private SQLiteConnectionHelper con;

    private NavController navController;

    private EditText justify;
    private String   send_id;

    private int user_id;

    public SendAdapter(ArrayList<SendVo> send_list) {
        this.send_list = send_list;
    }

    @NonNull
    @Override
    public SendAdapter.ViewHolderSends onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.send_item_list,null,false);

        SharedPreferences preferences = viewGroup.getContext().getSharedPreferences("credentials", viewGroup.getContext().MODE_PRIVATE);
        user_id = preferences.getInt("user_id",0);

        navController = Navigation.findNavController((Activity) viewGroup.getContext(), R.id.nav_host_fragment);

        return new ViewHolderSends(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SendAdapter.ViewHolderSends viewHolderSends, int i) {

        viewHolderSends.id.setText(send_list.get(i).getId()+" - "+send_list.get(i).getName_receiver());
        viewHolderSends.address.setText(send_list.get(i).getAddress());

        switch (send_list.get(i).getStatus()){
            case "2":
                viewHolderSends.state.setText("Pendiente");
                viewHolderSends.state.setTextColor(0xAAFF8000);
                break;
            case "3":
                viewHolderSends.state.setText("En Alistamiento");
                viewHolderSends.state.setTextColor(0xAA2FD6E9);
                viewHolderSends.edit.setAlpha(20);
                viewHolderSends.delete.setAlpha(20);
                break;
            case "4":
                viewHolderSends.state.setText("Despachado");
                viewHolderSends.state.setTextColor(0xAA43ED26);
                viewHolderSends.edit.setAlpha(20);
                viewHolderSends.delete.setAlpha(20);
                break;
            default:
                viewHolderSends.state.setText("");
                break;
        }

        viewHolderSends.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (viewHolderSends.state.getText().toString().equals("Despachado")) {
                    Toast.makeText(viewHolderSends.itemView.getContext(), "El envío ya se ha procesado", Toast.LENGTH_LONG).show();
                } else if (viewHolderSends.state.getText().toString().equals("En Alistamiento")){
                    Toast.makeText(viewHolderSends.itemView.getContext(), "El envío ya está en proceso", Toast.LENGTH_LONG).show();
                }else {
                    Bundle bundle = new Bundle();
                    bundle.putString("sendId",send_list.get(i).getId());
                    navController.navigate(R.id.sendItemFragment,bundle);
                }
            }
        });


        viewHolderSends.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (viewHolderSends.state.getText().toString().equals("Despachado")){
                    Toast.makeText(viewHolderSends.itemView.getContext(),"El envío ya se ha procesado",Toast.LENGTH_LONG).show();
                } else if (viewHolderSends.state.getText().toString().equals("En Alistamiento")){
                    Toast.makeText(viewHolderSends.itemView.getContext(), "El envío ya está en proceso", Toast.LENGTH_LONG).show();
                }else {
                    send_id = viewHolderSends.id.getText().toString();
                    confirmDelete(v, i);
                }
            }
        });

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

    public void updateList (ArrayList<SendVo> newList){
        send_list = new ArrayList<>();
        send_list.addAll(newList);
        notifyDataSetChanged();
    }

    private void confirmDelete(View v, final int i){
        final AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
        View view = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_text,null,false);

        justify        = view.findViewById(R.id.dialog_justify);
        Button confirm = view.findViewById(R.id.dialog_confirm);
        Button cancel  = view.findViewById(R.id.dialog_cancel);

        alert.setView(view);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(true);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (justify.getText().toString().length() > 15){
                    deleteSend(v, i);
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

    private void deleteSend(View v, int i){

        con = new SQLiteConnectionHelper(v.getContext(),"SPEDB",null,1);
        SQLiteDatabase db = con.getWritableDatabase();

        saveHistory();

        String[] parameters = {send_id};
        ContentValues values = new ContentValues();
        values.put(Utilities.INVENTARIO_ESTADO_ID,"1");
        values.put(Utilities.INVENTARIO_ENVIO_ID,"");

        db.update(Utilities.INVENTARIO, values, Utilities.INVENTARIO_ENVIO_ID + " = ?", parameters);

        db.delete(Utilities.ENVIOS,Utilities.ENVIOS_ID+"=?",parameters);

        send_list.remove(i);
        notifyDataSetChanged();
        Toast.makeText(v.getContext(),"Se ha cancelado el envío",Toast.LENGTH_LONG).show();
        db.close();
    }

    private void saveHistory() {

        SQLiteDatabase db = con.getWritableDatabase();

        String date        = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String description = "Cancelación de envío. " + justify.getText().toString();

        ContentValues values = new ContentValues();

        values.put(Utilities.HISTORIAL_ENVIOS_FECHA,date);
        values.put(Utilities.HISTORIAL_ENVIOS_DESCRIPCION,description);
        values.put(Utilities.HISTORIAL_ENVIOS_CLIENTE_ID,user_id);
        values.put(Utilities.HISTORIAL_ENVIOS_ENVIO_ID,send_id);

        Long idResult = db.insert(Utilities.HISTORIAL_ENVIOS, Utilities.HISTORIAL_ENVIOS_ID,values);

        Log.i("APP","ID delete history collect: "+ idResult);


        String[] parameters = {send_id};
        String queryInventorySend = "SELECT     referencias.id, "                           +
                                    "           referencias.codigo_barras, "                +
                                    "           count(*) "                                  +
                                    "FROM       inventario "                                +
                                    "INNER JOIN referencias "                               +
                                    "ON         referencias.id = inventario.referencia_id " +
                                    "WHERE      inventario.envio_id = ? "                   +
                                    "GROUP BY   referencias.id, "                           +
                                    "           referencias.codigo_barras";
        Cursor cursor = db.rawQuery(queryInventorySend,parameters);

        while (cursor.moveToNext()){
            ContentValues values1 = new ContentValues();

            values1.put(Utilities.DETALLE_HISTORIAL_ENVIOS_HISTORIAL_ENVIOS_ID,idResult);
            values1.put(Utilities.DETALLE_HISTORIAL_ENVIOS_REFERENCIA_ID,cursor.getString(0));
            values1.put(Utilities.DETALLE_HISTORIAL_ENVIOS_DESCRIPCION,"Cancelación de envío. Referencia: "+cursor.getString(1)+" Cantidad: "+cursor.getString(2));

            Long idResult2 = db.insert(Utilities.DETALLE_HISTORIAL_ENVIOS, Utilities.DETALLE_HISTORIAL_ENVIOS_ID,values1);

            Log.i("APP","ID delete detail history collect: "+ idResult2);
        }

    }
}
