package com.polytech.stfu.ihm;


import android.app.AlertDialog;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.polytech.stfu.jeu.Jeu;


public class GameActivity extends Activity {

    private GameReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TetrisView tetrisView = new TetrisView(this);
        setContentView(tetrisView);

        receiver = new GameReceiver(tetrisView);

        Jeu.getJeu().startGame();
    }


    @Override
    protected void onResume() {
        super.onResume();
        //registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //unregisterReceiver(receiver);
    }



    // EVENTS

    /**
     * Permet au joueur de mettre le jeu en pause
     * @param keyCode   La touche enfoncée
     * @param event L'evenement effectué
     * @return  Boolean que l'action a été fait avec succés
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getKeyCode() == 4){
            this.showPauseDialog();
        }
        return super.onKeyDown(keyCode, event);
    }

    // DIALOG BOX
    public void showPauseDialog() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        adb.setTitle("Menu pause");

        //On modifie l'icône de l'AlertDialog
        adb.setIcon(android.R.drawable.ic_dialog_alert);

        adb.setPositiveButton("Reprendre", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                //Lorsque l'on cliquera sur le bouton reprendre on reprendra une partie
            }
        });
        adb.setNeutralButton("Rejouer", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Lorsque l'on cliquera sur le bouton rejouer on recommencera une partie
            }
        });
        adb.setNegativeButton("Retourner au menu", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Lorsque l'on cliquera sur Retourner au menu on retourna a la page du menu principal
                Intent maineMenuActivity = new Intent(GameActivity.this, mainMenuActivity.class);
                startActivity(maineMenuActivity);
            }
        });
        adb.show();
    }
    public void showScoresDialog(){
        LayoutInflater factory = LayoutInflater.from(this);
        final View alertDialogView = factory.inflate(R.layout.dialog_highscores, null);

        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        //On affecte la vue personnalis? que l'on a cr?e ? notre AlertDialog
        adb.setView(alertDialogView);
        adb.setTitle("Tableau des scores");

        //On modifie l'ic?ne de l'AlertDialog
        adb.setIcon(android.R.drawable.ic_dialog_alert);

        //On affecte un bouton "OK" ? notre AlertDialog et on lui affecte un ?v?nement
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                //Lorsque l'on cliquera sur le bouton "OK", on r?cup?re l'EditText correspondant ? notre vue personnalis?e (cad ? alertDialogView)
                //EditText et = (EditText)alertDialogView.findViewById(R.id.player_pseudo);
                //et.setEnabled(false);

            } });

        //On cr?e un bouton "Annuler" ? notre AlertDialog et on lui affecte un ?v?nement
        adb.setNegativeButton("Rejouer", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Lorsque l'on cliquera sur rejouer une partie est relanc?e

            } });
        adb.show();
    }
}
