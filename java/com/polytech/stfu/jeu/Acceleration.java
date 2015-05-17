package com.polytech.stfu.jeu;

/**
 * Enumeration des options d'acceleration du jeu
 * @author Stfu
 *
 */
public enum Acceleration {
	NULLE(0),
	MODEREE(100),
	FORTE(200);
	
	private int val;
	
	private Acceleration(int v){
		val = v;
	}
	
	public int getVal(){
		return val;
	}
}
