package com.proyecto.rubio.proyectovictorautores.ver;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
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
import com.proyecto.rubio.proyectovictorautores.R;
import com.proyecto.rubio.proyectovictorautores.adaptadorList.AdaptadorListaAutores;

public class VerAutorUnoActivity extends AppCompatActivity {

    private String[] nombreAutor, fechaNacimiento;
    ListView lista;

    private static final int REQUEST_CODE=10;
    BaseDeDatos autores = new BaseDeDatos(this, "BaseDeDatos", null, 1);;
    SQLiteDatabase db;
    int id;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_autor_uno);

        rellenarArrays();

        //Recoger valor del modo de la ventana anterior

        int modoActual = getIntent().getIntExtra("modoActual",2);

        //Sacamos por pantalla el modo actual
        Toast.makeText(getApplicationContext(), ("ModoActual:"+ modoActual), Toast.LENGTH_SHORT).show();



        //Condicionar si el modo esta en borrar o en ver
         if(modoActual==3){//borrar

             //****************************************
             final AdaptadorListaAutores adapter = new AdaptadorListaAutores(this, nombreAutor, fechaNacimiento);
             lista=(ListView)findViewById(R.id.lista_ver_autor);
             lista.setAdapter(adapter);
             lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                     final long idAutor=id;
                //CLICK
                     Toast.makeText(getApplicationContext(),  "ID:________   "+idAutor+"", Toast.LENGTH_SHORT).show();




                     AlertDialog.Builder builder = new AlertDialog.Builder(VerAutorUnoActivity.this);
                     builder.setMessage("¿Seguro que quiere borrar el autor junto a todas sus citas?")
                             .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                 public void onClick(DialogInterface dialog, int id) {

                                     Toast.makeText(getApplicationContext(), ("Ha pulsado aceptar, el id del autor es:"+idAutor+""), Toast.LENGTH_SHORT).show();

                                     SQLiteDatabase db = autores.getWritableDatabase();

                                     String[] misCampos = new String[] {"id", "nombre"};
                                     Cursor cur = db.query("Autor",misCampos,null,null,null,null,null);
                                     cur.moveToFirst();

                                     cur.getColumnIndex("id");

                                     String IDDD = cur.getString(0);
                                     Toast.makeText(getApplicationContext(), (IDDD+" "), Toast.LENGTH_SHORT).show();
                                     String whereArgs[] = {( Long.toString(idAutor))};
                                     db.delete("Cita", "id_autor="+IDDD,null);
                                     //db.execSQL("delete from Cita where id_autor="+idAutor+" ");
                                     db.execSQL("delete from Autor where id="+IDDD+" ");
                                 }
                             })
                             .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                 public void onClick(DialogInterface dialog, int id) {
                                     // User cancelled the dialog
                                     Toast.makeText(getApplicationContext(), ("Ha pulsado cancelar"), Toast.LENGTH_SHORT).show();
                                 }
                             });
                     builder.create();
                     builder.show();



                 }
             });
             //****************************************


/*
             AlertDialog.Builder builder = new AlertDialog.Builder(this);
             builder.setMessage("¿Seguro que quiere borrar el autor junto a todas sus citas?")
                     .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int id) {
                             // FIRE ZE MISSILES!
                             Toast.makeText(getApplicationContext(), ("Ha pulsado aceptar"), Toast.LENGTH_SHORT).show();
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
                builder.show();*/

         //En caso de ver:
         }else if (modoActual==2){//listar-ver
             AdaptadorListaAutores adapter = new AdaptadorListaAutores(this, nombreAutor, fechaNacimiento);
             lista=(ListView)findViewById(R.id.lista_ver_autor);
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

        BaseDeDatos autores = new BaseDeDatos(this, "BaseDeDatos", null, 1);
        SQLiteDatabase db = autores.getWritableDatabase();

        String[] misCampos = new String[] {"nombre", "fecha_nacimiento"};
        Cursor cur = db.query("Autor", misCampos, null, null, null, null, null);

        nombreAutor = new String[cur.getCount()];
        fechaNacimiento = new String[cur.getCount()];

        int cont=0;

        if (cur.moveToFirst()) {
            do {
                nombreAutor[cont] = cur.getString(0);
                fechaNacimiento[cont] = cur.getString(1);

                cont++;

            } while(cur.moveToNext());
        }

    }


}
