package com.example.cuentasclaras.model.database.dao;

import com.example.cuentasclaras.model.Movimiento;
import com.example.cuentasclaras.model.database.BD;

import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DAOMovimiento implements DAO<Movimiento>{

    private Context contexto;
    private BD conexion;
    private SQLiteDatabase db;
    private Cursor cursor;
    private final String RUTA_BD =
            Environment.getExternalStorageDirectory().
                    getPath()+"/cuentasClaras/cuentasClarasDB.sqlite";

    public DAOMovimiento(Context contexto) {
        this.contexto = contexto;
    }

    @Override
    public void create(Movimiento movimiento) {
        conexion = new BD(contexto, RUTA_BD, null, 1);
        db = conexion.getWritableDatabase();

        String insert = "INSERT INTO movimiento VALUES(" +
                "NULL, " +
                "'"+movimiento.getMonto()+"'," +
                "'"+movimiento.getCuentaOrigen()+"'," +
                "'"+movimiento.getCuentaDestino()+"'," +
                "'"+movimiento.getDetalle()+"'," +
                "date('now'));";

        Log.v("INSERT movimiento", insert);

        db.execSQL(insert);
        db.close();
    }

    @Override
    public List<Movimiento> read() {
        List<Movimiento> movimientos = new ArrayList<>();

        conexion = new BD(contexto, RUTA_BD, null, 1);
        db = conexion.getWritableDatabase();

        String select = "SELECT * FROM movimiento";

        cursor = db.rawQuery(select, null);

        Movimiento movimiento;
        if(cursor.moveToFirst()){
            do{
                movimiento = new Movimiento();

                movimiento.setId(cursor.getInt(0));
                movimiento.setMonto(cursor.getInt(1));
                movimiento.setCuentaOrigen(cursor.getString(2));
                movimiento.setCuentaDestino(cursor.getString(3));
                movimiento.setDetalle(cursor.getString(4));
                movimiento.setFecha(cursor.getString(5));

                movimientos.add(movimiento);
            }while(cursor.moveToNext());
        }

        db.close();

        return movimientos;
    }

    @Override
    public void update(Movimiento object) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Movimiento readByID(int id) {
        return null;
    }
}
