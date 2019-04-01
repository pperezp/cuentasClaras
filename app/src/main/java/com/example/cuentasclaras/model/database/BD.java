package com.example.cuentasclaras.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BD extends SQLiteOpenHelper {

    private String tablaMovimiento = "CREATE TABLE movimiento("+
        "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
        "monto INTEGER,"+ // TEXT as ISO8601 strings ("YYYY-MM-DD HH:MM:SS.SSS").
        "cuentaOrigen TEXT,"+
        "cuentaDestino TEXT,"+
        "detalle TEXT,"+
        "fecha TEXT," +
        "cuentaOrigenResourceId INTEGER," +
        "cuentaDestinoResourceId INTEGER" +
    ")";

	/*
	https://stackoverflow.com/questions/11643294/
	what-is-the-use-of-sqlitedatabase-cursorfactory-in-android?utm_medium=organic&
	utm_source=google_rich_qa&
	utm_campaign=google_rich_qa
	*/

    public BD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tablaMovimiento);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
