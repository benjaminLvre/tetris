package com.polytech.stfu.jeu;

public class JeuClassique extends Jeu {
	private int score;
	
	private Object lockScore;
	
	public JeuClassique(){
		super();
		score = 0;
		
		lockScore = new Object();
	}
	
	protected boolean isFinish(){
		return !grille.topLineIsEmpty();
	}
	
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
