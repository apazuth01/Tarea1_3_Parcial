package com.example.tarea1_3_parcial.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.tarea1_3_parcial.Models.Empleados;

import java.util.ArrayList;

public class DbEmpleados extends DbHelper {
    Context context;

    public DbEmpleados(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarEmpleado(String nombre, String apellidos, String edad, String direccion, String puesto) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("apellidos", apellidos);
            values.put("edad", edad);
            values.put("direccion", direccion);
            values.put("puesto", puesto);

            id = db.insert(TABLE_EMPLEADOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Empleados> mostrarEmpleados() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Empleados> listaEmpleados = new ArrayList<>();
        Empleados empleado;
        Cursor cursorEmpleados;

        cursorEmpleados = db.rawQuery("SELECT * FROM " + TABLE_EMPLEADOS + " ORDER BY nombre ASC", null);

        if (cursorEmpleados.moveToFirst()) {
            do {
                empleado = new Empleados();
                empleado.setId(cursorEmpleados.getInt(0));
                empleado.setNombre(cursorEmpleados.getString(1));
                empleado.setApellidos(cursorEmpleados.getString(2));
                empleado.setEdad(cursorEmpleados.getString(3));
                empleado.setDireccion(cursorEmpleados.getString(4));
                empleado.setPuesto(cursorEmpleados.getString(5));
                listaEmpleados.add(empleado);
            } while (cursorEmpleados.moveToNext());
        }

        cursorEmpleados.close();

        return listaEmpleados;
    }

    public Empleados verEmpleado(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Empleados empleado = null;
        Cursor cursorEmpleados;

        cursorEmpleados = db.rawQuery("SELECT * FROM " + TABLE_EMPLEADOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorEmpleados.moveToFirst()) {
            empleado = new Empleados();
            empleado.setId(cursorEmpleados.getInt(0));
            empleado.setNombre(cursorEmpleados.getString(1));
            empleado.setApellidos(cursorEmpleados.getString(2));
            empleado.setEdad(cursorEmpleados.getString(3));
            empleado.setDireccion(cursorEmpleados.getString(4));
            empleado.setPuesto(cursorEmpleados.getString(5));
        }

        cursorEmpleados.close();

        return empleado;
    }

    public boolean editarEmpleado(int id, String nombre, String apellidos, String edad, String direccion, String puesto) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_EMPLEADOS + " SET nombre = '" + nombre + "', apellidos = '" + apellidos + "', edad = '" + edad + "', direccion = '" + direccion +
                     "', puesto = '" + puesto + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarEmpleado(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_EMPLEADOS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

}
