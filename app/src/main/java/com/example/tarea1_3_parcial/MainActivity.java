package com.example.tarea1_3_parcial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.tarea1_3_parcial.Models.Empleados;
import com.example.tarea1_3_parcial.adaptadores.ListaEmpleadosAdapter;
import com.example.tarea1_3_parcial.db.DbEmpleados;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    SearchView txtBuscar;
    RecyclerView listaEmpleados;
    ArrayList<Empleados> listaArrayEmpleados;
    FloatingActionButton fabNuevo;
    ListaEmpleadosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBuscar = findViewById(R.id.txtBuscar);
        listaEmpleados = findViewById(R.id.listaEmpleados);
        fabNuevo = findViewById(R.id.favNuevo);
        listaEmpleados.setLayoutManager(new LinearLayoutManager(this));

        DbEmpleados dbEmpleados = new DbEmpleados(MainActivity.this);

        listaArrayEmpleados = new ArrayList<>();
        adapter = new ListaEmpleadosAdapter(dbEmpleados.mostrarEmpleados());
        listaEmpleados.setAdapter(adapter);

        fabNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoRegistro();
            }
        });
        txtBuscar.setOnQueryTextListener(this);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }
   @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

   @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}