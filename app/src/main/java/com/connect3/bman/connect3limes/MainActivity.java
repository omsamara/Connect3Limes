package com.connect3.bman.connect3limes;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import static com.connect3.bman.connect3limes.R.id.sapce0;

public class MainActivity extends AppCompatActivity {

    /** 0 means it is the player's turn. 1 means it is the CPU's turn */
    private int currTurn;

    /** Array of ints representing the game's state. 2 means no player has picked this spot */
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    ImageView space0;
    ImageView space1;
    ImageView space2;
    ImageView space3;
    ImageView space4;
    ImageView space5;
    ImageView space6;
    ImageView space7;
    ImageView space8;

    /**
     * Method that runs when the app is launched.
     * @param savedInstanceState unknown for now
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currTurn = 0;
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
            gameState[gridPos] = 0;
            counter.setImageResource(R.drawable.lime);
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            new CountDownTimer(400, 100) {

                public void onTick(long millisUntilFinished) {
                    //do nothing
                }
                public void onFinish() {
                    takeCPUTurn();
                }
            }.start();


        }
        else {
          return;
        }



    }

    public void takeCPUTurn() {
        space0 = (ImageView) findViewById(R.id.sapce0);
        space0.setTranslationY(-1000f);
        gameState[0] = 1;
        space0.setImageResource(R.drawable.lemon);
        space0.animate().translationYBy(1000f).rotation(360).setDuration(300);
    }
}
