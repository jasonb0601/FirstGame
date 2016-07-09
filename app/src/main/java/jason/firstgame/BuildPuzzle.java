package jason.firstgame;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class BuildPuzzle 
{
	public int[][] solutionGrid = new int[3][3];
	public int[] solutionValues = standardValues();

	public int[][] puzzleGrid = new int[3][3];
	public int[] puzzleValues = standardValues();

	public BuildPuzzle()/*(String[] args)*/
	{
		//Create the solved grid

		makeGrid(solutionGrid,solutionValues);
		
		//Create the puzzle grid

		shuffleValues(puzzleValues);
		makeGrid(puzzleGrid,puzzleValues);
	
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
		
		gridValues[0] = 1;
		gridValues[1] = 2;
		gridValues[2] = 3;
		gridValues[3] = 4;
		gridValues[4] = 5;
		gridValues[5] = 6;
		gridValues[6] = 7;
		gridValues[7] = 8;
		gridValues[8] = 0;
		
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
	public String printSolutionGrid()
	{
		return printGrid(this.solutionGrid);
	}
	public String printPuzzleGrid() { return printGrid(this.puzzleGrid); }
}
