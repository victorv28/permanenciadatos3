package com.example.permanenciadatos;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;


import java.util.HashMap;

public class BaseDeDatos extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db";
    private static final int DATABASE_VERSION = 1;


    public static final String TABLE_NAME = "puntuacion";
    public static final String COLUMN_NAME = "jugador";
    public static final String COLUMN_PT = "puntos";


    private static final String DATABASE_CREATE ="CREATE TABLE " + TABLE_NAME
            + "(" + COLUMN_NAME + " text not null primary key, " + COLUMN_PT
            + " int);";


    public BaseDeDatos(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }


    public Cursor ViewDataO(){
       SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
       Cursor cursor =sqLiteDatabase.rawQuery("SELECT "+COLUMN_PT+" FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME+" = 'X'; ",null);
       return cursor;
    }
    public Cursor ViewDataX(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor =sqLiteDatabase.rawQuery("SELECT "+COLUMN_PT+" FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME+" = 'O'; ",null);
        return cursor;
    }

    public boolean update_puntos(int puntos,String nombre){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_PT,puntos);
        db.update(TABLE_NAME,contentValues, COLUMN_NAME+" = ?",new String[]{nombre});
        return true;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        db.execSQL("INSERT INTO "+TABLE_NAME+" ("+COLUMN_NAME+", "+COLUMN_PT+") VALUES ('X',0)");
        db.execSQL("INSERT INTO "+TABLE_NAME+" ("+COLUMN_NAME+", "+COLUMN_PT+") VALUES ('O',0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int _old,int _new) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }


}
