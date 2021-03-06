// MODIFY stackSolve() in this file

import java.util.Stack;
import java.util.Scanner;

public class StackSolver
{
	public int[][] stackmaze;
	public int dimension;

	StackSolver( int[][] inmaze )
	{
		// deep copy
		stackmaze = new int[inmaze[0].length][inmaze[0].length];
		for ( int ii = 0; ii < inmaze[0].length; ii++ )
		{
			for ( int jj = 0; jj < inmaze[ii].length; jj++ )
			{
				stackmaze[ii][jj] = inmaze[ii][jj];
			}
		}

		dimension = inmaze[0].length; // assuming square maze
	}

	public void showMaze()
	{
		// clear the screen
		System.out.print("\033[H\033[2J");
		System.out.flush();

		// print maze
		for ( int ii = 0; ii < dimension; ii++ )
		{
			for ( int jj = 0; jj < dimension; jj++ )
			{
				System.out.print( stackmaze[ii][jj] + " " );
			}
			System.out.println();
		}
	}

	// uses Coordinate (with 2 parameter constructor)
	// solves the maze with a stack
	// does not modify any global state
	public void stackSolve()
	{
		// row & col are our current position, tracks coordinates of which space we are currently on
		// start at (0, 0)
		int col = 0;
		int row = 0;

		// create stack
		Stack<Coordinate> myStack = new Stack<Coordinate>();
		// mark starting space as explored
		stackmaze[0][0] = 0;
		// set coordinate to push on stack
		Coordinate myC = new Coordinate ( 0, 0 );
		// push first starting space onto stack
		myStack.push( myC );

		// FOR DEBUGGING: scanner for showing maze step by step
		Scanner myScan = new Scanner( System.in );

		// iteratively push surrounding spaces onto stack and move
		// loop while top of stack is NOT the finish position in the maze
		while ( stackmaze[myStack.peek().x][myStack.peek().y] != 3 )
		{
			// COMMENT THIS BREAK OUT WHEN WORKING ON STACKSOLVE()
			//break;

			// FOR DEBUGGING: print maze, wait for user to go to next step
			//showMaze();
			//myScan.nextLine();

			// WRITE YOUR CODE HERE, KEEP THIS ORDERING (UP, DOWN, LEFT, RIGHT)
			row = myStack.peek().x;
			col = myStack.peek().y;

			if ( ((row-1) >= 0) && (stackmaze[row-1][col]>=2) ) //explore up only if it is unexplored/solved and possible
			{
					if( stackmaze[row-1][col] == 3) //check if finished
					{
						myStack.push( new Coordinate(row-1, col));
					}
					else
					{
						stackmaze[row-1][col] = 0;
						myStack.push( new Coordinate(row-1, col));
					}
			}

			else if ( ((row+1) < dimension) && (stackmaze[row+1][col]>=2)) //explore down only if it is unexplored/solved and possible
			{
					if( stackmaze[row+1][col] == 3) //check if finished
					{
						myStack.push( new Coordinate(row+1, col));
					}
					else
					{
						stackmaze[row+1][col] = 0;
						myStack.push( new Coordinate( row+1, col));
					}
			}

			else if ( ((col-1) >= 0) && (stackmaze[row][col-1]>=2)) //explore left only if it is unexplored/solved and possible
			{
					if( stackmaze[row][col-1] == 3) // check if finished
					{
						myStack.push( new Coordinate(row, col-1));
					}
					else
					{
						stackmaze[row][col-1] = 0;
						myStack.push( new Coordinate( row, col-1));
					}
			}

			else if ( ((col+1) < dimension) && (stackmaze[row][col+1]>=2)) //explore right only if it is unexplored/solved and possible
			{
					if ( stackmaze[row][col+1] == 3) // check if finished
					{
						myStack.push( new Coordinate(row, col+1));
					}
					else
					{
						stackmaze[row][col+1] = 0;
						myStack.push( new Coordinate( row, col+1));
					}
			}
			else  if((myStack.peek().x == row) && (myStack.peek().y == col)) //if no where else to go, pop off stack and run again
			 {
					 myStack.pop();
			 }



			// explore up && make sure space is not explored before and not a wall
				// if can explore, push onto stack
				// remember to set explored spaces as 0 in maze (but leave finish as 3 in map)
				// remember to set new current space (row, col variables)

			// otherwise, if can't explore up, explore down && make sure space is not explored before and not a wall
			// otherwise, if can't explore down, explore left && make sure space is not explored before and not a wall
			// otherwise, if can't explore left, explore right && make sure space is not explored before and not a wall

			// otherwise, no other immediate spaces can be explored (we cannot move), pop and update current position
		}

		// print coordinates from stack
		System.out.println( "Finished stack solver!" );
		while ( !myStack.isEmpty() )
		{
			Coordinate tempC = myStack.pop();
			System.out.println( tempC.x + " " + tempC.y + " " );
		}

		// wait between stack result and start of queue solver
		//myScan.nextLine();
	}

	// 10 pts extra credit, solve the maze recursively with a stack
	//public void stackSolveRecursive(/* use whatever parameters you need */)
	//{
	//}
}
