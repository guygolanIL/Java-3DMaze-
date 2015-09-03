package algorithms.search;



import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * The type contains a priority queue and a counter for the evaluated nodes.
 * @author Guy Golan
 *
 */

public abstract class CommonSearcher<T> implements Searcher<T> {

	protected PriorityQueue<State<T>> openList;
	private int evaluatedNodes;
	
	/**
	 * A non - default constructor
	 */
	public CommonSearcher(){
		openList = new PriorityQueue<State<T>>(new Comparator<State<T>>(){
		
			@Override
			public int compare(State<T> s1, State<T> s2) {
				
				return (int)((s1.getCost() - s2.getCost()));
			}
			
		});
		
		evaluatedNodes = 0; 
	}
	
	
	@Override
	public int getNumberOfNodesEvaluated(){
		return this.evaluatedNodes;
	}
	
	@Override
	public abstract Solution<T> search(Searchable<T> s);
	
	
	/**
	 * The method extracts the head of the queue and returns it. <br>Also incrementing the evaluatedNodes by 1.
	 * @return The head of the queue State.
	 */
	protected State<T> popOpenList(){
		evaluatedNodes++;
		return openList.poll();
	}
	
	protected void addToOpenList(State<T> s){
		this.openList.add(s);
	}
	
	/**
	 * The method searches for the given State in the given queue and <br> changes
	 * its cost and cameFrom members.
	 * @param openList - a queue to search state in.
	 * @param state - A State to be searched in the queue.
	 */
	protected void adjustPriority(PriorityQueue<State<T>> openList, State<T> state) {
		Iterator<State<T>> itr = openList.iterator();
		State<T> checkedState = null;
		
		while(itr.hasNext()){
			checkedState = itr.next();
			if(checkedState.getState().equals(state.getState()))
			{
				openList.remove(checkedState);
				setDeterminedCost(checkedState);
				openList.add(checkedState);
				return;
			}
			
		}
		
	}
	
	/**
	 * The method traces back through the cameFrom reference to <br> determine a series of States to solve the problem.
	 * @param goalState - The goal State of the problem.
	 * @param startState - The starting State of the problem.
	 * @return Solution - The solution of the problem (in States).
	 */
	protected Solution<T> backTrace(State<T> goalState, State<T> startState) {
		Solution<T> answer = new Solution<T>();
		
		while(startState != goalState )		//continues to backtrace until we have reached the starting State.
		{
			answer.add(0, goalState);	//inserting the goal/current State into the first place in the list.
			goalState = goalState.getCameFrom();  //traces back through cameFrom member.
		}
		
		answer.add(0, goalState);  //inserting the last (starting State) to the list.
		
		return answer;
	}
	
	/**
	 * searches the openlist for the given state.
	 * @param state - State - the checked state.
	 * @return - boolean - true if the given state exists within the openList.
	 */
	protected boolean openListContain(State<T> state) {
		for(State<T> currentState : openList)
		{
			if(currentState.equals(state))
			{
				return true;
			}	
		}
		return false;
		
	}
	
	/**
	 * Searches for state inside the closedSet.
	 * @param closedSet - HashSet.
	 * @param state - State
	 * @return true if found.
	 */
	protected boolean closedSetContain(HashSet<State<T>> closedSet, State<T> state) {
		
		for(State<T> currentState : closedSet)
		{
			if(currentState.equals(state))
			{
				return true;
			}	
		}
		return false;
	}
	
	
	
	/**
	 * setting the given state its f() value.
	 * @param s - State. 
	 */
	protected abstract void setDeterminedCost(State<T> s);
	
	
	
}
