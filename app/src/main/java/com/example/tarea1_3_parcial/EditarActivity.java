package com.example.tarea1_3_parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tarea1_3_parcial.Models.Empleados;
import com.example.tarea1_3_parcial.adaptadores.ListaEmpleadosAdapter;
import com.example.tarea1_3_parcial.db.DbEmpleados;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EditarActivity extends AppCompatActivity {
    EditText txtNombre, txtApellidos, txtEdad, txtDireccion, txtPuesto;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    Empleados empleado;
    int id = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtEdad = findViewById(R.id.txtEdad);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtPuesto = findViewById(R.id.txtPuesto);
        btnGuarda = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);
       btnGuarda.setText("Actualizar Datos");

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbEmpleados dbEmpleados = new DbEmpleados(EditarActivity.this);
        empleado = dbEmpleados.verEmpleado(id);

        if (empleado != null) {
            txtNombre.setText(empleado.getNombre());
            txtApellidos.setText(empleado.getApellidos());
            txtEdad.setText(empleado.getEdad());
            txtDireccion.setText(empleado.getDireccion());
            txtPuesto.setText(empleado.getPuesto());
        }
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().equals("") && !txtApellidos.getText().toString().equals("") && !txtEdad.getText().toString().equals("")
                        && !txtDireccion.getText().toString().equals("")&& !txtPuesto.getText().toString().equals("")) {
                    correcto = dbEmpleados.editarEmpleado(id, txtNombre.getText().toString(), txtApellidos.getText().toString(), txtEdad.getText().toString()
                            ,txtDireccion.getText().toString(), txtPuesto.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarActivity.this, "Registro Actualizado Exitosamente", Toast.LENGTH_LONG).show();
                        verRegistro();lista();
                    } else {
                        Toast.makeText(EditarActivity.this, "Error al Actualizar Registro", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "Hay Campos Vacios! Favor llenarlos todos!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
    private void limpiar() {
        txtNombre.setText("");
        txtApellidos.setText("");
        txtEdad.setText("");
        txtDireccion.setText("");
        txtPuesto.setText("");
    }
    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}