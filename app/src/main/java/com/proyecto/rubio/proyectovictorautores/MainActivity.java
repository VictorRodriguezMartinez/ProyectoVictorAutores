package com.proyecto.rubio.proyectovictorautores;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.proyecto.rubio.proyectovictorautores.añadir.AnnadirAutorActivity;
import com.proyecto.rubio.proyectovictorautores.añadir.AnnadirCitaActivityUno;
import com.proyecto.rubio.proyectovictorautores.editar.EditarAutorUnoActivity;
import com.proyecto.rubio.proyectovictorautores.editar.EditarCitaUnoActivity;
import com.proyecto.rubio.proyectovictorautores.ver.VerAutorUnoActivity;
import com.proyecto.rubio.proyectovictorautores.ver.VerCitaUnoActivity;
import com.proyecto.rubio.proyectovictorautores.ver.VerUltimoAutorAnadido;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE=10;

    //Declaracion de obsjetos
    Spinner spinner;
    //ImageButton imagenAutores, imagenCitas;
    Button botonAutores, botonCitas;
     static  int modoActual=0;//indexamos los los diferentes modos en la funcion onCreateOptionsMenu (0=añadir , 1=editar, 2=ver, 3=borrar)

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

        //imagenAutores = (ImageButton) findViewById(R.id.imageButtonAutores);
        //imagenCitas = (ImageButton) findViewById(R.id.imageButtonCitas);
        botonAutores = (Button) findViewById(R.id.buttonAutores);
        botonCitas = (Button) findViewById(R.id.buttonCitas);

        //Inicializamos un nuevo objeto de tipo BaseDeDatos llamado autores
        BaseDeDatos autores = new BaseDeDatos(this, "BaseDeDatos", null, 1);
        //SQLiteDatabase db = autores.getWritableDatabase();

        botonAutores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                switch (modoActual) {
                    case 0://Intent Añadir Autor
                        Intent ventanaAddAutor = new Intent(getApplication(), AnnadirAutorActivity.class);
                        startActivityForResult(ventanaAddAutor, REQUEST_CODE);
                        break;
                    case 1://Intent Editar Autor (lista autores)
                        Intent ventanaEditAutor = new Intent(getApplication(), EditarAutorUnoActivity.class);
                        startActivityForResult(ventanaEditAutor, REQUEST_CODE);

                        break;
                    case 2://Intent Lista todos autores.
                        Intent ventanaVerAutor = new Intent(getApplication(), VerAutorUnoActivity.class);
                        ventanaVerAutor.putExtra("modoActual",modoActual);
                        startActivityForResult(ventanaVerAutor, REQUEST_CODE);
                        break;
                    case 3://Intent borrar
                        Intent ventanaVerAutor2 = new Intent(getApplication(), VerAutorUnoActivity.class);
                        ventanaVerAutor2.putExtra("modoActual",modoActual);
                        startActivityForResult(ventanaVerAutor2, REQUEST_CODE);
                        break;

                }

            }
        });

        botonCitas.setOnClickListener(new View.OnClickListener() {
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
                        ventanaVerCita.putExtra("modoActual", 2);
                        startActivityForResult(ventanaVerCita, REQUEST_CODE);
                        //Lista todas citas.
                        break;
                    case 3:
                        ventanaVerCita = new Intent(getApplication(), VerCitaUnoActivity.class);
                        ventanaVerCita.putExtra("modoActual", 3);
                        startActivityForResult(ventanaVerCita, REQUEST_CODE);

                        //Lista todas citas.
                        break;

                }

            }
        });

    }


    //Funciones del spinner para cambiar modo, cambiar idioma etc.
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
                //@VICTORROMA: se asigna el numero a la variable modoActual
                // segun el orden de la lista desplegable (spinner)
                switch (position) {
                    case 0://Añadir-Insert
                        Toast.makeText(getApplicationContext(),  "Modo Añadir Activado", Toast.LENGTH_SHORT).show();
                        modoActual = 0;
                        break;
                    case 1://Editar-Modificar
                        Toast.makeText(getApplicationContext(),  "Modo Editar Activado", Toast.LENGTH_SHORT).show();
                        modoActual = 1;
                        break;
                    case 2://Ver-Listar
                        Toast.makeText(getApplicationContext(),  "Modo Ver Activado", Toast.LENGTH_SHORT).show();
                        modoActual = 2;
                        break;
                    case 3://Borrar
                        Toast.makeText(getApplicationContext(),  "Modo Borrar Activado", Toast.LENGTH_SHORT).show();
                        modoActual = 3;
                        break;
                    case 4:
                        Intent ventanaOpciones = new Intent(getApplication(), Opciones.class);
                        startActivityForResult(ventanaOpciones, REQUEST_CODE);
                        break;
                    case 5:
                        Intent ventanaVerUltimoAutor = new Intent(getApplication(), VerUltimoAutorAnadido.class);
                        startActivityForResult(ventanaVerUltimoAutor, REQUEST_CODE);
                        break;

                }

                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                //AQUI NO VA NADA, ya que por defecto se muestra una opcion
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
