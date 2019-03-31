package com.example.cuentasclaras;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Spinner cboCuentaOrigen;
    private Spinner cboCuentaDestino;
    private Switch switchAutoSelect;
    private Button btnRegistrarMovimiento;
    private EditText txtMonto;
    private EditText txtDetalle;


    private List<Cuenta> cuentas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        init();
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
                DAOMovimiento daoMovimiento = new DAOMovimiento(getApplicationContext());

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
        cboCuentaOrigen = findViewById(R.id.cboCuentaOrigen);
        cboCuentaDestino =  findViewById(R.id.cboCuentaDestino);
        switchAutoSelect = findViewById(R.id.switchAutoSelect);
        btnRegistrarMovimiento = findViewById(R.id.btnRegistrar);
        txtMonto = findViewById(R.id.txtMonto);
        txtDetalle = findViewById(R.id.txtDetalle);
    }

    private void cargarSpinnerCuentas() {
        ArrayAdapter<Cuenta> cuentasAdapter = new ArrayAdapter<>(
            getApplicationContext(),
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
