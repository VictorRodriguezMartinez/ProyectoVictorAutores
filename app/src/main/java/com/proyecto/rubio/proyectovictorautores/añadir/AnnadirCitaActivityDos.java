package com.proyecto.rubio.proyectovictorautores.añadir;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.proyecto.rubio.proyectovictorautores.BaseDeDatos;
import com.proyecto.rubio.proyectovictorautores.R;

public class AnnadirCitaActivityDos extends AppCompatActivity {

    private EditText editTextTextoCita, editTextFechaCreacionCita;
    private Button buttonAnnadirCita;

    private BaseDeDatos autores;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cita_dos);

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        editTextTextoCita = (EditText) findViewById(R.id.editTextTextoCita);
        editTextFechaCreacionCita = (EditText) findViewById(R.id.editTextFechaCreacionCita);
        buttonAnnadirCita = (Button) findViewById(R.id.buttonAnnadirCita);

        autores = new BaseDeDatos(this, "BaseDeDatos", null, 1);
        db = autores.getWritableDatabase();

        Bundle extras = getIntent().getExtras();

        String[] misCampos = new String[] {"id"};
        final Cursor cur = db.query("Autor", misCampos, null, null, null, null, null);

        cur.moveToPosition(extras.getInt("posicion"));

        buttonAnnadirCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.execSQL("INSERT INTO Cita (cita, id_autor, fecha)" +
                        "VALUES ('" +editTextTextoCita.getText().toString() +"', '" +cur.getInt(0) +"', '" +editTextFechaCreacionCita.getText().toString() +"')");

                Toast.makeText(getApplicationContext(),  "Añadido correctamente", Toast.LENGTH_SHORT).show();

                finish();

            }
        });

    }

}
