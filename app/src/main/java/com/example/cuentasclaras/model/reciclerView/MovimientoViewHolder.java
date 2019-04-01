package com.example.cuentasclaras.model.reciclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuentasclaras.R;

public class MovimientoViewHolder extends RecyclerView.ViewHolder {
    private TextView lblFecha;
    private TextView lblOrigen;
    private TextView lblDestino;
    private TextView lblDetalle;
    private TextView lblMonto;
    private ImageView imgOrigen;
    private ImageView imgDestino;

    public MovimientoViewHolder(@NonNull View itemView) {
        super(itemView);

        lblFecha = itemView.findViewById(R.id.lblFecha);
        lblOrigen = itemView.findViewById(R.id.lblOrigen);
        lblDestino = itemView.findViewById(R.id.lblDestino);
        lblDetalle = itemView.findViewById(R.id.lblDetalle);
        lblMonto = itemView.findViewById(R.id.lblMonto);
        imgOrigen = itemView.findViewById(R.id.imgOrigen);
        imgDestino = itemView.findViewById(R.id.imgDestino);
    }

    public TextView getLblFecha() {
        return lblFecha;
    }

    public TextView getLblOrigen() {
        return lblOrigen;
    }

    public TextView getLblDestino() {
        return lblDestino;
    }

    public TextView getLblDetalle() {
        return lblDetalle;
    }

    public TextView getLblMonto() {
        return lblMonto;
    }

    public ImageView getImgOrigen() {
        return imgOrigen;
    }

    public ImageView getImgDestino() {
        return imgDestino;
    }
}
