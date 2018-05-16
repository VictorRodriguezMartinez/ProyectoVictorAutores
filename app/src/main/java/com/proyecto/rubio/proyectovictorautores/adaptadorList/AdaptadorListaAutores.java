package com.proyecto.rubio.proyectovictorautores.adaptadorList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.proyecto.rubio.proyectovictorautores.R; //Importante implementar R.


public class AdaptadorListaAutores extends ArrayAdapter<String> {


    private Activity context;
    private TextView textViewNombreAutor, textViewFechaNacimiento;
    private String[] nombreAutor, fechaNacimiento;


    public AdaptadorListaAutores(Activity context, String[] nombreAutor, String[] fechaNacimiento) {
        super(context, R.layout.detalle_lista_autor, nombreAutor);
        this.context = context;
        this.nombreAutor = nombreAutor;
        this.fechaNacimiento = fechaNacimiento;

    }

    public View getView(int posicion, View view, ViewGroup parent){

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.detalle_lista_autor,null,true);

        textViewNombreAutor = rowView.findViewById(R.id.textViewListaNombreAutor);
        textViewFechaNacimiento = rowView.findViewById(R.id.textViewFechaListaAutor);

        textViewNombreAutor.setText(nombreAutor[posicion]);
        textViewFechaNacimiento.setText(fechaNacimiento[posicion]);

        return rowView;

    }

}


