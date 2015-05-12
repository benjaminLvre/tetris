package com.polytech.ben.tetris;

import android.app.Activity;
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
    private RadioGroup vitGroup;
    private RadioGroup accelGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        // checkBox theme
        RadioButton theme = (RadioButton)findViewById(R.id.themePolytech);
        // checkBox mode
        RadioButton modeClassique = (RadioButton)findViewById(R.id.modeClassique);
        // checkBox vitesse
        RadioButton vit1 = (RadioButton)findViewById(R.id.vit1);
        // checkBox acceleration
        RadioButton accel0 = (RadioButton)findViewById(R.id.accel0);

        themeGroup = (RadioGroup)findViewById(R.id.themeGroup);
        modeGroup = (RadioGroup)findViewById(R.id.modeGroup);
        vitGroup = (RadioGroup)findViewById(R.id.vitGroup);
        accelGroup = (RadioGroup)findViewById(R.id.accelGroup);

        

    }

    public void changeTheme(View view){
        Toast.makeText(OptionsActivity.this, "Theme modifié", Toast.LENGTH_SHORT).show();
        //Jeu.theme = themeGroup.getCheckedRadioButtonId();
    }
    public void changeMode(View view){
        Toast.makeText(OptionsActivity.this, "Mode modifié" + String.valueOf(modeGroup.getCheckedRadioButtonId()), Toast.LENGTH_SHORT).show();
        //Jeu.mode = modeGroup.getCheckedRadioButtonId();
    }
    public void changeVitesse(View view){
        Toast.makeText(OptionsActivity.this, "Vitesse modifiée" + String.valueOf(vitGroup.getCheckedRadioButtonId()), Toast.LENGTH_SHORT).show();
        //Jeu.vitesse = vitGroup.getCheckedRadioButtonId();
    }
    public void changeAcceleration(View view){
        Toast.makeText(OptionsActivity.this, "Accelération modifiée" + String.valueOf(accelGroup.getCheckedRadioButtonId()), Toast.LENGTH_SHORT).show();
        //Jeu.accel = accelGroup.getCheckedRadioButtonId();
    }

    public void openMusic(View view){

    }
}
