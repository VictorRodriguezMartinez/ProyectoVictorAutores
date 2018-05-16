package com.proyecto.rubio.proyectovictorautores.ver;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.proyecto.rubio.proyectovictorautores.BaseDeDatos;
import com.proyecto.rubio.proyectovictorautores.R;


public class VerAutorDosActivity extends AppCompatActivity {

    private TextView textViewNombreAutor, textViewVerFechaNacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_autor_dos);

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        textViewNombreAutor = (TextView) findViewById(R.id.textViewNombreAutor);
        textViewVerFechaNacimiento = (TextView) findViewById(R.id.textViewVerFechaNacimiento);

        Bundle extras = getIntent().getExtras();

        BaseDeDatos autores = new BaseDeDatos(this, "BaseDeDatos", null, 1);
        SQLiteDatabase db = autores.getWritableDatabase();

        String[] misCampos = new String[] {"nombre", "fecha_nacimiento"};
        Cursor cur = db.query("Autor", misCampos, null, null, null, null, null);

        cur.moveToPosition(extras.getInt("posicion"));

        textViewNombreAutor.setText(cur.getString(0));
        textViewVerFechaNacimiento.setText(cur.getString(1));

    }

}
