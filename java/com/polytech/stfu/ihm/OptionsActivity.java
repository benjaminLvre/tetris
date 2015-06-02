package com.polytech.stfu.ihm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;

import com.polytech.stfu.jeu.Acceleration;
import com.polytech.stfu.jeu.Jeu;
import com.polytech.stfu.jeu.JeuChrono;
import com.polytech.stfu.jeu.JeuClassique;
import com.polytech.stfu.jeu.Vitesse;

/**
 * Permet d'afficher les options sur l'ecran
 * @see ViewDesign
 */
public class OptionsActivity extends Activity {
    /**
     * Mise en place des composants de l'interface lors de son ouverture
     * et application du theme
     * @param savedInstanceState    Etat de l'activite
     * @see ViewDesign#changeOptions(Activity)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_options);
        ViewDesign.changeOptions(this);
    }
    /**
     * Modifie le fichier de sauvegarde contenant le theme et applique les changements a
     * la page lors du changement de theme
     * @param view  Le radioButton selectionne
     * @see ViewDesign#changeOptions(Activity)
     */
    public void changeTheme(View view){
        SharedPreferences scores = this.getSharedPreferences("Theme", 0);
        SharedPreferences.Editor editor = scores.edit();

        switch (view.getId()){
            case R.id.themePolytech :
                editor.putString("theme", "polytech");
                editor.apply();
                break;
            case R.id.themeWalkingDead :
                editor.putString("theme", "walking_dead");
                editor.apply();
                break;
            default:
                editor.putString("theme", "classique");
                editor.apply();
                break;
        }
        ViewDesign.changeOptions(this);
    }
    /**
     * Modifie le fichier de sauvegarde contenant le mode de jeu
     * @param view  Le radioButton clique
     */
    public void changeMode(View view){
        SharedPreferences scores = this.getSharedPreferences("Mode", 0);
        SharedPreferences.Editor editor = scores.edit();
        switch (view.getId()){
            case R.id.modeClassique : editor.putString("mode", "classique");break;
            case R.id.modeCLM : editor.putString("mode", "contre-la-montre");break;
        }
        editor.apply();
    }
    /**
     * Modifie le fichier de sauvegarde contenant la vitesse de jeu
     * @param view  Le radioButton clique
     */
    public void changeVitesse(View view){
        SharedPreferences scores = this.getSharedPreferences("Vitesse", 0);
        SharedPreferences.Editor editor = scores.edit();
        switch (view.getId()){
            case R.id.vit1 : editor.putString("vitesse", "FAIBLE");break;
            case R.id.vit2 : editor.putString("vitesse", "NORMALE");break;
            case R.id.vit3 : editor.putString("vitesse", "ELEVEE");break;
        }
        editor.apply();
    }
    /**
     * Modifie le fichier de sauvegarde contenant l'acceleration du jeu
     * @param view  Le radioButton clique
     */
    public void changeAcceleration(View view){
        SharedPreferences scores = this.getSharedPreferences("Acceleration", 0);
        SharedPreferences.Editor editor = scores.edit();
        switch (view.getId()){
            case R.id.accel0 : editor.putString("acceleration", "NULLE");break;
            case R.id.accel1 : editor.putString("acceleration", "MODEREE");break;
            case R.id.accel2 : editor.putString("acceleration", "FORTE");break;
        }
        editor.apply();
    }
    /**
     * Permet d'ouvrir l'application de musique
     * @param view Le bouton pour ouvrir l'application
     */
    public void openMusic(View view){
        Intent musicApp = new Intent("android.intent.action.MUSIC_PLAYER");
        startActivity(musicApp);
    }
}
