package com.polytech.stfu.score;

import java.util.SortedSet;
import java.util.TreeSet;

public abstract class Score {
	public static SortedSet<Couple> getHighScoreList(Mode mode){
		SortedSet<Couple> ret = new TreeSet<Couple>();
		
		SharedPreferences scores = getSharedPreferences("Scores", 0);
		for(int i = 1; i<6; i++){
			Couple tmp = new Couple(score.getString(mode.getNom()+"pseudo"+i, null), score.getInt(mode.getNom()+"score"+i, -1));
			if(tmp.getDeux() == -1){
				break;
			}
			ret.add(tmp);
		}
		return ret;
	}

	public static boolean isHighScore(Mode mode, int score){
		SharedPreferences scores = getSharedPreferences("Scores", 0);
		return score > scores.getInt(mode.getNom()+"score"+5, -1);
	}
	
	public static void save(Mode mode, String pseudo, int score){
		SharedPreferences scores = getSharedPreferences("Scores", 0);
		SharedPreferences.Editor editor = scores.edit();

		SortedSet<Couple> setScores = getHighScoreList(mode);
		scores.add(new Couple(pseudo, score));
		
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
