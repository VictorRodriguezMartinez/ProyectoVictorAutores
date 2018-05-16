package com.proyecto.rubio.proyectovictorautores.ver;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.proyecto.rubio.proyectovictorautores.BaseDeDatos;
import com.proyecto.rubio.proyectovictorautores.R;

public class VerCitaDosActivity extends AppCompatActivity {

    private TextView textViewTextoVerCita, textViewVerFechaCita, textViewVerNombreAutorCita;

    BaseDeDatos citas;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cita_dos);

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        textViewTextoVerCita = (TextView) findViewById(R.id.textViewTextoVerCita);
        textViewVerFechaCita = (TextView) findViewById(R.id.textViewVerFechaCita);
        textViewVerNombreAutorCita = (TextView) findViewById(R.id.textViewVerNombreAutorCita);

        Bundle extras = getIntent().getExtras();

        citas = new BaseDeDatos(this, "BaseDeDatos", null, 1);
        db = citas.getWritableDatabase();

        String[] misCampos = new String[] {"cita", "id_autor", "fecha"};
        Cursor cur = db.query("Cita", misCampos, null, null, null, null, null);

        cur.moveToPosition(extras.getInt("posicion"));

        textViewTextoVerCita.setText(cur.getString(0));
        textViewVerFechaCita.setText(cur.getString(2));
        textViewVerNombreAutorCita.setText(getNombreAutor(cur.getString(1)));

    }

    private String getNombreAutor(String id_autor) {

        String[] Campos = new String[] {"id", "nombre"};
        String whereClause = "id = ?";
        String[] whereArgs = new String[] {
                id_autor //,nombre (Victor)
        };
        Cursor curAutor = db.query("Autor", Campos, whereClause, whereArgs, null, null, null);

        curAutor.moveToFirst();
        return curAutor.getString(1);

    }

}
