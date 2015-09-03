package algorithms.search;

import java.util.ArrayList;


import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

/**
 * Defines a maze problem.
 * @author Guy Golan
 *
 */
public class MazeDomain implements Searchable<Position> {

	private State<Position> startState;
	private State<Position> goalState;
	private Maze3d m; 
	
	public MazeDomain(Maze3d m){
		this.m = m;
		this.startState = new State<Position>(m.getEntrance());
		this.goalState = new State<Position>(m.getExit());
	}
	
	@Override
	public State<Position> getStartState() {return startState;}

	@Override
	public State<Position> getGoalState() {return goalState;}

	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> s) {
		
		Position p = s.getState();
		String[] moves = m.getPossibleMoves(p);
		ArrayList<State<Position>> arr = new ArrayList<State<Position>>();
		State<Position> tempState = null;
		
		for(String move : moves)
		{
			switch (move) {
			case "RIGHT":
				tempState = new State<Position>(new Position(p.getX(), p.getY(), p.getZ()-1));
				
				break;
			case "LEFT":
				tempState = new State<Position>(new Position(p.getX(), p.getY(), p.getZ()+1));
				
				break;
			case "UP":
				tempState = new State<Position>(new Position(p.getX()+1, p.getY(), p.getZ()));
				
				break;
			case "DOWN":
				tempState = new State<Position>(new Position(p.getX()-1, p.getY(), p.getZ()));
				
				break;
			case "FORWARD":
				tempState = new State<Position>(new Position(p.getX(), p.getY()+1, p.getZ()));
				
				break;
			case "BACKWARD":
				tempState = new State<Position>(new Position(p.getX(), p.getY()-1, p.getZ()));
				
				break;
			default:
				break;
			}
			tempState.setCameFrom(s);	
			tempState.setCost(advanceCost());
			arr.add(tempState);
		}
		
		
		return arr;
	}

	@Override
	public double advanceCost() {
		
		return 10;
	}

}
