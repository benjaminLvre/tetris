package com.polytech.stfu.ihm;


import android.view.MotionEvent;

import com.polytech.stfu.jeu.Jeu;
import com.polytech.stfu.jeu.TypeMove;

public class MotionControl {

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int quarter = getWidth()/4;
        // Clic sur l'ecran
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(event.getX()< quarter){
                //Toast.makeText(this.mContext, "move left ", Toast.LENGTH_SHORT).show();
                Jeu.getJeu().move(TypeMove.LEFT);
            }
            else if(event.getX() > quarter && event.getX() < 3*quarter){
                //Toast.makeText(this.mContext, "rotate piece ", Toast.LENGTH_SHORT).show();
                Jeu.getJeu().rotate();
            }
            else{
                //Toast.makeText(this.mContext, "move right ", Toast.LENGTH_SHORT).show();
                Jeu.getJeu().move(TypeMove.RIGHT);
            }
        }
        return super.onTouchEvent(event);
    }

}
