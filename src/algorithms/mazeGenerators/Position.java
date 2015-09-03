package algorithms.mazeGenerators;

/**
 * Defines a position type for any 3D maze.
 * @author Guy Golan
 *
 */
public class Position {		//defining Position

	private int x,y,z;

	
	/**
	 * Ctor - booting the x,y,z.
	 * @param x - the x coordinate.
	 * @param y - the y coordinate.
	 * @param z - the z coordinate.
	 */
	public Position(int x,int y, int z){				//Ctor
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Position(Position p){						//copy Ctor
		this.setX(p.getX());
		this.setY(p.getY());
		this.setZ(p.getZ());
	}
	
	/**
	 * prints the Position.
	 */
	public void print(){						//prints the position
		
		System.out.println("{"+this.x+","+this.y+","+this.z+"}");
	}
	
	@Override
		public String toString() {
			String output;
			
			output = "{"+this.getX()+","+this.getY()+","+this.getZ()+"}";
			return output ;
		}
	
	@Override
	public boolean equals(Object obj){		//checks if two positions are equal
		
		Position p = (Position)obj;
		if(this.getX() == p.getX() && this.getY() == p.getY() && this.getZ() == p.getZ() )
		{
			return true;
		}
		return false;
	}
	
									//--------------setters and getters-------------------
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	public byte[] toByteArray() {
		byte[] b = new byte[3] ;
		int  i =0 ;
		
		b[i++] = (byte)this.getX();
		b[i++] = (byte)this.getY();
		b[i++] = (byte)this.getZ();
		

		return b;
	}
	
	
	
}
