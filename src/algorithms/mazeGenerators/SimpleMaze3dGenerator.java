package algorithms.mazeGenerators;


import java.util.Random;

/**
 * A simple 'naive' maze generator.
 * @author Guy Golan
 *
 */
public class SimpleMaze3dGenerator extends CommonMazeGenerator {

	@Override
	public Maze3d generate(int xAxis, int yAxis, int zAxis) {
		Maze3d m = new Maze3d(xAxis, yAxis, zAxis);
		Random r = new Random();
		int num = 0;
		
		m.fullWall();			//creating all the walls in the maze
		
		for(int i = 1 ; i < xAxis-1 ; i++ ){					//inserting 1 or 0 into the maze randomly
			for(int j = 1 ; j < yAxis-1 ; j++){
				for(int h = 1 ; h < zAxis-1 ; h++){
					num = r.nextInt(2);	
					m.setCell(i, j, h, num);
				}
			}
		}
		m.createEnteranceExit();		//creating entrance and exit on two opposite edges of the maze
		
		m.eraseWall(m.getEntrance());		
		m.eraseWall(m.getExit());
		m.setCell(m.getEntrance(),0);
		
		Position p = m.getFirstCell();		//using the entrance to find the new closest cell to the edge
		m.eraseWall(p);

		int direction = 0;
										//starting the path making process
		while(!(p.equals(m.getExit())))
		{
			direction = r.nextInt(3);		//choosing an axis randomly to advance in
			
			switch (direction) {	//if X
			case 0:
				if(p.getX() > m.getExit().getX() && p.getX() > 0)
				{
					p.setX(p.getX() - 1);
				}
				else if(p.getX() < m.getExit().getX() && p.getX() < m.getxAxis())
				{
					p.setX(p.getX() + 1);
				}
				break;
			case 1:					//if Y
				if(p.getY() > m.getExit().getY() && p.getY() >0)
				{
					p.setY(p.getY() - 1);
				}
				else if(p.getY() < m.getExit().getY() && p.getY() < m.getyAxis())
				{
					p.setY(p.getY() + 1);
				}
				break;
			case 2:					//if Z
				if(p.getZ() > m.getExit().getZ() && p.getZ() > 0)
				{
					p.setZ(p.getZ() - 1);
				}
				else if(p.getZ() < m.getExit().getZ() && p.getZ() < m.getzAxis())
				{
					p.setZ(p.getZ() + 1);
				}
				break;
			default:
				break;
			}
			
			m.eraseWall(p);
			

			

		}
		m.eraseWall(p);
		m.setCell(m.getExit(),0);
		
		return m;
	}

	
	
	
}
















