package jason.firstgame;
import android.util.Log;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class BuildPuzzle 
{
	public int[][] solutionGrid = new int[3][3];
	public int[] solutionValues = standardValues();

	public int[][] puzzleGrid = new int[3][3];
	public int[] puzzleValues = standardValues();

	public String[][] tileID = new String[3][3];

	public int[] zeroPosition = new int[2];

	public int moves = 0;

	public int inversions = 0;

	public BuildPuzzle()/*(String[] args)*/
	{
		//Create the solved grid

		makeGrid(solutionGrid,solutionValues);



		
		//Create the puzzle grid

		shuffleValues(puzzleValues);
		while(isLegitPuzzle() == false){
			shuffleValues(puzzleValues);
		}
		makeGrid(puzzleGrid,puzzleValues);
		findZero();


	
		//Print both grids
		/*printGrid(solutionGrid);
		printGrid(puzzleGrid);*/
	}
	private void makeGrid(int[][] grid,int[] values)
	{
		//Makes 2D 3x3 array from 1D array of values
		int i,j;
		int k = 0;
		
		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				grid[i][j] = values[k];
				k++;
			}
		}
		
	}
	private String printGrid(int[][] grid)
	{
		int i,j;
		String gridOutput = "";
		
		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				//System.out.print(grid[i][j] + " ");
				if (gridOutput.length() != 0)
				gridOutput = gridOutput.concat(Integer.toString(grid[i][j])+" ");
				else
				gridOutput = Integer.toString(grid[i][j])+" ";
			}
			//System.out.print("\n");
			gridOutput = gridOutput.concat("\n");
		}
		//System.out.print("\n");
		gridOutput = gridOutput.concat("\n");
		return gridOutput;
	}
	private static int[] standardValues()
	{
		//Hard-codes standard values into 1D array
		int[] gridValues = new int[9];
		
		gridValues[0] = 0;
		gridValues[1] = 1;
		gridValues[2] = 2;
		gridValues[3] = 3;
		gridValues[4] = 4;
		gridValues[5] = 5;
		gridValues[6] = 6;
		gridValues[7] = 7;
		gridValues[8] = 8;
		
		return gridValues;
	}
	private void shuffleValues(int[] values)
	{
		Random rnd = ThreadLocalRandom.current();
		
		//count down i index to 0
		for (int i = values.length - 1; i > 0; i--)
	    {
	      //Pick random index between 0 and current i
	      int randomIndexBelow_i = rnd.nextInt(i + 1);
	      
	      //swap with index at i
	      int temp = values[randomIndexBelow_i];
	      values[randomIndexBelow_i] = values[i];
	      values[i] = temp;
	    }
	}
	public void findZero()
	{
		for(int i = 0;i<=2;i++){
			for(int j = 0;j<=2;j++){
				if(puzzleGrid[i][j] == 0){
					zeroPosition[0] = i;
					zeroPosition[1] = j;
					return;
				}
			}
		}
	}
	public void handleMove(String move)
	{
		//Makes changes to number grid based on swipe
		switch(move){
			case "up":
				if(zeroPosition[0] < 2){
					puzzleGrid[zeroPosition[0]][zeroPosition[1]] = puzzleGrid[zeroPosition[0]+ 1][zeroPosition[1]];
					puzzleGrid[zeroPosition[0]+ 1][zeroPosition[1]] = 0;
					zeroPosition[0]++;
					moves++;
				}
				break;
			case"down":
				if(zeroPosition[0] > 0){
					puzzleGrid[zeroPosition[0]][zeroPosition[1]] = puzzleGrid[zeroPosition[0]- 1][zeroPosition[1]];
					puzzleGrid[zeroPosition[0]- 1][zeroPosition[1]] = 0;
					zeroPosition[0]--;
					moves++;
				}
				break;
			case"left":
				if(zeroPosition[1] < 2){
					puzzleGrid[zeroPosition[0]][zeroPosition[1]] = puzzleGrid[zeroPosition[0]][zeroPosition[1]+1];
					puzzleGrid[zeroPosition[0]][zeroPosition[1]+1] = 0;
					zeroPosition[1]++;
					moves++;
				}
				break;
			case"right":
				if(zeroPosition[1] > 0){
					puzzleGrid[zeroPosition[0]][zeroPosition[1]] = puzzleGrid[zeroPosition[0]][zeroPosition[1]-1];
					puzzleGrid[zeroPosition[0]][zeroPosition[1]-1] = 0;
					zeroPosition[1]--;
					moves++;
				}
				break;
		}


	}
	public boolean isLegitPuzzle()
	{
		boolean isLegit;
		inversions = 0;
		//Count inversions
		for(int i = 0;i<8;i++){
			for(int j = i+1;j<=8;j++){
				if((puzzleValues[i] > puzzleValues[j])&& puzzleValues[j] != 0){
					inversions++;
				}
			}
		}
		//If statements on whether number is odd or even
		if(inversions % 2 == 1){
			isLegit = false;
		}else{
			isLegit = true;
		}
		return isLegit;
	}
	public void setTileIDGrid()
	{

		for(int i = 0;i<=2;i++){
			for(int j = 0;j<=2;j++){
				if(puzzleGrid[i][j] == 0){
					tileID[i][j] = "empty_tile";
				}else {
					tileID[i][j] = "tile" + (puzzleGrid[i][j]+1);
				}
			}
		}
	}
	public String printSolutionGrid()
	{
		return printGrid(this.solutionGrid);
	}
	public String printPuzzleGrid() { return printGrid(this.puzzleGrid); }
}
