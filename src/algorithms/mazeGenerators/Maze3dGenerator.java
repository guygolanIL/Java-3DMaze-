package algorithms.mazeGenerators;

/**
 * The Interface defines what every maze generator must implement.
 * @author Guy Golan
 *
 */
public interface Maze3dGenerator {
/**
 * generate receives 3 dimensions for the maze and create one accordingly.
 * @param xAxis - The X dimension of the maze.
 * @param yAxis - The Y dimension of the maze.
 * @param zAxis - The Z dimension of the maze.
 * @return A new complete  Maze3D. 
 */
	public Maze3d generate(int xAxis,int yAxis,int zAxis);			//generating a 3d maze with given dimensions
	/**
	 * The method checks who much time in milliseconds the generate algorithm took.
	 * @param xAxis - The X dimension of the maze.
	 * @param yAxis - The Y dimension of the maze.
	 * @param zAxis - The Z dimension of the maze.
	 * @return - String  - the time the algorithm took in milliseconds.
	 */
	public String measureAlgorithmTime(int xAxis,int yAxis,int zAxis);		//measuring the time the generate alg took
}
