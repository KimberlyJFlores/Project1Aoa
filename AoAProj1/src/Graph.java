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
		Scanner scanIn = null;
		try {
			scanIn = new Scanner( new File("data/"+fileName) );
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
	public ArrayList<Line> findConvexHull(ArrayList<Points> points) {
		
		ArrayList<Line> ret = new ArrayList<Line>();
		if( points.size() == 3 ) /* 3 points left */
		{
			if( points.get(1).getyCord() < points.get(2).getyCord() ) /* get bottom point */
			{
				ret.add( new Line( points.get(0), points.get(1) ) );
				ret.add( new Line( points.get(1), points.get(2) ) );
				ret.add( new Line( points.get(2), points.get(0) ) ); /* connect bottom to right point */
			}
			else 													 /* get bottom point */
			{
				ret.add( new Line( points.get(0), points.get(2) ) );
				ret.add( new Line( points.get(2), points.get(1) ) );
				ret.add( new Line( points.get(1), points.get(0) ) ); /* connect bottom to right point */
			}
			System.out.println(ret);
			return ret;
		}
		if( points.size() == 2 ) /* 2 points left */
		{
			ret.add( new Line( points.get(1), points.get(0) ) );
			System.out.println(ret);
			return ret;
		}
		ArrayList<Line> leftSide = findConvexHull( new ArrayList<Points>( points.subList(0, points.size()/2) ) ); // left side
		//System.out.println(leftSide);
		ArrayList<Line> rightSide = findConvexHull( new ArrayList<Points>( points.subList(points.size()/2, points.size() ) ) ); // left side
		//System.out.println(rightSide);
		return null; /* combine sides */
	}
	
	/* TODO: this stuff */
	public ArrayList<Line> combineSides( ArrayList<Line> leftSide, ArrayList<Line> rightSide )
	{
		return null;
	}
	
	/* TODO: this stuff */
	public Line lowerTangent( ArrayList<Line> leftSide, ArrayList<Line> rightSide )
	{
		Points a = leftSide.get(leftSide.size()-1).point1; /* Rightmost point of a */
		Points b = leftSide.get(0).point1; /* Leftmost point of b */
		Line curr = new Line( a,b );
		return null;
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
