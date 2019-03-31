package com.example.cuentasclaras.model.reciclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuentasclaras.R;
import com.example.cuentasclaras.model.Movimiento;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecyclerViewAdapter extends RecyclerView.Adapter<MovimientoViewHolder>{

    private List<Movimiento> movimientos;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat dbDateFormat;
    private Locale locale;
    private NumberFormat currencyFormatter;

    public RecyclerViewAdapter(List<Movimiento> movimientos){
        this.movimientos = movimientos;

        dateFormat = new SimpleDateFormat("d 'de' MMMM 'de' y [HH:mm:ss]");
        dbDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        locale = new Locale("es", "CL");
        currencyFormatter = NumberFormat.getCurrencyInstance(locale);
    }

    @NonNull
    @Override
    public MovimientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.item_movimiento,
            parent,
            false
        );

        /*
        view.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                ViewHolder viewHolder = new ViewHolder(view);

                Toast.makeText(view.getContext(), viewHolder.lblMonto.getText(), Toast.LENGTH_SHORT).show();

                return true;
            }
        });
        */

        MovimientoViewHolder viewHolder = new MovimientoViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovimientoViewHolder holder, int position) {
        Movimiento movimiento = movimientos.get(position);

        String fecha = null;

        try {
            Date fechaDB = dbDateFormat.parse(movimiento.getFecha());
            fecha = dateFormat.format(fechaDB);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String monto = currencyFormatter.format(movimiento.getMonto());

        holder.getLblFecha().setText(fecha);
        holder.getLblOrigen().setText(movimiento.getCuentaOrigen());
        holder.getLblDestino().setText(movimiento.getCuentaDestino());
        holder.getLblDetalle().setText(movimiento.getDetalle());
        holder.getLblMonto().setText(monto);
    }

    @Override
    public int getItemCount() {
        return movimientos.size();
    }

}

