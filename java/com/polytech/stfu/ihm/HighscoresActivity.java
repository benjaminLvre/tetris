package com.polytech.stfu.ihm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.polytech.stfu.jeu.Mode;
import com.polytech.stfu.score.Couple;

import java.util.SortedSet;

import static com.polytech.stfu.score.Score.getHighScoreList;

/**
 * Permet d'afficher l'activite Highscore sur l'ecran
 * @see com.polytech.stfu.score.Score
 * @see com.polytech.stfu.score.Couple
 */
public class HighscoresActivity extends Activity {

    /**
     * Mise en place des composants de l'interface lors de son ouverture
     * et application du theme
     * @param savedInstanceState    Etat de l'activite
     * @see ViewDesign#changeHighscore(Activity) (Activity)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_highscores);

        ViewDesign.changeHighscore(this);
    }
    /**
     * Permet d'afficher les scores.
     * @param view  Le bouton cliqué
     * @see com.polytech.stfu.score.Score#getHighScoreList(Mode, Context)
     * @see Couple#getPseudo() (String)
     * @see Couple#getScore()
     */
    public void displayHighscores(View view) {
        SortedSet<Couple> highscores;

        if(view.getId() == R.id.scoreClassique) {  highscores = getHighScoreList(Mode.CLASSIQUE,this);}
        else {highscores = getHighScoreList(Mode.CHRONO,this);}

        TextView textName1 = (TextView)findViewById(R.id.name1);
        TextView textName2 = (TextView)findViewById(R.id.name2);
        TextView textName3 = (TextView)findViewById(R.id.name3);
        TextView textName4 = (TextView)findViewById(R.id.name4);
        TextView textName5 = (TextView)findViewById(R.id.name5);
        TextView tabNames[] = {textName1,textName2,textName3,textName4,textName5};

        TextView textScore1 = (TextView)findViewById(R.id.score1);
        TextView textScore2 = (TextView)findViewById(R.id.score2);
        TextView textScore3 = (TextView)findViewById(R.id.score3);
        TextView textScore4 = (TextView)findViewById(R.id.score4);
        TextView textScore5 = (TextView)findViewById(R.id.score5);
        TextView tabScores[] = {textScore1,textScore2,textScore3,textScore4,textScore5};

        int i = 0;
        for(Couple c : highscores) {
            tabNames[i].setText(c.getPseudo());
            tabScores[i].setText(String.valueOf(c.getScore()));
            i++;
        }
        for(int j=i;j<5; j++){
            tabNames[j].setText("");
            tabScores[j].setText("");
        }

    }
}
