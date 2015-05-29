package com.polytech.stfu.ihm;
import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

/**
 * Permet d'afficher les instructions sur l'ecran
 * @see ViewDesign
 */
public class InstructionsActivity extends Activity {
    /**
     * Mise en place des composants de l'interface lors de son ouverture
     * et application du theme
     * @param savedInstanceState    Etat de l'activite
     * @see ViewDesign#changeInstruction(Activity)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_page_instructions);
        ViewDesign.changeInstruction(this);
    }
}
