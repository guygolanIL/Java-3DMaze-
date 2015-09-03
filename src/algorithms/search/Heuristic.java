package algorithms.search;

/**
 * Defines what every Heuristic must implement
 * @author guy
 *
 */

public interface Heuristic<T> {

	/**
	 * Given a certain State the method evaluates it's h() value.
	 * @param state - of which we want to get it's h() value.
	 * @return Double - The h() value of a certain Heuristic.
	 */
	public double evaluate(State<T> state);
}
