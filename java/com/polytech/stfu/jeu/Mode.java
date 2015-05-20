package com.polytech.stfu.jeu;

/**
 * Enumeration des modes de jeu
 */
public enum Mode {
	CLASSIQUE("classique"),
	CHRONO("chrono");
	
	private String nom;
	
	private Mode(String v){
		nom = v;
	}
	
	public String getNom(){
		return nom;
	}
}
