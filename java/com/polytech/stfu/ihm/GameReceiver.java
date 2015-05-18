package com.polytech.stfu.ihm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class GameReceiver extends BroadcastReceiver {

    private TetrisView mView;
    private Jeu jeu;

    public GameReceiver(TetrisView view, Jeu jeu){
        this.mView = view;
        this.jeu = jeu;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Jeu.GAME_STATE_CHANGE)){
        	if(intent.getStringExtra("Source").equals("Jeu")){
        		//Actualiser l'affichage du jeu
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
        		Jeu.pause();
        	}
        }
        else if(intent.getAction().equals(Jeu.GAME_UNPAUSE)){
        	if(intent.getStringExtra("Source").equals("Ihm")){
        		//Remettre les controles du Jeu
        		Jeu.restart();
        	}
        }
    }
} 