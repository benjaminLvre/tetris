package com.polytech.stfu.ihm;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.polytech.stfu.jeu.JeuClassique;


public class mainMenuActivity extends Activity {



    /**
     * Mise en place des composants de l'interface lors de son ouverture
     * @param savedInstanceState    Etat de l'activité
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        intialisationSharedFiles();
        ViewDesign.changeMain(this);

        new JeuClassique(mainMenuActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewDesign.changeMain(this);
    }

    public void launchGame(View view){
        Intent gameActivity = new Intent(mainMenuActivity.this, GameActivity.class);
        startActivity(gameActivity);
    }
    /**
     * Ouvre l'activité permettant de lire les instructions de jeu
     * @param view  Le bouton cliqué
     */
    public void seeInstructions(View view){
        Intent instructionsActivity = new Intent(mainMenuActivity.this, InstructionsActivity.class);
        startActivity(instructionsActivity);
    }
    /**
     * Ouvre l'activité permettant de modifier les options de l'application
     * @param view  Le bouton cliqué
     */
    public void changeOptions(View view){
        Intent optionsActivity = new Intent(mainMenuActivity.this, OptionsActivity.class);
        startActivity(optionsActivity);
    }

    public void seeHighscores(View view){
        Intent highscoresActivity = new Intent(mainMenuActivity.this, HighscoresActivity.class);
        startActivity(highscoresActivity);
    }

    public void intialisationSharedFiles(){

        SharedPreferences themeRegister =this.getSharedPreferences("Theme", 0);
        String themeRegisterValue = themeRegister.getString("theme", null);
        SharedPreferences.Editor editor;
        if(themeRegisterValue == null){
            editor = themeRegister.edit();
            editor.putString("theme", "classique");
            editor.apply();
        }
        SharedPreferences modeRegister =this.getSharedPreferences("Mode", 0);
        String modeRegisterValue = modeRegister.getString("mode", null);
        if(modeRegisterValue == null){
            editor = modeRegister.edit();
            editor.putString("mode", "classique");
            editor.apply();
        }
        SharedPreferences vitesseRegister =this.getSharedPreferences("Vitesse", 0);
        String vitesseRegisterValue = vitesseRegister.getString("vitesse", null);
        if(vitesseRegisterValue == null){
            editor = vitesseRegister.edit();
            editor.putString("vitesse", "FAIBLE");
            editor.apply();
        }
        SharedPreferences accelerationRegister = this.getSharedPreferences("Acceleration", 0);
        String accelerationRegisterValue = accelerationRegister.getString("acceleration", null);
        if(accelerationRegisterValue == null){
            editor = accelerationRegister.edit();
            editor.putString("acceleration", "NULLE");
            editor.apply();
        }

    }

}
