package algorithms.search;

/**
 * The State represents the simplest form of a generic problem's state.
 * <p>
 * @author Guy Golan
 * 
 *
 */

public class State<T> {
	private T state;
	private double cost;
	private State<T> cameFrom;
	
	/**
	 * A non - default constructor. 
	 * @param state - A String which describes the State
	 */
	
	public State(T state){
		this.state = state;
		this.cost = 0;
		this.cameFrom = null;
		
	}
	
	
	@Override
	public boolean equals(Object s){
		return state.equals(((State<T>) s).getState());
	}

	
	public void setCameFrom(State<T> n) {this.cameFrom = n;}
	
	public State<T> getCameFrom() {return this.cameFrom;}

	public void setState(T state) {this.state = state;}

	public T getState() {return state;}
	
	public void setCost(double cost) {this.cost = cost;}

	public double getCost() {return cost;}


	
}
