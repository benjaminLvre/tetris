package com.polytech.stfu.ihm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.polytech.stfu.jeu.Acceleration;
import com.polytech.stfu.jeu.Jeu;
import com.polytech.stfu.jeu.JeuChrono;
import com.polytech.stfu.jeu.JeuClassique;
import com.polytech.stfu.jeu.Vitesse;

public class OptionsActivity extends Activity {

    // private int theme = Jeu.theme;
    // private int vitesse = Jeu.vitesse;
    // private int accel = Jeu.accel;
    // private int mode = Jeu.mode;

    private RadioGroup themeGroup;
    private RadioGroup modeGroup;
    private RadioGroup vitesseGroup;
    private RadioGroup accelGroup;

    /**
     * Mise en place des composants de l'interface lors de son ouverture
     * @param savedInstanceState    Etat de l'activité
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

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

        themeGroup = (RadioGroup)findViewById(R.id.themeGroup);
        modeGroup = (RadioGroup)findViewById(R.id.modeGroup);
        vitesseGroup = (RadioGroup)findViewById(R.id.vitGroup);
        accelGroup = (RadioGroup)findViewById(R.id.accelGroup);


        // Mise a jour des paramètre selon les choix précedent
        /*
        if(theme != R.id.themeClassique && themeGroup.getCheckedRadioButtonId() == R.id.themeClassique){
            themeGroup.check(JEU.THEME);
        }
        if(mode != R.id.modeClassique && modeGroup.getCheckedRadioButtonId() == R.id.modeClassique){
            modeGroup.check(mode);
        }
        if(vitesse != R.id.vit1 && vitesseGroup.getCheckedRadioButtonId() == R.id.vit1){
            vitGroup.check(vitesse);
        }
        if(accel != R.id.accel0 && accelGroup.getCheckedRadioButtonId() == R.id.accel0){
            accelGroup.check(accel);
        }
         */

    }

    /**
     * Modifie la variable concernant le theme de l'application dans le class Jeu
     * @param view  Le radioButton sélectionné"
     */
    public void changeTheme(View view){
        Toast.makeText(OptionsActivity.this, "Theme modifié", Toast.LENGTH_SHORT).show();
        // Jeu.THEME = view.getId();
    }
    /**
     * Modifie la variable concernant le mode de l'application dans le class Jeu
     * @param view  Le radioButton cliqué
     */
    public void changeMode(View view){
        //Toast.makeText(OptionsActivity.this, "Mode modifié", Toast.LENGTH_SHORT).show();

        if(view.getId() == R.id.modeClassique) new JeuClassique();
        else new JeuChrono();
    }

    /**
     * Modifie la variable concernant la vitesse de l'application dans le class Jeu
     * @param view  Le radioButton cliqué
     */
    public void changeVitesse(View view){
        //Toast.makeText(OptionsActivity.this, "Vitesse modifiée", Toast.LENGTH_SHORT).show();
        switch (view.getId()){
            case R.id.vit1 : getJeu().setVitesse(Vitesse.FAIBLE); break;
            case R.id.vit2 : getJeu().setVitesse(Vitesse.NORMALE); break;
            case R.id.vit3 : getJeu().setVitesse(Vitesse.ELEVEE); break;
        }
    }

    /**
     * Modifie la variable concernant l'accélaration de l'application dans le class Jeu
     * @param view  Le radioButton cliqué
     */
    public void changeAcceleration(View view){
        //Toast.makeText(OptionsActivity.this, "Acceleration modifiée", Toast.LENGTH_SHORT).show();
        switch (view.getId()){
            case R.id.accel0 : getJeu().setAcceleration(Acceleration.NULLE); break;
            case R.id.accel1 : getJeu().setAcceleration(Acceleration.MODEREE); break;
            case R.id.accel2 : getJeu().setAcceleration(Acceleration.FORTE); break;
        }
    }

    public void openMusic(View view){
        Intent musicApp = new Intent("android.intent.action.MUSIC_PLAYER");
        startActivity(musicApp);
    }

}
