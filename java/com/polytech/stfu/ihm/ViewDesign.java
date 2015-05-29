package com.polytech.stfu.ihm;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.app.Activity;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.polytech.stfu.jeu.Acceleration;
import com.polytech.stfu.jeu.Jeu;
import com.polytech.stfu.jeu.JeuChrono;
import com.polytech.stfu.jeu.JeuClassique;
import com.polytech.stfu.jeu.Mode;
import com.polytech.stfu.jeu.Vitesse;
import com.polytech.stfu.score.Couple;

import java.util.SortedSet;

import static com.polytech.stfu.score.Score.getHighScoreList;

/**
 * Classe permettant de gerer le design des differentes activites selon le theme choisi
 */
public class ViewDesign {
    /**
     * Permet de mettre en place le design du menu principal
     * @param pActivity L'activite a modifiee
     */
    public static void changeMain(Activity pActivity){

        LinearLayout layout = (LinearLayout)pActivity.findViewById(R.id.linearMain);
        Button bJouer = (Button)pActivity.findViewById(R.id.jouer);
        Button bInstructions = (Button)pActivity.findViewById(R.id.instructions);
        Button bOptions = (Button)pActivity.findViewById(R.id.options);
        Button bScores = (Button)pActivity.findViewById(R.id.highscores);
        Button bAbout = (Button)pActivity.findViewById(R.id.babout);

        SharedPreferences themeRegister = pActivity.getSharedPreferences("Theme", 0);
        String themeRegisterValue = themeRegister.getString("theme", null);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.bottomMargin = 10;

        switch (themeRegisterValue){
                case "polytech":
                    layout.setBackgroundResource(R.drawable.background_polytech);

                    bJouer.setBackgroundResource(R.drawable.btn_polytech);
                    bJouer.setLayoutParams(params);
                    bJouer.setTextColor(Color.BLACK);
                    bJouer.setTextSize(24.0f);
                    bJouer.setTypeface(null, Typeface.NORMAL);
                    bJouer.setPadding(10, 0, 0, 0);

                    bInstructions.setBackgroundResource(R.drawable.btn_polytech);
                    bInstructions.setLayoutParams(params);
                    bInstructions.setTextColor(Color.BLACK);
                    bInstructions.setTextSize(20.0f);
                    bInstructions.setTypeface(null, Typeface.NORMAL);
                    bInstructions.setPadding(40, 0, 0, 0);

                    bOptions.setBackgroundResource(R.drawable.btn_polytech);
                    bOptions.setLayoutParams(params);
                    bOptions.setTextColor(Color.BLACK);
                    bOptions.setTextSize(24.0f);
                    bOptions.setTypeface(null, Typeface.NORMAL);
                    bOptions.setPadding(20, 0, 0, 0);

                    bScores.setBackgroundResource(R.drawable.btn_polytech);
                    bScores.setLayoutParams(params);
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

                    bJouer.setLayoutParams(params);
                    bJouer.setBackgroundResource(R.drawable.btn_wd);
                    bJouer.setTextColor(Color.BLACK);
                    bJouer.setTextSize(30.0f);
                    bJouer.setTypeface(null, Typeface.BOLD);
                    bJouer.setPadding(0, 0, 0, 0);

                    bInstructions.setBackgroundResource(R.drawable.btn_wd);
                    bInstructions.setLayoutParams(params);
                    bInstructions.setTextColor(Color.BLACK);
                    bInstructions.setTextSize(30.0f);
                    bInstructions.setTypeface(null, Typeface.BOLD);
                    bInstructions.setPadding(0, 0, 0, 0);

                    bOptions.setBackgroundResource(R.drawable.btn_wd);
                    bOptions.setLayoutParams(params);
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

                    bJouer.setBackgroundResource(R.drawable.btn_classique);
                    bJouer.setLayoutParams(params);
                    bJouer.setTextColor(Color.WHITE);
                    bJouer.setTextSize(24.0f);
                    bJouer.setTypeface(null, Typeface.NORMAL);
                    bJouer.setPadding(0, 0, 0, 0);

                    bInstructions.setBackgroundResource(R.drawable.btn_classique);
                    bInstructions.setLayoutParams(params);
                    bInstructions.setTextColor(Color.WHITE);
                    bInstructions.setTextSize(24.0f);
                    bInstructions.setTypeface(null, Typeface.NORMAL);
                    bInstructions.setPadding(0, 0, 0, 0);

                    bOptions.setBackgroundResource(R.drawable.btn_classique);
                    bOptions.setLayoutParams(params);
                    bOptions.setTextColor(Color.WHITE);
                    bOptions.setTextSize(24.0f);
                    bOptions.setTypeface(null, Typeface.NORMAL);
                    bOptions.setPadding(0, 0, 0, 0);

                    bScores.setBackgroundResource(R.drawable.btn_classique);
                    bScores.setLayoutParams(params);
                    bScores.setTextColor(Color.WHITE);
                    bScores.setTextSize(24.0f);
                    bScores.setTypeface(null, Typeface.NORMAL);
                    bScores.setPadding(0,0,0,0);

                    bAbout.setTextColor(Color.WHITE);
                    bAbout.setBackgroundResource(R.drawable.btn_about);
                    bAbout.setTypeface(null, Typeface.NORMAL);
            }
    }
    /**
     * Permet de mettre en place le design de la page des options
     * @param pActivity L'activite a modifiee
     */
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

        LinearLayout linearOptions1 = (LinearLayout)pActivity.findViewById(R.id.linearOptions1);
        LinearLayout linearOptions2 = (LinearLayout)pActivity.findViewById(R.id.linearOptions2);
        LinearLayout linearOptions3 = (LinearLayout)pActivity.findViewById(R.id.linearOptions3);
        LinearLayout linearOptions4 = (LinearLayout)pActivity.findViewById(R.id.linearOptions4);
        LinearLayout linearOptions5 = (LinearLayout)pActivity.findViewById(R.id.linearOptions5);

        SharedPreferences themeRegister = pActivity.getSharedPreferences("Theme", 0);
        String themeRegisterValue = themeRegister.getString("theme", null);
        switch (themeRegisterValue){
            case "classique":
                themeC.setChecked(true);
                scrollViewOption.setBackgroundResource(R.drawable.background_classique);
                linearOptions1.setBackgroundResource(R.drawable.fond_classique);
                linearOptions2.setBackgroundResource(R.drawable.fond_classique);
                linearOptions3.setBackgroundResource(R.drawable.fond_classique);
                linearOptions4.setBackgroundResource(R.drawable.fond_classique);
                linearOptions5.setBackgroundResource(R.drawable.fond_classique);
                break;
            case "polytech":
                themeP.setChecked(true);
                scrollViewOption.setBackgroundResource(R.drawable.background_polytech);
                linearOptions1.setBackgroundResource(R.drawable.fond_polytech);
                linearOptions2.setBackgroundResource(R.drawable.fond_polytech);
                linearOptions3.setBackgroundResource(R.drawable.fond_polytech);
                linearOptions4.setBackgroundResource(R.drawable.fond_polytech);
                linearOptions5.setBackgroundResource(R.drawable.fond_polytech);
                break;
            case "walking_dead":
                themeWD.setChecked(true);
                scrollViewOption.setBackgroundResource(R.drawable.background_wd);
                linearOptions1.setBackgroundResource(R.drawable.fond_wd);
                linearOptions2.setBackgroundResource(R.drawable.fond_wd);
                linearOptions3.setBackgroundResource(R.drawable.fond_wd);
                linearOptions4.setBackgroundResource(R.drawable.fond_wd);
                linearOptions5.setBackgroundResource(R.drawable.fond_wd);
                break;
        }

        SharedPreferences modeRegister = pActivity.getSharedPreferences("Mode", 0);
        String modeRegisterValue = modeRegister.getString("mode", null);
        switch (modeRegisterValue){
            case "classique": modeClassique.setChecked(true);break;
            case "contre-la-montre": modeCLM.setChecked(true);break;
        }
        SharedPreferences vitesseRegister = pActivity.getSharedPreferences("Vitesse", 0);
        String vitesseRegisterValue = vitesseRegister.getString("vitesse", null);
        switch (vitesseRegisterValue){
            case "FAIBLE": vit1.setChecked(true);break;
            case "NORMALE": vit2.setChecked(true);break;
            case "ELEVEE": vit3.setChecked(true);break;
        }
        SharedPreferences accelerationRegister =pActivity.getSharedPreferences("Acceleration", 0);
        String accelerationRegisterValue = accelerationRegister.getString("acceleration", null);

        switch (accelerationRegisterValue){
            case "NULLE": accel0.setChecked(true);break;
            case "MODEREE": accel1.setChecked(true);break;
            case "FORTE": accel2.setChecked(true);break;
        }


    }
    /**
     * Permet de mettre en place le design de la page des instructions
     * @param pActivity L'activite a modifiee
     */
    public static void changeInstruction(Activity pActivity){
        ScrollView scrollViewInstructions = (ScrollView)pActivity.findViewById(R.id.scrollIntruction);

        LinearLayout linearInstructions1 = (LinearLayout)pActivity.findViewById(R.id.linearInstructions1);
        LinearLayout linearInstructions2 = (LinearLayout)pActivity.findViewById(R.id.linearInstructions2);
        LinearLayout linearInstructions3 = (LinearLayout)pActivity.findViewById(R.id.linearInstructions3);

        SharedPreferences themeRegister = pActivity.getSharedPreferences("Theme", 0);
        String themeRegisterValue = themeRegister.getString("theme", null);

        switch (themeRegisterValue){
            case "classique":
                scrollViewInstructions.setBackgroundResource(R.drawable.background_classique);
                linearInstructions1.setBackgroundResource(R.drawable.fond_classique);
                linearInstructions2.setBackgroundResource(R.drawable.fond_classique);
                linearInstructions3.setBackgroundResource(R.drawable.fond_classique);
                break;
            case "polytech":
                scrollViewInstructions.setBackgroundResource(R.drawable.background_polytech);
                linearInstructions1.setBackgroundResource(R.drawable.fond_polytech);
                linearInstructions2.setBackgroundResource(R.drawable.fond_polytech);
                linearInstructions3.setBackgroundResource(R.drawable.fond_polytech);
                break;
            case "walking_dead":
                scrollViewInstructions.setBackgroundResource(R.drawable.background_wd);
                linearInstructions1.setBackgroundResource(R.drawable.fond_wd);
                linearInstructions2.setBackgroundResource(R.drawable.fond_wd);
                linearInstructions3.setBackgroundResource(R.drawable.fond_wd);
                break;
        }

    }
    /**
     * Permet de mettre en place le design de la page about
     * @param pActivity L'activite a modifiee
     */
    public static void changeAbout(Activity pActivity){
        LinearLayout linearAbout = (LinearLayout)pActivity.findViewById(R.id.linearAbout);

        SharedPreferences themeRegister = pActivity.getSharedPreferences("Theme", 0);
        String themeRegisterValue = themeRegister.getString("theme", null);

        switch (themeRegisterValue){
            case "classique": linearAbout.setBackgroundResource(R.drawable.background_classique);break;
            case "polytech": linearAbout.setBackgroundResource(R.drawable.background_polytech);break;
            case "walking_dead": linearAbout.setBackgroundResource(R.drawable.background_wd);break;
        }

    }
    /**
     * Permet de mettre en place le design de la page listant les meilleurs scores
     * @param pActivity L'activite a modifiee
     */
    public static void changeHighscore(Activity pActivity){

        ScrollView scrollViewHighscore = (ScrollView)pActivity.findViewById(R.id.scrollHighscore);
        Button bClassique = (Button)pActivity.findViewById(R.id.scoreClassique);
        Button bChrono = (Button)pActivity.findViewById(R.id.scoreChrono);
        TableLayout tScore = (TableLayout) pActivity.findViewById(R.id.tableScore);

        SharedPreferences themeRegister = pActivity.getSharedPreferences("Theme", 0);
        String themeRegisterValue = themeRegister.getString("theme", null);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.bottomMargin = 10;
        params.weight = 1;

        switch (themeRegisterValue){
            case "classique":
                scrollViewHighscore.setBackgroundResource(R.drawable.background_classique);

                bClassique.setBackgroundResource(R.drawable.btn_classique);
                bClassique.setLayoutParams(params);
                bClassique.setTextColor(Color.WHITE);
                bClassique.setTextSize(24.0f);
                bClassique.setTypeface(null, Typeface.NORMAL);
                bClassique.setPadding(0, 0, 0, 0);

                bChrono.setBackgroundResource(R.drawable.btn_classique);
                bChrono.setLayoutParams(params);
                bChrono.setTextColor(Color.WHITE);
                bChrono.setTextSize(24.0f);
                bChrono.setTypeface(null, Typeface.NORMAL);
                bChrono.setPadding(0,0,0,0);

                tScore.setBackgroundResource(R.drawable.fond_classique);
                break;
            case "polytech":
                scrollViewHighscore.setBackgroundResource(R.drawable.background_polytech);

                bClassique.setBackgroundResource(R.drawable.btn_polytech2);
                bClassique.setLayoutParams(params);
                bClassique.setTextColor(Color.BLACK);
                bClassique.setTextSize(24.0f);
                bClassique.setTypeface(null, Typeface.NORMAL);
                bClassique.setPadding(0, 0, 0, 0);

                bChrono.setBackgroundResource(R.drawable.btn_polytech2);
                bChrono.setLayoutParams(params);
                bChrono.setTextColor(Color.BLACK);
                bChrono.setTextSize(24.0f);
                bChrono.setTypeface(null, Typeface.NORMAL);
                bChrono.setPadding(0,0,0,0);

                tScore.setBackgroundResource(R.drawable.fond_polytech);
                break;
            case "walking_dead":
                scrollViewHighscore.setBackgroundResource(R.drawable.background_wd);
                bClassique.setBackgroundResource(R.drawable.btn_wd);
                bClassique.setLayoutParams(params);
                bClassique.setTextColor(Color.BLACK);
                bClassique.setTextSize(24.0f);
                bClassique.setTypeface(null, Typeface.BOLD);
                bClassique.setPadding(0, 0, 0, 0);

                bChrono.setBackgroundResource(R.drawable.btn_wd);
                bChrono.setLayoutParams(params);
                bChrono.setTextColor(Color.BLACK);
                bChrono.setTextSize(24.0f);
                bChrono.setTypeface(null, Typeface.BOLD);
                bChrono.setPadding(0,0,0,0);

                tScore.setBackgroundResource(R.drawable.fond_wd);
                break;
        }

        SortedSet<Couple> highscores = getHighScoreList(Mode.CLASSIQUE,pActivity);


        TextView textName1 = (TextView)pActivity.findViewById(R.id.name1);
        TextView textName2 = (TextView)pActivity.findViewById(R.id.name2);
        TextView textName3 = (TextView)pActivity.findViewById(R.id.name3);
        TextView textName4 = (TextView)pActivity.findViewById(R.id.name4);
        TextView textName5 = (TextView)pActivity.findViewById(R.id.name5);
        TextView tabNames[] = {textName1,textName2,textName3,textName4,textName5};

        TextView textScore1 = (TextView)pActivity.findViewById(R.id.score1);
        TextView textScore2 = (TextView)pActivity.findViewById(R.id.score2);
        TextView textScore3 = (TextView)pActivity.findViewById(R.id.score3);
        TextView textScore4 = (TextView)pActivity.findViewById(R.id.score4);
        TextView textScore5 = (TextView)pActivity.findViewById(R.id.score5);
        TextView tabScores[] = {textScore1,textScore2,textScore3,textScore4,textScore5};

        int i = 0;
        for(Couple c : highscores) {
            tabNames[i].setText(c.getPseudo());
            tabScores[i].setText(String.valueOf(c.getScore()));
            i++;
        }
    }
}
