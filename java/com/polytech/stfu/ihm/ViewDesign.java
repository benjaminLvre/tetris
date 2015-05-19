package com.polytech.stfu.ihm;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.LinearLayout;
import android.app.Activity;
import android.widget.RadioButton;
import android.widget.ScrollView;

import com.polytech.stfu.jeu.Acceleration;
import com.polytech.stfu.jeu.Jeu;
import com.polytech.stfu.jeu.JeuChrono;
import com.polytech.stfu.jeu.JeuClassique;
import com.polytech.stfu.jeu.Vitesse;

public class ViewDesign {


    public static void changeMain(Activity pActivity){

        LinearLayout layout = (LinearLayout)pActivity.findViewById(R.id.linearMain);
        Button bJouer = (Button)pActivity.findViewById(R.id.jouer);
        Button bInstructions = (Button)pActivity.findViewById(R.id.instructions);
        Button bOptions = (Button)pActivity.findViewById(R.id.options);
        Button bScores = (Button)pActivity.findViewById(R.id.highscores);
        Button bAbout = (Button)pActivity.findViewById(R.id.babout);

        SharedPreferences themeRegister = pActivity.getSharedPreferences("Theme", 0);
        String themeRegisterValue = themeRegister.getString("theme", null);


        LinearLayout.LayoutParams params;

        switch (themeRegisterValue){
                case "polytech":
                    layout.setBackgroundResource(R.drawable.background_polytech);

                    //params = (LinearLayout.LayoutParams)layout.getLayoutParams();
                    //params.setMargins(0, 0, 0, 0);

                    bJouer.setBackgroundResource(R.drawable.btn_polytech);
                    //bJouer.setLayoutParams(params);
                    bJouer.setTextColor(Color.BLACK);
                    bJouer.setTextSize(24.0f);
                    bJouer.setTypeface(null, Typeface.NORMAL);
                    bJouer.setPadding(10,0,0,0);

                    bInstructions.setBackgroundResource(R.drawable.btn_polytech);
                    //bInstructions.setLayoutParams(params);
                    bInstructions.setTextColor(Color.BLACK);
                    bInstructions.setTextSize(20.0f);
                    bInstructions.setTypeface(null, Typeface.NORMAL);
                    bInstructions.setPadding(40,0,0,0);

                    bOptions.setBackgroundResource(R.drawable.btn_polytech);
                    //bOptions.setLayoutParams(params);
                    bOptions.setTextColor(Color.BLACK);
                    bOptions.setTextSize(24.0f);
                    bOptions.setTypeface(null, Typeface.NORMAL);
                    bOptions.setPadding(20,0,0,0);

                    bScores.setBackgroundResource(R.drawable.btn_polytech);
                    //bScores.setLayoutParams(params);
                    bScores.setTextColor(Color.BLACK);
                    bScores.setTextSize(24.0f);
                    bScores.setTypeface(null, Typeface.NORMAL);
                    bScores.setPadding(15,0,0,0);

                    bAbout.setTextColor(Color.parseColor("#02a9bd"));
                    bAbout.setBackgroundResource(R.drawable.btn_about_polytech);
                    bAbout.setTypeface(null, Typeface.NORMAL);
                break;

                case "walking_dead":
                    layout.setBackgroundResource(R.drawable.background_wd);
                    //params = (LinearLayout.LayoutParams)layout.getLayoutParams();
                    //params.setMargins(0, 0, 0, 10);

                    //bJouer.setLayoutParams(params);
                    bJouer.setBackgroundResource(R.drawable.btn_wd);
                    bJouer.setTextColor(Color.BLACK);
                    bJouer.setTextSize(30.0f);
                    bJouer.setTypeface(null, Typeface.BOLD);
                    bJouer.setPadding(0,0,0,0);

                    bInstructions.setBackgroundResource(R.drawable.btn_wd);
                    //bInstructions.setLayoutParams(params);
                    bInstructions.setTextColor(Color.BLACK);
                    bInstructions.setTextSize(30.0f);
                    bInstructions.setTypeface(null, Typeface.BOLD);
                    bInstructions.setPadding(0,0,0,0);

                    bOptions.setBackgroundResource(R.drawable.btn_wd);
                    //bOptions.setLayoutParams(params);
                    bOptions.setTextColor(Color.BLACK);
                    bOptions.setTextSize(30.0f);
                    bOptions.setTypeface(null, Typeface.BOLD);
                    bOptions.setPadding(0,0,0,0);

                    bScores.setBackgroundResource(R.drawable.btn_wd);
                    bScores.setTextColor(Color.BLACK);
                    bScores.setTextSize(30.0f);
                    bScores.setTypeface(null, Typeface.BOLD);
                    bScores.setPadding(0,0,0,0);

                    bAbout.setTextColor(Color.BLACK);
                    bAbout.setBackgroundResource(R.drawable.btn_about_wd);
                    bAbout.setTypeface(null, Typeface.BOLD);
                    break;
                default:
                    layout.setBackgroundResource(R.drawable.background_classique);

                    //params = (LinearLayout.LayoutParams)layout.getLayoutParams();
                    //params.setMargins(0, 0, 0, 0);

                    bJouer.setBackgroundResource(R.drawable.btn_classique);
                    //bJouer.setLayoutParams(params);
                    bJouer.setTextColor(Color.WHITE);
                    bJouer.setTextSize(24.0f);
                    bJouer.setTypeface(null, Typeface.NORMAL);
                    bJouer.setPadding(0,0,0,0);

                    bInstructions.setBackgroundResource(R.drawable.btn_classique);
                    //bInstructions.setLayoutParams(params);
                    bInstructions.setTextColor(Color.WHITE);
                    bInstructions.setTextSize(24.0f);
                    bInstructions.setTypeface(null, Typeface.NORMAL);
                    bInstructions.setPadding(0,0,0,0);

                    bOptions.setBackgroundResource(R.drawable.btn_classique);
                    //bOptions.setLayoutParams(params);
                    bOptions.setTextColor(Color.WHITE);
                    bOptions.setTextSize(24.0f);
                    bOptions.setTypeface(null, Typeface.NORMAL);
                    bOptions.setPadding(0,0,0,0);

                    bScores.setBackgroundResource(R.drawable.btn_classique);
                    //bScores.setLayoutParams(params);
                    bScores.setTextColor(Color.WHITE);
                    bScores.setTextSize(24.0f);
                    bScores.setTypeface(null, Typeface.NORMAL);
                    bScores.setPadding(0,0,0,0);

                    bAbout.setTextColor(Color.WHITE);
                    bAbout.setBackgroundResource(R.drawable.btn_about);
                    bAbout.setTypeface(null, Typeface.NORMAL);
                    break;
            }

    }
    public static void changeOptions(Activity pActivity){
        ScrollView scrollViewOption = (ScrollView)pActivity.findViewById(R.id.themeOptions);

        // Radio theme
        RadioButton themeC = (RadioButton)pActivity.findViewById(R.id.themeClassique);
        RadioButton themeP = (RadioButton)pActivity.findViewById(R.id.themePolytech);
        RadioButton themeWD = (RadioButton)pActivity.findViewById(R.id.themeWalkingDead);
        // Radio mode
        RadioButton modeClassique = (RadioButton)pActivity.findViewById(R.id.modeClassique);
        RadioButton modeCLM = (RadioButton)pActivity.findViewById(R.id.modeCLM);
        // Radio vitesse
        RadioButton vit1 = (RadioButton)pActivity.findViewById(R.id.vit1);
        RadioButton vit2 = (RadioButton)pActivity.findViewById(R.id.vit2);
        RadioButton vit3 = (RadioButton)pActivity.findViewById(R.id.vit3);
        // Radio acceleration
        RadioButton accel0 = (RadioButton)pActivity.findViewById(R.id.accel0);
        RadioButton accel1 = (RadioButton)pActivity.findViewById(R.id.accel1);
        RadioButton accel2 = (RadioButton)pActivity.findViewById(R.id.accel2);


        SharedPreferences themeRegister = pActivity.getSharedPreferences("Theme", 0);
        String themeRegisterValue = themeRegister.getString("theme", null);
        SharedPreferences.Editor editor;

        switch (themeRegisterValue){
            case "classique": themeC.setChecked(true); scrollViewOption.setBackgroundResource(R.drawable.background_classique);break;
            case "polytech": themeP.setChecked(true);break;
            case "walking_dead": themeWD.setChecked(true);scrollViewOption.setBackgroundResource(R.drawable.background_wd);break;
        }

        SharedPreferences modeRegister = pActivity.getSharedPreferences("Mode", 0);
        String modeRegisterValue = modeRegister.getString("mode", null);

        switch (modeRegisterValue){
            case "classique": modeClassique.setChecked(true);new JeuClassique(pActivity); break;
            case "contre-la-montre": modeCLM.setChecked(true);new JeuChrono(pActivity);break;
        }
        SharedPreferences vitesseRegister = pActivity.getSharedPreferences("Vitesse", 0);
        String vitesseRegisterValue = vitesseRegister.getString("vitesse", null);

        switch (vitesseRegisterValue){
            case "FAIBLE": vit1.setChecked(true);
                Jeu.getJeu().setVitesse(Vitesse.FAIBLE);break;
            case "NORMALE": vit2.setChecked(true);
                Jeu.getJeu().setVitesse(Vitesse.NORMALE);
                break;
            case "ELEVEE": vit3.setChecked(true);
                Jeu.getJeu().setVitesse(Vitesse.ELEVEE);
                break;
        }

        SharedPreferences accelerationRegister =pActivity.getSharedPreferences("Acceleration", 0);
        String accelerationRegisterValue = accelerationRegister.getString("acceleration", null);

        switch (accelerationRegisterValue){
            case "NULLE": accel0.setChecked(true);Jeu.getJeu().setAcceleration(Acceleration.NULLE); break;
            case "MODEREE": accel1.setChecked(true);Jeu.getJeu().setAcceleration(Acceleration.MODEREE);break;
            case "FORTE": accel2.setChecked(true);Jeu.getJeu().setAcceleration(Acceleration.FORTE);break;
        }


    }
}
