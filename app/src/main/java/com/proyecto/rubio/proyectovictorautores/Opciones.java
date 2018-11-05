package com.proyecto.rubio.proyectovictorautores;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class Opciones extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        Button botonEs = (Button) findViewById(R.id.buttonEs);
        Button botonEn = (Button) findViewById(R.id.buttonEn);

        botonEs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale localizacion = new Locale("es", "ES");

                Locale.setDefault(localizacion);
                Configuration config = new Configuration();
                config.locale = localizacion;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

                Intent menuPrincipal = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(menuPrincipal,0);

                //Shared preferences
                SharedPreferences.Editor editor = getSharedPreferences("idioma", MODE_PRIVATE).edit();
                editor.putString("lenguaje", "es");
                editor.putString("pais", "ES");
                editor.apply();




                SharedPreferences prefs = getSharedPreferences("idioma", MODE_PRIVATE);
                String restoredText = prefs.getString("lenguaje", null);
                if (restoredText != null) {
                    String lenguaje = prefs.getString("lenguaje", "No name defined");//"No name defined" is the default value.
                    int idName = prefs.getInt("idName", 0); //0 is the default value.
                Toast.makeText(getApplicationContext(),  "Idioma cambiado a español"+lenguaje, Toast.LENGTH_SHORT).show();
                }

            }
        });

        botonEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Locale localizacion = new Locale("en", "GB");
                Locale localizacion = new Locale("en", "GB");
                Locale.setDefault(localizacion);
                Configuration config = new Configuration();
                config.locale = localizacion;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

                Intent menuPrincipal = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(menuPrincipal,0);




                Toast.makeText(getApplicationContext(),  "Idioma cambiado a inglés", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = getSharedPreferences("idioma", MODE_PRIVATE).edit();
                editor.putString("lenguaje", "en");
                editor.putString("pais", "GB");
                editor.apply();



                v.refreshDrawableState();
            }
        });

    }

}
