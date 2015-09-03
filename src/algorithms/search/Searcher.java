package algorithms.search;

/**
 * Defines what any searcher algorithm must implement.
 * @author Guy Golan
 *
 */

public interface Searcher<T> {

	/**
	 * Used to find the best solution of a given problem.
	 * <p>
	 * @param s - A searchable problem.
	 * @return Solution type - the sequence of the states needed to solve the problem.
	 */
	public Solution<T> search(Searchable<T> s);
	/**
	 * Used to determine the number of states the search algorithm evaluated.
	 * @return The total number of States that were evaluated.
	 */
	public int getNumberOfNodesEvaluated();
	
}
