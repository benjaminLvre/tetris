package com.polytech.ben.tetris;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PauseDialog extends DialogFragment {

    public PauseDialog(){};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_pause, container);
        getDialog().setTitle("Pause");

        return view;
    }

    public void seeHighscores(){}
    public void restartGame(){

    }
}
