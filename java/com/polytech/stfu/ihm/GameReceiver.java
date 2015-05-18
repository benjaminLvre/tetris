package com.polytech.stfu.ihm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class GameReceiver extends BroadcastReceiver {

    private TetrisView mView;

    public GameReceiver(TetrisView view){
        this.mView = view;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("GAME_CHANGE")){

        }
    }

}

