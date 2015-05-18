package com.polytech.stfu.ihm;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.polytech.stfu.jeu.Jeu;

public class GameReceiver extends BroadcastReceiver {

    private static final String TAG = GameReceiver.class.getSimpleName();

    private TetrisView mView;
    private Activity mActivity;
    private Jeu jeu;

    public GameReceiver(){}

    public GameReceiver(Activity pActivity, TetrisView view, Jeu jeu){
        this.mView = view;
        this.jeu = jeu;

        this.mActivity = pActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"onReceive");
        if(intent.getStringExtra("Action").equals(Jeu.GAME_STATE_CHANGE)){
            Log.d(TAG,"extra : Action");
        	if(intent.getStringExtra("Source").equals("Jeu")){
                Log.d(TAG,"extra : Source");
        		//Actualiser l'affichage du jeu
                //Toast.makeText(this.mActivity, "GAME_STATE_CHANGE ", Toast.LENGTH_SHORT).show();
                Jeu.getJeu().aff();
                mActivity.runOnUiThread(mView.getmThread());
        	}
        }
        else if(intent.getAction().equals(Jeu.GAME_END)){
        	if(intent.getStringExtra("Source").equals("Jeu")){
        		//Enlever les controles du Jeu
        		//Afficher le menu de fin

        	}
        	else if(intent.getStringExtra("Source").equals("Ihm")){
        		jeu.end();
        		//Enlever les controles du Jeu
        	}
        }
        else if(intent.getAction().equals(Jeu.NEW_SCORE)){
        	if(intent.getStringExtra("Source").equals("Jeu")){
        		//Afficher le nouveau score
        	}
        }
        else if(intent.getAction().equals(Jeu.GAME_PAUSE)){
        	if(intent.getStringExtra("Source").equals("Ihm")){
        		//Enlever les controles du Jeu
        		jeu.pause();
        	}
        }
        else if(intent.getAction().equals(Jeu.GAME_UNPAUSE)){
        	if(intent.getStringExtra("Source").equals("Ihm")){
        		//Remettre les controles du Jeu
        		jeu.restart();
        	}
        }
    }
}