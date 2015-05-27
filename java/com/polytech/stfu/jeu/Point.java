package com.polytech.stfu.jeu;

/**
 * Classe representant une case de la grille
 *
 * Cette classe permet de gerer facilement les deplacements d'une case de la grille en determinant les futures positions apres un mouvement ou une rotation.
 *
 * @author Stfu
 */
public class Point {
	/**
	 * Coordonnee en absisse
	 */
	private int x;
	/**
	 * Coordonnee en ordonnee
	 */
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Methode retournant le point transforme par une rotation
	 * @param centreRotation Le centre de la rotation
	 */
	public Point getRotatePosition(Point centreRotation){
		int x = centreRotation.x-this.x;
		int y = centreRotation.y-this.y;
		return new Point(y + centreRotation.x, -x + centreRotation.y);
	}
	
	/**
	 * Methode applicant une rotation au point courant
	 * @param centreRotation Le centre de la rotation
	 */
	public void setRotatePosition(Point centreRotation){
		int tmpX = centreRotation.x-this.x;
		int tmpY = centreRotation.y-this.y;
		y = -tmpX + centreRotation.y;
		x = tmpY + centreRotation.x;
	}

	/**
	 * Methode retournant le point transforme par une translation
	 * @param move La direction de la translation
	 */
	public Point getMovePosition(TypeMove move){
		switch(move){
		case LEFT:
			return new Point(x-1, y);
		case RIGHT:
			return new Point(x+1, y);
		case DOWN:
			return new Point(x, y+1);
		default:
			return null;
		}
	}
	

	/**
	 * Methode applicant une rotation au point courant
	 * @param move La direction de la translation
	 */
	public void setMovePosition(TypeMove move){
		switch(move){
		case LEFT:
			x--; break;
		case RIGHT:
			x++; break;
		case DOWN:
			y++; break;
		}
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
