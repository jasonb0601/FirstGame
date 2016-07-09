package jason.firstgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGame();



    }

    //Displays puzzle after button press
    public void startGame() {
        //BuildPuzzle Class generates Puzzle and Solution Grids
        BuildPuzzle puzzleTest = new BuildPuzzle();

        TextView showPuzzleArray = (TextView) findViewById(R.id.puzzleArray);
        showPuzzleArray.setText(puzzleTest.printPuzzleGrid());
    }

}
