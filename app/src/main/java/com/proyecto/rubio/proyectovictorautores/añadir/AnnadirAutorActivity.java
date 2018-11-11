package com.proyecto.rubio.proyectovictorautores.añadir;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.proyecto.rubio.proyectovictorautores.BaseDeDatos;
import com.proyecto.rubio.proyectovictorautores.R;

public class AnnadirAutorActivity extends AppCompatActivity {

    private EditText editTextNombreAutor, editTextFechaNacimientoAutor;
    private Button buttonAnnadirAutor;

    private BaseDeDatos autores;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_autor);

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        editTextNombreAutor = (EditText) findViewById(R.id.editTextNombreAutor);
        editTextFechaNacimientoAutor = (EditText) findViewById(R.id.editTextFechaNacimientoAutor);
        buttonAnnadirAutor = (Button) findViewById(R.id.buttonAnnadirAutor);

        autores = new BaseDeDatos(this, "BaseDeDatos", null, 1);
        db = autores.getWritableDatabase();

        buttonAnnadirAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.execSQL("INSERT INTO Autor (nombre, fecha_nacimiento)" +
                        "VALUES ('" +editTextNombreAutor.getText().toString() +"', '" +editTextFechaNacimientoAutor.getText().toString() +"')");

                Toast.makeText(getApplicationContext(),  "Añadido correctamente", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = getSharedPreferences("ultimoAutorAnadido", MODE_PRIVATE).edit();
                editor.putString("nombre", editTextNombreAutor.getText().toString());
                editor.putString("fechaNacimiento", editTextFechaNacimientoAutor.getText().toString());
                editor.apply();




                finish();


            }
        });

    }

}
