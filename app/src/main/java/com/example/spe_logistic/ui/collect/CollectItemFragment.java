package com.example.spe_logistic.ui.collect;


import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.spe_logistic.R;
import com.example.spe_logistic.SQLiteConnectionHelper;
import com.example.spe_logistic.utilities.Utilities;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.OnLocationClickListener;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectItemFragment extends Fragment implements View.OnClickListener, OnMapReadyCallback, OnLocationClickListener, PermissionListener, MapboxMap.OnMapClickListener {

    private String collect_id;

    private EditText date;
    private EditText time;
    private EditText address;
    private EditText amount;
    private EditText height;
    private EditText width;
    private EditText large;
    private EditText weight;
    private EditText statement;
    private EditText value;

    private String textDate;
    private String textTime;
    private String textAddress;
    private int textAmount;
    private int textHeight;
    private int textWidth;
    private int textLarge;
    private int textWeight;
    private String textStatement;
    private int textValue;

    private Button save;
    private NavController navController;
    private MapView mapView;
    private MapboxMap mapboxMap;
    private LocationComponent locationComponent;
    private FragmentActivity fragmentActivity;

    private SQLiteConnectionHelper con;

    View root;

    public CollectItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(fragmentActivity, getString(R.string.key_map));//inicializar el elemento del mapa con la llave
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_collect_item, container, false);

        collect_id = (getArguments().getString("collectId", "-1"));

        date = root.findViewById(R.id.collect_item_date);
        date.setOnClickListener(this);

        time = root.findViewById(R.id.collect_item_time);
        time.setOnClickListener(this);

        address = root.findViewById(R.id.collect_item_address);
        amount = root.findViewById(R.id.collect_item_amount);
        height = root.findViewById(R.id.collect_item_height);
        width = root.findViewById(R.id.collect_item_width);
        large = root.findViewById(R.id.collect_item_large);
        weight = root.findViewById(R.id.collect_item_weight);
        statement = root.findViewById(R.id.collect_item_statement);
        value = root.findViewById(R.id.collect_item_value);

        save = root.findViewById(R.id.collect_item_save_button);
        save.setOnClickListener(this);

        mapView = root.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        if (!collect_id.equals("-1")) {
            getCollectData();
        }

        return root;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.collect_item_save_button:
                validateData();
                break;

            case R.id.collect_item_date:
                selectDate();
                break;

            case R.id.collect_item_time:
                selectTime();
                break;
        }
    }

    private void getCollectData() {
        con = new SQLiteConnectionHelper(this.getContext(), "SPEDB", null, 1);
        SQLiteDatabase db = con.getReadableDatabase();

        String[] parameters = {collect_id};
        String queryCollectData = "SELECT fecha," +
                "       direccion," +
                "       cantidad_cajas," +
                "       alto_caja," +
                "       ancho_caja," +
                "       largo_caja," +
                "       peso," +
                "       descripcion_contenido," +
                "       valor_declarado " +
                "FROM   recogidas " +
                "WHERE  id = ?";

        Cursor cursor = db.rawQuery(queryCollectData, parameters);

        cursor.moveToFirst();

        date.setText(cursor.getString(0).split(" ")[0]);
        time.setText(cursor.getString(0).split(" ")[1]);
        address.setText(cursor.getString(1));
        amount.setText(cursor.getString(2));
        height.setText(cursor.getString(3));
        width.setText(cursor.getString(4));
        large.setText(cursor.getString(5));
        weight.setText(cursor.getString(6));
        statement.setText(cursor.getString(7));
        value.setText(cursor.getString(8));

        db.close();
    }

    private void validateData() {

        String isNumber = "[0-9]+";

        String sAmount = amount.getText().toString();
        String sHeight = height.getText().toString();
        String sWidth = width.getText().toString();
        String sLarge = large.getText().toString();
        String sWeight = weight.getText().toString();
        String sValue = value.getText().toString();

        if (!sAmount.matches(isNumber) || !sHeight.matches(isNumber) || !sWidth.matches(isNumber) || !sLarge.matches(isNumber) || !sWeight.matches(isNumber) ||
                sAmount.length() == 0 || sHeight.length() == 0 || sWidth.length() == 0 || sLarge.length() == 0 || sWeight.length() == 0) {
            Toast.makeText(this.getActivity(), "Las medidas deben ser numéricas y en CM", Toast.LENGTH_LONG).show();
            return;
        }

        if (!sValue.matches(isNumber) || sValue.length() == 0) {
            Toast.makeText(this.getActivity(), "El valor es incorrecto", Toast.LENGTH_LONG).show();
            return;
        }

        textAddress = address.getText().toString();
        textAmount = Integer.parseInt(sAmount);
        textHeight = Integer.parseInt(sHeight);
        textWidth = Integer.parseInt(sWidth);
        textLarge = Integer.parseInt(sLarge);
        textWeight = Integer.parseInt(sWeight);
        textStatement = statement.getText().toString();
        textValue = Integer.parseInt(sValue);

        if (textAddress.length() < 5) {
            Toast.makeText(this.getActivity(), "Ingrese una dirección válida (min 5 caracteres)", Toast.LENGTH_LONG).show();
            return;
        }

        if (textStatement.length() < 15) {
            Toast.makeText(this.getActivity(), "Ingrese una descripción válida (min 15 caracteres)", Toast.LENGTH_LONG).show();
            return;
        }

        saveCollect();
        navController.navigate(R.id.navigation_collect);

    }

    private void saveCollect() {
        con = new SQLiteConnectionHelper(this.getContext(), "SPEDB", null, 1);
        SQLiteDatabase db = con.getWritableDatabase();

        SharedPreferences preferences = this.getActivity().getSharedPreferences("credentials", this.getActivity().MODE_PRIVATE);
        Integer user_id = preferences.getInt("user_id", 0);

        ContentValues values = new ContentValues();

        values.put(Utilities.RECOGIDAS_FECHA, date.getText().toString() + " " + time.getText().toString());
        values.put(Utilities.RECOGIDAS_CLIENTE_ID, user_id);
        values.put(Utilities.RECOGIDAS_DIRECCION, textAddress);
        values.put(Utilities.RECOGIDAS_CANTIDAD_CAJAS, textAmount);
        values.put(Utilities.RECOGIDAS_ALTO_CAJA, textHeight);
        values.put(Utilities.RECOGIDAS_ANCHO_CAJA, textWidth);
        values.put(Utilities.RECOGIDAS_LARGO_CAJA, textLarge);
        values.put(Utilities.RECOGIDAS_PESO, textWeight);
        values.put(Utilities.RECOGIDAS_DESCRIPCION_CONTENIDO, textStatement);
        values.put(Utilities.RECOGIDAS_VALOR_DECLARADO, textValue);
        values.put(Utilities.RECOGIDAS_ESTADO_ID, "1");


        if (collect_id.equals("-1")) {
            Long idResult = db.insert(Utilities.RECOGIDAS, Utilities.RECOGIDAS_ID, values);
            Toast.makeText(this.getActivity(), "Recogida con id " + idResult + " creada", Toast.LENGTH_LONG).show();
        } else {

            saveHistory();

            String[] parameters = {collect_id};
            db.update(Utilities.RECOGIDAS, values, Utilities.RECOGIDAS_ID + " = ?", parameters);
            Toast.makeText(this.getActivity(), "La recogida ha sido actualizada", Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    private void saveHistory() {

        SQLiteDatabase db = con.getWritableDatabase();

        String[] parameters = {collect_id};
        String queryCollectData = "SELECT fecha," +
                "       direccion," +
                "       cantidad_cajas," +
                "       alto_caja," +
                "       ancho_caja," +
                "       largo_caja," +
                "       peso," +
                "       descripcion_contenido," +
                "       valor_declarado " +
                "FROM   recogidas " +
                "WHERE  id = ?";
        Cursor cursor = db.rawQuery(queryCollectData, parameters);
        cursor.moveToFirst();

        String description = "Modificación de recogida. ";

        if (!cursor.getString(0).equals(date.getText().toString() + " " + time.getText().toString())) {
            description += "Cambia fecha '" + cursor.getString(0) + "' por '" + textDate + " " + textTime + "'. ";
        }
        if (!cursor.getString(1).equals(textAddress)) {
            description += "Cambia direccion '" + cursor.getString(1) + "' por '" + textAddress + "'. ";
        }
        if (!cursor.getString(2).equals(String.valueOf(textAmount))) {
            description += "Cambia cantidad de cajas '" + cursor.getString(2) + "' por '" + textAmount + "'. ";
        }
        if (!cursor.getString(3).equals(String.valueOf(textHeight))) {
            description += "Cambia alto de cajas '" + cursor.getString(3) + "' por '" + textHeight + "'. ";
        }
        if (!cursor.getString(4).equals(String.valueOf(textWidth))) {
            description += "Cambia ancho de cajas '" + cursor.getString(4) + "' por '" + textWidth + "'. ";
        }
        if (!cursor.getString(5).equals(String.valueOf(textLarge))) {
            description += "Cambia largo de cajas '" + cursor.getString(5) + "' por '" + textLarge + "'. ";
        }
        if (!cursor.getString(6).equals(String.valueOf(textWeight))) {
            description += "Cambia peso de cajas '" + cursor.getString(6) + "' por '" + textWeight + "'. ";
        }
        if (!cursor.getString(7).equals(textStatement)) {
            description += "Cambia declaracion '" + cursor.getString(7) + "' por '" + textStatement + "'. ";
        }
        if (!cursor.getString(8).equals(String.valueOf(textValue))) {
            description += "Cambia valor declarado '" + cursor.getString(8) + "' por '" + textValue + "'. ";
        }

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        ContentValues values = new ContentValues();

        values.put(Utilities.HISTORIAL_RECOGIDAS_FECHA, date);
        values.put(Utilities.HISTORIAL_RECOGIDAS_DESCRIPCION, description);
        values.put(Utilities.HISTORIAL_RECOGIDAS_RECOGIDA_ID, collect_id);

        Long idResult = db.insert(Utilities.HISTORIAL_RECOGIDAS, Utilities.HISTORIAL_RECOGIDAS_ID, values);

        Log.i("APP", "ID change history collect: " + idResult + " " + description);

    }

    private void selectDate() {

        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        calendar2.add(Calendar.MONTH, +1);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this.getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                textDate = year + "-" + String.format("%02d", month + 1) + "-" + String.format("%02d", dayOfMonth);
                date.setText(textDate);

            }
        }, year, month, dayOfMonth);

        datePickerDialog.getDatePicker().setMinDate(calendar2.getTimeInMillis());
        datePickerDialog.show();
    }

    private void selectTime() {

        Calendar calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this.getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                textTime = String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute) + ":00";
                time.setText(textTime);

            }
        }, hour, minute, true);

        timePickerDialog.show();

    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
        mapboxMap.setStyle(Style.LIGHT, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {

                mapboxMap.setMaxZoomPreference(15);
                mapboxMap.setMinZoomPreference(10);
                mapboxMap.addOnMapClickListener(CollectItemFragment.this);

                enableLocation(style);
            }
        });
    }

    @SuppressWarnings({"MissingPermission"})
    private void enableLocation(@NonNull Style loadedMapStyle) {
        if (PermissionsManager.areLocationPermissionsGranted(fragmentActivity)) {

            locationComponent = mapboxMap.getLocationComponent();
            LocationComponentActivationOptions locationComponentActivationOptions =
                    LocationComponentActivationOptions.builder(fragmentActivity, loadedMapStyle)
                            //.locationComponentOptions(customLocationComponentOptions)
                            .build();
            // Activate with options
            locationComponent.activateLocationComponent(locationComponentActivationOptions);
            // Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);
            // Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);
            // Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);
            // Add the location icon click listener
            locationComponent.addOnLocationClickListener(this);

        } else {
            requestPermission();
        }
    }

    @Override
    public void onLocationComponentClick() {

    }

    private void requestPermission() {
        Dexter.withActivity(fragmentActivity)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(this)
                .check();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        fragmentActivity = getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
    }

    @Override
    public void onPermissionGranted(PermissionGrantedResponse response) {
        mapboxMap.getStyle(this::enableLocation);
    }

    @Override
    public void onPermissionDenied(PermissionDeniedResponse response) {
        if (response.isPermanentlyDenied()) {

            Navigation.findNavController(fragmentActivity, R.id.nav_host_fragment)
                    .navigate(R.id.navigation_collect);
        }
    }

    @Override
    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
        token.continuePermissionRequest();
    }

    @Override
    public boolean onMapClick(@NonNull LatLng point) {
        mapboxMap.clear();
        //aqui se obtiene la ubicacion
        Log.d(CollectItemFragment.class.getSimpleName(), "latitud: " + point.getLatitude() + " longitud: " + point.getLongitude());

        mapboxMap.addMarker(new MarkerOptions()
                .position(point)
                .title("Recogida"));

        return true;
    }

}
