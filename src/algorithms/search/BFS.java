package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;



/**
 * The type represents a Best First Search algorithm
 * @author Guy Golan
 *
 */
public class BFS<T> extends CommonSearcher<T> {

	@Override
	public Solution<T> search(Searchable<T> s) {
		
		addToOpenList(s.getStartState());
		HashSet<State<T>> closedSet = new HashSet<State<T>>();
		
		while(openList.size() > 0)
		{
			State<T> n = popOpenList();
			closedSet.add(n);
			
			if(n.equals(s.getGoalState()))
			{
				return backTrace(n , s.getStartState());
			}
			
			ArrayList<State<T>> successors = s.getAllPossibleStates(n);		
			
			
			for(State<T> state : successors)
			{
				if(!closedSetContain(closedSet,state) && !openListContain(state)) 
				{
					state.setCameFrom(n);
					setDeterminedCost(state);
					addToOpenList(state);			
				}
				else
				{
					
					if(state.getCost() < (n.getCost() + s.advanceCost())){		
						if(!openListContain(state)){
						
							setDeterminedCost(state);
							state.setCameFrom(n);
							addToOpenList(state);
						}
						else
						{
							adjustPriority(openList,state);
						}
					}
				}
			}
		}
		return null;
		
	}

	@Override
	protected void setDeterminedCost(State<T> s) {
		s.setCost(s.getCameFrom().getCost() + s.getCost());
	}
	
	

}
