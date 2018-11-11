package com.proyecto.rubio.proyectovictorautores.ver;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.proyecto.rubio.proyectovictorautores.R;

public class VerUltimoAutorAnadido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_ultimo_autor_anadido);

        SharedPreferences.Editor editor = getSharedPreferences("ultimoAutorAnadido", MODE_PRIVATE).edit();
        SharedPreferences prefs = getSharedPreferences("ultimoAutorAnadido", MODE_PRIVATE);
        String nombre = prefs.getString("nombre", null);
        String fechaNacimiento = prefs.getString("fechaNacimiento", null);

        TextView nombreTextView = (TextView) findViewById(R.id.textViewNomAutor);
        nombreTextView.setText(nombre);

        TextView fechaNacimientoTextView = (TextView) findViewById(R.id.FechNacAut);
        fechaNacimientoTextView.setText(fechaNacimiento);

    }
}
