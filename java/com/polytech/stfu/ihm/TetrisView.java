package com.polytech.stfu.ihm;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
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
    private static final String TAG = TetrisView.class.getSimpleName();
    // Holder
    final private SurfaceHolder mSurfaceHolder;

    private Paint linePaint;
    private Paint bgc;
    private  Paint scoreBgc;
    private Paint scoreColor;

    final int  HORIZONTAL_LINES = 21;
    final int VERTICAL_LINES = 11;

    private DrawingThread drawingThread;

    private Context mContext;
    private boolean created;
    private Drawable designCase;
    private Rect cube;

    /**
     * Constructeur
     * @param pContext  Contexte de l'activit?
     */
    public TetrisView(Context pContext){
        super(pContext);
        mContext = pContext;
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);

        linePaint = new Paint();

        drawingThread = new DrawingThread();

        bgc = new Paint();
        scoreBgc = new Paint();
        scoreColor = new Paint();
        cube = new Rect();

        bgc.setColor(Color.WHITE);
        linePaint.setColor(Color.LTGRAY);

        created = false;

    }
    /**
     * Lance quand la vue se cr��e.
     * @param pHolder   Permet de contr�ler la surface
     */
    @Override
    public void surfaceCreated(SurfaceHolder pHolder) {
        Log.d(TAG, "created");
        created = true;
        drawingThread.start();
    }
    /**
     * Lance lorsque l'ecran subit un changement, non utilis� ici
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Ne fait rien
        Log.d(TAG, "changed");
    }
    /**
     * Lance quand la vue est d�truite.
     * @param pHolder Permet de contr�ler la surface
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder pHolder) {
        boolean retry = true;
        while(retry){
            try{
                drawingThread.join();
                retry = false;
                created = false;
            }
            catch (InterruptedException e){}
        }
    }

    /**
     * Fonction permettant de dessiner la vue
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

        pCanvas.drawRect(0.0f, 0.0f, width, horizontalLargeLine, this.scoreBgc);
        pCanvas.drawText("Score : " + Jeu.getJeu().getScore(), 15.0f, 25.0f, this.scoreColor);

        // Dessin de la grille
        for(int hl=2; hl<=HORIZONTAL_LINES +1; hl++){
            pCanvas.drawLine(0.0f,horizontalLargeLine*(hl), width,horizontalLargeLine*(hl), this.linePaint);
        }
        for(int vl=1; vl<=VERTICAL_LINES; vl++){
            pCanvas.drawLine(verticalLargeLine*(vl),horizontalLargeLine,verticalLargeLine*(vl), height, this.linePaint);
        }

        SharedPreferences themeRegister = mContext.getSharedPreferences("Theme", 0);
        String themeRegisterValue = themeRegister.getString("theme", null);

        // Dessine une case
        for(int li=1; li<= HORIZONTAL_LINES +1; li++){
            for(int col=0; col<VERTICAL_LINES +1; col++){
                if(Jeu.getJeu().getGrille()[li][col] != TypePiece.None){
                    switch (themeRegisterValue){
                        case "polytech" :
                            switch (Jeu.getJeu().getGrille()[li][col]){
                                case L : designCase = getResources().getDrawable(R.drawable.red_cube);break;
                                case O: designCase = getResources().getDrawable(R.drawable.blue_cube);break;
                                case S:designCase = getResources().getDrawable(R.drawable.green_cube);break;
                                case Z: designCase = getResources().getDrawable(R.drawable.yellow_cube);break;
                                case T: designCase = getResources().getDrawable(R.drawable.purple_cube);break;
                                case J: designCase = getResources().getDrawable(R.drawable.orange_cube);break;
                                case I: designCase = getResources().getDrawable(R.drawable.turquoiz_cube);break;
                                default:break;
                            }break;
                        case "walking_dead": switch (Jeu.getJeu().getGrille()[li][col]){
                            case L : designCase = getResources().getDrawable(R.drawable.red_cube);break;
                            case O: designCase = getResources().getDrawable(R.drawable.blue_cube);break;
                            case S:designCase = getResources().getDrawable(R.drawable.green_cube);break;
                            case Z: designCase = getResources().getDrawable(R.drawable.yellow_cube);break;
                            case T: designCase = getResources().getDrawable(R.drawable.purple_cube);break;
                            case J: designCase = getResources().getDrawable(R.drawable.orange_cube);break;
                            case I: designCase = getResources().getDrawable(R.drawable.turquoiz_cube);break;
                            default:break;
                        }break;
                        default: switch (Jeu.getJeu().getGrille()[li][col]){
                            case L : designCase = getResources().getDrawable(R.drawable.red_cube);break;
                            case O: designCase = getResources().getDrawable(R.drawable.blue_cube);break;
                            case S:designCase = getResources().getDrawable(R.drawable.green_cube);break;
                            case Z: designCase = getResources().getDrawable(R.drawable.yellow_cube);break;
                            case T: designCase = getResources().getDrawable(R.drawable.purple_cube);break;
                            case J: designCase = getResources().getDrawable(R.drawable.orange_cube);break;
                            case I: designCase = getResources().getDrawable(R.drawable.turquoiz_cube);break;
                            default:break;
                        }break;
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
     * Operation realisee lors du clic sur l'ecran de jeu
     * @param event L'�v�nement
     * @return boolean
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int quarter = getWidth()/4;
        float height = (float)getHeight();
        float horizontalLargeLine = height / (float)(HORIZONTAL_LINES +2);
        // Clic sur l'ecran
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(event.getX()< quarter && event.getY() < horizontalLargeLine* 20){
                Jeu.getJeu().move(TypeMove.LEFT);
            }
            else if(event.getX() > quarter && event.getX() < 3*quarter && event.getY() < horizontalLargeLine* 20){
                    Jeu.getJeu().rotate();
            }
            else if(event.getX() > 3*quarter && event.getY() < horizontalLargeLine* 20){
                    Jeu.getJeu().move(TypeMove.RIGHT);
            }
            else{
                Jeu.getJeu().down();
            }
        }
        return super.onTouchEvent(event);
    }

    public Thread gThread(){
        return drawingThread;
    }

    public boolean getCreated(){return created;}

    /**
     * Classe Thread interne permettant de dessiner la vue du jeu
     */
    private class DrawingThread extends Thread{

        @Override
        public void run() {
                Canvas canvas = null;
                if(!this.isAlive()){
                    Log.d(TAG,"lock donc initialiser canvas = null");
                }
                else{
                    Log.d(TAG,"faire unlock");
                }
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
