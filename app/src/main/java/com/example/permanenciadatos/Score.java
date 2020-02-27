package com.example.permanenciadatos;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Score extends MainActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switch_item);
        BaseDeDatos bdd= new BaseDeDatos(this);
    }


    public void cambiar(View view){
        String button_text;
        button_text=((Button) view).getText().toString();
        if (button_text.equals("atras"))
        {
            Intent intent = new Intent(this,MainActivity.class);
            finish();
        }

    }

    public void actualizar(View view){
        Cursor cursor =bdd.ViewDataO();
        Cursor cursor2 =bdd.ViewDataX();
        TextView textViewo = findViewById(R.id.puntoso);
        TextView textViewx = findViewById(R.id.puntosx);

        StringBuilder stringBuildero = new StringBuilder();
        StringBuilder stringBuilderx = new StringBuilder();

        while(cursor.moveToNext()){
            stringBuildero.append(cursor.getInt(0));
        }
//        textViewo.setText(stringBuildero.toString());
        while(cursor2.moveToNext()){
            stringBuilderx.append(cursor.getInt(0));
        }
//        textViewx.setText(stringBuilderx.toString());
    }




}
