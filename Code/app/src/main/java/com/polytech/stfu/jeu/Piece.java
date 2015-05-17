package com.polytech.stfu.jeu;

/**
 * Classe represente une piece de Tetris
 * @author Stfu
 * @see Point
 *
 */
public class Piece {
	/**
	 * Les cases occupees par la piece
	 */
	private Point[] cases;
	/**
	 * Le centre de rotation pour la piece
	 */
	private Point centreRotation;
	/**
	 * Le type de la piece
	 */
	private TypePiece type;
	
	/**
	 * Cnstructeur a partir de la position et le type de la piece
	 * @param position Les points composants la piece
	 * @param t Le type de la piece
	 */
	private Piece(Point[] position, TypePiece t){
		cases = position;
		type = t;
	}
	
	/**
	 * Methode de creation de piece de type I
	 * @param pointInitial Le point de reference ou placer la piece
	 */
	protected static Piece createPieceI(Point pointInitial){
		Point[] cases = new Point[4];
		cases[0] = new Point(pointInitial.getX()-1, pointInitial.getY());
		cases[1] = new Point(pointInitial.getX(), pointInitial.getY());
		cases[2] = new Point(pointInitial.getX()+1, pointInitial.getY());
		cases[3] = new Point(pointInitial.getX()+2, pointInitial.getY());
		return new Piece(cases, TypePiece.I);
	}
	
	
	/**
	 * Methode de creation de piece de type S
	 * @param pointInitial Le point de reference ou placer la piece
	 */
	protected static Piece createPieceS(Point pointInitial){
		Point[] cases = new Point[4];
		cases[0] = new Point(pointInitial.getX()-1, pointInitial.getY()+1);
		cases[1] = new Point(pointInitial.getX(), pointInitial.getY());
		cases[2] = new Point(pointInitial.getX(), pointInitial.getY()+1);
		cases[3] = new Point(pointInitial.getX()+1, pointInitial.getY());
		return new Piece(cases, TypePiece.S);
	}
	
	
	/**
	 * Methode de creation de piece de type Z
	 * @param pointInitial Le point de reference ou placer la piece
	 */
	protected static Piece createPieceZ(Point pointInitial){
		Point[] cases = new Point[4];
		cases[0] = new Point(pointInitial.getX()-1, pointInitial.getY());
		cases[1] = new Point(pointInitial.getX(), pointInitial.getY());
		cases[2] = new Point(pointInitial.getX(), pointInitial.getY()+1);
		cases[3] = new Point(pointInitial.getX()+1, pointInitial.getY()+1);
		return new Piece(cases, TypePiece.Z);
	}
	
	
	/**
	 * Methode de creation de piece de type L
	 * @param pointInitial Le point de reference ou placer la piece
	 */
	protected static Piece createPieceL(Point pointInitial){
		Point[] cases = new Point[4];
		cases[0] = new Point(pointInitial.getX()-1, pointInitial.getY());
		cases[1] = new Point(pointInitial.getX(), pointInitial.getY());
		cases[2] = new Point(pointInitial.getX()+1, pointInitial.getY());
		cases[3] = new Point(pointInitial.getX()-1, pointInitial.getY()+1);
		return new Piece(cases, TypePiece.L);
	}
	
	/**
	 * Methode de creation de piece de type J
	 * @param pointInitial Le point de reference ou placer la piece
	 */
	protected static Piece createPieceJ(Point pointInitial){
		Point[] cases = new Point[4];
		cases[0] = new Point(pointInitial.getX()-1, pointInitial.getY());
		cases[1] = new Point(pointInitial.getX(), pointInitial.getY());
		cases[2] = new Point(pointInitial.getX()+1, pointInitial.getY());
		cases[3] = new Point(pointInitial.getX()+1, pointInitial.getY()+1);
		return new Piece(cases, TypePiece.J);
	}
	
	
	/**
	 * Methode de creation de piece de type O
	 * @param pointInitial Le point de reference ou placer la piece
	 */
	protected static Piece createPieceO(Point pointInitial){
		Point[] cases = new Point[4];
		cases[0] = new Point(pointInitial.getX()+1, pointInitial.getY());
		cases[1] = new Point(pointInitial.getX(), pointInitial.getY());
		cases[2] = new Point(pointInitial.getX()+1, pointInitial.getY()+1);
		cases[3] = new Point(pointInitial.getX(), pointInitial.getY()+1);
		return new Piece(cases, TypePiece.O);
	}
	
	
	/**
	 * Methode de creation de piece de type T
	 * @param pointInitial Le point de reference ou placer la piece
	 */
	protected static Piece createPieceT(Point pointInitial){
		Point[] cases = new Point[4];
		cases[0] = new Point(pointInitial.getX()-1, pointInitial.getY());
		cases[1] = new Point(pointInitial.getX(), pointInitial.getY());
		cases[2] = new Point(pointInitial.getX()+1, pointInitial.getY());
		cases[3] = new Point(pointInitial.getX(), pointInitial.getY()+1);
		return new Piece(cases, TypePiece.T);
	}
	
	protected Point[] getPosition(){
		return cases;
	}
	
	/**
	 * Methode retournant la position de la piece apres rotation
	 */
	protected Point[] getRotatePosition(){
		Point[] futurePos = new Point[4];
		for(int i=0; i<cases.length; i++){
			futurePos[i] = cases[i].getRotatePosition(centreRotation);
		}
		return futurePos;
	}
	
	/**
	 * Methode effactuant une rotation a la piece
	 */
	protected void rotate(){
		for(Point p : cases){
			p.setRotatePosition(centreRotation);
		}
	}
	
	/**
	 * Methode retournant la position de la piece apres rotation
	 * @param move La direction du mouvement
	 */
	protected Point[] getMovePosition(TypeMove move){
		Point[] futurePos = new Point[4];
		for(int i=0; i<cases.length; i++){
			futurePos[i] = cases[i].getMovePosition(move);
		}
		centreRotation.setMovePosition(move);
		return futurePos;
	}
	
	/**
	 * Methode effactuant le deplacement d'une piece
	 * @param move La direction du mouvement
	 */
	protected void move(TypeMove move){
		for(Point p : cases){
			p.setMovePosition(move);
		}
	}
	
	/**
	 * Methode retournant si la piece est sur une case
	 * @param p La case a tester
	 */
	protected boolean isOn(Point p){
		for(Point c : cases){
			if(p.equals(c)){
				return true;
			}
		}
		return false;
	}
	
	protected TypePiece getTypePiece(){
		return type;
	}
}
