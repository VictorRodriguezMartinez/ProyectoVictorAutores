package com.proyecto.rubio.proyectovictorautores.ver;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.proyecto.rubio.proyectovictorautores.BaseDeDatos;
import com.proyecto.rubio.proyectovictorautores.MainActivity;
import com.proyecto.rubio.proyectovictorautores.R;
import com.proyecto.rubio.proyectovictorautores.adaptadorList.AdaptadorListaAutores;
import com.proyecto.rubio.proyectovictorautores.adaptadorList.AdaptadorListaCitas;

public class VerCitaUnoActivity extends AppCompatActivity {

    private String[] cita, nombreAutor, fecha;
    ListView lista;

    private static final int REQUEST_CODE=10;
    BaseDeDatos autores = new BaseDeDatos(this, "BaseDeDatos", null, 1);;

    BaseDeDatos citas;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cita_uno);

        rellenarArrays();
        Bundle datos = this.getIntent().getExtras();
        int modoActual = datos.getInt("modoActual");

        //int modoActual = getIntent().getIntExtra("modoActual",2);
        //modoActual=3;

        //Sacamos por pantalla el modo actual
        Toast.makeText(getApplicationContext(), ("modoActual:"+ modoActual), Toast.LENGTH_SHORT).show();



        //Condicionar si el modo esta en borrar o en ver
        if(modoActual==3){//borrar

            //****************************************
            AdaptadorListaCitas adapter = new AdaptadorListaCitas(this, cita, nombreAutor, fecha);
            lista=(ListView)findViewById(R.id.lista_ver_cita);
            lista.setAdapter(adapter);
            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Intent ventanaDetalle = new Intent(getApplication(), VerCitaDosActivity.class);
                    //ventanaDetalle.putExtra("posicion", position);
                    //startActivityForResult(ventanaDetalle, REQUEST_CODE);

                        final long idLV = id;
                    //Toast.makeText(getApplicationContext(), "aaaa"+Long.toString(idLV), Toast.LENGTH_SHORT).show();



                    AlertDialog.Builder builder = new AlertDialog.Builder(VerCitaUnoActivity.this);
                    builder.setMessage("¿Seguro que quiere borrar la cita seleccionada?")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // FIRE ZE MISSILES!
                                    Toast.makeText(getApplicationContext(), "aaaa"+Long.toString(idLV), Toast.LENGTH_SHORT).show();

                                    SQLiteDatabase db = autores.getWritableDatabase();

                                    String[] misCampos = new String[] {"id"};
                                    Cursor cur = db.query("Cita",misCampos,null,null,null,null,null);
                                    cur.moveToFirst();

                                    String IDDD = cur.getString(0);
                                    //Toast.makeText(getApplicationContext(), (IDDD+" ______ "), Toast.LENGTH_SHORT).show();
                                    db.delete("Cita", "id="+IDDD,null);

                                    Intent ventanaPrincipal = new Intent(getApplication(), MainActivity.class);
                                    startActivityForResult(ventanaPrincipal, REQUEST_CODE);

                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                    Toast.makeText(getApplicationContext(), ("Ha pulsado cancelar"), Toast.LENGTH_SHORT).show();
                                }
                            });
                    // Create the AlertDialog object and return it
                    builder.create();
                    builder.show();


                    //Toast.makeText(getApplicationContext(),  "Edición Realizada correctamente", Toast.LENGTH_SHORT).show();


                }
            });

            //En caso de ver:
        }else if (modoActual==2){//listar-ver
            AdaptadorListaCitas adapter = new AdaptadorListaCitas(this, cita, nombreAutor, fecha);
            lista=(ListView)findViewById(R.id.lista_ver_cita);
            lista.setAdapter(adapter);

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent ventanaDetalle = new Intent(getApplication(), VerAutorDosActivity.class);
                    ventanaDetalle.putExtra("posicion", position);
                    startActivityForResult(ventanaDetalle, REQUEST_CODE);
                }
            });
        }

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
