package com.polytech.stfu.ihm;
import android.app.Activity;
import android.os.Bundle;



public class InstructionsActivity extends Activity {

    /**
     * Mise en place des composants de l'interface lors de son ouverture
     * @param savedInstanceState    Etat de l'activité
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_instructions);
    }

}
