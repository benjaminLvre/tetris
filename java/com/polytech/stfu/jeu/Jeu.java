package com.polytech.stfu.jeu;

import android.content.Context;
import android.content.Intent;
import android.database.CrossProcessCursor;
import android.os.Parcelable;

public abstract class Jeu extends Thread{
	protected Vitesse vitesse;
	protected Acceleration acceleration;
	protected Grille grille;
	protected TypePiece piece;
	protected int intervalTime;
	private Mode mode;

	private Context mContext;

	protected boolean fin;
	protected boolean pause;
	protected Object lockPause;
	
	private static Jeu jeu;

	public final static String GAME_STATE_CHANGE  = "com.polytech.stfu.jeu.GAME_STATE_CHANGE";
	public final static String NEW_SCORE  = "com.polytech.stfu.jeu.NEW_SCORE";
	public final static String GAME_END  = "com.polytech.stfu.jeu.GAME_END";
	public final static String GAME_PAUSE  = "com.polytech.stfu.jeu.GAME_PAUSE";
	public final static String GAME_UNPAUSE  = "com.polytech.stfu.jeu.GAME_UNPAUSE";
	
	public Jeu(Context pContext){
		pause = false;
		lockPause = new Object();

		mContext = pContext;
		fin = false;
		vitesse = Vitesse.NORMALE;
		acceleration = Acceleration.MODEREE;
		grille = new Grille();
		intervalTime = 500 * vitesse.getVal()/100;
		
		Jeu.jeu = this;
	}
	
	public static Jeu getJeu(){
		return jeu;
	}
	
	public void move(TypeMove move){
		if(grille.canMovePiece(move)){
			grille.movePiece(move);
		}
	}

	public void rotate(){
		if(grille.canRotatePiece()) {
			grille.rotatePiece();
		}
	}
	
	public void down(){
		while(grille.canMovePiece(TypeMove.DOWN)){
			grille.movePiece(TypeMove.DOWN);
		}
	}
	
	public TypePiece getTypeNextPiece(){
		return piece;
	}

	public void run(){
		piece = createFuturPiece();
		while(!fin){
			lockPause();
			try {
				sleep(intervalTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(grille.canMovePiece(TypeMove.DOWN)){
				grille.movePiece(TypeMove.DOWN);
			}
			else{
				updateScore(grille.removeLines());
				sendNewScore();
				if(isFinish()){
					sendGameEnd();
					break;
				}
				piece = createFuturPiece();
				intervalTime *= 0.01 * acceleration.getVal();
			}
			sendGameStateChange();
		}
	}
	
	public void startGame(){
		start();
	}
	
	public void pause(){
		synchronized (lockPause) {
			pause = true;
		}
	}
	
	public void restart(){
		synchronized (lockPause) {
			pause = false;
		}
	}
	
	public void end(){
		fin = true;
	}
	
	protected void lockPause(){
		while(pause){
			yield();
		}
	}
	
	protected abstract boolean isFinish();
	
	protected abstract void updateScore(int line);
	
	protected TypePiece createFuturPiece(){
		int type = (int)(Math.random()*7);
		Point pointInitial = new Point(5,0);
		Piece newPiece;
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
		default:
			newPiece = Piece.createPieceI(pointInitial);
		}
		piece = newPiece.getTypePiece();
		grille.setNewPiece(newPiece);
		return piece;
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
	
	public TypePiece[][] getGrille(){
		return grille.getPlateau();
	}
	
	public TypePiece getTypePiece(){
		return piece;
	}
	
	public Mode getMode(){
		return mode;
	}
	
	public void aff(){
		System.out.println(grille);
	}

	private void sendGameStateChange(){
		Intent intent = new Intent(GAME_STATE_CHANGE);
		intent.putExtra("Source", "Jeu");
		mContext.sendBroadcast(intent);
	}

	private void sendNewScore(){
		Intent intent = new Intent(NEW_SCORE);
		intent.putExtra("Source", "Jeu");
		mContext.sendBroadcast(intent);
	}

	private void sendGameEnd(){
		Intent intent = new Intent(GAME_END);
		intent.putExtra("Source", "Jeu");
		mContext.sendBroadcast(intent);
	}
}
