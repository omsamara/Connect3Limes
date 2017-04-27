package com.connect3.bman.connect3limes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    /** 0 means it is the player's turn. 1 means it is the CPU's turn */
    private int playerTurn;

    /** Array of ints representing the game's state. 2 means no player has picked this spot */
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    /**
     * Method that runs when the app is launched.
     * @param savedInstanceState unknown for now
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerTurn = 0;
    }

    /**
     * The onclick method for an ImageView. Drops the image into the appropriate
     * row and column
     * @param view the ImageView that was clicked to run this function
     */
    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int gridPos = Integer.parseInt((String)counter.getTag());
        if (gameState[gridPos] == 2) {
            counter.setTranslationY(-1000f);

            if (playerTurn == 0) {
                gameState[gridPos] = 0;
                counter.setImageResource(R.drawable.lime);
                playerTurn = 1;
            }
            else if (playerTurn == 1){
                gameState[gridPos] = 1;
                counter.setImageResource(R.drawable.lemon);
                playerTurn = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
        }
        else {
            return;
        }



    }
}
