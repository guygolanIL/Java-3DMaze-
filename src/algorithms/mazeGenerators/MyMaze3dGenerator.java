package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/*-------------RECURSIVE BACKTRACKER ALGORITHM--------------
1. Make the initial cell the current cell and mark it as visited
2. While there are unvisited cells
	2.1 If the current cell has any neighbors which have not been visited
		2.1.1 Choose randomly one of the unvisited neighbors
		2.1.2 Push the current cell to the stack
		2.1.3 Remove the wall between the current cell and the chosen cell
		2.1.4 Make the chosen cell the current cell and mark it as visited
	2.2 Else if stack is not empty
		2.2.1 Pop a cell from the stack
		2.2.2 Make it the current cell
	2.3	Else
		2.3.1 Pick a random unvisited cell, make it the current cell and mark it as visited 
*/
/**
 *Implementation for the Recursive Backtracker maze generating algorithm.
 * @author Guy Golan
 *
 */
public class MyMaze3dGenerator extends CommonMazeGenerator {

	@Override
	public Maze3d generate(int xAxis, int yAxis, int zAxis) {		//generating a 3d maze
		int numOfVisitedCells = 0;		//counting the number of cells we have visited
		int totalCells = (xAxis/2)*(yAxis/2)*(zAxis/2);		
		
		Stack<Position> stack = new Stack<Position>();
		boolean[][][] visited = new boolean[xAxis][yAxis][zAxis];		//marking true if we visited on each corresponding cell
		Random r = new Random();
		Maze3d m = new Maze3d(xAxis,yAxis,zAxis);
		
		m.fullWall(); 		//filling the maze with the maximum amount of walls
		
		
		Position currentCell = m.getRandomCell();			//getting a random open cell to start with
		visited[currentCell.getX()][currentCell.getY()][currentCell.getZ()] = true;		//marking the cell; visited
		
		numOfVisitedCells++;
		
		while(numOfVisitedCells < totalCells)		//stops when we visited all the cells
		{
			ArrayList<Position> neighbors = m.getUnvisitedNeighbors(visited, currentCell);	//getting each cell's unvisited neighbor cells
			if(neighbors.size() > 0)	//if there are any neighbors left to visit
			{
				Position newCell = neighbors.get(r.nextInt(neighbors.size()));	//getting a random neighbor
				Position wall = m.eraseWallBetween(currentCell,newCell);	//breaking the wall between the current cell and the chosen random cell
				
				stack.push(currentCell);		
				
				currentCell = newCell;	//making the current cell the new neighbor	
				visited[currentCell.getX()][currentCell.getY()][currentCell.getZ()] = true;		//marking the cell; visited
				numOfVisitedCells++;
			}
			else if(!stack.isEmpty())
			{
				currentCell = stack.pop();
			}
			else
			{
				currentCell = m.getRandomUnvisitedCell(visited);		//jumping to a new random unvisited cell
				visited[currentCell.getX()][currentCell.getY()][currentCell.getZ()] = true;		//marking the cell; visited
				numOfVisitedCells++;
			}
			
		}
		
		m.createEntrance();		//creating an entrance on the surface of the maze
		m.createExit();			//creating an exit on the surface of the maze
		
		
		return m;
	}

}
