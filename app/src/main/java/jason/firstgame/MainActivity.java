package jason.firstgame;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GestureDetector.OnGestureListener{

    GestureDetector detector;
    BuildPuzzle puzzleTest;
    TextView showPuzzleArray;
    TextView moveCounter;
    ImageView img0;
    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    ImageView img6;
    ImageView img7;
    ImageView img8;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        startGame();

        detector = new GestureDetector(this, this);




    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return detector.onTouchEvent(event);
    }

    //Displays puzzle after button press
    public void startGame() {
        showPuzzleArray = (TextView) findViewById(R.id.puzzleArray);
        moveCounter = (TextView) findViewById(R.id.textView);
        Button button1 = (Button) findViewById(R.id.newPuzzleButton);
        button1.setOnClickListener(MainActivity.this);

        puzzleTest = new BuildPuzzle();





    }
    /*ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(ImageView,
            PropertyValuesHolder.ofFloat("scaleX", 1.2f),
            PropertyValuesHolder.ofFloat("scaleY", 1.2f));
    scaleDown.setDuration(310);

    scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
    scaleDown.setRepeatMode(ObjectAnimator.REVERSE);

    scaleDown.start();*/

    public void setTiles(){
        img0 = (ImageView) findViewById(R.id.imageView);
        img0.setImageResource(this.getResources().
                getIdentifier(puzzleTest.tileID[0][0], "drawable", this.getPackageName()));
        img1 = (ImageView) findViewById(R.id.imageView2);
        img1.setImageResource(this.getResources().
                getIdentifier(puzzleTest.tileID[0][1], "drawable", this.getPackageName()));
        img2 = (ImageView) findViewById(R.id.imageView3);
        img2.setImageResource(this.getResources().
                getIdentifier(puzzleTest.tileID[0][2], "drawable", this.getPackageName()));
        img3 = (ImageView) findViewById(R.id.imageView4);
        img3.setImageResource(this.getResources().
                getIdentifier(puzzleTest.tileID[1][0], "drawable", this.getPackageName()));
        img4 = (ImageView) findViewById(R.id.imageView5);
        img4.setImageResource(this.getResources().
                getIdentifier(puzzleTest.tileID[1][1], "drawable", this.getPackageName()));
        img5 = (ImageView) findViewById(R.id.imageView6);
        img5.setImageResource(this.getResources().
                getIdentifier(puzzleTest.tileID[1][2], "drawable", this.getPackageName()));
        img6 = (ImageView) findViewById(R.id.imageView7);
        img6.setImageResource(this.getResources().
                getIdentifier(puzzleTest.tileID[2][0], "drawable", this.getPackageName()));
        img7 = (ImageView) findViewById(R.id.imageView8);
        img7.setImageResource(this.getResources().
                getIdentifier(puzzleTest.tileID[2][1], "drawable", this.getPackageName()));
        img8 = (ImageView) findViewById(R.id.imageView9);
        img8.setImageResource(this.getResources().
                getIdentifier(puzzleTest.tileID[2][2], "drawable", this.getPackageName()));
    }

    public void moveOutput(){

        puzzleTest.setTileIDGrid();
        setTiles();
        if(Arrays.deepEquals(puzzleTest.puzzleGrid,puzzleTest.solutionGrid)){
            showPuzzleArray.setText("You win");
        }else{
            showPuzzleArray.setText(puzzleTest.printPuzzleGrid());
            moveCounter.setText(Integer.toString(puzzleTest.moves));
        }
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.newPuzzleButton:
                //BuildPuzzle Class generates Puzzle and Solution Grids
                puzzleTest = new BuildPuzzle();
                puzzleTest.setTileIDGrid();
                setTiles();

                showPuzzleArray.setText(puzzleTest.printPuzzleGrid());
                moveCounter.setText(Integer.toString(puzzleTest.moves));


                break;
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
       //Toast.makeText(getApplicationContext(),"Down Swipe",Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float deltaX = abs(e1.getX() - e2.getX());
        float deltaY = abs(e1.getY() - e2.getY());
        float slope = deltaX - deltaY ;
        if (slope > 0){
            if (e1.getX() < e2.getX()){
                //Toast.makeText(getApplicationContext(),"Right Swipe",Toast.LENGTH_SHORT).show();
                puzzleTest.handleMove("right");
                moveOutput();
            }else if(e1.getX() > e2.getX()){
                //Toast.makeText(getApplicationContext(),"Left Swipe",Toast.LENGTH_SHORT).show();
                puzzleTest.handleMove("left");
                moveOutput();
            }
        }else if (slope < 0){
            if(e1.getY() < e2.getY()){
               //Toast.makeText(getApplicationContext(),"Down Swipe",Toast.LENGTH_SHORT).show();
                puzzleTest.handleMove("down");
                moveOutput();
            }else if(e1.getY() > e2.getY()){
                //Toast.makeText(getApplicationContext(),"Up Swipe",Toast.LENGTH_SHORT).show();
                puzzleTest.handleMove("up");
                moveOutput();
            }
        }

        return true;
    }
}
