package algorithms.mazeGenerators;

/**
 * Abstract class which implements the measureAlgorithmTime method that each mazeGenerator has.
 * @author Guy Golan
 *
 */
public abstract class CommonMazeGenerator implements Maze3dGenerator {

	@Override
	public abstract Maze3d generate(int xAxis, int yAxis, int zAxis);		

	@Override
	public String measureAlgorithmTime(int xAxis,int yAxis,int zAxis) {		//measuring calculation time for the maze generate algorithm
		long time1,time2;
		
		time1 = System.currentTimeMillis();
		generate(xAxis, yAxis, zAxis);
		time2 = System.currentTimeMillis();
				
		long time3 = time2 - time1;		//the actual time the alg took
		String answer = ""+time3+" milliseconds";
		
		return answer;
	}

}
