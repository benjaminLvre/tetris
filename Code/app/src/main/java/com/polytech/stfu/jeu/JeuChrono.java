package com.polytech.stfu.jeu;

public class JeuChrono extends Jeu {
	private int tempsLimite;
	private Chrono chrono;
	
	public JeuChrono(){
		chrono = new Chrono();
		tempsLimite = 60;
	}
	
	public void pause(){
		synchronized (pause) {
			pause = true;
		}
		chrono.pause();
	}
	
	public void restart(){
		synchronized (pause) {
			pause = false;
		}
		chrono.restart();
	}
	
	public void end(){
		chrono.end();
	}
	
	protected void updateScore(int line){
		
	}
	
	protected void lockPause(){
		while(pause){
			chrono.pause();
			yield();
		}
	}
	
	public int getScore(){
		return chrono.getTemps();
	}
}
