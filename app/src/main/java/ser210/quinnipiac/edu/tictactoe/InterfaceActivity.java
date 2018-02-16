package ser210.quinnipiac.edu.tictactoe;

import android.content.Intent;
import android.media.Image;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class InterfaceActivity extends AppCompatActivity {
    private TicTacToe TTTBoard;
    private int state;
    private TextView textView;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);
        if (savedInstanceState != null) {
            state = savedInstanceState.getInt("state");
        }
        TTTBoard = new TicTacToe();
        name = getIntent().getStringExtra("name");
        textView = (TextView) findViewById(R.id.names);
        textView.setText("Hello " + name + "!!");
        Toast.makeText(this,"Your Turn!", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < 9; i++){
            ((ImageButton) findViewById(R.id.button0 + i)).setBackgroundResource(R.mipmap.githublogo);
        }

    }
    //re-creates the board after each move with the updated moves
    public void makeBoard() {
        doCell(0,R.id.button0);
        doCell(1,R.id.button1);
        doCell(2,R.id.button2);
        doCell(3,R.id.button3);
        doCell(4,R.id.button4);
        doCell(5,R.id.button5);
        doCell(6,R.id.button6);
        doCell(7,R.id.button7);
        doCell(8,R.id.button8);
    }

    //when the reset button is click, clear the board and remake an empty board
    public void onResetClick(View view) {
        TTTBoard.clearBoard();
        state = TTTBoard.checkForWinner();
        this.makeBoard();
        textView.setText("Hello " + name + "!!");
        Toast.makeText(this,"Your Turn!", Toast.LENGTH_SHORT).show();
    }

    //whenever any of the buttons are clicked by the player, set the move, remake the board and check to see if winner
    public void onButtonClick(View view) {
        TTTBoard.setMove(ITicTacToe.CROSS, view.getId() - R.id.button0);
        view.setEnabled(false);
        makeBoard();
        state = TTTBoard.checkForWinner();
        if(state == ITicTacToe.NOUGHT_WON) {
            textView.setText("AI Won Yikess!!");
            freezeButtons();
        }else if (state == ITicTacToe.CROSS_WON) {
            textView.setText("You WON!!");
            freezeButtons();
        }else if (state == ITicTacToe.TIE) {
            textView.setText("You Tied");
        } else if (state == ITicTacToe.PLAYING) {
            AITurn();
        }

    }

    //do AI turn, which is the same as player except it incoorperates the AI instead of player button clicks
    public void AITurn() {
        TTTBoard.setMove(ITicTacToe.NOUGHT, TTTBoard.getComputerMove());
        makeBoard();
        state = TTTBoard.checkForWinner();
        if(state == ITicTacToe.NOUGHT_WON) {
            textView.setText("AI Won Yikess!!");
            freezeButtons();
        } else if (state == ITicTacToe.CROSS_WON) {
            textView.setText("You WON!!");
            freezeButtons();
        } else if (state == ITicTacToe.TIE) {
            textView.setText("You Tied");
        } else if (state == ITicTacToe.PLAYING) {
            textView.setText("AI Moved, Your Turn");
        }
    }

    //used for if the game was won, and there are still empty spaces, makes them unclickable
    public void freezeButtons() {
        for (int i = 0; i < 9; i++) {
            ((ImageButton) findViewById(R.id.button0 + i)).setEnabled(false);
        }
    }

    public void doCell(int i, int id){
        switch (TTTBoard.getCell(i)) {
            case ITicTacToe.CROSS:
                ((ImageButton) findViewById(id)).setBackgroundResource(R.mipmap.xtictac);
                ((ImageButton) findViewById(id)).setEnabled(false);
                break;
            case ITicTacToe.NOUGHT:
                ((ImageButton) findViewById(id)).setBackgroundResource(R.mipmap.otictac);
                ((ImageButton) findViewById(id)).setEnabled(false);
                break;
            case ITicTacToe.EMPTY:
                ((ImageButton) findViewById(id)).setBackgroundResource(R.mipmap.githublogo);
                ((ImageButton) findViewById(id)).setEnabled(true);
                break;
        }
    }


}
