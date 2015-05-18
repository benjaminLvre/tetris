package com.polytech.stfu.ihm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Toast;

import com.polytech.stfu.jeu.Acceleration;
import com.polytech.stfu.jeu.Jeu;
import com.polytech.stfu.jeu.JeuChrono;
import com.polytech.stfu.jeu.JeuClassique;
import com.polytech.stfu.jeu.Vitesse;

public class OptionsActivity extends Activity {

    /**
     * Mise en place des composants de l'interface lors de son ouverture
     * @param savedInstanceState    Etat de l'activité
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        ScrollView scrollViewOption = (ScrollView)findViewById(R.id.themeOptions);

        // Radio theme
        RadioButton themeC = (RadioButton)findViewById(R.id.themeClassique);
        RadioButton themeP = (RadioButton)findViewById(R.id.themePolytech);
        RadioButton themeWD = (RadioButton)findViewById(R.id.themeWalkingDead);
        // Radio mode
        RadioButton modeClassique = (RadioButton)findViewById(R.id.modeClassique);
        RadioButton modeCLM = (RadioButton)findViewById(R.id.modeCLM);
        // Radio vitesse
        RadioButton vit1 = (RadioButton)findViewById(R.id.vit1);
        RadioButton vit2 = (RadioButton)findViewById(R.id.vit2);
        RadioButton vit3 = (RadioButton)findViewById(R.id.vit3);
        // Radio acceleration
        RadioButton accel0 = (RadioButton)findViewById(R.id.accel0);
        RadioButton accel1 = (RadioButton)findViewById(R.id.accel1);
        RadioButton accel2 = (RadioButton)findViewById(R.id.accel2);


        SharedPreferences themeRegister = getApplicationContext().getSharedPreferences("Theme", 0);
        String themeRegisterValue = themeRegister.getString("theme", null);
        SharedPreferences.Editor editor;
        if(themeRegisterValue == null){
            editor = themeRegister.edit();
            editor.putString("theme", "classique");
            editor.apply();
        }else{
            switch (themeRegisterValue){
                case "classique": themeC.setChecked(true); scrollViewOption.setBackgroundResource(R.drawable.background_cubes);break;
                case "polytech": themeP.setChecked(true);break;
                case "walking_dead": themeWD.setChecked(true);break;
            }
        }
        SharedPreferences modeRegister = getApplicationContext().getSharedPreferences("Mode", 0);
        String modeRegisterValue = modeRegister.getString("mode", null);
        if(modeRegisterValue == null){
            editor = modeRegister.edit();
            editor.putString("mode", "classique");
            editor.apply();
        }else{
            switch (modeRegisterValue){
                case "classique": modeClassique.setChecked(true);new JeuClassique(); break;
                case "contre-la-montre": modeCLM.setChecked(true);new JeuChrono();break;
            }
        }

        SharedPreferences vitesseRegister = getApplicationContext().getSharedPreferences("Vitesse", 0);
        String vitesseRegisterValue = vitesseRegister.getString("vitesse", null);
        if(vitesseRegisterValue == null){
            editor = vitesseRegister.edit();
            editor.putString("vitesse", "FAIBLE");
            editor.apply();
        }else{
            switch (vitesseRegisterValue){
                case "FAIBLE": vit1.setChecked(true);Jeu.getJeu().setVitesse(Vitesse.FAIBLE);break;
                case "NORMALE": vit2.setChecked(true);Jeu.getJeu().setVitesse(Vitesse.NORMALE);break;
                case "ELEVEE": vit3.setChecked(true);Jeu.getJeu().setVitesse(Vitesse.ELEVEE);break;
            }
        }
        SharedPreferences accelerationRegister = getApplicationContext().getSharedPreferences("Mode", 0);
        String accelerationRegisterValue = accelerationRegister.getString("mode", null);
        if(accelerationRegisterValue == null){
            editor = accelerationRegister.edit();
            editor.putString("acceleration", "NULLE");
            editor.apply();
        }else{
            switch (accelerationRegisterValue){
                case "NULLE": accel0.setChecked(true);Jeu.getJeu().setAcceleration(Acceleration.NULLE); break;
                case "MODEREE": accel1.setChecked(true);Jeu.getJeu().setAcceleration(Acceleration.MODEREE);break;
                case "FORTE": accel2.setChecked(true);Jeu.getJeu().setAcceleration(Acceleration.FORTE);break;
            }
        }

        Toast.makeText(OptionsActivity.this, "theme enregistré : " + themeRegisterValue, Toast.LENGTH_SHORT).show();

    }

    /**
     * Modifie la variable concernant le theme de l'application dans le class Jeu
     * @param view  Le radioButton sélectionné"
     */
    public void changeTheme(View view){
        Toast.makeText(OptionsActivity.this, "Theme modifié", Toast.LENGTH_SHORT).show();

        saveTheme(view.getId(),getApplicationContext());
        ScrollView scrollView = (ScrollView)findViewById(R.id.themeOptions);
        scrollView.setBackgroundResource(R.drawable.background_cubes);
        // Jeu.THEME = view.getId();
    }
    /**
     * Modifie la variable concernant le mode de l'application dans le class Jeu
     * @param view  Le radioButton cliqué
     */
    public void changeMode(View view){
        //Toast.makeText(OptionsActivity.this, "Mode modifié", Toast.LENGTH_SHORT).show();
        saveMode(view.getId(), getApplicationContext());
        if(view.getId() == R.id.modeClassique) new JeuClassique();
        else new JeuChrono();
    }

    /**
     * Modifie la variable concernant la vitesse de l'application dans le class Jeu
     * @param view  Le radioButton cliqué
     */
    public void changeVitesse(View view){
        //Toast.makeText(OptionsActivity.this, "Vitesse modifiée", Toast.LENGTH_SHORT).show();
        saveVitesse(view.getId(), getApplicationContext());
        switch (view.getId()){
            case R.id.vit1 : Jeu.getJeu().setVitesse(Vitesse.FAIBLE); break;
            case R.id.vit2 : Jeu.getJeu().setVitesse(Vitesse.NORMALE); break;
            case R.id.vit3 : Jeu.getJeu().setVitesse(Vitesse.ELEVEE); break;
        }
    }

    /**
     * Modifie la variable concernant l'accélaration de l'application dans le class Jeu
     * @param view  Le radioButton cliqué
     */
    public void changeAcceleration(View view){
        //Toast.makeText(OptionsActivity.this, "Acceleration modifiée", Toast.LENGTH_SHORT).show();
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
