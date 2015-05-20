package com.polytech.stfu.ihm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.ScrollView;

import com.polytech.stfu.jeu.Acceleration;
import com.polytech.stfu.jeu.Jeu;
import com.polytech.stfu.jeu.JeuChrono;
import com.polytech.stfu.jeu.JeuClassique;
import com.polytech.stfu.jeu.Vitesse;

/**
 * Permet d'afficher les options sur l'ecran
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
        SharedPreferences themeRegister = this.getSharedPreferences("Theme", 0);
        String themeRegisterValue = themeRegister.getString("theme", null);
        SharedPreferences.Editor editor;
        editor = themeRegister.edit();

        saveTheme(view.getId(),getApplicationContext());
        ScrollView scrollView = (ScrollView)findViewById(R.id.themeOptions);
        switch (view.getId()){
            case R.id.themePolytech :
                scrollView.setBackgroundResource(R.drawable.background_polytech);
                editor.putString("theme", "polytech");
                editor.apply();break;
            case R.id.themeWalkingDead : scrollView.setBackgroundResource(R.drawable.background_wd);
                editor.putString("theme", "walking_dead");
                editor.apply();break;
            default:
                scrollView.setBackgroundResource(R.drawable.background_classique);
                editor.putString("theme", "classique");
                editor.apply();break;
        }

        // Jeu.THEME = view.getId();
    }
    /**
     * Modifie la variable concernant le mode de l'application dans le class Jeu
     * @param view  Le radioButton cliqu�
     */
    public void changeMode(View view){
        //Toast.makeText(OptionsActivity.this, "Mode modifi�", Toast.LENGTH_SHORT).show();
        saveMode(view.getId(), getApplicationContext());
        if(view.getId() == R.id.modeClassique) new JeuClassique(this);
        else new JeuChrono(this);
    }

    /**
     * Modifie la variable concernant la vitesse de l'application dans le class Jeu
     * @param view  Le radioButton cliqu�
     */
    public void changeVitesse(View view){
        //Toast.makeText(OptionsActivity.this, "Vitesse modifi�e", Toast.LENGTH_SHORT).show();
        saveVitesse(view.getId(), getApplicationContext());
        switch (view.getId()){
            case R.id.vit1 : Jeu.getJeu().setVitesse(Vitesse.FAIBLE); break;
            case R.id.vit2 : Jeu.getJeu().setVitesse(Vitesse.NORMALE); break;
            case R.id.vit3 : Jeu.getJeu().setVitesse(Vitesse.ELEVEE); break;
        }
    }

    /**
     * Modifie la variable concernant l'acc�laration de l'application dans le class Jeu
     * @param view  Le radioButton cliqu�
     */
    public void changeAcceleration(View view){
        //Toast.makeText(OptionsActivity.this, "Acceleration modifi�e", Toast.LENGTH_SHORT).show();
        saveAcceleration(view.getId(), getApplicationContext());
        switch (view.getId()){
            case R.id.accel0 : Jeu.getJeu().setAcceleration(Acceleration.NULLE); break;
            case R.id.accel1 : Jeu.getJeu().setAcceleration(Acceleration.MODEREE); break;
            case R.id.accel2 : Jeu.getJeu().setAcceleration(Acceleration.FORTE); break;
        }
    }

    public void openMusic(View view){
        Intent musicApp = new Intent("android.intent.action.MUSIC_PLAYER");
        startActivity(musicApp);
    }


    public static void saveTheme(int theme, Context context){
        SharedPreferences scores = context.getSharedPreferences("Theme", 0);
        SharedPreferences.Editor editor = scores.edit();
        switch (theme){
            case R.id.themeClassique : editor.putString("theme", "classique");break;
            case R.id.themePolytech : editor.putString("theme", "polytech");break;
            case R.id.themeWalkingDead : editor.putString("theme", "walking_dead");break;
        }
        editor.apply();
    }
    public static void saveMode(int mode, Context context){
        SharedPreferences scores = context.getSharedPreferences("Mode", 0);
        SharedPreferences.Editor editor = scores.edit();
        switch (mode){
            case R.id.modeClassique : editor.putString("mode", "classique");break;
            case R.id.modeCLM : editor.putString("mode", "contre-la-montre");break;
        }
        editor.apply();
    }
    public static void saveVitesse(int vitesse, Context context){
        SharedPreferences scores = context.getSharedPreferences("Vitesse", 0);
        SharedPreferences.Editor editor = scores.edit();
        switch (vitesse){
            case R.id.vit1 : editor.putString("vitesse", "FAIBLE");break;
            case R.id.vit2 : editor.putString("vitesse", "NORMALE");break;
            case R.id.vit3 : editor.putString("vitesse", "ELEVEE");break;
        }
        editor.apply();
    }
    public static void saveAcceleration(int acceleration, Context context){
        SharedPreferences scores = context.getSharedPreferences("Acceleration", 0);
        SharedPreferences.Editor editor = scores.edit();
        switch (acceleration){
            case R.id.accel0 : editor.putString("acceleration", "NULLE");break;
            case R.id.accel1 : editor.putString("acceleration", "MODEREE");break;
            case R.id.accel2 : editor.putString("acceleration", "FORTE");break;
        }
        editor.apply();
    }

}
