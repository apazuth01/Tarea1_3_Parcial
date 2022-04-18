package com.example.tarea1_3_parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tarea1_3_parcial.Models.Empleados;
import com.example.tarea1_3_parcial.db.DbEmpleados;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NuevoActivity extends AppCompatActivity {
    EditText txtNombre, txtApellidos, txtEdad, txtDireccion, txtPuesto;
    Button btnGuarda;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

         txtNombre = findViewById(R.id.txtNombre);
         txtApellidos = findViewById(R.id.txtApellidos);
         txtEdad = findViewById(R.id.txtEdad);
         txtDireccion = findViewById(R.id.txtDireccion);
         txtPuesto = findViewById(R.id.txtPuesto);
         btnGuarda = findViewById(R.id.btnGuarda);

         btnGuarda.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 if (!txtNombre.getText().toString().equals("") && !txtApellidos.getText().toString().equals("") && !txtEdad.getText().toString().equals("")
                         && !txtDireccion.getText().toString().equals("")&& !txtPuesto.getText().toString().equals("")) {

                     DbEmpleados dbEmpleados = new DbEmpleados(NuevoActivity.this);
                     long id = dbEmpleados.insertarEmpleado(txtNombre.getText().toString(), txtApellidos.getText().toString(), txtEdad.getText().toString()
                             ,txtDireccion.getText().toString(), txtPuesto.getText().toString());

                     if (id > 0) {
                         Toast.makeText(NuevoActivity.this, "Registro Agreado Exitosamente!", Toast.LENGTH_LONG).show();
                         limpiar();lista();
                         //dbEmpleados.mostrarEmpleados();
                     } else {
                         Toast.makeText(NuevoActivity.this, "Error al Guardar Registro", Toast.LENGTH_LONG).show();
                     }
                 } else {
                     Toast.makeText(NuevoActivity.this, "Hay Campos Vacios! Favor llenarlos todos!", Toast.LENGTH_LONG).show();
                 }
             }
         });

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