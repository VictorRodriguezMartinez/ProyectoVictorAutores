package com.proyecto.rubio.proyectovictorautores;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDatos extends SQLiteOpenHelper {

    String sqlCreateAutores = "CREATE TABLE Autor (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre STRING, fecha_nacimiento STRING)";
    String sqlCreateCitas = "CREATE TABLE Cita (id INTEGER PRIMARY KEY AUTOINCREMENT, cita STRING, id_autor INTEGER REFERENCES [Autor] (id), fecha DATE)";



public BaseDeDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        }

@Override
public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateAutores);
        db.execSQL(sqlCreateCitas);
        }

@Override //Peor forma. Mejorable. Por si se actualiza la bbdd, para cambiar el contenido de la bbdd
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Autores");
        db.execSQL("DROP TABLE IF EXISTS Citas");
        onCreate(db);
        }

        }
