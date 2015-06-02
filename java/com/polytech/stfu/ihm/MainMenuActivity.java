package com.polytech.stfu.ihm;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.view.WindowManager;

import com.polytech.stfu.jeu.Jeu;
import com.polytech.stfu.jeu.JeuChrono;
import com.polytech.stfu.jeu.JeuClassique;


public class MainMenuActivity extends Activity {
    /**
     * Mise en place des composants de l'interface lors de son ouverture,
     * application du bon theme et initialisation des fichiers d'enregistrement
     * @param savedInstanceState    Etat de l'activite
     * @see MainMenuActivity#intialisationSharedFiles()
     * @see ViewDesign#changeMain(Activity)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        intialisationSharedFiles();
        ViewDesign.changeMain(this);

        if(Jeu.getJeu() != null){
            Intent gameActivity = new Intent(MainMenuActivity.this, GameActivity.class);
            startActivity(gameActivity);
        }
    }
    /**
     * Lance lors de la reouverture de l'activite.
     * Application du bon theme
     * @see ViewDesign#changeMain(Activity)
     */
    @Override
    protected void onResume() {
        super.onResume();
        ViewDesign.changeMain(this);
    }
    /**
     * Permet de lancer l'activite GameActivity qui gere une partie
     * @param view Le bouton a clique
     * @see GameActivity
     */
    public void launchGame(View view){
        SharedPreferences modeRegister =this.getSharedPreferences("Mode", 0);
        String modeRegisterValue = modeRegister.getString("mode", null);

        if(modeRegisterValue.equals("classique"))
            new JeuClassique(MainMenuActivity.this);
        else new JeuChrono(MainMenuActivity.this);

        Intent gameActivity = new Intent(MainMenuActivity.this, GameActivity.class);
        startActivity(gameActivity);
    }
    /**
     * Ouvre l'activite permettant de lire les instructions de jeu
     * @param view  Le bouton clique
     * @see InstructionsActivity
     */
    public void seeInstructions(View view){
        Intent instructionsActivity = new Intent(MainMenuActivity.this, InstructionsActivity.class);
        startActivity(instructionsActivity);
    }
    /**
     * Ouvre l'activite permettant de modifier les options de l'application
     * @param view  Le bouton clique
     * @see OptionsActivity
     */
    public void changeOptions(View view){
        Intent optionsActivity = new Intent(MainMenuActivity.this, OptionsActivity.class);
        startActivity(optionsActivity);
    }
    /**
     * Ouvre l'activite permettant de voir les meilleurs scores
     * @param view  Le bouton cliquer
     * @see HighscoresActivity
     */
    public void seeHighscores(View view){
        Intent highscoresActivity = new Intent(MainMenuActivity.this, HighscoresActivity.class);
        startActivity(highscoresActivity);
    }
    /**
     * Ouvre l'activite permettant d'avoir des informations sur l'application
     * @param view  Le bouton cliquer
     * @see AboutActivity
     */
    public void seeAbout(View view){
        Intent aboutActivity = new Intent(MainMenuActivity.this, AboutActivity.class);
        startActivity(aboutActivity);
    }
    /**
     * Permet d'initialiser tous les fichiers de sauvegarde de l'application.
     */
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
