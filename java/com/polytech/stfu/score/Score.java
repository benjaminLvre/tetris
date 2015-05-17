package com.polytech.stfu.score;

import android.content.Context;
import android.content.SharedPreferences;

import com.polytech.stfu.jeu.Mode;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Classe gerant la sauvegarde des scores
 * @author Stfu
 *
 */
public abstract class Score {
    /**
     * Fonction pour obtenir l'ensemble des meilleurs scores d'un mode
     * @param mode Le mode de jeu voulu
     * @return L'ensemble des 5 meilleurs scores
     */
    public static SortedSet<Couple> getHighScoreList(Mode mode, Context context){
        SortedSet<Couple> ret = new TreeSet<Couple>();

        SharedPreferences scores = context.getSharedPreferences("Scores", 0);
        for(int i = 1; i<6; i++){
            Couple tmp = new Couple(scores.getString(mode.getNom()+"pseudo"+i, null), scores.getInt(mode.getNom()+"score"+i, -1));
            if(tmp.getScore() == -1){
                break;
            }
            ret.add(tmp);
        }
        return ret;
    }

    /**
     * Fonction pour tester si un score est dans les 5 meileurs du mode voulu
     * @param mode Le mode de jeu voulu
     * @param score Le score a tester
     */
    public static boolean isHighScore(Mode mode, int score, Context context){
        SharedPreferences scores = context.getSharedPreferences("Scores", 0);
        return score > scores.getInt(mode.getNom()+"score"+5, -1);
    }

    /**
     * Fonction pour sauvegarder un couple (pseudo, score) dans le mode voulu.
     * L'ordre des meilleurs scores est assure
     * @param mode Le mode de jeu voulu
     * @param pseudo Le pseudo a sauvegarder
     * @param score Le score a sauvegarder
     */
    public static void save(Mode mode, String pseudo, int score, Context context){
        SharedPreferences scores = context.getSharedPreferences("Scores", 0);
        SharedPreferences.Editor editor = scores.edit();

        SortedSet<Couple> setScores = getHighScoreList(mode, context);
        setScores.add(new Couple(pseudo, score));

        int i = 0;
        for(Couple c : setScores){
            if(i == 5){
                break;
            }
            editor.putString(mode.getNom()+"pseudo"+i, c.getPseudo());
            editor.putInt(mode.getNom()+"score"+i, c.getScore());
            i++;
        }
        editor.commit();
    }
}
