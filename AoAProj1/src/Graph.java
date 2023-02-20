import java.util.ArrayList;

public class Graph {
	ArrayList<Points> allPoints;
	ArrayList<Line> convexLines;
	public Graph() 
	{
		allPoints = new ArrayList<Points>();
		convexLines = new ArrayList<Line>();
	}
	public void setPoints(String fileName) {
		//TODO: read in all points from CSV FILE
	}
	public void findConvexHull() {
		//TODO: write all points that form the convex hull, in counter-clockwise order
	}	
	/**
	 * @return the allPoints
	 */
	public ArrayList<Points> getAllPoints() {
		return allPoints;
	}
	/**
	 * @param allPoints the allPoints to set
	 */
	public void setAllPoints(ArrayList<Points> allPoints) {
		this.allPoints = allPoints;
	}
	
}
