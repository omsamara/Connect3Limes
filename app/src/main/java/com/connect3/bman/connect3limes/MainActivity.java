package com.connect3.bman.connect3limes;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

//import static com.connect3.bman.connect3limes.R.id.space0;

public class MainActivity extends AppCompatActivity {

    /** Array of ints representing the game's state. 2 means no player has picked this spot */
    private int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    private int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    private ImageView space0;
    private ImageView space1;
    private ImageView space2;
    private ImageView space3;
    private ImageView space4;
    private ImageView space5;
    private ImageView space6;
    private ImageView space7;
    private ImageView space8;
    private TextView limeWins;
    private TextView lemonWins;
    private TextView draws;
    private Random rng;
    private int numLimeWins;
    private int numLemonWins;
    private int numDraws;
    private boolean gameIsActive;
    private boolean playerCanGo;

    /**
     * Method that runs when the app is launched.
     * @param savedInstanceState unknown for now
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rng = new Random();
        numLemonWins = 0;
        numLimeWins = 0;
        numDraws = 0;
        gameIsActive = true;
        playerCanGo = true;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        numLemonWins = preferences.getInt("lemonWins", 0);
        numLimeWins = preferences.getInt("limeWins", 0);
        numDraws = preferences.getInt("draws", 0);


        space0 = (ImageView) findViewById(R.id.space0);
        space1 = (ImageView) findViewById(R.id.space1);
        space2 = (ImageView) findViewById(R.id.space2);
        space3 = (ImageView) findViewById(R.id.space3);
        space4 = (ImageView) findViewById(R.id.space4);
        space5 = (ImageView) findViewById(R.id.space5);
        space6 = (ImageView) findViewById(R.id.space6);
        space7 = (ImageView) findViewById(R.id.space7);
        space8 = (ImageView) findViewById(R.id.space8);
        limeWins = (TextView) findViewById(R.id.limeWins);
        lemonWins = (TextView) findViewById(R.id.lemonWins);
        draws = (TextView) findViewById(R.id.draws);
        limeWins.setText("Lime Wins: " + numLimeWins);
        lemonWins.setText("Lemon Wins: " + numLemonWins);
        draws.setText("Draws: " + numDraws);
        //currTurn = 0;
    }

    /**
     * The onclick method for an ImageView. Drops the image into the appropriate
     * row and column
     * @param view the ImageView that was clicked to run this function
     */
    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int gridPos = Integer.parseInt((String)counter.getTag());
        if (gameState[gridPos] == 2 && gameIsActive && playerCanGo) {
            counter.setTranslationY(-1000f);
            gameState[gridPos] = 0;
            counter.setImageResource(R.drawable.lime);
            counter.animate().translationYBy(1000f).rotation(360).setDuration(350);
            playerCanGo = false;
            if (winActions()) {
                return;
            }
            new CountDownTimer(500, 100) {

                public void onTick(long millisUntilFinished) {
                    //do nothing
                }
                public void onFinish() {
                    takeCPUTurn();
                    winActions();
                }
            }.start();


        }
        else {
          return;
        }



    }

    public void takeCPUTurn() {


        int spaceToTake = -1;

        if(gameState[0]==2 &&
                ((gameState[1]==1 && gameState[2]==1) ||
                        (gameState[4]==1 && gameState[8]==1) ||
                        (gameState[3]==1 && gameState[6]==1))) {

            spaceToTake = 0;

        } else if (gameState[1]==2 &&
                ((gameState[4]==1 && gameState[7]==1) ||
                        (gameState[0]==1 && gameState[2]==1))) {

            spaceToTake = 1;

        } else if(gameState[2]==2 &&
                ((gameState[0]==1 && gameState[1]==1) ||
                        (gameState[6]==1 && gameState[4]==1) ||
                        (gameState[5]==1 && gameState[8]==1))) {

            spaceToTake = 2;

        } else if(gameState[3]==2 &&
                ((gameState[4]==1 && gameState[5]==1) ||
                        (gameState[0]==1 && gameState[6]==1))){

            spaceToTake = 3;

        } else if(gameState[4]==2 &&
                ((gameState[0]==1 && gameState[8]==1) ||
                        (gameState[1]==1 && gameState[7]==1) ||
                        (gameState[6]==1 && gameState[2]==1) ||
                        (gameState[3]==1 && gameState[5]==1))) {

            spaceToTake = 4;

        } else if(gameState[5]==2 &&
                ((gameState[3]==1 && gameState[4]==1) ||
                        (gameState[2]==1 && gameState[8]==1))) {

            spaceToTake = 5;

        } else if(gameState[6]==2 &&
                ((gameState[0]==1 && gameState[3]==0) ||
                        (gameState[7]==1 && gameState[8]==1) ||
                        (gameState[4]==1 && gameState[2]==1))){

            spaceToTake = 6;

        } else if(gameState[7]==2 &&
                ((gameState[1]==1 && gameState[4]==1) ||
                        (gameState[6]==1 && gameState[8]==1))) {

            spaceToTake = 7;

        }else if( gameState[8]==2 &&
                ((gameState[0]==1 && gameState[4]==1) ||
                        (gameState[2]==1 && gameState[5]==1) ||
                        (gameState[6]==1 && gameState[7]==1))) {

            spaceToTake = 8;

        }
        else if(gameState[0]==2 &&
                ((gameState[1]==0 && gameState[2]==0) ||
                        (gameState[4]==0 && gameState[8]==0) ||
                        (gameState[3]==0 && gameState[6]==0))) {

            spaceToTake = 0;

        } else if (gameState[1]==2 &&
                ((gameState[4]==0 && gameState[7]==0) ||
                        (gameState[0]==0 && gameState[2]==0))) {

            spaceToTake = 1;

        } else if(gameState[2]==2 &&
                ((gameState[0]==0 && gameState[1]==0) ||
                        (gameState[6]==0 && gameState[4]==0) ||
                        (gameState[5]==0 && gameState[8]==0))) {

            spaceToTake = 2;

        } else if(gameState[3]==2 &&
                ((gameState[4]==0 && gameState[5]==0) ||
                        (gameState[0]==0 && gameState[6]==0))){

            spaceToTake = 3;

        } else if(gameState[4]==2 &&
                ((gameState[0]==0 && gameState[8]==0) ||
                        (gameState[1]==0 && gameState[7]==0) ||
                        (gameState[6]==0 && gameState[2]==0) ||
                        (gameState[3]==0 && gameState[5]==0))) {

            spaceToTake = 4;

        } else if(gameState[5]==2 &&
                ((gameState[3]==0 && gameState[4]==0) ||
                        (gameState[2]==0 && gameState[8]==0))) {

            spaceToTake = 5;

        } else if(gameState[6]==2 &&
                ((gameState[0]==0 && gameState[3]==0) ||
                        (gameState[7]==0 && gameState[8]==0) ||
                        (gameState[4]==0 && gameState[2]==0))){

            spaceToTake = 6;

        } else if(gameState[7]==2 &&
                ((gameState[1]==0 && gameState[4]==0) ||
                        (gameState[6]==0 && gameState[8]==0))) {

            spaceToTake = 7;

        }else if( gameState[8]==2 &&
                ((gameState[0]==0 && gameState[4]==0) ||
                        (gameState[2]==0 && gameState[5]==0) ||
                        (gameState[6]==0 && gameState[7]==0))) {

            spaceToTake = 8;

        } else {
            spaceToTake = rng.nextInt(9);
            while (gameState[spaceToTake] != 2) {
                spaceToTake = rng.nextInt(9);
            }
        }    

        //TODO Consider a more efficient method for placing CPU token
        
        if(spaceToTake == 0 && gameIsActive) {

            space0 = (ImageView) findViewById(R.id.space0);
            space0.setTranslationY(-1000f);
            gameState[0] = 1;
            space0.setImageResource(R.drawable.lemon);
            space0.animate().translationYBy(1000f).rotation(360).setDuration(350);

        } else if (spaceToTake == 1 && gameIsActive) {

            space1 = (ImageView) findViewById(R.id.space1);
            space1.setTranslationY(-1000f);
            gameState[1] = 1;
            space1.setImageResource(R.drawable.lemon);
            space1.animate().translationYBy(1000f).rotation(360).setDuration(350);

        } else if (spaceToTake == 2 && gameIsActive) {

            space2 = (ImageView) findViewById(R.id.space2);
            space2.setTranslationY(-1000f);
            gameState[2] = 1;
            space2.setImageResource(R.drawable.lemon);
            space2.animate().translationYBy(1000f).rotation(360).setDuration(350);

        } else if (spaceToTake == 3 && gameIsActive) {

            space3 = (ImageView) findViewById(R.id.space3);
            space3.setTranslationY(-1000f);
            gameState[3] = 1;
            space3.setImageResource(R.drawable.lemon);
            space3.animate().translationYBy(1000f).rotation(360).setDuration(350);

        } else if (spaceToTake == 4 && gameIsActive) {

            space4 = (ImageView) findViewById(R.id.space4);
            space4.setTranslationY(-1000f);
            gameState[4] = 1;
            space4.setImageResource(R.drawable.lemon);
            space4.animate().translationYBy(1000f).rotation(360).setDuration(350);

        } else if (spaceToTake == 5 && gameIsActive) {

            space5 = (ImageView) findViewById(R.id.space5);
            space5.setTranslationY(-1000f);
            gameState[5] = 1;
            space5.setImageResource(R.drawable.lemon);
            space5.animate().translationYBy(1000f).rotation(360).setDuration(350);

        } else if (spaceToTake == 6 && gameIsActive) {

            space6 = (ImageView) findViewById(R.id.space6);
            space6.setTranslationY(-1000f);
            gameState[6] = 1;
            space6.setImageResource(R.drawable.lemon);
            space6.animate().translationYBy(1000f).rotation(360).setDuration(350);

        } else if (spaceToTake == 7 && gameIsActive) {

            space7 = (ImageView) findViewById(R.id.space7);
            space7.setTranslationY(-1000f);
            gameState[7] = 1;
            space7.setImageResource(R.drawable.lemon);
            space7.animate().translationYBy(1000f).rotation(360).setDuration(350);

        } else if (spaceToTake == 8 && gameIsActive) {

            space8 = (ImageView) findViewById(R.id.space8);
            space8.setTranslationY(-1000f);
            gameState[8] = 1;
            space8.setImageResource(R.drawable.lemon);
            space8.animate().translationYBy(1000f).rotation(360).setDuration(350);

        }
        playerCanGo = true;
    }

    public boolean winActions() {
        for (int[] winningPos: winningPositions) {

            if (gameState[winningPos[0]] == gameState[winningPos[1]] &&
                    gameState[winningPos[1]] == gameState[winningPos[2]] &&
                    gameState[winningPos[0]] != 2){

                //someone has won!

                gameIsActive = false;

                String winner = "The Lemons";
                if (gameState[winningPos[0]] == 0) {
                    winner = "You, the Limes,";
                }
                TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                winnerMessage.setText(winner + " have won!");
                LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

                if (winner.equals("The Lemons")) {
                    layout.setBackgroundColor(Color.YELLOW);
                    numLemonWins++;
                    lemonWins.setText("Lemon Wins: " + numLemonWins);
                    saveVariables();
                }
                else {
                    layout.setBackgroundColor(Color.GREEN);
                    numLimeWins++;
                    limeWins.setText("Lime Wins: " + numLimeWins);
                    saveVariables();
                }
                layout.setVisibility(View.VISIBLE);
                return true;

            } else {

                boolean gameIsOver = true;

                for (int counterState : gameState) {

                    if (counterState == 2) gameIsOver = false;
                }

                if (gameIsOver) {

                    //There has been  a draw

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                    winnerMessage.setText("It's a draw!");
                    numDraws++;
                    saveVariables();
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setBackgroundColor(Color.GRAY);
                    layout.setVisibility(View.VISIBLE);
                    draws.setText("Draws: " + numDraws);
                    return true;
                }
            }
        }
        return false;
    }

    public void playAgain(View view) {
        gameIsActive = true;
        playerCanGo = true; 
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    private void saveVariables() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt("draws", numDraws);
        editor.apply();

        editor.putInt("lemonWins", numLemonWins);
        editor.apply();

        editor.putInt("limeWins", numLimeWins);
        editor.apply();
    }

    public void resetWins(View view) {
        numLemonWins = 0;
        lemonWins.setText("Lemons wins: " + numLemonWins);
        numLimeWins = 0;
        limeWins.setText("Lime wins: " + numLimeWins);
        numDraws = 0;
        draws.setText("Draws: " + numDraws);
        saveVariables();
    }
}
