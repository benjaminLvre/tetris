package com.polytech.ben.tetris;


import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

import android.view.View;


public class MenuPrincipal extends Activity {

    /**
     * Mise en place des composants de l'interface lors de son ouverture
     * @param savedInstanceState    Etat de l'activit�
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Ouvre l'activit� permettant de lire les instructions de jeu
     * @param view  Le bouton cliqu�
     */
    public void seeInstructions(View view){
        Intent instructionsActivite = new Intent(MenuPrincipal.this, InstructionsActivity.class);
        startActivity(instructionsActivite);
    }
    /**
     * Ouvre l'activit� permettant de modifier les options de l'application
     * @param view  Le bouton cliqu�
     */
    public void changeOptions(View view){
        Intent optionsActivite = new Intent(MenuPrincipal.this, OptionsActivity.class);
        startActivity(optionsActivite);

    }

}
