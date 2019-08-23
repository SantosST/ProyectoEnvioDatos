package com.example.proyectojulio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adaptador extends BaseAdapter {

    Context contexto;
    List<EstructuraDatos> ListaProfesores;

    public Adaptador(Context contexto, List<EstructuraDatos> listaProfesores) {
        this.contexto = contexto;
        ListaProfesores = listaProfesores;
    }

    @Override
    public int getCount() {
        return ListaProfesores.size(); //retorna la cantidad de elementos de la lista
    }

    @Override
    public Object getItem(int position) {
        return ListaProfesores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ListaProfesores.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista=convertView;
        LayoutInflater inflate= LayoutInflater.from(contexto);
        vista= inflate.inflate(R.layout.itempersonalizadolistaprof,null);
        ImageView imgImagenPerfil = (ImageView) vista.findViewById(R.id.imgImagenPerfil);
        TextView txtNombre = (TextView) vista.findViewById(R.id.txtNombre);
        TextView txtMaterias = (TextView) vista.findViewById(R.id.txtMaterias);
        TextView txtPrecio = (TextView) vista.findViewById(R.id.txtPrecio);
        TextView txtLocalizacion = (TextView) vista.findViewById(R.id.txtLocalizacion);

        imgImagenPerfil.setImageResource(ListaProfesores.get(position).getImagen());
        txtNombre.setText(ListaProfesores.get(position).getNombre());
        txtMaterias.setText(ListaProfesores.get(position).getMaterias());
        txtPrecio.setText(Integer.toString(ListaProfesores.get(position).getPrecio()));
        txtLocalizacion.setText(ListaProfesores.get(position).getLocalizacion());


        return vista;
    }
}
