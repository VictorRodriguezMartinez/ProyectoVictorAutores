package com.proyecto.rubio.proyectovictorautores.editar;

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
import com.proyecto.rubio.proyectovictorautores.adaptadorList.AdaptadorListaCitas;
import com.proyecto.rubio.proyectovictorautores.ver.VerCitaDosActivity;

public class EditarCitaUnoActivity extends AppCompatActivity {

    private String[] cita, nombreAutor, fecha;
    ListView lista;

    private static final int REQUEST_CODE=10;

    BaseDeDatos citas;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cita_uno);

        rellenarArrays();

        AdaptadorListaCitas adapter = new AdaptadorListaCitas(this, cita, nombreAutor, fecha);
        lista=(ListView)findViewById(R.id.lista_edit_cita);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ventanaEdicion = new Intent(getApplication(), EditarCitaDosActivity.class);
                ventanaEdicion.putExtra("posicion", position);
                startActivityForResult(ventanaEdicion, REQUEST_CODE);
            }
        });

    }

    private void rellenarArrays() {

        citas = new BaseDeDatos(this, "BaseDeDatos", null, 1);
        db = citas.getWritableDatabase();

        String[] misCampos = new String[] {"cita", "id_autor", "fecha"};
        Cursor cur = db.query("Cita", misCampos, null, null, null, null, null);

        cita = new String[cur.getCount()];
        nombreAutor = new String[cur.getCount()];
        fecha = new String[cur.getCount()];

        int cont=0;

        if (cur.moveToFirst()) {
            do {
                cita[cont] = cur.getString(0);
                fecha[cont] = cur.getString(2);
                nombreAutor[cont] = getNombreAutor(cur.getString(1));

                cont++;

            } while(cur.moveToNext());
        }

    }

    private String getNombreAutor(String id_autor) {

        String[] Campos = new String[] {"id", "nombre"};
        String whereClause = "id = ?";
        String[] whereArgs = new String[] {
                id_autor
        };
        Cursor curAutor = db.query("Autor", Campos, whereClause, whereArgs, null, null, null);

        curAutor.moveToFirst();
        return curAutor.getString(1);

    }

}
