package com.polytech.stfu.jeu;

/**
 * Enumeration des options de vitesses du jeu
 * @author Stfu
 *
 */
public enum Vitesse{
	FAIBLE(125),
	NORMALE(100),
	ELEVEE(75);
	
	/**
	 * Pourcentage de la vitesse par rapport a celle normale (100)
	 */
	private int val;
	
	private Vitesse(int v){
		val = v;
	}
	
	public int getVal(){
		return val;
	}
	
	public int describeContents(){
		return 0;
	}
}