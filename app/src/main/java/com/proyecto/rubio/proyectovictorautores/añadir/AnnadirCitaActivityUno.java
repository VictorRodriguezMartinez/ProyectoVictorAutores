package com.proyecto.rubio.proyectovictorautores.añadir;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.proyecto.rubio.proyectovictorautores.BaseDeDatos;
import com.proyecto.rubio.proyectovictorautores.R;
import com.proyecto.rubio.proyectovictorautores.adaptadorList.AdaptadorListaAutores;

public class AnnadirCitaActivityUno extends AppCompatActivity {

    private String[] nombreAutor, fechaNacimiento;
    ListView lista;

    private static final int REQUEST_CODE=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cita_uno);

        rellenarArrays();

        AdaptadorListaAutores adapter = new AdaptadorListaAutores(this, nombreAutor, fechaNacimiento);
        lista=(ListView)findViewById(R.id.lista_add_cita);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ventanaAddCitaDos = new Intent(getApplication(), AnnadirCitaActivityDos.class);
                ventanaAddCitaDos.putExtra("posicion", position);
                startActivityForResult(ventanaAddCitaDos, REQUEST_CODE);
            }
        });

    }

    private void rellenarArrays() {

        BaseDeDatos autores = new BaseDeDatos(this, "BaseDeDatos", null, 1);
        SQLiteDatabase db = autores.getWritableDatabase();

        String[] misCampos = new String[] {"nombre", "fecha_nacimiento"};
        Cursor cur = db.query("Autor", misCampos, null, null, null, null, null);

        nombreAutor = new String[cur.getCount()];
        fechaNacimiento = new String[cur.getCount()];

        int cont=0;

        if (cur.moveToFirst()) {
            do {
                nombreAutor[cont] = cur.getString(0);
                fechaNacimiento[cont] = cur.getString(1);

                cont++;

            } while(cur.moveToNext());
        }

    }

}
