package com.example.permanenciadatos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.menu.MenuAdapter;


import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    BaseDeDatos bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bdd = new BaseDeDatos(this);
    }

    public void cambiar(View view){
        String button_text;
        button_text=((Button) view).getText().toString();
        if (button_text.equals("jugar"))
        {
            Intent intent = new Intent(this,Juego.class);
            startActivity(intent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu_,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.puntuacion:
                Intent intent=new Intent(this, Score.class);
                startActivity(intent);
            default: return super.onOptionsItemSelected(item);
        }

    }
}
