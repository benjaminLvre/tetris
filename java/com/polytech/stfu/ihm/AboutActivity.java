package com.polytech.stfu.ihm;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

/**
 * Classe permettant de mettre en place la page about
 * @see ViewDesign
 */
public class AboutActivity extends Activity {
    /**
     * Mise en place des composants de l'interface lors de son ouverture
     * et application du bon theme
     * @param savedInstanceState    Etat de l'activite
     * @see ViewDesign#changeAbout(Activity)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about);

        ViewDesign.changeAbout(this);
    }
}