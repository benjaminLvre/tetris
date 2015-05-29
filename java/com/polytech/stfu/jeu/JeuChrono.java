package com.polytech.stfu.jeu;

import android.content.Context;

/**
 * Jeu representant une partie contre la montre
 * @author Stfu
 */
public class JeuChrono extends Jeu {
	/**
	 * Temps limite qui defini la fin de la partie
	 */
	private int tempsLimite;
	/**
	 * Lock servant pour l'exclusion mutuel sur le tempsLimite
	 */
	private Object lockTempsLimite;
	/**
	 * Chronometre integre dans la partie
	 */
	private Chrono chrono;
	
	public JeuChrono(Context pContext){
		super(pContext, Mode.CHRONO);
		chrono = new Chrono();
		tempsLimite = 60;
		lockTempsLimite = new Object();
	}

	/**
	 * Methode pour savoir si le jeu est fini
	 * @return Si le jeu est fini
	 */
	public boolean isTimesUp(){
		synchronized (lockPause) {
			return tempsLimite <= chrono.getTemps();
		}
	}

	/**
	 * Methode pour lancer le jeu
	 */
	public void startGame(){
		super.startGame();
		chrono.start();
	}

	/**
	 * Methode mettant le jeu en pause
	 */
	public void pause(){
		synchronized (lockPause) {
			pause = true;
		}
		chrono.pause();
	}

	/**
	 * Methode sortant le jeu de la pause
	 */
	public void restart(){
		synchronized (lockPause) {
			pause = false;
		}
		chrono.restart();
	}

	/**
	 * Methode cloturant la partie
	 */
	public void end(){
		chrono.end();
	}

	/**
	 * Methode mettant a jour le temps limite de la partie suivant le nombre de lignes supprimees
	 * @param line Nombre de lignes supprimees
	 */
	protected void updateScore(int line){
		synchronized (lockPause) {
			switch(line){
				case 1:
					tempsLimite += 6;
					break;
				case 2:
					tempsLimite += 15;
					break;
				case 3:
					tempsLimite += 35;
					break;
				case 4:
					tempsLimite += 75;
					break;
				default:
			}
		}
	}

	/**
	 * Methode pour bloquer le jeu en pause si il est en pause
	 */
	protected void lockPause(){
		while(pause){
			chrono.pause();
			yield();
		}
	}
	
	public int getScore(){
		return chrono.getTemps();
	}

	public int getTempsRestant(){
		return tempsLimite - chrono.getTemps();
	}

	public int getTempsTotal(){
			return chrono.getTemps();
	}
}
