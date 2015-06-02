package com.polytech.stfu.ihm;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.polytech.stfu.jeu.Jeu;
import com.polytech.stfu.jeu.TypeMove;
import com.polytech.stfu.jeu.TypePiece;

/**
 * Classe permettant de mettre en place la vue d'une partie
 * @see Jeu
 */
public class TetrisView extends SurfaceView implements SurfaceHolder.Callback{
    // Holder
    final private SurfaceHolder mSurfaceHolder;
    private Context mContext;
    private boolean created;
    private Handler mHandler;

    final int  HORIZONTAL_LINES = 21;
    final int VERTICAL_LINES = 11;

    private Paint linePaint;
    private Paint bgc;
    private Paint scoreBgc;
    private Paint scoreColor;
    private Drawable designCase;
    private Drawable designNextPiece;
    private Rect cube;
    private Rect rectNextPiece;

    private DrawingThread drawingThread;

    private boolean isPressure;
    private float startY;

    /**
     * Constructeur
     * @param pContext  Contexte de l'activite
     */
    public TetrisView(Context pContext){
        super(pContext);
        mContext = pContext;
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);

        linePaint = new Paint();

        bgc = new Paint();
        scoreBgc = new Paint();
        scoreColor = new Paint();

        rectNextPiece = new Rect();
        cube = new Rect();

        bgc.setColor(Color.WHITE);
        linePaint.setColor(Color.LTGRAY);

        created = false;
        isPressure = false;
        startY = 0.0f;
    }
    /**
     * Lance quand la vue se creee.
     * Un Thread de dessin est cree et lance
     * @param pHolder   Permet de controler la surface
     */
    @Override
    public void surfaceCreated(SurfaceHolder pHolder) {
        drawingThread = new DrawingThread();
        drawingThread.start();
        created = true;
    }
    /**
     * Lance lorsque l'ecran subit un changement, non utilisee ici
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Ne fait rien
    }
    /**
     * Lance quand la vue est detruite.
     * @param pHolder Permet de controler la surface
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder pHolder) {
        created = false;
    }
    /**
     * Fonction permettant de dessiner la vue selon les dimension de l'écran
     * @param pCanvas   Le canvas sur lequel on dessine
     */
    @Override
    protected void onDraw(Canvas pCanvas) {
        int width = getWidth();
        int height = getHeight();
        int horizontalLargeLine = height / (HORIZONTAL_LINES +2);
        int verticalLargeLine = width / (VERTICAL_LINES +1);

        // Fond de l'ecran
        pCanvas.drawColor(this.bgc.getColor());

        this.scoreColor.setColor(Color.WHITE);
        this.scoreBgc.setColor(Color.BLACK);

        SharedPreferences modeRegister = mContext.getSharedPreferences("Mode", 0);
        String modeRegisterValue = modeRegister.getString("mode", null);

        pCanvas.drawRect(0.0f, horizontalLargeLine * 23, width, height, this.scoreBgc);
        pCanvas.drawRect(0.0f, 0.0f, width, horizontalLargeLine, this.scoreBgc);
        pCanvas.drawRect(0.0f, 0.0f, width, horizontalLargeLine, this.scoreBgc);

        if(modeRegisterValue.equals("classique")){
            pCanvas.drawText("Score : " + Jeu.getJeu().getScore(), 15.0f, 25.0f, this.scoreColor);
        }
        else{
            pCanvas.drawText("Temps restant : " + Jeu.getJeu().getTempsRestant(), 15.0f, 25.0f, this.scoreColor);
        }

        SharedPreferences themeRegister = mContext.getSharedPreferences("Theme", 0);
        String themeRegisterValue = themeRegister.getString("theme", null);

        // Dessin prochaine piece
        rectNextPiece.set(verticalLargeLine*2,horizontalLargeLine*2,verticalLargeLine*10,horizontalLargeLine*6);
        TypePiece nextPiece = Jeu.getJeu().getTypeNextPiece();

        switch(themeRegisterValue){
            case "classique":
                switch (nextPiece){
                    case L : designNextPiece = getResources().getDrawable(R.drawable.next_piece_l);break;
                    case O: designNextPiece = getResources().getDrawable(R.drawable.next_piece_o);break;
                    case S:designNextPiece = getResources().getDrawable(R.drawable.next_piece_s);break;
                    case Z: designNextPiece = getResources().getDrawable(R.drawable.next_piece_z);break;
                    case T: designNextPiece = getResources().getDrawable(R.drawable.next_piece_t);break;
                    case J: designNextPiece = getResources().getDrawable(R.drawable.next_piece_j);break;
                    case I: designNextPiece = getResources().getDrawable(R.drawable.next_piece_i);break;
                    default:break;
                }break;
            case "polytech":
                switch (nextPiece){
                    case L : designNextPiece = getResources().getDrawable(R.drawable.next_piece_l_polytech);break;
                    case O: designNextPiece = getResources().getDrawable(R.drawable.next_piece_o_polytech);break;
                    case S:designNextPiece = getResources().getDrawable(R.drawable.next_piece_s_polytech);break;
                    case Z: designNextPiece = getResources().getDrawable(R.drawable.next_piece_z_polytech);break;
                    case T: designNextPiece = getResources().getDrawable(R.drawable.next_piece_t_polytech);break;
                    case J: designNextPiece = getResources().getDrawable(R.drawable.next_piece_j_polytech);break;
                    case I: designNextPiece = getResources().getDrawable(R.drawable.next_piece_i_polytech);break;
                    default:break;
                }break;
            case "walking_dead":
                switch (nextPiece){
                    case L : designNextPiece = getResources().getDrawable(R.drawable.next_piece_l_wd);break;
                    case O: designNextPiece = getResources().getDrawable(R.drawable.next_piece_o_wd);break;
                    case S:designNextPiece = getResources().getDrawable(R.drawable.next_piece_s_wd);break;
                    case Z: designNextPiece = getResources().getDrawable(R.drawable.next_piece_z_wd);break;
                    case T: designNextPiece = getResources().getDrawable(R.drawable.next_piece_t_wd);break;
                    case J: designNextPiece = getResources().getDrawable(R.drawable.next_piece_j_wd);break;
                    case I: designNextPiece = getResources().getDrawable(R.drawable.next_piece_i_wd);break;
                    default:break;
                }break;
        }

        designNextPiece.setBounds(rectNextPiece);
        designNextPiece.draw(pCanvas);

        // Dessin de la grille
        for(int hl=2; hl<=HORIZONTAL_LINES +1; hl++){
            pCanvas.drawLine(0.0f,horizontalLargeLine*(hl), width,horizontalLargeLine*(hl), this.linePaint);
        }
        for(int vl=1; vl<=VERTICAL_LINES; vl++){
            pCanvas.drawLine(verticalLargeLine*(vl),horizontalLargeLine,verticalLargeLine*(vl), horizontalLargeLine*23, this.linePaint);
        }

        // Dessine une case
        for(int li=1; li<= HORIZONTAL_LINES +1; li++){
            for(int col=0; col<VERTICAL_LINES +1; col++){
                if(Jeu.getJeu().getGrille()[li][col] != TypePiece.None){
                    switch (themeRegisterValue){
                        case "classique" :
                            switch (Jeu.getJeu().getGrille()[li][col]){
                                case L : designCase = getResources().getDrawable(R.drawable.orange_cube);break;
                                case O: designCase = getResources().getDrawable(R.drawable.yellow_cube);break;
                                case S:designCase = getResources().getDrawable(R.drawable.green_cube);break;
                                case Z: designCase = getResources().getDrawable(R.drawable.red_cube);break;
                                case T: designCase = getResources().getDrawable(R.drawable.purple_cube);break;
                                case J: designCase = getResources().getDrawable(R.drawable.blue_cube);break;
                                case I: designCase = getResources().getDrawable(R.drawable.turquoiz_cube);break;
                                default:break;
                            }break;
                        case "walking_dead":
                            switch (Jeu.getJeu().getGrille()[li][col]){
                                case L : designCase = getResources().getDrawable(R.drawable.orange_cube_wd1);break;
                                case O: designCase = getResources().getDrawable(R.drawable.yellow_cube_wd1);break;
                                case S: designCase = getResources().getDrawable(R.drawable.green_cube_wd1);break;
                                case Z: designCase = getResources().getDrawable(R.drawable.red_cube_wd1);break;
                                case T: designCase = getResources().getDrawable(R.drawable.purple_cube_wd1);break;
                                case J: designCase = getResources().getDrawable(R.drawable.blue_cube_wd1);break;
                                case I: designCase = getResources().getDrawable(R.drawable.turquoiz_cube_wd1);break;
                                default:break;
                        }break;
                        case "polytech":
                            switch (Jeu.getJeu().getGrille()[li][col]){
                                case L : designCase = getResources().getDrawable(R.drawable.cube_polytech12);break;
                                case O: designCase = getResources().getDrawable(R.drawable.cube_polytech22);break;
                                case S: designCase = getResources().getDrawable(R.drawable.cube_polytech32);break;
                                case Z:  designCase = getResources().getDrawable(R.drawable.cube_polytech42);break;
                                case T: designCase = getResources().getDrawable(R.drawable.cube_polytech52);break;
                                case J:  designCase = getResources().getDrawable(R.drawable.cube_polytech62);break;
                                case I:  designCase = getResources().getDrawable(R.drawable.cube_polytech72);break;
                                default:break;
                            }break;
                        default:break;
                    }

                    if(li == 0 && col == 0){
                        cube.set(0, horizontalLargeLine, verticalLargeLine, horizontalLargeLine);
                    }
                    else if(col == 0){
                        cube.set(0, horizontalLargeLine*li, verticalLargeLine,  horizontalLargeLine*(li+1));
                    }else if(li == 0) {
                        cube.set( verticalLargeLine*col, horizontalLargeLine, verticalLargeLine*(col+1),  horizontalLargeLine);
                    }
                    else{
                        cube.set( verticalLargeLine*col, horizontalLargeLine *li, verticalLargeLine*(col+1),  horizontalLargeLine*(li+1));
                    }
                    designCase.setBounds(cube);
                    designCase.draw(pCanvas);
                }
            }
        }
    }
    /**
     * Thread de mouvement a droite
     */
    Runnable moveRight = new Runnable() {
        public void run() {
            Jeu.getJeu().move(TypeMove.RIGHT);
            if(mHandler != null && isPressure){
                double accel = (1 - (0.01 * Jeu.getJeu().getAcceleration().getVal()/100));
                mHandler.postDelayed(this, (long)((500*Jeu.getJeu().getVitesse().getVal()/100)*accel));

            }
        }
    };
    /**
     * Thread de mouvement a droite
     */
    Runnable moveLeft = new Runnable() {
        public void run() {
            Jeu.getJeu().move(TypeMove.LEFT);
            if(mHandler != null && isPressure){
                double accel = (1 - (0.01 * Jeu.getJeu().getAcceleration().getVal()/100));
                mHandler.postDelayed(this, (long)((500*Jeu.getJeu().getVitesse().getVal()/100)*accel));
            }
        }
    };
    /**
     * Operation realisee lors du clic sur l'ecran de jeu.
     * L'endroit du clic agit sur la piece:
     * - 1/4 de l'ecran en largeur a gauche et 20/23 en hauteur en haut : la piece bouge a gauche
     * - 1/2 de l'ecran en largeur au centre et 20/23 en hauteur en haut: la piece subit une rotation
     * - 1/4 de l'ecran en largeur a droite et 20/23 en hauteur en haut: la piece bouge a droite
     * - Si effet de glisser vertical lors du clic, alors la pie chute
     * @param event L'evenement
     * @return boolean
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int quarter = getWidth()/4;

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            isPressure = true;
            if(event.getX()< quarter){
                // Lancement du thrad de mouvement à gauche
                mHandler = new Handler();
                mHandler.post(moveLeft);
            }
            else if(event.getX() > quarter && event.getX() < 3*quarter){startY = event.getY();}
            else if(event.getX() > 3*quarter){
                // Lancement du thrad de mouvement à droite
                mHandler = new Handler();
                mHandler.post(moveRight);
            }
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            isPressure = false;
            if(event.getX()< quarter) {
                if (mHandler != null) {
                    // Arret du Thread de mouvement à gauche
                    mHandler.removeCallbacks(moveLeft);
                    mHandler = null;
                }
            }
            if(event.getX() > quarter && event.getX() < 3*quarter){
                // si pas de glisser alors la pièce subit une rotation
                if(startY == event.getY()){ Jeu.getJeu().rotate();}
                // sinon elle chute
                else{ if(event.getY() - startY > 100.0f){ Jeu.getJeu().down(); } }
            }
            if(event.getX() > 3*quarter) {
                if (mHandler != null) {
                    // Arret du Thread de mouvement à droite
                    mHandler.removeCallbacks(moveRight);
                    mHandler = null;
                }
            }
        }
        return true;
    }
    /**
     * Retourne le Thread de dessin
     * @return  Thread  La variable drawingThread
     */
    public Thread gThread(){ return drawingThread; }
    /**
     * Retourne si la vue a deja ete cree
     * @return  boolean La variable created
     */
    public boolean getCreated(){return created;}
    /**
     * Classe Thread interne permettant de dessiner la vue du jeu
     */
    private class DrawingThread extends Thread{
        @Override
        public void run() {
            Canvas canvas = null;
            try{
                canvas = mSurfaceHolder.lockCanvas();
                // Aucun autre thread n'a acces au Holder
                synchronized (mSurfaceHolder){
                    onDraw(canvas);
                }
            }
            finally{
                // Dessin fini, relache le Canvas pour l'afficher
                if(canvas != null){
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
