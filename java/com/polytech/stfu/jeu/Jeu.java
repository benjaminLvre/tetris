package com.polytech.stfu.jeu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.support.v4.content.LocalBroadcastManager;

import com.polytech.stfu.ihm.R;

/**
 * Classe representant l'asbtraction d'une partie
 *
 * Elle represente l'interface du module. Cette abstraction du moteur de jeu permet de rendre son utilisation intuitive (stratGame, pause, move, rotate...).
 * Cette classe commande intégralement la classe grille, qui elle fournit elle même une abstraction forte de la gestion de la grille de jeu.
 *
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
	protected Piece piece;

	protected Piece futurePiece;
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
	
	public Jeu(Context pContext, Mode mode){
		pause = false;
		lockPause = new Object();
		lockMove = new Object();
		futurePiece = createFuturPiece();

		mContext = pContext;
		fin = false;
		SharedPreferences saveAcceleration = mContext.getSharedPreferences("Acceleration", 0);
		SharedPreferences saveVitesse = mContext.getSharedPreferences("Vitesse", 0);
		String tmp = saveVitesse.getString("vitesse", "");
		switch(tmp){
			case "FAIBLE":
				vitesse = Vitesse.FAIBLE;
				break;
			case "NORMALE":
				vitesse = Vitesse.NORMALE;
				break;
			case "ELEVEE":
				vitesse = Vitesse.ELEVEE;
				break;
			default:
				vitesse = Vitesse.NORMALE;
				break;
		}
		tmp = saveAcceleration.getString("acceleration", "");
		switch(tmp){
			case "NULLE":
				acceleration = Acceleration.NULLE;
				break;
			case "MODEREE":
				acceleration = Acceleration.MODEREE;
				break;
			case "FORTE":
				acceleration = Acceleration.FORTE;
				break;
			default:
				acceleration = Acceleration.MODEREE;
		}
		grille = new Grille();
		intervalTime = 500 * vitesse.getVal()/100;
        this.mode = mode;
		
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
			if (grille.canMovePiece(move)){
				grille.movePiece(move);
				sendGameStateChange();
			}
		}
	}

	/**
	 * Methode pour faire tourner la piece
	 */
	public void rotate(){
		synchronized (lockMove) {
			if (grille.canRotatePiece()) {
				grille.rotatePiece();
				sendGameStateChange();
			}
		}
	}

	/**
	 * Methode pour faire chuter la piece
	 */
	public void down(){
		synchronized (lockMove) {
			while (grille.canMovePiece(TypeMove.DOWN)) {
				try { Thread.sleep(10);}
				catch (InterruptedException e) { e.printStackTrace(); }
				grille.movePiece(TypeMove.DOWN);
			}
		}
	}
	
	public TypePiece getTypeNextPiece(){
		return futurePiece.getTypePiece();
	}

	/**
	 * Methode qui déroule la partie
	 */
	public void run(){
		piece = futurePiece;
		futurePiece = createFuturPiece();
		grille.setNewPiece(piece);
		sendGameStateChange();
		while(!fin){
			if (isTimesUp()) {
				sendGameEnd();
				break;
			}
			try {
				sleep(intervalTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lockPause();
			synchronized (lockMove) {
				if (grille.canMovePiece(TypeMove.DOWN)) {
					grille.movePiece(TypeMove.DOWN);
				} else {
					int tmp = grille.removeLines();
					updateScore(tmp);
					if(isFinish() || !grille.pieceHasValidPosition(futurePiece)) {
						sendGameEnd();
						break;
					}
					piece = futurePiece;
					grille.setNewPiece(piece);
					futurePiece = createFuturPiece();
					grille.setNewPiece(piece);
					if (acceleration.getVal() != 0)
						intervalTime *= (1 - (0.01 * acceleration.getVal()/100));
				}
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
	protected boolean isFinish(){
		return !grille.topLineIsEmpty();
	}

	/**
	 * Methode pour mettre a jour les scores
	 * @param line Nombre de lignes supprimees
	 */
	protected abstract void updateScore(int line);

	/**
	 * Creation de la nouvelle piece courante et ajout de celle ci sur la grille
	 * @return le type de la piece
	 */
	protected Piece createFuturPiece(){
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
		return newPiece;
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
		return piece.getTypePiece();
	}
	
	public Mode getMode(){
		return mode;
	}

	public int getTempsRestant(){
		return 0;
	}

	public boolean isInPause(){
		synchronized (lockPause) {
			return pause;
		}
	}

	public boolean isTimesUp(){
		return false;
	}

	public void aff(){
		Log.d(TAG, "grille : \n" + grille.toString());
		//System.out.println(grille);

	}

	/**
	 * Methode pour envoyer un evenement pour annoncer que l'etat du jeu a changer
	 */
	public void sendGameStateChange(){
		Intent intent = new Intent("TETRIS");
		intent.putExtra("Source", "Jeu");
		intent.putExtra("Action", mContext.getString(R.string.GAME_STATE_CHANGE));
		LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
	}

	/**
	 * Methode pour envoyer un evenement pour anoncer que le jeu est fini
	 */
	private void sendGameEnd(){
		Intent intent = new Intent("TETRIS");
		intent.putExtra("Source", "Jeu");
		intent.putExtra("Action", mContext.getString(R.string.GAME_END));
		LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
	}

	public float getDifficultCoeff(){
		return (100f/(float)vitesse.getVal())*(1+(float)acceleration.getVal()/100f)/2f;
	}
}
