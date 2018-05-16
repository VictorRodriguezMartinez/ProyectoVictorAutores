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
import android.widget.Toast;

import com.proyecto.rubio.proyectovictorautores.BaseDeDatos;
import com.proyecto.rubio.proyectovictorautores.MainActivity;
import com.proyecto.rubio.proyectovictorautores.R;

public class EditarAutorDosActivity extends AppCompatActivity {

    private EditText editTextEditarNombreAutor, editTextEditarFechaNacimientoAutor;
    private Button buttonEditarAutor;

    int id;

    BaseDeDatos autores;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_autor_dos);

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        editTextEditarNombreAutor = (EditText) findViewById(R.id.editTextEditarNombreAutor);
        editTextEditarFechaNacimientoAutor = (EditText) findViewById(R.id.editTextEditarFechaNacimientoAutor);
        buttonEditarAutor = (Button) findViewById(R.id.buttonEditarAutor);

        Bundle extras = getIntent().getExtras();

        autores = new BaseDeDatos(this, "BaseDeDatos", null, 1);
        db = autores.getWritableDatabase();

        String[] misCampos = new String[] {"nombre", "fecha_nacimiento", "id"};
        Cursor cur = db.query("Autor", misCampos, null, null, null, null, null);

        cur.moveToPosition(extras.getInt("posicion"));

        editTextEditarNombreAutor.setText(cur.getString(0));
        editTextEditarFechaNacimientoAutor.setText(cur.getString(1));

        id = cur.getInt(2);

        buttonEditarAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues cv = new ContentValues();
                cv.put("nombre", editTextEditarNombreAutor.getText().toString());
                cv.put("fecha_nacimiento", editTextEditarFechaNacimientoAutor.getText().toString());

                db.update("Autor", cv, "id="+id, null);

                Toast.makeText(getApplicationContext(),  "Edición Realizada correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplication(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Removes other Activities from stack
                startActivity(intent);
            }
        });

    }

}
