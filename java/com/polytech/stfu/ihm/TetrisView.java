package com.polytech.stfu.ihm;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.polytech.stfu.jeu.Jeu;
import com.polytech.stfu.jeu.TypeMove;
import com.polytech.stfu.jeu.TypePiece;

/**
 * Classe permettant de cr�er la vue d'une partie
 */
public class TetrisView extends SurfaceView implements SurfaceHolder.Callback{
    private static final String TAG = TetrisView.class.getSimpleName();
    // Holder
    private SurfaceHolder mSurfaceHolder;
    private Runnable drawingThread;

    private Paint linePaint;
    private Paint bgc;
    private  Paint scoreBgc;
    private Paint scoreColor;

    private Paint caseColor;

    final int  HORIZONTAL_LINES = 21;
    final int VERTICAL_LINES = 11;

    Context mContext;

    /**
     * Constructeur
     * @param pContext  Contexte de l'activit?
     */
    public TetrisView(Context pContext){
        super(pContext);
        mContext = pContext;
        this.mSurfaceHolder = getHolder();
        this.mSurfaceHolder.addCallback(this);

        this.linePaint = new Paint();

        this.bgc = new Paint();
        this.scoreBgc = new Paint();
        this.scoreColor = new Paint();
        this.caseColor = new Paint();

        this.bgc.setColor(Color.WHITE);
        this.linePaint.setColor(Color.LTGRAY);

        this.drawingThread = new DrawingThread();
    }

    /**
     * Fonction permettant de dessiner la vue
     * @param pCanvas   Le canvas sur lequel on dessine
     */
    @Override
    protected void onDraw(Canvas pCanvas) {
        float width = (float)getWidth();
        float height = (float)getHeight();
        float horizontalLargeLine = height / (float)(HORIZONTAL_LINES +2);
        float verticalLargeLine = width / (float)(VERTICAL_LINES +1);

        // Fond de l'�cran
        pCanvas.drawColor(this.bgc.getColor());

        this.scoreColor.setColor(Color.WHITE);
        this.scoreBgc.setColor(Color.BLACK);

        pCanvas.drawRect(0.0f, 0.0f, width, horizontalLargeLine, this.scoreBgc);
        pCanvas.drawText("Score : " + Jeu.getJeu().getScore(), 15.0f, 25.0f, this.scoreColor);

        // Dessin de la grille
        for(int hl=2; hl<=HORIZONTAL_LINES +1; hl++){

            pCanvas.drawLine(0.0f,horizontalLargeLine*(hl), width,horizontalLargeLine*(hl), this.linePaint);
        }
        for(int vl=1; vl<=VERTICAL_LINES; vl++){
                    //srtX,srtY, spx, spY
            pCanvas.drawLine(verticalLargeLine*(vl),horizontalLargeLine,verticalLargeLine*(vl), height, this.linePaint);
        }

        // Dessine une case
        for(int li=1; li<= HORIZONTAL_LINES +1; li++){
            for(int col=0; col<VERTICAL_LINES +1; col++){
                if(Jeu.getJeu().getGrille()[li][col] != TypePiece.None){
                    switch (Jeu.getJeu().getGrille()[li][col]){
                        case L : caseColor.setColor(Color.RED);break;
                        case O: caseColor.setColor(Color.BLUE);break;
                        case S: caseColor.setColor(Color.GREEN);break;
                        case Z: caseColor.setColor(Color.YELLOW);break;
                        case T: caseColor.setColor(Color.MAGENTA);break;
                        case J: caseColor.setColor(Color.BLACK);break;
                        case I: caseColor.setColor(Color.CYAN);break;
                        default:break;
                    }
                    if(li == 0 && col == 0){
                        pCanvas.drawRect(0.0f,horizontalLargeLine,verticalLargeLine,horizontalLargeLine, caseColor);
                    }
                    else if(col == 0){
                        pCanvas.drawRect(0.0f,horizontalLargeLine*li,verticalLargeLine,horizontalLargeLine*(li+1), caseColor);
                    }else if(li == 0) {
                        pCanvas.drawRect(verticalLargeLine*col,horizontalLargeLine,verticalLargeLine*(col+1),horizontalLargeLine, caseColor);
                    }
                    else{
                        pCanvas.drawRect(verticalLargeLine*col,horizontalLargeLine*li,verticalLargeLine*(col+1),horizontalLargeLine*(li+1), caseColor);
                    }
                }
            }
        }
    }
    /**
     * Lanc�e lorsque l'ecran subit un changement, non utilis� ici
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Ne fait rien
    }

    /**
     * Lanc�e quand la vue se cr��e.
     * @param pHolder   Permet de contr�ler la surface
     */
    @Override
    public void surfaceCreated(SurfaceHolder pHolder) {
        ((Activity)mContext).runOnUiThread(getmThread());
    }
    /**
     * Lanc�e quand la vue est d�truite.
     * @param pHolder Permet de contr�ler la surface
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder pHolder) {

    }

    /**
     * Operation r�alis� lors du clic sur l'ecran de jeu
     * @param event L'�v�nement
     * @return boolean
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int quarter = getWidth()/4;
        // Clic sur l'ecran
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(event.getX()< quarter){
                Jeu.getJeu().move(TypeMove.LEFT);
            }
            else if(event.getX() > quarter && event.getX() < 3*quarter){
                float height = (float)getHeight();
                float horizontalLargeLine = height / (float)(HORIZONTAL_LINES +2);
                if(event.getY() > horizontalLargeLine* 20){
                    Jeu.getJeu().down();
                }
                else{
                    Jeu.getJeu().rotate();
                }

            }
            else{
                Jeu.getJeu().move(TypeMove.RIGHT);
            }
        }
        return super.onTouchEvent(event);
    }

    public Runnable getmThread(){
        return new DrawingThread();
    }


    /**
     * Classe Thread interne permettant de dessiner la vue du jeu
     */
    private class DrawingThread implements Runnable{

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
