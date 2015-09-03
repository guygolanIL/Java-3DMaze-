package algorithms.search;

public class AStar<T> extends BFS<T> {

	private Heuristic<T> h;
	
	/**
	 * Ctor.
	 * @param h - any Heuristic type. 
	 */
	public AStar(Heuristic<T> h){
		this.h = h;
	}

	@Override
	protected void setDeterminedCost(State<T> s) {
		s.setCost(s.getCameFrom().getCost() + s.getCost() + h.evaluate(s));	
	}
	
	
	
}
