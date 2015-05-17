package com.polytech.stfu.jeu;

public abstract class Jeu {
	private Vitesse vitesse;
	private Acceleration acceleration;
	private Grille grille;
	private Piece piece;
	
	public void move(TypeMove move){
		if(grille.canMovePiece(move)){
			grille.movePiece(move);
		}
	}
	
	public void down(){
		while(grille.canMovePiece(TypeMove.DOWN)){
			grille.movePiece(TypeMove.DOWN);
		}
	}
	
	public TypePiece getTypeNextPiece(){
		return piece.getTypePiece();
	}
	
	public void start(){
		
	}
	
	public void pause(){
		
	}
	
	public void restart(){
		
	}
	
	public void end(){
		
	}
	
	private Piece createFuturPiece(){
		int type = (int)(Math.random()*7);
		Piece newPiece;
		Point pointInitial = new Point(5,0);
		switch(type){
		case 0:
			newPiece = Piece.createPieceI(pointInitial);
			break;
		case 1:
			newPiece = Piece.createPieceJ(pointInitial);
			break;
		case 2:
			newPiece = Piece.createPieceL(pointInitial);
			break;
		case 3:
			newPiece = Piece.createPieceO(pointInitial);
			break;
		case 4:
			newPiece = Piece.createPieceS(pointInitial);
			break;
		case 5:
			newPiece = Piece.createPieceT(pointInitial);
			break;
		case 6:
			newPiece = Piece.createPieceZ(pointInitial);
			break;
		}
	}
	
	public abstract int getScore();

	public Vitesse getVitesse() {
		return vitesse;
	}

	public void setVitesse(Vitesse vitesse) {
		this.vitesse = vitesse;
	}

	public Acceleration getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Acceleration acceleration) {
		this.acceleration = acceleration;
	}
}
