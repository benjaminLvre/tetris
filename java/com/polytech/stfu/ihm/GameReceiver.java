package com.polytech.stfu.ihm;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.polytech.stfu.jeu.Acceleration;
import com.polytech.stfu.jeu.Jeu;
import com.polytech.stfu.jeu.JeuChrono;
import com.polytech.stfu.jeu.JeuClassique;
import com.polytech.stfu.jeu.Vitesse;

/**
 * Classe gerant le controleur des messages qui traversent l'application
 * @see TetrisView
 * @see
 */
public class GameReceiver extends BroadcastReceiver {

    private static final String TAG = GameReceiver.class.getSimpleName();

    private TetrisView mView;
    private Activity mActivity;

    public GameReceiver(){}

    /**
     * Contructeur
     * @param pActivity Activité d'ou provient le receiver
     * @param view  La vue appartenant a l'activité
     */
    public GameReceiver(Activity pActivity, TetrisView view){
        this.mView = view;

        this.mActivity = pActivity;
    }

    /**
     * Fonction permettant de gerer les messages reçu :
     * GAME_STATE_CHANGE LE plateau a changé
     * GAME_END LA partie est finie
     * GAME_PAUSE La partie est mise en pause
     * GAME_UNPAUSE La partie reprend
     * GAME_RESTART L partie est recommencé
     * @param context   Le context du receiver
     * @param intent    Intent envoyé par l'événement
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getStringExtra("Action").equals(mView.getResources().getString(R.string.GAME_STATE_CHANGE))){
        	if(intent.getStringExtra("Source").equals("Jeu")){
                mActivity.runOnUiThread(mView.getmThread());
        	}
        }
        else if(intent.getStringExtra("Action").equals(mView.getResources().getString(R.string.GAME_END))){
        	if(intent.getStringExtra("Source").equals("Jeu")){
        		//Enlever les controles du Jeu
        		//Afficher le menu de fin

        	}
        	else if(intent.getStringExtra("Source").equals("Ihm")){
        		Jeu.getJeu().end();
        		//Enlever les controles du Jeu
        	}
        }
        else if(intent.getStringExtra("Action").equals(mView.getResources().getString(R.string.GAME_PAUSE))){
        	if(intent.getStringExtra("Source").equals("Ihm")){
        		//Enlever les controles du Jeu
                Jeu.getJeu().pause();
        	}
        }
        else if(intent.getStringExtra("Action").equals(mView.getResources().getString(R.string.GAME_UNPAUSE))){
        	if(intent.getStringExtra("Source").equals("Ihm")){
        		//Remettre les controles du Jeu
                Jeu.getJeu().restart();
        	}
        }
        else if(intent.getStringExtra("Action").equals(mView.getResources().getString(R.string.GAME_RESTART))){
            if(intent.getStringExtra("Source").equals("Ihm")){
                // Lancer une nouvelle partie
                SharedPreferences modeRegister = mActivity.getSharedPreferences("Mode", 0);
                String modeRegisterValue = modeRegister.getString("mode", "classique");

                SharedPreferences vitesseRegister = mActivity.getSharedPreferences("Vitesse", 0);
                String vitesseRegisterValue = vitesseRegister.getString("vitesse", "FAIBLE");

                SharedPreferences accelerationRegister = mActivity.getSharedPreferences("Acceleration", 0);
                String accelerationRegisterValue = accelerationRegister.getString("acceleration", "NULLE");

                Jeu jeu;

                if(modeRegisterValue.equals("classique"))
                   jeu = new JeuClassique(mActivity);
                else jeu = new JeuChrono(mActivity);

                switch (accelerationRegisterValue){
                    case "NULLE": jeu.setAcceleration(Acceleration.NULLE); break;
                    case "MODEREE": jeu.setAcceleration(Acceleration.MODEREE); break;
                    case "FORTE": jeu.setAcceleration(Acceleration.FORTE); break;
                }
                switch (vitesseRegisterValue){
                    case "FAIBLE": jeu.setVitesse(Vitesse.FAIBLE); break;
                    case "NORMALE": jeu.setVitesse(Vitesse.NORMALE); break;
                    case "ELEVEE": jeu.setVitesse(Vitesse.ELEVEE); break;
                }

                jeu.startGame();
            }
        }
    }
}