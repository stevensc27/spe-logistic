package com.example.spe_logistic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

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

        db.execSQL(Utilities.REFERENCIAS_REGS);
        db.execSQL(Utilities.REFERENCIAS_REGS2);
        db.execSQL(Utilities.REFERENCIAS_REGS3);
        db.execSQL(Utilities.REFERENCIAS_REGS4);

        db.execSQL(Utilities.RECOGIDAS_REGS0);
        db.execSQL(Utilities.RECOGIDAS_REGS1);
        db.execSQL(Utilities.RECOGIDAS_REGS2);
        db.execSQL(Utilities.RECOGIDAS_REGS3);
        db.execSQL(Utilities.RECOGIDAS_REGS4);
        db.execSQL(Utilities.RECOGIDAS_REGS5);
        db.execSQL(Utilities.RECOGIDAS_REGS6);
        db.execSQL(Utilities.RECOGIDAS_REGS7);
        db.execSQL(Utilities.RECOGIDAS_REGS8);
        db.execSQL(Utilities.RECOGIDAS_REGS9);
        db.execSQL(Utilities.RECOGIDAS_REGS10);
        db.execSQL(Utilities.RECOGIDAS_REGS11);
        db.execSQL(Utilities.RECOGIDAS_REGS12);
        db.execSQL(Utilities.RECOGIDAS_REGS13);
        db.execSQL(Utilities.RECOGIDAS_REGS14);
        db.execSQL(Utilities.RECOGIDAS_REGS15);
        db.execSQL(Utilities.RECOGIDAS_REGS0);
        db.execSQL(Utilities.RECOGIDAS_REGS1);
        db.execSQL(Utilities.RECOGIDAS_REGS2);
        db.execSQL(Utilities.RECOGIDAS_REGS3);
        db.execSQL(Utilities.RECOGIDAS_REGS4);
        db.execSQL(Utilities.RECOGIDAS_REGS5);
        db.execSQL(Utilities.RECOGIDAS_REGS6);
        db.execSQL(Utilities.RECOGIDAS_REGS7);
        db.execSQL(Utilities.RECOGIDAS_REGS8);
        db.execSQL(Utilities.RECOGIDAS_REGS9);
        db.execSQL(Utilities.RECOGIDAS_REGS10);
        db.execSQL(Utilities.RECOGIDAS_REGS11);
        db.execSQL(Utilities.RECOGIDAS_REGS12);
        db.execSQL(Utilities.RECOGIDAS_REGS13);
        db.execSQL(Utilities.RECOGIDAS_REGS14);
        db.execSQL(Utilities.RECOGIDAS_REGS15);

        /*
        db.execSQL(Utilities.REFERENCIAS_REGS);
        db.execSQL(Utilities.CATEGORIAS_PQRS_REGS);
        db.execSQL(Utilities.CIUDADES_REGS);
        db.execSQL(Utilities.ESTADOS_ENVIOS_REGS);
        db.execSQL(Utilities.ESTADOS_INVENTARIO_REGS);

        db.execSQL(Utilities.ESTADOS_RECOGIDAS_REGS);
        db.execSQL(Utilities.ESTADOS_PQRS_REGS);
        */

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
        onCreate(db);
    }
}
