package com.polytech.ben.tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class TetrisView extends SurfaceView implements SurfaceHolder.Callback{
    private static final String TAG = TetrisView.class.getSimpleName();
    // Holder
    private SurfaceHolder mSurfaceHolder;
    private Thread mThread;

    private Paint linePaint;
    private Paint bgc;

    int grille[][] = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 2, 0, 4, 4, 0, 3, 3, 1},
            {1, 1, 1, 1, 2, 2, 2, 4, 4, 3, 3, 1}
    };

    final int  HORIZONTAL_LINES = 21;
    final int VERTICAL_LINES = 11;

    // Permet d'arreter le dessin quand il le faut
   boolean keepDrawing = true;

    public TetrisView(Context pContext){
        super(pContext);
        this.mSurfaceHolder = getHolder();
        this.mSurfaceHolder.addCallback(this);

        this.mThread = new DrawingThread();
        this.linePaint = new Paint();

        this.bgc = new Paint();

        this.bgc.setColor(Color.WHITE);
        this.linePaint.setColor(Color.LTGRAY);

    }

    @Override
    protected void onDraw(Canvas pCanvas) {
        Log.d(TAG, "OnDraw canvas");
        int width = getWidth();
        int height = getHeight();
        int horizontalLargeLine = height / (HORIZONTAL_LINES +1);
        int verticalLargeLine = width / (VERTICAL_LINES +1);

        // Fond de l'écran
        pCanvas.drawColor(this.bgc.getColor());
        Paint caseColor = new Paint();

        // Dessin de la grille
        for(int hl=0; hl<HORIZONTAL_LINES; hl++){
            pCanvas.drawLine(0.0f,(float)horizontalLargeLine*(hl+1), (float)width,(float)horizontalLargeLine*(hl+1), this.linePaint);
        }
        for(int vl=0; vl<VERTICAL_LINES; vl++){
            pCanvas.drawLine((float)verticalLargeLine*(vl+1),0.0f,(float)verticalLargeLine*(vl+1), (float)height, this.linePaint);
        }


        // Dessin case
        for(int li=0; li< HORIZONTAL_LINES +1; li++){
            for(int col=0; col<VERTICAL_LINES +1; col++){
                if(grille[li][col] != 0){
                    switch (grille[li][col]){
                        case 1: caseColor.setColor(Color.RED);break;
                        case 2: caseColor.setColor(Color.BLUE);break;
                        case 3: caseColor.setColor(Color.GREEN);break;
                        case 4: caseColor.setColor(Color.YELLOW);break;
                        default:break;
                    }
                    Log.d(TAG, " draw un carré");
                    if(li == 0 && col == 0){
                        pCanvas.drawRect(0.0f,0.0f,(float)verticalLargeLine,(float)horizontalLargeLine, caseColor);
                    }
                    else if(col == 0){
                        pCanvas.drawRect(0.0f,(float)horizontalLargeLine*li,(float)verticalLargeLine,(float)horizontalLargeLine*(li+1), caseColor);
                    }else if(li == 0) {
                        pCanvas.drawRect((float)verticalLargeLine*col,0.0f,(float)verticalLargeLine*(col+1),(float)horizontalLargeLine, caseColor);
                    }
                    else{
                        pCanvas.drawRect((float)verticalLargeLine*col,(float)horizontalLargeLine*li,(float)verticalLargeLine*(col+1),(float)horizontalLargeLine*(li+1), caseColor);
                    }
                }
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Ne fait rien
    }

    @Override
    public void surfaceCreated(SurfaceHolder pHolder) {
        keepDrawing = true;
        this.mThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        this.keepDrawing = false;
        boolean retry = true;
        while(retry){
            try{
                mThread.join();
                retry = false;
            }catch (InterruptedException e){}
        }
    }

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
                    // Dessin fini, relacher le Canvas pour l'afficher
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
