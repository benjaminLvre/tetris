package com.polytech.stfu.ihm;

import android.app.AlertDialog;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.TextView;

import com.polytech.stfu.jeu.Jeu;
import com.polytech.stfu.jeu.Mode;
import com.polytech.stfu.score.Couple;
import com.polytech.stfu.score.Score;

import java.util.SortedSet;
import static com.polytech.stfu.score.Score.getHighScoreList;

/**
 * Classe permettant de mettre en place la page de la partie
 * @see GameReceiver
 * @see TetrisView
 */
public class GameActivity extends Activity {

    private GameReceiver receiver;
    private static GameActivity mActivity;

    /**
     * Mise en place des composants de l'interface lors de son ouverture.
     * Lancement du Thread de jeu ou ouverture de la boite de dialogue de pause si une
     * partie avait deja ete commmencee.
     *
     * @param savedInstanceState    Etat de l'activite
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TetrisView tetrisView = new TetrisView(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(tetrisView);

        receiver = new GameReceiver(this,tetrisView);
        mActivity = this;

        if(!Jeu.getJeu().isAlive()){
            Jeu.getJeu().startGame();
        }
        else{
            showPauseDialog();
        }

    }
    /**
     * Lancer lors de la reouverture de l'activite.
     * Le controleur des messages est reouvert.
     */
    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("TETRIS"));
    }

    /**
     * Lancé lors de la mise en pause de l'activité.
     * Le controleur des messages est fermé.
     */
    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
    /**
     * Clic sur le bouton de retour.
     * Si le jeu est en cours, met le Thread de jeu en pause et ouvre la boite
     * de dialogue de pause sinon le Thread de jeu est relance.
     */
    @Override
    public void onBackPressed() {
        if(Jeu.getJeu().isInPause()){
            sendGameUnpause();
        }
        else{
            sendGamePause();
            showPauseDialog();
        }
    }
    /**
     * Clic sur le bouton home
     * Si le Thread de jeu n'est pas en pause celui ci est mis en pause est la boite
     * de dialogue de pause est ouverte.
     */
    @Override
    protected void onUserLeaveHint() {
        if(!Jeu.getJeu().isInPause()) {
            sendGamePause();
            showPauseDialog();
        }
    }
    /**
     * Permet de lancer le message GAME_RESTART a travers l'application pour
     * relancer le jeu.
     *
     */
    private void sendGameRestart(){
        Intent intent = new Intent("TETRIS");
        intent.putExtra("Source", "Ihm");
        intent.putExtra("Action",  getResources().getString(R.string.GAME_RESTART));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
    /**
     * Permet de lancer le message GAME_PAUSE a travers l'application pour
     * mettre le jeu en pause.
     */
    protected void sendGamePause(){
        Intent intent = new Intent("TETRIS");
        intent.putExtra("Source", "Ihm");
        intent.putExtra("Action", getResources().getString(R.string.GAME_PAUSE));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
    /**
     * Permet de lancer le message GAME_UNPAUSE a travers l'application pour
     * reprendre le jeu.
     */
    protected void sendGameUnpause(){
        Intent intent = new Intent("TETRIS");
        intent.putExtra("Source", "Ihm");
        intent.putExtra("Action",  getResources().getString(R.string.GAME_UNPAUSE));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
    /**
     * Permet de lancer le message GAME_END a travers l'application pour stopper
     * le jeu.
     */
    protected void sendGameEnd(){
        Intent intent = new Intent("TETRIS");
        intent.putExtra("Source", "Ihm");
        intent.putExtra("Action",  getResources().getString(R.string.GAME_END));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
    /**
     * Permet de lancer une boite de dialogue qui met le jeu en pause.
     * @see GameActivity#sendGamePause()
     * @see GameActivity#sendGameUnpause()
     * @see GameActivity#sendGameRestart()
     * @see GameActivity#sendGameEnd()
     */
    public static void showPauseDialog() {
        AlertDialog.Builder adb = new AlertDialog.Builder(mActivity);

        adb.setTitle("Menu pause");
        adb.setIcon(android.R.drawable.ic_dialog_alert);

        adb.setPositiveButton("Reprendre", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mActivity.sendGameUnpause();
            }
        });
        adb.setNeutralButton("Rejouer", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mActivity.sendGameRestart();
            }
        });
        adb.setNegativeButton("Quitter", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mActivity.sendGameEnd();
            }
        });
        adb.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                mActivity.sendGameUnpause();
            }
        });
        adb.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    mActivity.sendGamePause();
                    return false;
                }
                else { return true; }
            }
        });
        adb.show();
    }
    /**
     * Permet de lancer une boite de dialogue qui affiche les scores lors de l'arret
     * du jeu.
     * @see Couple#getPseudo()
     * @see Couple#getScore()
     */
    public static void showScoresDialog(){
        LayoutInflater factory = LayoutInflater.from(mActivity);
        final View alertDialogView = factory.inflate(R.layout.dialog_highscores, null);

        AlertDialog.Builder adb = new AlertDialog.Builder(mActivity);

        adb.setView(alertDialogView);
        adb.setCancelable(false);
        adb.setTitle("Tableau des scores");
        adb.setIcon(android.R.drawable.ic_dialog_alert);

        SortedSet<Couple> highscores = getHighScoreList(Jeu.getJeu().getMode(), mActivity);

        TextView textName1 = (TextView)alertDialogView.findViewById(R.id.highscore_name1);
        TextView textName2 = (TextView)alertDialogView.findViewById(R.id.highscore_name2);
        TextView textName3 = (TextView)alertDialogView.findViewById(R.id.highscore_name3);
        TextView textName4 = (TextView)alertDialogView.findViewById(R.id.highscore_name4);
        TextView textName5 = (TextView)alertDialogView.findViewById(R.id.highscore_name5);
        TextView tabNames[] = {textName1,textName2,textName3,textName4,textName5};

        TextView textScore1 = (TextView)alertDialogView.findViewById(R.id.highscore_score1);
        TextView textScore2 = (TextView)alertDialogView.findViewById(R.id.highscore_score2);
        TextView textScore3 = (TextView)alertDialogView.findViewById(R.id.highscore_score3);
        TextView textScore4 = (TextView)alertDialogView.findViewById(R.id.highscore_score4);
        TextView textScore5 = (TextView)alertDialogView.findViewById(R.id.highscore_score5);
        TextView tabScores[] = {textScore1,textScore2,textScore3,textScore4,textScore5};

        int i = 0;
        for(Couple c : highscores) {
            tabNames[i].setText(c.getPseudo());
            tabScores[i].setText(String.valueOf(c.getScore()));
            i++;
        }

        //On cree un bouton "Rejouer" a notre AlertDialog et on lui affecte un evenement
        adb.setPositiveButton("Rejouer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mActivity.sendGameRestart();
            }
        });
        adb.setNeutralButton("Retourner au menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mActivity.finish();
            }
        });
        adb.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return false;
            }
        });
        adb.show();
    }
    /**
     * Permet de lancer une boite de dialogue qui permet au joueur d'enregistrer
     * son score.
     * @see Score#save(Mode, String, int, Context)
     * @see GameActivity#showScoresDialog()
     */
    public static void showNewScoreDialog(){
        LayoutInflater factory = LayoutInflater.from(mActivity);
        final View alertDialogView = factory.inflate(R.layout.dialog_new_score, null);

        AlertDialog.Builder adb = new AlertDialog.Builder(mActivity);

        adb.setView(alertDialogView);
        adb.setCancelable(false);
        adb.setTitle("Nouveau meilleur score");
        adb.setIcon(android.R.drawable.ic_dialog_alert);

        adb.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                EditText et = (EditText) alertDialogView.findViewById(R.id.player_pseudo);
                Score.save(Jeu.getJeu().getMode(), et.getText().toString(), (int)(Jeu.getJeu().getScore()*Jeu.getJeu().getDifficultCoeff() ), mActivity);
                showScoresDialog();
            }
        });
        // bloquer le retour arriere
        adb.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return false;
            }
        });

        final AlertDialog dialog = adb.create();
        dialog.show();

        /**
         *  Controle de la saisie :
         *  - Pas de pseudo vide sinon bouton de validation desactive
         *  - Pas de pseudo depassant 10 caracteres sinon bouton de validation desactive
         */
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        EditText et = (EditText) alertDialogView.findViewById(R.id.player_pseudo);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                } else {
                    if(s.length() <= 10) {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    }
                    else{
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    }
                }
            }
        });
    }
}
