package algorithms.demo;

import algorithms.mazeGenerators.*;
import algorithms.search.*;
//sandak was here!!!!!
/**
 * Demo demonstrates a use of the search algorithms BFS and A*. 
 * @author Guy Golan
 *
 */
public class Demo {

	/**
	 * The method activates and runs a demonstration of BFS and A*.
	 */
	public void run(){
		Maze3d m = new MyMaze3dGenerator().generate(7, 17, 17);		//generating a 3D maze.
		m.printMaze();											//printing the maze.
		
		//creating a new BFS search algorithms 
		BFS<Position> myBFSAlg =new BFS<Position>();
		// using the BFS instance to search a solution of the maze
		Solution<Position> BFSsolution = myBFSAlg.search(new MazeDomain(m));			
		System.out.println("The number of States BFS has evaluated is: "+ myBFSAlg.getNumberOfNodesEvaluated());		
		BFSsolution.print();		//prints the solution.
		
		//creating an AStar search algorithm instance according to MazeManhattanDistance heuristic.
		AStar<Position> myAStarManhattanAlg =  new AStar<Position>(new MazeManhattanDistance(new State<Position>(m.getExit())));
		//using the AStar instance to solve the maze.
		Solution<Position> AStarManhattanSolution =myAStarManhattanAlg.search(new MazeDomain(m));		
		System.out.println("The number of States AStar with heuristic #1 has evaluated is: "+ myAStarManhattanAlg.getNumberOfNodesEvaluated());
		AStarManhattanSolution.print();   //prints the solution.
		
		//creating an AStar search algorithm instance according to AirDistance heuristic.
		AStar<Position> myAStarAirDistanceAlg = new AStar<Position>(new MazeAirDistance(new State<Position>(m.getExit())));
		//using the AStar instance to solve the maze.
		Solution<Position> AStarAirDistanceSolution = myAStarAirDistanceAlg.search(new MazeDomain(m));		
		System.out.println("The number of States AStar with heuristic #2 has evaluated is: "+ myAStarAirDistanceAlg.getNumberOfNodesEvaluated());
		AStarAirDistanceSolution.print();		//prints the solution.
		
		
		
		
	}
}
