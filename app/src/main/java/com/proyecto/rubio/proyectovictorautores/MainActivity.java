package com.proyecto.rubio.proyectovictorautores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.proyecto.rubio.proyectovictorautores.añadir.AnnadirAutorActivity;
import com.proyecto.rubio.proyectovictorautores.añadir.AnnadirCitaActivityUno;
import com.proyecto.rubio.proyectovictorautores.editar.EditarAutorUnoActivity;
import com.proyecto.rubio.proyectovictorautores.editar.EditarCitaUnoActivity;
import com.proyecto.rubio.proyectovictorautores.ver.VerAutorUnoActivity;
import com.proyecto.rubio.proyectovictorautores.ver.VerCitaUnoActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE=10;

    Spinner spinner;
    ImageButton imagenAutores, imagenCitas;
    int modoActual;//indexamos los los diferentes modos en la funcion onCreateOptionsMenu (0=añadir , 1=editar, 2=ver)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this.setTitle("Añade títulos");


        inicializarComponentes();

    }

    /*

     */
    private void inicializarComponentes() {

        imagenAutores = (ImageButton) findViewById(R.id.imageButtonAutores);
        imagenCitas = (ImageButton) findViewById(R.id.imageButtonCitas);

        //Inicializamos un nuevo objeto de tipo BaseDeDatos llamado autores
        BaseDeDatos autores = new BaseDeDatos(this, "BaseDeDatos", null, 1);
        //SQLiteDatabase db = autores.getWritableDatabase();

        imagenAutores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (modoActual) {
                    case 0:

                        Intent ventanaAddAutor = new Intent(getApplication(), AnnadirAutorActivity.class);
                        startActivityForResult(ventanaAddAutor, REQUEST_CODE);

                        //Intent Añadir Autor
                        break;
                    case 1:
                        Intent ventanaEditAutor = new Intent(getApplication(), EditarAutorUnoActivity.class);
                        startActivityForResult(ventanaEditAutor, REQUEST_CODE);
                        //Intent Editar Autor (lista autores)
                        break;
                    case 2:
                        Intent ventanaVerAutor = new Intent(getApplication(), VerAutorUnoActivity.class);
                        startActivityForResult(ventanaVerAutor, REQUEST_CODE);
                        //Lista todos autores.
                        break;
                }

            }
        });

        imagenCitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (modoActual) {
                    case 0:
                        Intent ventanaAddCita = new Intent(getApplication(), AnnadirCitaActivityUno.class);
                        startActivityForResult(ventanaAddCita, REQUEST_CODE);
                        //Intent Añadir Cita
                        break;
                    case 1:
                        Intent ventanaEditCita = new Intent(getApplication(), EditarCitaUnoActivity.class);
                        startActivityForResult(ventanaEditCita, REQUEST_CODE);
                        //Intent Editar Cita (lista autores)
                        break;
                    case 2:
                        Intent ventanaVerCita = new Intent(getApplication(), VerCitaUnoActivity.class);
                        startActivityForResult(ventanaVerCita, REQUEST_CODE);
                        //Lista todas citas.
                        break;
                }

            }
        });

    }


    //Estos dos métodos son sobre el menu del Main.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_activity_menu, menu);

        MenuItem item = menu.findItem(R.id.spinner);
        spinner = (Spinner) item.getActionView();

        //Recogemos el valor del spinner para realizar la accion deseada(insertar, modificar, listar)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opcionesMenu, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                switch (position) {
                    case 0:
                        Toast.makeText(getApplicationContext(),  "Modo Añadir Activado", Toast.LENGTH_SHORT).show();
                        modoActual = 0;
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(),  "Modo Editar Activado", Toast.LENGTH_SHORT).show();
                        modoActual = 1;
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(),  "Modo Ver Activado", Toast.LENGTH_SHORT).show();
                        modoActual = 2;
                        break;
                }

                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                // your code here AQUI NO VA NADA, ya que por defecto se muestra una opcion
                    }
        });

        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.spinner:
                Toast.makeText(getApplicationContext(),  item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
