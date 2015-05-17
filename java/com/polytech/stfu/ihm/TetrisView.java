package com.polytech.stfu.ihm;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.polytech.stfu.jeu.TypePiece;

/**
 * Classe permettant de créer la vue d'une partie
 */
public class TetrisView extends SurfaceView implements SurfaceHolder.Callback{
    private static final String TAG = TetrisView.class.getSimpleName();
    // Holder
    private SurfaceHolder mSurfaceHolder;
    private Thread mThread;

    private TypePiece grille[][];

    private Paint linePaint;
    private Paint bgc;
    private  Paint scoreBgc;
    private Paint scoreColor;

    private Paint caseColor;

    final int  HORIZONTAL_LINES = 21;
    final int VERTICAL_LINES = 11;

    // Permet d'arreter le dessin quand il le faut
    boolean keepDrawing = true;

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

        this.mThread = new DrawingThread();

        this.grille = new TypePiece[23][12];

        this.linePaint = new Paint();

        this.bgc = new Paint();
        this.scoreBgc = new Paint();
        this.scoreColor = new Paint();
        this.caseColor = new Paint();

        this.bgc.setColor(Color.WHITE);
        this.linePaint.setColor(Color.LTGRAY);
    }

    /**
     * Fonction permettant de dessiner la vue
     * @param pCanvas   Le canvas sur lequel on dessine
     */
    @Override
    protected void onDraw(Canvas pCanvas) {
        Log.d(TAG, "OnDraw canvas");
        int width = getWidth();
        int height = getHeight() - 50;
        int horizontalLargeLine = height / (HORIZONTAL_LINES +1);
        int verticalLargeLine = width / (VERTICAL_LINES +1);

        // Fond de l'écran
        pCanvas.drawColor(this.bgc.getColor());

        this.scoreColor.setColor(Color.WHITE);
        this.scoreBgc.setColor(Color.BLACK);

        pCanvas.drawRect(0.0f,0.0f,(float)width,50.0f, this.scoreBgc);
        pCanvas.drawText("Score : 0000", 15.0f, 25.0f, this.scoreColor);

        // Dessin de la grille
        for(int hl=0; hl<HORIZONTAL_LINES; hl++){
            pCanvas.drawLine(0.0f,(float)horizontalLargeLine*(hl+1)+50.0f, (float)width,(float)horizontalLargeLine*(hl+1)+50.0f, this.linePaint);
        }
        for(int vl=0; vl<VERTICAL_LINES; vl++){
            pCanvas.drawLine((float)verticalLargeLine*(vl+1),50.0f,(float)verticalLargeLine*(vl+1), (float)height +50.0f, this.linePaint);
        }

        // Dessine une case
        for(int li=0; li< HORIZONTAL_LINES +1; li++){
            for(int col=0; col<VERTICAL_LINES +1; col++){
                if(grille[li][col] != TypePiece.None){
                    switch (grille[li][col]){
                        case L : caseColor.setColor(Color.RED);break;
                        case O: caseColor.setColor(Color.BLUE);break;
                        case S: caseColor.setColor(Color.GREEN);break;
                        case Z: caseColor.setColor(Color.YELLOW);break;
                        case T: caseColor.setColor(Color.MAGENTA);break;
                        case J: caseColor.setColor(Color.BLACK);break;
                        case I: caseColor.setColor(Color.CYAN);break;
                        default:break;
                    }
                    Log.d(TAG, " draw un carré");
                    if(li == 0 && col == 0){
                        pCanvas.drawRect(0.0f,50.0f,(float)verticalLargeLine,(float)horizontalLargeLine +50.0f, caseColor);
                    }
                    else if(col == 0){
                        pCanvas.drawRect(0.0f,(float)horizontalLargeLine*li +50.0f,(float)verticalLargeLine,(float)horizontalLargeLine*(li+1)+50.0f, caseColor);
                    }else if(li == 0) {
                        pCanvas.drawRect((float)verticalLargeLine*col,50.0f,(float)verticalLargeLine*(col+1),(float)horizontalLargeLine +50.0f, caseColor);
                    }
                    else{
                        pCanvas.drawRect((float)verticalLargeLine*col,(float)horizontalLargeLine*li +50.0f,(float)verticalLargeLine*(col+1),(float)horizontalLargeLine*(li+1) +50.0f, caseColor);
                    }
                }
            }
        }
    }
    @Override
    /**
     * Lancée lorsque l'ecran subit un changement, non utilisé ici
     */
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Ne fait rien
    }

    /**
     * Lancée quand la vue se créée.
     * @param pHolder   Permet de contrôler la surface
     */
    @Override
    public void surfaceCreated(SurfaceHolder pHolder) {
        keepDrawing = true;
        this.mThread.start();
    }
    /**
     * Lancée quand la vue est détruite.
     * @param pHolder Permet de contrôler la surface
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder pHolder) {
        this.keepDrawing = false;
        boolean retry = true;
        while(retry){
            try{
                mThread.join();
                retry = false;
            }catch (InterruptedException e){}
        }
    }

    /**
     * Operation réalisé lors du clic sur l'ecran de jeu
     * @param event L'évènement
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int quarter = getWidth()/4;
        // Clic sur l'ecran
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(event.getX()< quarter){
                Toast.makeText(this.mContext, "move left ", Toast.LENGTH_SHORT).show();
            }
            else if(event.getX() > quarter && event.getX() < 3*quarter){
                Toast.makeText(this.mContext, "rotate piece ", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this.mContext, "move right ", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * Classe Thread interne permettant de dessiner la vue du jeu
     */
    private class DrawingThread extends Thread{

        @Override
        public void run() {
            while(keepDrawing){
                Canvas canvas = null;
                keepDrawing = false;
                try{
                    canvas = mSurfaceHolder.lockCanvas();
                    // Aucun autre thread n'a acces au Holder
                    synchronized (mSurfaceHolder){
                        Log.d(TAG, "draw thread canvas");
                        onDraw(canvas);
                    }
                }
                finally{
                    // Dessin fini, relache le Canvas pour l'afficher
                    if(canvas != null){
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }

                // Permet de dessiner à 50 fps
                try{
                    Thread.sleep(20);
                }catch (InterruptedException e){}
            }
        }

    }

}
