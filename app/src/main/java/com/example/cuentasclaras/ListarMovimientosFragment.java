package com.example.cuentasclaras;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuentasclaras.model.database.dao.DAOMovimiento;
import com.example.cuentasclaras.model.reciclerView.RecyclerViewAdapter;


public class ListarMovimientosFragment extends Fragment {
    private RecyclerView reciclerMovimientos;
    private RecyclerViewAdapter recyclerViewAdapter;
    private View view;
    private Context context;
    private DAOMovimiento daoMovimiento;

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {
        view = inflater.inflate(R.layout.fragment_listar_movimientos, container, false);
        context = view.getContext();

        init();

        return view;
    }

    private void init() {
        daoMovimiento = new DAOMovimiento(context);

        loadComponents();
    }

    private void loadComponents() {
        reciclerMovimientos = view.findViewById(R.id.reciclerMovimientos);
        reciclerMovimientos.setLayoutManager(new LinearLayoutManager(context));

        recyclerViewAdapter = new RecyclerViewAdapter(daoMovimiento.read());
        reciclerMovimientos.setAdapter(recyclerViewAdapter);
    }

}
