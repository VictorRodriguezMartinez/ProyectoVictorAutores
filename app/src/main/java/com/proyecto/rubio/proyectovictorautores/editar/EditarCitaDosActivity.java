package com.proyecto.rubio.proyectovictorautores.editar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.proyecto.rubio.proyectovictorautores.BaseDeDatos;
import com.proyecto.rubio.proyectovictorautores.MainActivity;
import com.proyecto.rubio.proyectovictorautores.R;

public class EditarCitaDosActivity extends AppCompatActivity {

    private EditText editTextTextoEditarCita, editTextEditarFechaCita;
    private TextView textViewEditarVerNombreAutor;
    private Button buttonEditarCita, ButtonEditarAutor;

    private int id;

    BaseDeDatos citas;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cita_dos);

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        citas = new BaseDeDatos(this, "BaseDeDatos", null, 1);
        db = citas.getWritableDatabase();

        editTextTextoEditarCita = (EditText) findViewById(R.id.editTextTextoEditarCita);
        editTextEditarFechaCita = (EditText) findViewById(R.id.editTextEditarFechaCita);
        textViewEditarVerNombreAutor = (TextView) findViewById(R.id.textViewEditarVerNombreAutor);
        buttonEditarCita = (Button) findViewById(R.id.buttonEditarCita);
        ButtonEditarAutor = (Button) findViewById(R.id.ButtonEditarAutorDeCita);

        Bundle extras = getIntent().getExtras();

        String[] misCampos = new String[] {"cita", "id_autor", "fecha", "id"};
        Cursor cur = db.query("Cita", misCampos, null, null, null, null, null);

        cur.moveToPosition(extras.getInt("posicion"));

        editTextTextoEditarCita.setText(cur.getString(0));
        editTextEditarFechaCita.setText(cur.getString(2));
        textViewEditarVerNombreAutor.setText(getNombreAutor(cur.getString(1)));

        id = cur.getInt(3);

        ButtonEditarAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buttonEditarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues cv = new ContentValues();
                cv.put("cita", editTextTextoEditarCita.getText().toString());
                cv.put("fecha", editTextEditarFechaCita.getText().toString());

                db.update("Cita", cv, "id="+id, null);

                Toast.makeText(getApplicationContext(),  "Edición Realizada correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplication(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Removes other Activities from stack
                startActivity(intent);
            }
        });

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
