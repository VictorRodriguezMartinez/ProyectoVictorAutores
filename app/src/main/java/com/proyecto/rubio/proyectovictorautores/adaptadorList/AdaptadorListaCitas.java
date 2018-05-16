package com.proyecto.rubio.proyectovictorautores.adaptadorList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.proyecto.rubio.proyectovictorautores.R; //Importante implementar R.


public class AdaptadorListaCitas extends ArrayAdapter<String> {

    private Activity context;
    private TextView textViewCita, textViewFechaCita, textViewAutorCita;
    private String[] cita, fechaCita, autorCita;


    public AdaptadorListaCitas(Activity context, String[] cita, String[] fechaCita, String[] autorCita) {
        super(context, R.layout.detalle_lista_cita, cita);
        this.context = context;
        this.cita = cita;
        this.fechaCita = fechaCita;
        this.autorCita = autorCita;

    }

    public View getView(int posicion, View view, ViewGroup parent){

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.detalle_lista_cita,null,true);

        textViewCita = rowView.findViewById(R.id.textViewDatoPrincipalLista);
        textViewFechaCita = rowView.findViewById(R.id.textViewFechaLista);
        textViewAutorCita = rowView.findViewById(R.id.textViewNombreAutorLista);

        textViewCita.setText(cita[posicion]);
        textViewFechaCita.setText(fechaCita[posicion]);
        textViewAutorCita.setText(autorCita[posicion]);

        return rowView;

    }

}
