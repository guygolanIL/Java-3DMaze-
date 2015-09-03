package algorithms.search;

import algorithms.mazeGenerators.Position;


/**
 * This type is used to calculate a given State it's h() (heuristic) value according to the Air Distance method.
 * @author Guy Golan
 *
 */
public class MazeAirDistance implements Heuristic<Position> {

	private State<Position> goalState;
	
	/**
	 * A non - default constructor.
	 * @param goalState - The final goal of the problem.
	 */
	public MazeAirDistance(State<Position> goalState){
		
		this.goalState = goalState;
	}

	@Override
	public double evaluate(State<Position> state) {
		Position p1 =state.getState();
		Position p2 = goalState.getState();
		double count = 0;
		int reqXmov = 0, reqYmov = 0, reqZmov = 0;
		
		reqXmov = Math.abs(p1.getX() - p2.getX());
		reqYmov = Math.abs(p1.getY() - p2.getY());
		reqZmov = Math.abs(p1.getZ() - p2.getZ());
		
		count = Math.sqrt(Math.pow(reqXmov, 2) + Math.pow(reqYmov, 2) + Math.pow(reqZmov, 2));
		
		return (count * 10);
	}

}
