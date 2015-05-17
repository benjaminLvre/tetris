package com.polytech.ben.tetris;


import android.app.FragmentManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TetrisView tetrisView = new TetrisView(this);
        setContentView(tetrisView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            this.showPauseDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showPauseDialog() {
        FragmentManager fm = getFragmentManager();
        PauseDialog pauseDialog = new PauseDialog();
        pauseDialog.show(fm,"salut");
    }
}
