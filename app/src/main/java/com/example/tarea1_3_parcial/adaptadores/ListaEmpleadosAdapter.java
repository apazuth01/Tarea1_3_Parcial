package com.example.tarea1_3_parcial.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea1_3_parcial.Models.Empleados;
import com.example.tarea1_3_parcial.R;
import com.example.tarea1_3_parcial.VerActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaEmpleadosAdapter extends RecyclerView.Adapter<ListaEmpleadosAdapter.EmpleadoViewHolder>{
    ArrayList<Empleados> listaEmpleados;
    ArrayList<Empleados> listaOriginal;

    public ListaEmpleadosAdapter(ArrayList<Empleados> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaEmpleados);
    }

    @NonNull
    @Override
    public EmpleadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_empleado, null, false);
        return new EmpleadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpleadoViewHolder holder, int position) {
        holder.viewNombre.setText(listaEmpleados.get(position).getNombre());
        holder.viewApellidos.setText(listaEmpleados.get(position).getApellidos());
         holder.viewPuesto.setText(listaEmpleados.get(position).getPuesto());
       // holder.viewDireccion.setText(listaEmpleados.get(position).getDireccion());
       // holder.viewPuesto.setText(listaEmpleados.get(position).getPuesto());
    }

    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaEmpleados.clear();
            listaEmpleados.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Empleados> collecion = listaEmpleados.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaEmpleados.clear();
                listaEmpleados.addAll(collecion);
            } else {
                for (Empleados c : listaOriginal) {
                    if (c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaEmpleados.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaEmpleados.size();
    }

    public class EmpleadoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewApellidos, viewEdad, viewDireccion, viewPuesto;

        public EmpleadoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewApellidos = itemView.findViewById(R.id.viewApellidos);
            viewEdad = itemView.findViewById(R.id.viewEdad);
            viewDireccion = itemView.findViewById(R.id.viewDireccion);
            viewPuesto = itemView.findViewById(R.id.viewPuesto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaEmpleados.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
