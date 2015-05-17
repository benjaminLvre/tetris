package com.polytech.ben.tetris;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
     * @param savedInstanceState    Etat de l'activit�
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        // Radio theme
        RadioButton theme = (RadioButton)findViewById(R.id.themeClassique);
        // Radio mode
        RadioButton modeClassique = (RadioButton)findViewById(R.id.modeClassique);
        // Radio vitesse
        RadioButton vit1 = (RadioButton)findViewById(R.id.vit1);
        // Radio acceleration
        RadioButton accel0 = (RadioButton)findViewById(R.id.accel0);

        themeGroup = (RadioGroup)findViewById(R.id.themeGroup);
        modeGroup = (RadioGroup)findViewById(R.id.modeGroup);
        vitesseGroup = (RadioGroup)findViewById(R.id.vitGroup);
        accelGroup = (RadioGroup)findViewById(R.id.accelGroup);


        // Mise a jour des param�tre selon les choix pr�cedent
        /*
        if(theme != R.id.themeClassique && themeGroup.getCheckedRadioButtonId() == R.id.themeClassique){
            themeGroup.check(theme);
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
     * @param view  Le radioButton s�lectionn�
     */
    public void changeTheme(View view){
        Toast.makeText(OptionsActivity.this, "Theme modifie", Toast.LENGTH_SHORT).show();
        // Jeu.THEME = view.getId();
    }
    /**
     * Modifie la variable concernant le mode de l'application dans le class Jeu
     * @param view  Le radioButton cliqu�
     */
    public void changeMode(View view){
        Toast.makeText(OptionsActivity.this, "Mode modifie", Toast.LENGTH_SHORT).show();
        //Jeu.mode = view.getId();
    }

    /**
     * Modifie la variable concernant la vitesse de l'application dans le class Jeu
     * @param view  Le radioButton cliqu�
     */
    public void changeVitesse(View view){
        Toast.makeText(OptionsActivity.this, "Vitesse modifiee", Toast.LENGTH_SHORT).show();
        //Jeu.vitesse = view.getId();
    }

    /**
     * Modifie la variable concernant l'acc�laration de l'application dans le class Jeu
     * @param view  Le radioButton cliqu�
     */
    public void changeAcceleration(View view){
        Toast.makeText(OptionsActivity.this, "Acceleration modifiee", Toast.LENGTH_SHORT).show();
        //Jeu.accel = view.getId();
    }

    public void openMusic(View view){
        Intent musicApp = new Intent("android.intent.action.MUSIC_PLAYER");
        startActivity(musicApp);

    }
}
