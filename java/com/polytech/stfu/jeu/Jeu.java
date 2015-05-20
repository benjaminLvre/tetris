package com.polytech.stfu.jeu;

import android.content.Context;
import android.content.Intent;
import android.database.CrossProcessCursor;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.polytech.stfu.ihm.R;

/**
 * Classe representant l'asbtraction d'une partie
 * @author Stfu
 * @see Grille
 */
public abstract class Jeu extends Thread{
	private static final String TAG = Jeu.class.getSimpleName();

	/**
	 * Option de vitesse de la partie
	 */
	protected Vitesse vitesse;
	/**
	 * Option d'acceleration de la partie
	 */
	protected Acceleration acceleration;
	/**
	 * Grille du jeu
	 */
	protected Grille grille;
	/**
	 * Type de la piece courante
	 */
	protected TypePiece piece;
	/**
	 * Intervale de temps entre deux chutes de la piece
	 */
	protected int intervalTime;
	/**
	 * Mode de jeu de la partie en cours
	 */
	private Mode mode;

	/**
	 * Context courant de la partie
	 */
	private Context mContext;
	/**
	 * Booleen pour savoir si la partie est finie
	 */
	protected boolean fin;
	/**
	 * Booleen pour savoir si la partie est en pause
	 */
	protected boolean pause;
	/**
	 * Lock pour l'exclusion mutuel sur pause
	 */
	protected Object lockPause;
	/**
	 * Lock pour l'exclusion mutel des mouvement
	 */
	protected Object lockMove;

	/**
	 * Jeu courant, il ne peut y en avoir qu'un parallelement
	 */
	private static Jeu jeu;
	
	public Jeu(Context pContext){
		pause = false;
		lockPause = new Object();
		lockMove = new Object();

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

	/**
	 * Methode pour deplacer la piece
	 * @param move La direction voulue
	 */
	public void move(TypeMove move){
		synchronized (lockMove) {
			if (grille.canMovePiece(move)) {
				grille.movePiece(move);
				sendGameStateChange();
			}
		}
	}

	/**
	 * Methode pour faire tourner la piece
	 */
	public void rotate(){
<<<<<<< HEAD
		synchronized (lockMove) {
			if (grille.canRotatePiece()) {
				grille.rotatePiece();
				Jeu.getJeu().aff();
				sendGameStateChange();
			}
=======
		if(grille.canRotatePiece()) {
			grille.rotatePiece();
			sendGameStateChange();
>>>>>>> 0a0ae0838ac3a6a44bb5387a6777fe4e056f3c6b
		}
	}

	/**
	 * Methode pour faire chuter la piece
	 */
	public void down(){
<<<<<<< HEAD
		synchronized (lockMove) {
			while (grille.canMovePiece(TypeMove.DOWN)) {
				grille.movePiece(TypeMove.DOWN);
			}
=======
		while(grille.canMovePiece(TypeMove.DOWN)){
			try{
				Thread.sleep(50);
			}catch (InterruptedException e){}

			grille.movePiece(TypeMove.DOWN);
>>>>>>> 0a0ae0838ac3a6a44bb5387a6777fe4e056f3c6b
		}
	}
	
	public TypePiece getTypeNextPiece(){
		return piece;
	}

	/**
	 * Methode qui déroule la partie
	 */
	public void run(){
		piece = createFuturPiece();
		sendGameStateChange();
		while(!fin){
			try {
				sleep(intervalTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lockPause();
			if(grille.canMovePiece(TypeMove.DOWN)){
				grille.movePiece(TypeMove.DOWN);
			}
			else{
				int tmp = grille.removeLines();
				updateScore(tmp);
<<<<<<< HEAD
=======
				sendNewScore();
>>>>>>> 0a0ae0838ac3a6a44bb5387a6777fe4e056f3c6b
				if(isFinish()){
					sendGameEnd();
					break;
				}
				piece = createFuturPiece();
				if(acceleration.getVal() != 0)
					intervalTime *= 0.01 * acceleration.getVal();
			}
			sendGameStateChange();
		}
	}

	/**
	 * Methode pour lancer le jeu
	 */
	public void startGame(){
		start();
	}

	/**
	 * Methode pour mettre le jeu en pause
	 */
	public void pause(){
		synchronized (lockPause) {
			pause = true;
		}
	}

	/**
	 * Methode pour reprendre la partie
	 */
	public void restart(){
		synchronized (lockPause) {
			pause = false;
		}
	}

	/**
	 * Methode pour cloturer la partie
	 */
	public void end(){
		fin = true;
	}

	/**
	 * Methode mettant en pause la partie si elle est active
	 */
	protected void lockPause(){
		while(pause){
			yield();
		}
	}

	/**
	 * Methode pour savoir si la partie est finie
	 * @return Si la partie est finie
	 */
	protected abstract boolean isFinish();

	/**
	 * Methode pour mettre a jour les scores
	 * @param line Nombre de lignes supprimees
	 */
	protected abstract void updateScore(int line);

	/**
	 * Creation de la nouvelle piece courante et ajout de celle ci sur la grille
	 * @return le type de la piece
	 */
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

	public boolean isInPause(){
		synchronized (lockPause) {
			return pause;
		}
	}

	public void aff(){
		Log.d(TAG, "grille : \n" + grille.toString());
		//System.out.println(grille);

	}

	/**
	 * Methode pour envoyer un evenement pour anoncer que l'etat du jeu a changer
	 */
	private void sendGameStateChange(){
		Intent intent = new Intent("TETRIS");
		intent.putExtra("Source", "Jeu");
		intent.putExtra("Action", R.string.GAME_STATE_CHANGE);
		LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
	}

	/**
	 * Methode pour envoyer un evenement pour anoncer que le jeu est fini
	 */
	private void sendGameEnd(){
		Intent intent = new Intent("TETRIS");
		intent.putExtra("Source", "Jeu");
		intent.putExtra("Action", R.string.GAME_END);
		LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
	}
}
