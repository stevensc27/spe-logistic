package com.example.spe_logistic.ui.send;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.spe_logistic.R;
import com.example.spe_logistic.SQLiteConnectionHelper;
import com.example.spe_logistic.entities.Ciudades;
import com.example.spe_logistic.utilities.Utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendItemFragment extends Fragment implements View.OnClickListener {

    private String send_id;

    private EditText name;
    private EditText phone;
    private EditText email;
    private EditText address;
    private EditText invoice;
    private Spinner city;

    private EditText codebar;
    private EditText amount;

    private Button onDelete;
    private Button onAdd;
    private Button save;

    private SQLiteConnectionHelper con;
    private NavController navController;

    private ArrayAdapter<String> adapter_city;
    private ArrayList<String> citys;

    private int user_id;

    View root;

    private LinearLayout parentLinearLayout;

    public SendItemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SharedPreferences preferences = this.getActivity().getSharedPreferences("credentials",this.getActivity().MODE_PRIVATE);
        user_id = preferences.getInt("user_id",0);


        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_send_item, container, false);

        send_id = (getArguments().getString("sendId", "-1"));

        parentLinearLayout = root.findViewById(R.id.parent_linear_layout);


        onAdd = root.findViewById(R.id.add_field_button);
        onDelete = root.findViewById(R.id.delete_button);
        save = root.findViewById(R.id.send_item_save);

        onAdd.setOnClickListener(this);
        onDelete.setOnClickListener(this);
        save.setOnClickListener(this);

        name = root.findViewById(R.id.send_item_name);
        phone = root.findViewById(R.id.send_item_phone);
        email = root.findViewById(R.id.send_item_email);
        city = root.findViewById(R.id.send_item_city);
        address = root.findViewById(R.id.send_item_address);
        invoice = root.findViewById(R.id.send_item_invoice);
        codebar = root.findViewById(R.id.send_item_codebar);
        amount = root.findViewById(R.id.send_item_amount);

        /*road_type         = root.findViewById(R.id.send_item_road_type);
        number1           = root.findViewById(R.id.send_item_number1);
        chart1            = root.findViewById(R.id.send_item_chart1);
        orientation_road1 = root.findViewById(R.id.send_item_orientation_road1);
        number2           = root.findViewById(R.id.send_item_number2);
        chart2            = root.findViewById(R.id.send_item_chart2);
        orientation_road2 = root.findViewById(R.id.send_item_orientation_road2);
        number3           = root.findViewById(R.id.send_item_number3);*/

        getCitys();
        adapter_city = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, citys);
        city.setAdapter(adapter_city);

        /*ArrayAdapter<CharSequence> adapter_address_options = ArrayAdapter.createFromResource(this.getActivity(),R.array.address_options,android.R.layout.simple_spinner_item);
        road_type.setAdapter(adapter_address_options);
        ArrayAdapter<CharSequence> orientation_road_options = ArrayAdapter.createFromResource(this.getActivity(),R.array.orientation_road_options,android.R.layout.simple_spinner_item);
        orientation_road1.setAdapter(orientation_road_options);
        ArrayAdapter<CharSequence> orientation_road_options2 = ArrayAdapter.createFromResource(this.getActivity(),R.array.orientation_road_options,android.R.layout.simple_spinner_item);
        orientation_road2.setAdapter(orientation_road_options2);*/

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        if (!send_id.equals("-1")) {
            getCollectData();
        }

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_field_button:
                onAddField(v);
                break;

            case R.id.delete_button:
                onDeleteField(v);
                break;

            case R.id.send_item_save:
                if (validateData(v)) {
                    saveSend();
                    navController.navigate(R.id.navigation_send);
                }
                break;
        }
    }

    @SuppressLint("WrongViewCast")
    public void onAddField(View v) {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(this.getActivity().LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.reference_field, null);

        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 2);
    }

    public void onDeleteField(View v) {

        if (parentLinearLayout.getChildCount() > 4) {
            parentLinearLayout.removeViewAt(parentLinearLayout.getChildCount() - 3);
        }
    }

    private void getCitys() {
        con = new SQLiteConnectionHelper(this.getContext(), "SPEDB", null, 1);
        SQLiteDatabase db = con.getReadableDatabase();

        citys = new ArrayList<String>();

        String queryCitys = "SELECT     id,nombre " +
                "FROM       ciudades " +
                "ORDER BY   nombre";

        Cursor cursor = db.rawQuery(queryCitys, null);

        while (cursor.moveToNext()) {
            String city;
            city = cursor.getString(1) + " " + cursor.getString(0);
            citys.add(city);
        }
    }

    private void getCollectData() {
        con = new SQLiteConnectionHelper(this.getContext(), "SPEDB", null, 1);
        SQLiteDatabase db = con.getReadableDatabase();

        String[] parameters = {send_id};
        String querySendData = "SELECT     nombre_destinatario, " +
                "           telefono_destinatario, " +
                "           email_destinatario, " +
                "           direccion_destinatario," +
                "           ciudades.nombre||' '||ciudad_destinatario_id, " +
                "           factura " +
                "FROM       envios " +
                "INNER JOIN ciudades " +
                "ON         ciudades.id = envios.ciudad_destinatario_id " +
                "WHERE      envios.id = ?";
        Cursor cursor = db.rawQuery(querySendData, parameters);
        cursor.moveToFirst();

        name.setText(cursor.getString(0));
        phone.setText(cursor.getString(1));
        email.setText(cursor.getString(2));
        address.setText(cursor.getString(3));
        int spinnerPosition = adapter_city.getPosition(cursor.getString(4));
        city.setSelection(spinnerPosition);
        invoice.setText(cursor.getString(5));

        String queryGetInventoryReserved = "SELECT     referencias.codigo_barras, " +
                "           count(*) " +
                "FROM       inventario " +
                "INNER JOIN referencias " +
                "ON         referencias.id = inventario.referencia_id " +
                "WHERE      inventario.envio_id = ? " +
                "GROUP BY   referencias.codigo_barras";
        Cursor cursor2 = db.rawQuery(queryGetInventoryReserved, parameters);

        cursor2.moveToFirst();

        codebar.setText(cursor2.getString(0));
        amount.setText(cursor2.getString(1));


        while (cursor2.moveToNext()) {

            Log.i("APP", "CB: " + cursor2.getString(0));

            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(this.getActivity().LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.reference_field, null);

            parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 2);

            LinearLayout layout = root.findViewById(R.id.parent_linear_layout);
            View row = layout.getChildAt(parentLinearLayout.getChildCount() - 3);

            Log.i("APP", "Sons: " + (parentLinearLayout.getChildCount()));

            View field_codebar = ((LinearLayout) row).getChildAt(0);
            View field_amount = ((LinearLayout) row).getChildAt(1);

            ((EditText) field_codebar).setText(cursor2.getString(0));
            ((EditText) field_amount).setText(cursor2.getString(1));
        }

        db.close();
    }

    private boolean validateData(View v) {

        String isEmail = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

        if (name.getText().toString().length() < 5) {
            Toast.makeText(this.getActivity(), "Nombre de destinatario muy corto", Toast.LENGTH_LONG).show();
            return false;
        }

        if (phone.getText().toString().length() < 7) {
            Toast.makeText(this.getActivity(), "Teléfono de destinatario inválido", Toast.LENGTH_LONG).show();
            return false;
        }

        Pattern pattern = Pattern.compile(isEmail, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email.getText().toString());
        if (!matcher.matches()) {
            Toast.makeText(this.getActivity(), "Correo de destinatario inválido", Toast.LENGTH_LONG).show();
            return false;
        }

        if (address.getText().toString().length() < 5) {
            Toast.makeText(this.getActivity(), "Dirección de destinatario muy corta", Toast.LENGTH_LONG).show();
            return false;
        }

        if (invoice.getText().toString().length() == 0) {
            Toast.makeText(this.getActivity(), "Por favor ingrese una factura", Toast.LENGTH_LONG).show();
            return false;
        }

        LinearLayout layout = root.findViewById(R.id.parent_linear_layout);
        View row = null;
        for (int i = 1; i < layout.getChildCount() - 2; i++) {
            row = layout.getChildAt(i);
            View field_codebar = null;
            View field_amount = null;

            if (row instanceof LinearLayout) {
                field_codebar = ((LinearLayout) row).getChildAt(0);
                field_amount = ((LinearLayout) row).getChildAt(1);

                String codebar_unit = ((EditText) field_codebar).getText().toString();
                String amount_unit = ((EditText) field_amount).getText().toString();

                if (!validateReference(codebar_unit, amount_unit)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean validateReference(String codebar_unit, String amount_unit) {

        String isNumber = "[0-9]+";

        if (!codebar_unit.matches(isNumber)) {
            Toast.makeText(this.getActivity(), "Los códigos de barras deben ser numéricos", Toast.LENGTH_LONG).show();
            return false;
        }
        if (codebar_unit.length() < 12) {
            Toast.makeText(this.getActivity(), "El codigo de barras debe tener al menos 12 caracteres", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!amount_unit.matches(isNumber) || amount_unit.length() == 0) {
            Toast.makeText(this.getActivity(), "Cantidad de unidades inválida", Toast.LENGTH_LONG).show();
            return false;
        }

        con = new SQLiteConnectionHelper(this.getContext(), "SPEDB", null, 1);
        SQLiteDatabase db = con.getReadableDatabase();

        String or = "";
        if (!send_id.equals("-1"))
            or = " OR estado_id = 2 AND referencias.codigo_barras = '" + codebar_unit + "' AND envio_id = " + send_id;

        String[] parameters = {codebar_unit};
        String queryAmountReference = "SELECT     count(*) " +
                "FROM       inventario " +
                "INNER JOIN referencias " +
                "ON         referencias.id = inventario.referencia_id " +
                "WHERE      (estado_id = 1   AND" +
                "           referencias.codigo_barras = ?)" + or;

        Cursor cursor = db.rawQuery(queryAmountReference, parameters);

        cursor.moveToFirst();
        if (Integer.parseInt(cursor.getString(0)) < Integer.parseInt(amount_unit)) {
            Toast.makeText(this.getActivity(), "No hay suficientes unidades disponibles: " + codebar_unit, Toast.LENGTH_LONG).show();
            return false;
        }

        db.close();
        return true;
    }

    private void saveSend() {
        con = new SQLiteConnectionHelper(this.getContext(), "SPEDB", null, 1);
        SQLiteDatabase db = con.getWritableDatabase();

        String city_id = city.getSelectedItem().toString().split(" ")[1];

        ContentValues values = new ContentValues();

        values.put(Utilities.ENVIOS_NOMBRE_DESTINATARIO, name.getText().toString());
        values.put(Utilities.ENVIOS_DIRECCION_DESTINATARIO, address.getText().toString());
        values.put(Utilities.ENVIOS_CIUDAD_DESTINATARIO_ID, city_id);
        values.put(Utilities.ENVIOS_TELEFONO_DESTINATARIO, phone.getText().toString());
        values.put(Utilities.ENVIOS_EMAIL_DESTINATARIO, email.getText().toString());
        values.put(Utilities.ENVIOS_FACTURA, invoice.getText().toString());
        values.put(Utilities.ENVIOS_FECHA_RESERVADO, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        values.put(Utilities.ENVIOS_CLIENTE_ID, user_id);
        values.put(Utilities.ENVIOS_ESTADO_ID, "2");


        if (send_id.equals("-1")) {

            Long idResult = db.insert(Utilities.ENVIOS, Utilities.ENVIOS_ID, values);
            reserveInventory(Long.toString(idResult));
            Toast.makeText(this.getActivity(), "Envio con id " + idResult + " creado", Toast.LENGTH_LONG).show();

        } else {

            saveHistory();

            String[] parameters = {send_id};
            db.update(Utilities.ENVIOS, values, Utilities.ENVIOS_ID + " = ?", parameters);
            reserveInventory(send_id);
            Toast.makeText(this.getActivity(), "El envío ha sido actualizado", Toast.LENGTH_LONG).show();
        }


        db.close();
    }

    private void reserveInventory(String id) {

        SQLiteDatabase db = con.getWritableDatabase();

        if (!send_id.equals("-1")) {
            String[] parameters = {send_id};
            ContentValues values = new ContentValues();
            values.put(Utilities.INVENTARIO_ESTADO_ID, "1");
            values.put(Utilities.INVENTARIO_ENVIO_ID, "");
            db.update(Utilities.INVENTARIO, values, Utilities.INVENTARIO_ENVIO_ID + " = ?", parameters);
        }

        LinearLayout layout = root.findViewById(R.id.parent_linear_layout);
        View row = null;
        for (int i = 1; i < layout.getChildCount() - 2; i++) {
            row = layout.getChildAt(i);
            View field_codebar = null;
            View field_amount = null;

            if (row instanceof LinearLayout) {
                field_codebar = ((LinearLayout) row).getChildAt(0);
                field_amount = ((LinearLayout) row).getChildAt(1);

                String codebar_unit = ((EditText) field_codebar).getText().toString();
                String amount_unit = ((EditText) field_amount).getText().toString();

                String[] parametersReference = {codebar_unit};

                Cursor cursor = db.rawQuery("SELECT id " +
                        "FROM   referencias " +
                        "WHERE  codigo_barras = ?", parametersReference);
                cursor.moveToFirst();

                String[] parametersInventory = {"2", id, "1", cursor.getString(0)};
                cursor = db.rawQuery("UPDATE inventario " +
                        "SET    estado_id = ?, envio_id = ? " +
                        "WHERE  id in (SELECT  id" +
                        "              FROM    inventario" +
                        "              WHERE   estado_id = ? AND referencia_id = ? " +
                        "              LIMIT   " + amount_unit + ")", parametersInventory);
                cursor.moveToFirst();
                cursor.close();

            }
        }
    }

    private void saveHistory(){
        SQLiteDatabase db = con.getWritableDatabase();

        String[] parameters = {send_id};
        String querySendData = "SELECT     nombre_destinatario, " +
                "           telefono_destinatario, " +
                "           email_destinatario, " +
                "           direccion_destinatario," +
                "           ciudades.nombre||' '||ciudad_destinatario_id, " +
                "           factura " +
                "FROM       envios " +
                "INNER JOIN ciudades " +
                "ON         ciudades.id = envios.ciudad_destinatario_id " +
                "WHERE      envios.id = ?";
        Cursor cursor = db.rawQuery(querySendData, parameters);
        cursor.moveToFirst();

        String description = "Modificación de envío. ";

        if (!cursor.getString(0).equals(name.getText().toString())) {
            description += "Cambia nombre '" + cursor.getString(0) + "' por '" + name.getText().toString() + "'. ";
        }
        if (!cursor.getString(1).equals(phone.getText().toString())) {
            description += "Cambia telefono '" + cursor.getString(1) + "' por '" + phone.getText().toString() + "'. ";
        }
        if (!cursor.getString(2).equals(email.getText().toString())) {
            description += "Cambia email '" + cursor.getString(2) + "' por '" + email.getText().toString() + "'. ";
        }
        if (!cursor.getString(3).equals(address.getText().toString())) {
            description += "Cambia dirección '" + cursor.getString(3) + "' por '" + address.getText().toString() + "'. ";
        }
        if (!cursor.getString(4).equals(city.getSelectedItem().toString())) {
            description += "Cambia ciudad '" + cursor.getString(4) + "' por '" + city.getSelectedItem().toString() + "'. ";
        }
        if (!cursor.getString(5).equals(invoice.getText().toString())) {
            description += "Cambia factura '" + cursor.getString(5) + "' por '" + invoice.getText().toString() + "'. ";
        }

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        ContentValues values = new ContentValues();

        values.put(Utilities.HISTORIAL_ENVIOS_FECHA, date);
        values.put(Utilities.HISTORIAL_ENVIOS_DESCRIPCION, description);
        values.put(Utilities.HISTORIAL_ENVIOS_CLIENTE_ID,user_id);
        values.put(Utilities.HISTORIAL_ENVIOS_ENVIO_ID, send_id);

        Long idResult = db.insert(Utilities.HISTORIAL_ENVIOS, Utilities.HISTORIAL_ENVIOS_ID, values);

        Log.i("APP", "ID change history send: " + idResult + " " + description);

    }
}
