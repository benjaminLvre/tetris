package com.polytech.stfu.jeu;

public class JeuClassique extends Jeu {
	private int score;
	
	public JeuClassique(){
		super();
		score = 0;
	}
	
	public void startGame(){
		super.start();
	}
	
	public void pause(){
		
	}
	
	public void restart(){
		
	}
	
	public void end(){
		
	}
	
	protected boolean isFinish(){
		return !grille.topLineIsEmpty();
	}
	
	public int getScore(){
		return score;
	}
}
