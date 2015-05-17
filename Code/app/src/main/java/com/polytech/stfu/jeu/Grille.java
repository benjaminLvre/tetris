package com.polytech.stfu.jeu;

import java.util.ArrayList;
import java.util.List;
/**
 * Classe représentant la grille du Tetris
 * @author Stfu
 * @see Piece
 * @see Point
 * 
 */
public class Grille {
	/**
	 * Matrice contenant les valeurs représentant des cases de la grille du jeu
	 */
	private TypePiece[][] plateau;
	/**
	 * Piece courante
	 */
	private Piece piece;
	
	/**
	 * Contructeur par defaut pour definir une grille de tetris standard
	 */
	protected Grille(){
		plateau = new TypePiece[23][12];
	}
	
	/**
	 * Contructeur a partir de la largeur et la hauteur de la grille
	 * @param largeur Largeur de la grille
	 * @param hauteur Hauteur de la grille
	 */
	protected Grille(int largeur, int hauteur){
		plateau = new TypePiece[hauteur+1][largeur];
	}
	
	/**
	 * Methode testant une ligne est complete
	 * @param line Numero de la ligne a tester, la ligne 0 etant la ligne la plus haute de la grille
	 */
	private boolean isFullLine(int line){
		for(TypePiece col : plateau[line]){
			if(col == TypePiece.None){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Methode testant une ligne est vide
	 * @param line Numero de la ligne a tester, la ligne 0 etant la ligne la plus haute de la grille
	 */
	private boolean isEmptyLine(int line){
		for(TypePiece col : plateau[line]){
			if(col != TypePiece.None){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Methode retournant le numero de la ligne pleine la plus basse dans la grille 
	 * @return Le numero de la ligne pleine la plus basse de la grille
	 */
	private int searchFullLine(){
		for(int line = plateau.length-1; line>=0; line--){
			if(isFullLine(line)){
				return line;
			}
		}
		return -1;
	}
	
	/**
	 * Methode retournant la liste de tous les numeros de lignes qui sont pleines
	 * @return Les numeros de toutes les lignes pleines
	 */
	private List<Integer> getFullLines(){
		List<Integer> ret = new ArrayList<Integer>();
		for(int line = plateau.length-1; line>=0; line--){
			if(isFullLine(line)){
				ret.add(line);
			}
		}
		return ret;
	}
	
	/**
	 * Methode supprimant une ligne et rabaissant toutes les lignes supérieures
	 * @param line Numero de la ligne a tester, la ligne 0 etant la ligne la plus haute de la grille
	 */
	private void removeLine(int line){
		while(line > 0 && !isEmptyLine(line)){
			plateau[line] = plateau[line-1];
			line--;
		}
	}
	
	/**
	 * Methode testant une position de piece est libre
	 * @param testedPosition La position a tester
	 */
	private boolean isValidPosition(Point[] testedPosition){
		for(Point pTest : testedPosition){
			if(!isEmptyCase(pTest) && !piece.isOn(pTest)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Methode testant une case est libre
	 * @param p Le point a tester
	 */
	private boolean isEmptyCase(Point p){
		return plateau[p.getY()][p.getX()] == TypePiece.None;
	}

	/**
	 * Methode testant un mouvement de la piece est possible
	 * @param move Direction du mouvement voulu
	 */
	protected boolean canMovePiece(TypeMove move){
		return isValidPosition(piece.getMovePosition(move));
	}
	
	/**
	 * Methode testant la rotation est possible
	 */
	protected boolean canRotatePiece(){
		return isValidPosition(piece.getRotatePosition());
	}
	
	/**
	 * Methode deplacant la piece
	 * @param move Direction du mouvement voulu
	 */
	protected void movePiece(TypeMove move){
		removePieceToPlateau();
		piece.move(move);
		setPieceOnPlateau();
	}
	
	/**
	 * Methode faisant une rotation de la piece
	 */
	protected void rotatePiece(){
		removePieceToPlateau();
		piece.rotate();
		setPieceOnPlateau();
	}
	
	/**
	 * Methode attribuant une nouvelle piece courante
	 * @param p La nouvelle piece
	 */
	protected void setNewPiece(Piece p){
		piece = p;
		setPieceOnPlateau();
	}
	
	/**
	 * Methode effectant une type de piece a une case de la grille
	 * @param p La case concernee
	 * @param type Le type de la piece presente sur la case
	 */
	private void setTypeOnCase(Point p, TypePiece type){
		plateau[p.getY()][p.getX()] = type;
	}
	
	/**
	 * Methode supprimant la piece de la grille
	 */
	private void removePieceToPlateau(){
		for(Point p : piece.getPosition()){
			setTypeOnCase(p, TypePiece.None);
		}
	}
	
	/**
	 * Methode posant la piece sur la grille
	 */
	private void setPieceOnPlateau(){
		for(Point p : piece.getPosition()){
			setTypeOnCase(p, piece.getTypePiece());
		}
	}
}
