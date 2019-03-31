package com.example.cuentasclaras;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.cuentasclaras.model.Cuenta;
import com.example.cuentasclaras.model.Movimiento;
import com.example.cuentasclaras.model.database.dao.DAOMovimiento;

import java.util.ArrayList;
import java.util.List;


public class CrearMovimientoFragment extends Fragment {

    private Spinner cboCuentaOrigen;
    private Spinner cboCuentaDestino;
    private Switch switchAutoSelect;
    private Button btnRegistrarMovimiento;
    private EditText txtMonto;
    private EditText txtDetalle;
    private View view;
    private Context context;

    private List<Cuenta> cuentas;

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {

        view =  inflater.inflate(R.layout.fragment_crear_movimiento, container, false);
        context = view.getContext();

        init();

        return view;
    }

    private void init() {
        cargarComponentes();
        loadListeners();
        crearCuentas();
        cargarSpinnerCuentas();
    }

    private void loadListeners() {
        cboCuentaOrigen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(switchAutoSelect.isChecked()){
                    cboCuentaDestino.setSelection(i == 0 ? 1 : 0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        switchAutoSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCuentaAutoSelectSelected) {
                cboCuentaDestino.setEnabled(!isCuentaAutoSelectSelected);

                if(isCuentaAutoSelectSelected){
                    int cuentaOrigenIndex = cboCuentaOrigen.getSelectedItemPosition();
                    cboCuentaDestino.setSelection(cuentaOrigenIndex == 0 ? 1 : 0);
                }
            }
        });

        btnRegistrarMovimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAOMovimiento daoMovimiento = new DAOMovimiento(context);

                Movimiento movimiento = new Movimiento();

                movimiento.setMonto(Integer.parseInt(txtMonto.getText().toString()));
                movimiento.setCuentaOrigen(cboCuentaOrigen.getSelectedItem().toString());
                movimiento.setCuentaDestino(cboCuentaDestino.getSelectedItem().toString());
                movimiento.setDetalle(txtDetalle.getText().toString());

                daoMovimiento.create(movimiento);

                for (Movimiento m : daoMovimiento.read()){
                    Log.i("movimiento", m.toString());
                }
            }
        });
    }

    private void cargarComponentes() {
        cboCuentaOrigen = view.findViewById(R.id.cboCuentaOrigen);
        cboCuentaDestino =  view.findViewById(R.id.cboCuentaDestino);
        switchAutoSelect = view.findViewById(R.id.switchAutoSelect);
        btnRegistrarMovimiento = view.findViewById(R.id.btnRegistrar);
        txtMonto = view.findViewById(R.id.txtMonto);
        txtDetalle = view.findViewById(R.id.txtDetalle);
    }

    private void cargarSpinnerCuentas() {
        ArrayAdapter<Cuenta> cuentasAdapter = new ArrayAdapter<>(
            context,
            android.R.layout.simple_spinner_dropdown_item   ,
            cuentas
        );

        cboCuentaOrigen.setAdapter(cuentasAdapter);
        cboCuentaDestino.setAdapter(cuentasAdapter);

        cboCuentaDestino.setEnabled(!switchAutoSelect.isChecked());

        cboCuentaDestino.setSelection(1);
    }

    private void crearCuentas() {
        cuentas = new ArrayList<>();

        Cuenta rut = new Cuenta();
        Cuenta corriente = new Cuenta();

        rut.setId(1);
        rut.setNombre("RUT");

        corriente.setId(2);
        corriente.setNombre("Corriente");

        cuentas.add(rut);
        cuentas.add(corriente);
    }
}
