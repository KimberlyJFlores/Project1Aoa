import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


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
		Scanner scanIn = null;
		try {
			scanIn = new Scanner( new File(fileName) );
			//reads in data from a CSV line by line
			while(scanIn.hasNextLine()) {
				String nextLine = scanIn.nextLine();
				if(nextLine != null) {
					String[] tokens = nextLine.split(",");
					Points newPoint;
					newPoint = new Points(Double.parseDouble(tokens[0]),Double.parseDouble(tokens[1]));
					allPoints.add(newPoint);
				}
				
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
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
