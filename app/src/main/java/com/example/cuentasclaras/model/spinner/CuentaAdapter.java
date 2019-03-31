package com.example.cuentasclaras.model.spinner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuentasclaras.R;
import com.example.cuentasclaras.model.Cuenta;

import java.util.List;

public class CuentaAdapter extends ArrayAdapter<Cuenta> {
    private List<Cuenta> cuentas;

    public CuentaAdapter(@NonNull Context context, @NonNull List<Cuenta> objects) {
        super(context, 0, objects);

        cuentas = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View coverView, ViewGroup parent){
        if(coverView == null){
            coverView = LayoutInflater.from(getContext()).inflate(R.layout.cuenta_item, parent, false);
        }

        ImageView cuentaImagen = coverView.findViewById(R.id.imgCuenta);
        TextView nombreCuenta = coverView.findViewById(R.id.lblNombreCuenta);

        Cuenta cuenta = cuentas.get(position);

        cuentaImagen.setImageResource(cuenta.getImageId());
        nombreCuenta.setText(cuenta.getNombre());

        return coverView;
    }
}
