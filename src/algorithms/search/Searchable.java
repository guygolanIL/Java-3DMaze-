package algorithms.search;

import java.util.ArrayList;

/**
 * Defines what any Searchable problem must implement.
 * @author Guy Golan
 *
 */


public interface Searchable<T> {

	/**
	 * Used to get the starting State of the problem.
	 * @return the starting State of the problem.
	 */
	State<T> getStartState();
	/**
	 * Used to get the goal State of the problem.
	 * @return The goal State of the problem.
	 */
	State<T> getGoalState();
	/**
	 * Given a State returns it's next possible States in an ArrayList.
	 * @param s - A State of which we want it's next possible States.
	 * @return	An ArrayList<State> that contains all s's next possible States.
	 */
	ArrayList<State<T>> getAllPossibleStates(State<T> s);
	
	/**
	 * The method returns the specific problem's cost to advance from state to the next.
	 * @return	The cost to advance from n to state
	 */
	double advanceCost();
}
