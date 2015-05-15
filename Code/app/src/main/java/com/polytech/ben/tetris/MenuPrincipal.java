package com.polytech.ben.tetris;


import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

import android.view.View;


public class MenuPrincipal extends Activity {

    /**
     * Mise en place des composants de l'interface lors de son ouverture
     * @param savedInstanceState    Etat de l'activité
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchGame(View view){
        Intent gameActivity = new Intent(MenuPrincipal.this, GameActivity.class);
        startActivity(gameActivity);
    }
    /**
     * Ouvre l'activité permettant de lire les instructions de jeu
     * @param view  Le bouton cliqué
     */
    public void seeInstructions(View view){
        Intent instructionsActivity = new Intent(MenuPrincipal.this, InstructionsActivity.class);
        startActivity(instructionsActivity);
    }
    /**
     * Ouvre l'activité permettant de modifier les options de l'application
     * @param view  Le bouton cliqué
     */
    public void changeOptions(View view){
        Intent optionsActivity = new Intent(MenuPrincipal.this, OptionsActivity.class);
        startActivity(optionsActivity);

    }

}
