package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * This type is used to calculate a given State it's h() (heuristic) value according to the Manhattan Distance method.
 * @author Guy Golan
 *
 */
public class MazeManhattanDistance implements Heuristic<Position> {

	
	private State<Position> goalState;
	
	/**
	 * A non - default constructor.
	 * @param goalState - The final goal of the problem.
	 */
	public MazeManhattanDistance(State<Position> goalState){
		
		this.goalState = goalState;
	}
	
	@Override
	public double evaluate(State<Position> state) {
	Position p1 = state.getState();
	Position p2 = goalState.getState();
	
	double count = 0;
	
	count += Math.abs(p1.getX() - p2.getX());
	count += Math.abs(p1.getY() - p2.getY());
	count += Math.abs(p1.getZ() - p2.getZ());
	
		return (count * 10);
	}

}
