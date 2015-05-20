package com.polytech.stfu.jeu;

import android.content.Context;

/**
 * Classe représentant une partie de type classique
 * @author Stfu
 */
public class JeuClassique extends Jeu {
	/**
	 * Score courant de la partie
	 */
	private int score;
	/**
	 * Lock servant pour l'exclusion mutuel sur le score
	 */
	private Object lockScore;

	
	public JeuClassique(Context pContext){
		super(pContext, Mode.CLASSIQUE);
		score = 0;
		
		lockScore = new Object();
	}

	/**
	 * Methode pour savoir si le jeu est fini
	 * @return Si le jeu est fini
	 */
	protected boolean isFinish(){
		return !grille.topLineIsEmpty();
	}

	/**
	 * Methode mettant a jour le score de la partie suivant le nombre de lignes supprimees
	 * @param line Nombre de lignes supprimees
	 */
	protected void updateScore(int line){
		synchronized (lockScore) {
			switch(line){
			case 1:
				score += 40;
				break;
			case 2:
				score += 100;
				break;
			case 3:
				score += 300;
				break;
			case 4:
				score += 1200;
				break;
				default:
			}
		}
	}
	
	public int getScore(){
		synchronized (lockScore) {
			return score;
		}
	}
}
