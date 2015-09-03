package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

/**
 * Maze3d represents a 3D maze. 
 * @author Guy Golan
 *
 */
public class Maze3d {

	private final int xAxis;			////maze dimensions
	private final int yAxis;
	private final int zAxis;
	
	private Position entrance;			//maze's entrance and exit 
	private Position exit;
	
	private int[][][] arr;				//array of 1 and 0
	
	/**
	 * Ctor. <br>
	 * Booting arr with a new 3D array of int and fills it with '0'.<br>
	 * Booting entrance and exit to null.<br>
	 * Booting x,y,z accordingly.
	 * 
	 * @param x - the maze's X dimension.
	 * @param y - the maze's Y dimension.
	 * @param z - the maze's Z dimension.
	 */
	public Maze3d(int x, int y, int z) {		//Ctor
		this.xAxis = x;
		this.yAxis = y;
		this.zAxis = z;
		
		arr = new int[x][y][z];
		
		for(int i = 0 ;i < x ; i++ ){					
			for(int j = 0 ; j < y ; j++){			//setting the whole maze to 0
				for(int h = 0 ; h < z ; h++){
					arr[i][j][h] = 0;
				}
			}
		}
		
		this.entrance = null;		//default enter and exit
		this.exit = null;
	}
	
	/**
	 * fullWall is used to fill the maze with "cells" , creating a grid.
	 */
	public void fullWall(){				//fill the whole maze with walls
		
	
		for(int i = 0 ;i < this.getxAxis() ; i++ ){
			for(int j = 0 ; j < this.getyAxis() ; j++){
				for(int h = 0 ; h < this.getzAxis() ; h++){				//creating a grid of 1 and 0 (an outer shell of the maze)
					this.arr[i][j][h] = 1;
						if(i % 2 != 0 && j % 2 != 0 && h % 2 != 0){
							this.arr[i][j][h] = 0;
						}

				}
			}
		}
		
	}
	
	/**
	 * The method is used to get a certain Position's possible movement options.
	 * @param p - Position.
	 * @return String[] which contains the moves the player can take from a given Position.
	 */
	public String[] getPossibleMoves(Position p){		//getting all possible moves given a certain position
		int count = 0 ;
		ArrayList<String> directions = new ArrayList<String>();
		if(p.getX()+1 <= this.getxAxis()-1 && this.arr[p.getX()+1][p.getY()][p.getZ()] == 0)		//checks for each dimension if we reached the maximum or minimum value of there isnt a wall there
		{
			count++;
			directions.add("UP");
		}
		if(p.getX()-1 >= 0 && this.arr[p.getX()-1][p.getY()][p.getZ()] == 0)
		{
			count++;
			directions.add("DOWN");
		}
		if(p.getY()+1 <= this.getyAxis()-1 && this.arr[p.getX()][p.getY()+1][p.getZ()] == 0)
		{
			count++;
			directions.add("FORWARD");
		}
		if(p.getY()-1 >= 0 && this.arr[p.getX()][p.getY()-1][p.getZ()] == 0)
		{
			count++;
			directions.add("BACKWARD");
		}
		if(p.getZ()+1 <= this.getzAxis()-1 && this.arr[p.getX()][p.getY()][p.getZ()+1] == 0)
		{
			count++;
			directions.add("LEFT");
		}
		if(p.getZ()-1 >= 0 && this.arr[p.getX()][p.getY()][p.getZ()-1] == 0)
		{
			count++;
			directions.add("RIGHT");
		}
		
		String[] possibleMoves = new String[count];
		for(int i=0 ; i < count ; i++)
		{
			possibleMoves[i] = directions.get(i);
		}
		
		return possibleMoves;
	}
	
	/**
	 * The method is used in the generate algorithm in order to find a random cell inside the maze.
	 * @return Position - the position of the random cell.
	 */
	public Position getRandomCell(){			//getting a random Cell inside the maze
		Random r = new Random();
		int x = 0 , y = 0 , z = 0;
		
		while(this.arr[x][y][z]!=0)
		{
			x = 1 + r.nextInt(xAxis-1);
			y = 1 + r.nextInt(yAxis-1);
			z = 1 + r.nextInt(zAxis-1);
		}
		Position answer = new Position(x,y,z);
		return answer;
	}
	
	/**
	 * The method is used to find a random unvisited cell in the maze.
	 * @param visited - a boolean[][][] that specify which cell hasn't been visited yet.
	 * @return Position - the position of the random unvisited cell.
	 */
	public Position getRandomUnvisitedCell(boolean[][][] visited){	//returns a position of a random unvisited cell
		int x = 0, y = 0 , z = 0;
		Random r = new Random();
		
		while(visited[x][y][z] == true)
		{
			x = 1 + r.nextInt(xAxis-1);
			y = 1 + r.nextInt(yAxis-1);
			z = 1 + r.nextInt(zAxis-1);
			
		}
		
		return new Position(x, y, z);
	}
	
	
	
	/**
	 * Used to create an entrance and an exit on opposite edges of the maze.
	 */
	public void createEnteranceExit(){			//creating an entrance and exit on two opposite edges of the simple maze
		
		int x = 0, y = 0, z = 0;
		Random r = new Random();
		int edge = r.nextInt(6);
		
			x = r.nextInt(xAxis-2)+1;
			y = r.nextInt(yAxis-2)+1;
			z = r.nextInt(zAxis-2)+1;
		
		switch (edge) {
		case 0:
			z = 0;
			break;
		case 1:
			z = zAxis - 1;
			break;
		case 2:
			y = 0;
			break;
		case 3:
			y = yAxis - 1;
			break;
		case 4:
			x = 0;
			break;
		case 5:
			x = xAxis - 1;
			break;
		
		default:
			break;
		}
		
		this.entrance = new Position(x,y,z);
		
		x = r.nextInt(xAxis-2)+1;
		y = r.nextInt(yAxis-2)+1;
		z = r.nextInt(zAxis-2)+1;
		
		switch (edge) {
		case 0:
			z = this.getzAxis() -1 ; 
			break;
		case 1:
			z = 0;
			break;
		case 2:
			y = this.getyAxis() - 1;
			break;
		case 3:
			y = 0;
			break;
		case 4:
			x = this.getxAxis() - 1;
			break;
		case 5:
			x = 0;
			break;
		default:
			break;
		}
		
		this.exit = new Position(x,y,z);
		
	}
	
	/**
	 * Used to 'carve' an exit on one of the edges of the maze.
	 * @return Position - the position of the exit.
	 */
	public Position createExit() {				//creates an exit on one of the edges
		int x = 0 , y = 0 , z = 0; 
		Random r = new Random();
		
		Position exit =new Position(this.getEntrance());
		int edge = r.nextInt(6);
		
		while(exit.equals(this.getEntrance()))		//searching for a new exit if exit is the entrance also
		{
			while(x % 2 ==0 || y % 2 == 0 || z % 2 == 0)	//searches for an odd dimensions
			{
				x = r.nextInt(xAxis-2)+1;
				y = r.nextInt(yAxis-2)+1;
				z = r.nextInt(zAxis-2)+1;
			}
			switch (edge) {				//the switch case determines on which edge to "stick" the exit on
			case 0:
				z = 0;
				break;
			case 1:
				z = zAxis - 1;
				break;
			case 2:
				y = 0;
				break;
			case 3:
				y = yAxis - 1;
				break;
			case 4:
				x = 0;
				break;
			case 5:
				x = xAxis - 1;
				break;
			
			default:
				break;
			}
			exit = new Position(x,y,z);
		}
		
		
		this.setExit(exit);
		this.eraseWall(this.getExit());
		return exit;
		
	}
	
	/**
	 * Used to 'carve' a new entrance for the maze.
	 * @return Position - the new entrance.
	 */
	public Position createEntrance(){			//creates an entrance on one of the edges
		
		int x = 0, y = 0, z = 0;
		Random r = new Random();
		int edge = r.nextInt(6);
		
		while(x % 2 ==0 || y % 2 == 0 || z % 2 == 0)		//searches for an odd dimensions
		{
			x = r.nextInt(xAxis-2)+1;
			y = r.nextInt(yAxis-2)+1;
			z = r.nextInt(zAxis-2)+1;
		}
		switch (edge) {					//the switch case determines on which edge to "stick" the exit on
		case 0:
			z = 0;
			break;
		case 1:
			z = zAxis - 1;
			break;
		case 2:
			y = 0;
			break;
		case 3:
			y = yAxis - 1;
			break;
		case 4:
			x = 0;
			break;
		case 5:
			x = xAxis - 1;
			break;
		
		default:
			break;
		}
		
		this.entrance = new Position(x,y,z);
		this.eraseWall(this.getEntrance());
		return entrance;
		
	}
	
	/**
	 * The method used to find the unvisited neighbors of a give Position.
	 * @param visited - a boolean[][][] which specify which cell has been visited.
	 * @param p - Position - the position which we want to find it's unvisited neighbors.
	 * @return - ArrayList{Position} that contains the position of the unvisited neighbors of p.
	 */
	public ArrayList<Position> getUnvisitedNeighbors(boolean[][][] visited,Position p){	//returns a ArrayList of positions of the given position's adjacent unvisited neighbors
		
		ArrayList<Position> arr = new ArrayList<Position>();
		
		int x = p.getX();
		int y = p.getY();
		int z = p.getZ();
		 
		
		//checking the boundaries of x,y,z.
		if( x < this.xAxis - 2)
		{
			if(visited[x+2][y][z] == false)
			{
				arr.add(new Position( x + 2 , y , z ));
			}
		}
		if(x - 2 > 0)
		{
			if(visited[x-2][y][z] == false)
			{
				arr.add(new Position( x - 2 , y , z ));
			}
		}
		if( y < this.yAxis - 2)
		{
			if(visited[x][y+2][z] == false)
			{
				arr.add(new Position( x , y + 2 , z ));
			}
		}
		if(y - 2 > 0)
		{
			if(visited[x][y-2][z] == false)
			{
				arr.add(new Position( x , y - 2 , z ));
			}
		}
		if( z < this.zAxis - 2)
		{	
			if(visited[x][y][z+2] == false)
			{
				arr.add(new Position( x , y , z + 2 ));
			}
		}
		if( z - 2 > 0)
		{
			if(visited[x][y][z-2] == false)
			{
				arr.add(new Position( x , y , z - 2 ));
			}
		}
		
		
		return arr;
		
	}
	
	/**
	 * erases the wall between two Positions.
	 * @param currentCell - Position.
	 * @param newCell - Position.
	 * @return Position - the position of the erased wall.
	 */
	public Position eraseWallBetween(Position currentCell , Position newCell){		//given to positions the method erases the wall between them
		
		int x = currentCell.getX(), y = currentCell.getY(), z = currentCell.getZ();
		
		if(currentCell.getX() != newCell.getX())
		{
			if(currentCell.getX() > newCell.getX())
			{
				x = currentCell.getX() - 1;
			}
			else
			{
				x = currentCell.getX() + 1;
			}
		}
		else if(currentCell.getY() != newCell.getY())
		{
			if(currentCell.getY() > newCell.getY())
			{
				y = currentCell.getY() - 1;
			}
			else
			{
				y = currentCell.getY() + 1;
			}
		}
		else if(currentCell.getZ() != newCell.getZ())
		{
			if(currentCell.getZ() > newCell.getZ())
			{
				z = currentCell.getZ() - 1;
			}
			else
			{
				z = currentCell.getZ() + 1;
			}
		}
		Position wall = new Position(x, y, z);
		this.eraseWall(wall);
		return  wall;
	}
	
	/**
	 * erases a wall in a given Position in the maze.
	 * @param e - Position.
	 */
	public void eraseWall(Position e){					//put 0 in a given position
		this.arr[e.getX()][e.getY()][e.getZ()] = 0;
	}
	
	/**
	 * creates a wall in a given Position in the maze.
	 * @param e - Position.
	 */
	public void buildWall(Position e){					//put 1 in a given position
		this.arr[e.getX()][e.getY()][e.getZ()] = 1;
	}
	
	
	/**
	 * Prints the whole maze to the console in a graphic manner.
	 */
	public void printMaze(){							//prints the whole maze
		
		for(int i = 0 ; i < this.xAxis ; i++){
			for(int j = 0 ; j < this.yAxis; j++){
				for(int h = 0 ; h < this.zAxis ; h++){
					System.out.print(this.arr[i][j][h]+" ");
					
				}
				System.out.println("");
			}
			System.out.println("");
		}
		
		
	}
													
	public Position getEntrance(){return this.entrance;}			
	
	public Position getExit(){return this.exit;}
	
	public void setEntrance(Position entrance){this.entrance = entrance;}
	public void setExit(Position exit) {this.exit = exit;}
	
	/**
	 * setCell changes a 'cell' in the maze to any integer value.
	 * @param x - int. 
	 * @param y - int.
	 * @param z - int.
	 * @param value - int.
	 */
	public void setCell(int x , int y, int z , int value){
		arr[x][y][z] = value;
	}
	
	/**
	 * getCell retrieves a cell's value. 
	 * @param x - int. 
	 * @param y - int.
	 * @param z - int.
	 * @param value - int.
	 * @return int - the found value.
	 */
	public int getCell(int x , int y, int z){
		return arr[x][y][z];
	}
	
	public int getxAxis() {
		return xAxis;
	}
	
	public int getyAxis() {
		return yAxis;
	}
	
	public int getzAxis() {
		return zAxis;
	}
										
	public Position getGoalPosition() {		//return the goal exit position
		
		return this.getExit();
	}

	/**
	 * used to get a plain of the maze by setting the X factor to i.
	 * @param i - int.
	 * @return int[][].
	 * 
	 */
	public int[][] getCrossSectionByX(int i) throws IndexOutOfBoundsException {				//return a plain (2d maze) by a certain x
		if(i >= 0 && i <= this.getxAxis())
		{
			int[][] plain = new int[this.getyAxis()][this.getzAxis()];
		
			for(int j = 0 ; j < this.getyAxis() ;j++)
			{
				for(int k = 0 ; k < this.getzAxis(); k++)
				{
					plain[j][k] = this.arr[i][j][k];
				}
			}
		
			return plain;
		}
		else
		{
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * used to get a plain of the maze by setting the Y factor to i.
	 * @param i - int.
	 * @return int[][].
	 * 
	 */
	public int[][] getCrossSectionByY(int i) throws IndexOutOfBoundsException {				//return a plain (2d maze) by a certain y
		if( i >= 0 && i <= this.getyAxis())
		{
			int[][] plain = new int[this.getxAxis()][this.getzAxis()];
		
			for(int j = 0 ; j < this.getxAxis() ;j++)
			{
				for(int k = 0 ; k < this.getzAxis(); k++)
				{
					plain[j][k] = this.arr[j][i][k];
				}
			}
		
			return plain;
		}
		else
		{
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * used to get a plain of the maze by setting the Z factor to i.
	 * @param i - int.
	 * @return int[][].
	 * @throws IndexOutOfBoundsException.
	 */
	public int[][] getCrossSectionByZ(int i) throws IndexOutOfBoundsException{			//return a plain (2d maze) by a certain z
		if(i >= 0 && i <= this.getzAxis())
		{
			int[][] plain = new int[this.getxAxis()][this.getyAxis()];
			
			for(int j = 0 ; j < this.getxAxis() ;j++)
			{
				for(int k = 0 ; k < this.getyAxis(); k++)
				{
					plain[j][k] = this.arr[j][k][i];
				}
			}
			
			return plain;
		}
		else
			throw new IndexOutOfBoundsException();
	}

	/**
	 * The method using the entrance to find the closest cell to the edge to start generating the maze from. 
	 * @return Position - the first cell to run the generate the algorithm from.
	 */
	public Position getFirstCell() {			//using the entrance to find closest cell to edge
		int x = this.getEntrance().getX(), y = this.getEntrance().getY(), z = this.getEntrance().getZ();
		if(x == 0)
		{
			x++;
		}
		if(x == this.getxAxis() - 1){
			x--;
		}
		if(y == 0)
		{
			y++;
		}
		if(y == this.getyAxis() - 1){
			y--;
		}
		if(z == 0)
		{
			z++;
		}
		if(z == this.getzAxis() - 1){
			z--;
		}
		
		
		Position answer = new Position(x,y,z);
		return answer;
	}

	/**
	 * setting a cell in the maze to value given it's Position.
	 * @param p - Position of the desired cell.
	 * @param value - the desired value (int).
	 */
	public void setCell(Position p,int value) {				//set a position's value
		this.arr[p.getX()][p.getY()][p.getZ()] = value;
		
	}

}
