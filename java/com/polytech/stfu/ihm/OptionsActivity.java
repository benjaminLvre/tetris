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
     * @param savedInstanceState    Etat de l'activit�
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_options);

        ViewDesign.changeOptions(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewDesign.changeOptions(this);
    }

    /**
     * Modifie la variable concernant le theme de l'application dans le class Jeu
     * @param view  Le radioButton s�lectionn�"
     */
    public void changeTheme(View view){
        //Toast.makeText(OptionsActivity.this, "Theme modifi�", Toast.LENGTH_SHORT).show();
        SharedPreferences scores = this.getSharedPreferences("Theme", 0);
        SharedPreferences.Editor editor = scores.edit();
        ScrollView scrollView = (ScrollView)findViewById(R.id.themeOptions);

        LinearLayout linearOptions1 = (LinearLayout)findViewById(R.id.linearOptions1);
        LinearLayout linearOptions2 = (LinearLayout)findViewById(R.id.linearOptions2);
        LinearLayout linearOptions3 = (LinearLayout)findViewById(R.id.linearOptions3);
        LinearLayout linearOptions4 = (LinearLayout)findViewById(R.id.linearOptions4);
        LinearLayout linearOptions5 = (LinearLayout)findViewById(R.id.linearOptions5);
        switch (view.getId()){
            case R.id.themePolytech :
                scrollView.setBackgroundResource(R.drawable.background_polytech);
                editor.putString("theme", "polytech");
                editor.apply();
                linearOptions1.setBackgroundResource(R.drawable.fond_polytech);
                linearOptions2.setBackgroundResource(R.drawable.fond_polytech);
                linearOptions3.setBackgroundResource(R.drawable.fond_polytech);
                linearOptions4.setBackgroundResource(R.drawable.fond_polytech);
                linearOptions5.setBackgroundResource(R.drawable.fond_polytech);
                break;
            case R.id.themeWalkingDead : scrollView.setBackgroundResource(R.drawable.background_wd);
                editor.putString("theme", "walking_dead");
                editor.apply();
                linearOptions1.setBackgroundResource(R.drawable.fond_wd);
                linearOptions2.setBackgroundResource(R.drawable.fond_wd);
                linearOptions3.setBackgroundResource(R.drawable.fond_wd);
                linearOptions4.setBackgroundResource(R.drawable.fond_wd);
                linearOptions5.setBackgroundResource(R.drawable.fond_wd);
                break;
            default:
                scrollView.setBackgroundResource(R.drawable.background_classique);
                editor.putString("theme", "classique");
                editor.apply();
                linearOptions1.setBackgroundResource(R.drawable.fond_classique);
                linearOptions2.setBackgroundResource(R.drawable.fond_classique);
                linearOptions3.setBackgroundResource(R.drawable.fond_classique);
                linearOptions4.setBackgroundResource(R.drawable.fond_classique);
                linearOptions5.setBackgroundResource(R.drawable.fond_classique);
                break;
        }

        // Jeu.THEME = view.getId();
    }
    /**
     * Modifie la variable concernant le mode de l'application dans le class Jeu
     * @param view  Le radioButton cliqu�
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
     * Modifie la variable concernant la vitesse de l'application dans le class Jeu
     * @param view  Le radioButton cliqu�
     */
    public void changeVitesse(View view){
        //Toast.makeText(OptionsActivity.this, "Vitesse modifi�e", Toast.LENGTH_SHORT).show();
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
     * Modifie la variable concernant l'acc�laration de l'application dans le class Jeu
     * @param view  Le radioButton cliqu�
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

    public void openMusic(View view){
        Intent musicApp = new Intent("android.intent.action.MUSIC_PLAYER");
        startActivity(musicApp);
    }

}
