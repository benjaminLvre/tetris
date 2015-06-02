package com.polytech.stfu.ihm;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.polytech.stfu.jeu.Acceleration;
import com.polytech.stfu.jeu.Jeu;
import com.polytech.stfu.jeu.JeuChrono;
import com.polytech.stfu.jeu.JeuClassique;
import com.polytech.stfu.jeu.Mode;
import com.polytech.stfu.jeu.Vitesse;

import static com.polytech.stfu.score.Score.isHighScore;

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
     * Fonction permettant de gerer les messages reçus :
     * GAME_STATE_CHANGE Le plateau a change
     * GAME_END La partie est finie
     * GAME_PAUSE La partie est mise en pause
     * GAME_UNPAUSE La partie reprend
     * GAME_RESTART La partie est recommencé
     * @param context   Le context du receiver
     * @param intent    Intent envoyé par l'événement
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getStringExtra("Action").equals(mView.getResources().getString(R.string.GAME_STATE_CHANGE))){
        	if(intent.getStringExtra("Source").equals("Jeu")){
                if(mView.getCreated())
                    mView.gThread().run();
        	}
        }
        else if(intent.getStringExtra("Action").equals(mView.getResources().getString(R.string.GAME_END))){
        	if(intent.getStringExtra("Source").equals("Jeu")){
                if(isHighScore(Jeu.getJeu().getMode(), Jeu.getJeu().getScore(), mActivity)){
                    GameActivity.showNewScoreDialog();
                }
                else{ GameActivity.showScoresDialog(); }
        	}
        	else if(intent.getStringExtra("Source").equals("Ihm")){
                if(isHighScore(Jeu.getJeu().getMode(), Jeu.getJeu().getScore(), mActivity)){
                    GameActivity.showNewScoreDialog();
                }
                else{ GameActivity.showScoresDialog(); }
        	}
            Jeu.getJeu().end();
        }
        else if(intent.getStringExtra("Action").equals(mView.getResources().getString(R.string.GAME_PAUSE))){
        	if(intent.getStringExtra("Source").equals("Ihm")){
                Jeu.getJeu().pause();
        	}
        }
        else if(intent.getStringExtra("Action").equals(mView.getResources().getString(R.string.GAME_UNPAUSE))){
        	if(intent.getStringExtra("Source").equals("Ihm")){
                Jeu.getJeu().restart();
        	}
        }
        else if(intent.getStringExtra("Action").equals(mView.getResources().getString(R.string.GAME_RESTART))){
            if(intent.getStringExtra("Source").equals("Ihm")){
                SharedPreferences modeRegister = mActivity.getSharedPreferences("Mode", 0);
                String modeRegisterValue = modeRegister.getString("mode", "classique");

                Jeu.getJeu().end();

                Jeu jeu;
                if(modeRegisterValue.equals("classique"))
                   jeu = new JeuClassique(mActivity);
                else jeu = new JeuChrono(mActivity);
                jeu.startGame();
            }
        }
    }
}