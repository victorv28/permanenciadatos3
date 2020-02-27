package com.example.permanenciadatos;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Juego extends MainActivity {

    boolean gameActive = true;
    int contX =0;
    int contO=0;
    int activePlayer = 0;
    int[] gameState = {2, 2 , 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);
        BaseDeDatos bdd= new BaseDeDatos(this);

    }

    public void playerTap(View view){
        BaseDeDatos bdd= new BaseDeDatos(this);

        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if(gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.equis);
                activePlayer = 1;
            } else {
                img.setImageResource(R.drawable.circulo);
                activePlayer = 0;
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        // Check if any player has won
        for(int[] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2) {
                // Somebody has won! - Find out who!
                String winnerStr;
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X has won";
                    Cursor cursor=bdd.ViewDataX();
                    StringBuffer buffer=new StringBuffer();
                    while(cursor.moveToNext()){
                        buffer.append(cursor.getInt(0));
                    }
                    contX=Integer.parseInt(buffer.toString());
                    bdd.update_puntos(contX+1,"X");

                } else {
                    winnerStr = "O has won";
                    Cursor cursor=bdd.ViewDataX();
                    StringBuffer buffer=new StringBuffer();
                    while(cursor.moveToNext()){
                        buffer.append(cursor.getInt(0));
                    }
                    contO=Integer.parseInt(buffer.toString());
                    bdd.update_puntos(contO+1,"O");

                }
                // Update the status bar for winner announcement

                Toast.makeText(this,winnerStr,Toast.LENGTH_SHORT).show();

            }
        }

    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
//        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView11)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView12)).setImageResource(0);
    }



}
