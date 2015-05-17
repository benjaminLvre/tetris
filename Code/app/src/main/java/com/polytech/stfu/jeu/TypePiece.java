package com.polytech.stfu.jeu;

/**
 * Enumeration des types de pieces
 * @author Stfu
 *
 */
public enum TypePiece {
	L,
	J,
	T,
	S,
	Z,
	O,
	I,
	None;
	
	public String toString(){
		return name();
	}
}
