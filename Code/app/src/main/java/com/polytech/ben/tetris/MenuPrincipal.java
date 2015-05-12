package com.polytech.ben.tetris;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

import android.view.View;


public class MenuPrincipal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void seeInstructions(View view){
        Intent instructionsActivite = new Intent(MenuPrincipal.this, InstructionsActivity.class);
        startActivity(instructionsActivite);
    }

    public void changeOptions(View view){
        Intent optionsActivite = new Intent(MenuPrincipal.this, OptionsActivity.class);
        startActivity(optionsActivite);

    }

}
