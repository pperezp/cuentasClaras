package com.example.cuentasclaras.model.reciclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cuentasclaras.R;
import com.example.cuentasclaras.model.Movimiento;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<Movimiento> movimientos;

    public RecyclerViewAdapter(List<Movimiento> movimientos){
        this.movimientos = movimientos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.item_movimiento,
            parent,
            false
        );

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.lblFecha.setText(movimientos.get(position).getFecha());
        holder.lblOrigen.setText(movimientos.get(position).getCuentaOrigen());
        holder.lblDestino.setText(movimientos.get(position).getCuentaDestino());
        holder.lblDetalle.setText(movimientos.get(position).getDetalle());
        holder.lblMonto.setText("$ "+movimientos.get(position).getMonto());
    }

    @Override
    public int getItemCount() {
        return movimientos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView lblFecha;
        private TextView lblOrigen;
        private TextView lblDestino;
        private TextView lblDetalle;
        private TextView lblMonto;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lblFecha = itemView.findViewById(R.id.lblFecha);
            lblOrigen = itemView.findViewById(R.id.lblOrigen);
            lblDestino = itemView.findViewById(R.id.lblDestino);
            lblDetalle = itemView.findViewById(R.id.lblDetalle);
            lblMonto = itemView.findViewById(R.id.lblMonto);
        }


    }
}

