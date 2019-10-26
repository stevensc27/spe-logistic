package com.example.spe_logistic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.spe_logistic.utilities.Utilities;

public class SQLiteConnectionHelper extends SQLiteOpenHelper {


    public SQLiteConnectionHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilities.CREATE_CATEGORIAS_PQRS);
        db.execSQL(Utilities.CREATE_ESTADOS_PQRS);
        db.execSQL(Utilities.CREATE_PQRS);
        db.execSQL(Utilities.CREATE_TIPOS_VIAS);
        db.execSQL(Utilities.CREATE_CIUDADES);
        db.execSQL(Utilities.CREATE_DIRECCIONES);
        db.execSQL(Utilities.CREATE_CLIENTES);
        db.execSQL(Utilities.CREATE_DESPACHOS);
        db.execSQL(Utilities.CREATE_ESTADOS_ENVIOS);
        db.execSQL(Utilities.CREATE_ENVIOS);
        db.execSQL(Utilities.CREATE_HISTORIAL_ENVIOS);
        db.execSQL(Utilities.CREATE_DETALLE_HISTORIAL_ENVIOS);
        db.execSQL(Utilities.CREATE_REFERENCIAS);
        db.execSQL(Utilities.CREATE_HISTORIAL_REFERENCIAS);
        db.execSQL(Utilities.CREATE_ESTADOS_INVENTARIO);
        db.execSQL(Utilities.CREATE_INVENTARIO);
        db.execSQL(Utilities.CREATE_HISTORIAL_INVENTARIO);
        db.execSQL(Utilities.CREATE_ESTADOS_RECOGIDAS);
        db.execSQL(Utilities.CREATE_RECOGIDAS);
        db.execSQL(Utilities.CREATE_HISTORIAL_RECOGIDAS);

        Utilities.insertInventory(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(Utilities.DROP_CATEGORIAS_PQRS);
        db.execSQL(Utilities.DROP_ESTADOS_PQRS);
        db.execSQL(Utilities.DROP_PQRS);
        db.execSQL(Utilities.DROP_TIPOS_VIAS);
        db.execSQL(Utilities.DROP_CIUDADES);
        db.execSQL(Utilities.DROP_DIRECCIONES);
        db.execSQL(Utilities.DROP_CLIENTES);
        db.execSQL(Utilities.DROP_DESPACHOS);
        db.execSQL(Utilities.DROP_ESTADOS_ENVIOS);
        db.execSQL(Utilities.DROP_ENVIOS);
        db.execSQL(Utilities.DROP_HISTORIAL_ENVIOS);
        db.execSQL(Utilities.DROP_DETALLE_HISTORIAL_ENVIOS);
        db.execSQL(Utilities.DROP_REFERENCIAS);
        db.execSQL(Utilities.DROP_HISTORIAL_REFERENCIAS);
        db.execSQL(Utilities.DROP_ESTADOS_INVENTARIO);
        db.execSQL(Utilities.DROP_INVENTARIO);
        db.execSQL(Utilities.DROP_HISTORIAL_INVENTARIO);
        db.execSQL(Utilities.DROP_ESTADOS_RECOGIDAS);
        db.execSQL(Utilities.DROP_RECOGIDAS);
        db.execSQL(Utilities.DROP_HISTORIAL_REFERENCIAS);
        onCreate(db);
    }
}
