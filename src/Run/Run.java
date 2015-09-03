package Run;

import algorithms.demo.Demo;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;

public class Run {

	private static void testMazeGenerator(Maze3dGenerator mg){
		// prints the time it takes the algorithm to run
		System.out.println(mg.measureAlgorithmTime(5,9,9));
		// generate another 3d maze
		Maze3d maze = mg.generate(5,9,9);
		maze.printMaze();
		// get the maze entrance
		Position p = maze.getEntrance();
		// print the position
		System.out.println("Entrance: "+p);
		// get all the possible moves from a position
		String[] moves=maze.getPossibleMoves(p);
		// print the moves
		for(String move : moves)
			System.out.println(move);
		// prints the maze exit position
		System.out.println("Goal: "+maze.getGoalPosition());

		
		try{
			// get 2d cross sections of the 3d maze
			int[][] maze2dx=maze.getCrossSectionByX(2);
			System.out.println("plain by X:");
			
			for(int[] i : maze2dx){
				for(int j : i){
					System.out.print(j+" ");
				}
				System.out.println("");
			}
			System.out.println("");
			
			int[][] maze2dy=maze.getCrossSectionByY(5);
			
			System.out.println("plain by Y:");
			
			for(int[] i : maze2dy){
				for(int j : i){
					System.out.print(j+" ");
				}
				System.out.println("");
			}
			System.out.println("");
			
			int[][] maze2dz=maze.getCrossSectionByZ(0);
			
			System.out.println("plain by Z:");
			
			for(int[] i : maze2dz){
				for(int j : i){
					System.out.print(j+" ");
				}
				System.out.println("");
			}
			System.out.println("");
			
			// this should throw an exception!
			maze.getCrossSectionByX(-1);
			} catch (IndexOutOfBoundsException e){
			System.out.println("good!");
			}
		
	}
	
	
	public static void main(String[] args) {
		//testMazeGenerator(new SimpleMaze3dGenerator());
		//testMazeGenerator(new MyMaze3dGenerator());
		
		Demo d =new Demo();
		
		d.run();
	}
	

}
