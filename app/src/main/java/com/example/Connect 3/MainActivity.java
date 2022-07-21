package com.example.game;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextClassifierEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    int activeplayer=0;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winningpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int count=0;
    boolean gameactive=true;
    public void dropIn(View view)
    {
        ImageView counter=(ImageView)view;
        Log.i("Tag",counter.getTag().toString());
        int tappedcounter=Integer.parseInt(counter.getTag().toString());
        if(gamestate[tappedcounter]==2 && gameactive) {
            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1500);
            if (activeplayer == 0) {
                activeplayer = 1;
                counter.setImageResource(R.drawable.red);
            } else if (activeplayer == 1) {
                activeplayer = 0;
                counter.setImageResource(R.drawable.yellow);
            }
            counter.animate().translationYBy(1500).rotation(1800).setDuration(400);
            for (int[] winningpos : winningpos) {
                if (gamestate[winningpos[0]] == gamestate[winningpos[1]] && gamestate[winningpos[1]] == gamestate[winningpos[2]] && gamestate[winningpos[0]] != 2) {
                    gameactive=false;
                    String message = " ";
                    if (activeplayer == 1) {
                        message = "Red";
                    } else {
                        message = "Yellow";
                    }
                    Toast.makeText(this, message + " has won!", Toast.LENGTH_SHORT).show();
                    Button playagainbutton=(Button)findViewById(R.id.playagainbutton);
                    playagainbutton.setVisibility(View.VISIBLE);
                    TextView winnertext=(TextView)findViewById(R.id.winnertext);
                    winnertext.setText(message+" has won the game!");
                    winnertext.setVisibility(View.VISIBLE);
                }
            }
            count++;
            if(count>8)
            {
                Button playagainbutton=(Button)findViewById(R.id.playagainbutton);
                playagainbutton.setVisibility(View.VISIBLE);
                TextView winnertext=(TextView)findViewById(R.id.winnertext);
                winnertext.setText("It's a draw!");
                winnertext.setVisibility(View.VISIBLE);
                gameactive=false;
            }
        }
    }
    public void restart(View view)
    {
        Button playagainbutton=(Button)findViewById(R.id.playagainbutton);
        playagainbutton.setVisibility(View.INVISIBLE);
        TextView winnertext=(TextView)findViewById(R.id.winnertext);
        winnertext.setVisibility(View.INVISIBLE);
        GridLayout mylayout=null;
            mylayout = (GridLayout) findViewById(R.id.gridLayout);
            for (int i = 0; i < mylayout.getChildCount(); i++) {
                ImageView counter=(ImageView)mylayout.getChildAt(i);
                counter.setImageDrawable(null);
            }
            for (int i = 0; i < gamestate.length; i++) {
                gamestate[i] = 2;
            }
            activeplayer=0;
            gameactive = true;
            count=0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}