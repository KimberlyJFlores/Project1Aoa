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
		int rowNum = 0;
		try {
			scanIn = new Scanner( new File("data/"+fileName) );
			//reads in data from a CSV line by line
			while(scanIn.hasNextLine()) {
				String nextLine = scanIn.nextLine();
				if(nextLine != null) {
					rowNum++;
					String[] tokens = nextLine.split(",");
					Points newPoint;
					newPoint = new Points(Double.parseDouble(tokens[0]),Double.parseDouble(tokens[1]), rowNum);
					allPoints.add(newPoint);
				}
				
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Points> findConvexHull(ArrayList<Points> points) {
		ArrayList<Points> ret = new ArrayList<Points>();
		if(points.size() == 3) 
		{
			if( points.get(1).getyCord() < points.get(2).getyCord() ) /* get bottom point */
			{
				ret.add( points.get(0));
				ret.add(points.get(1));
				ret.add(points.get(2)); /* connect bottom to right point */
			}
			else 													 /* get bottom point */
			{
				ret.add( points.get(0));
				ret.add(points.get(2));
				ret.add(points.get(1)); /* connect bottom to right point */
			}
			return ret;
		}
		else if (points.size()==2) 
		{
			return points;
		}
		ArrayList<Points>  leftSide = findConvexHull( new ArrayList<Points>( points.subList(0, points.size()/2) ) ); // left side
		ArrayList<Points>  rightSide = findConvexHull( new ArrayList<Points>( points.subList(points.size()/2, points.size() ) ) ); // left side
		ArrayList<Points>  exp = combineSides(leftSide,rightSide);
		return exp; /* combine sides */
	}
	
	public ArrayList<Points> combineSides(ArrayList<Points> leftSide, ArrayList<Points> rightSide)
	{
		Line lT = lowerTangent(leftSide,rightSide);
		Line uT = upperTangent(leftSide,rightSide);
		ArrayList<Points> combine = new ArrayList<Points>();
		
		int i = 0,j = 0;
		/* Tracing to get to uT pt1 */
		while( leftSide.get(i) != uT.point1 )
		{
			i++;
		}
		if( i == leftSide.size() )
			i = 0;
		while( leftSide.get(i) != lT.point1 )
		{
			combine.add(leftSide.get(i));
			i++;
			if( i == leftSide.size() )
				i = 0;

		}
		combine.add(lT.point1);
		combine.add(lT.point2);
		j = 0;
		while( rightSide.get(j) != lT.point2 )
		{
			j++;
			
		}
		j++;
		if( j == rightSide.size() )
			j = 0;
		/* now at pt2 lT */
		while( rightSide.get(j) != uT.point2 )
		{
			
			combine.add(rightSide.get(j));
			j++;
			if( j == rightSide.size() )
				j = 0;
		}
		combine.add(uT.getPoint2());
		return combine;
	}
	public Line lowerTangent(ArrayList<Points> leftSide, ArrayList<Points> rightSide) {
		int i =0;
		Points a = leftSide.get(0);
		for(i = 0; i< leftSide.size();i++) {
			if(leftSide.get(i).getxCord()>a.getxCord())
				a=leftSide.get(i);
		} /* Rightmost point of a */
		int c = 0;
		Points b = rightSide.get(c);
		Line lT = new Line(a,b);  /* Leftmost point of b */
		while(isLowerTangent(rightSide,lT)==false || isLowerTangent(leftSide,lT)==false ) {
			while(isLowerTangent(leftSide,lT)==false) {
				if(i == 0)
					i=leftSide.size()-1;
				else
					i--;		
				a=leftSide.get(i);
				lT = new Line(a,b);
			}
			while(isLowerTangent(rightSide,lT)==false) {
				if(c == rightSide.size()-1)
					c=0;
				else
					c++;
				b = rightSide.get(c);
				lT = new Line(a,b);
			}
		}
		return lT;
	}
	public boolean isLowerTangent( ArrayList<Points> shape, Line tan) {
		int i = 0;
		Points p=shape.get(i);
		for(i=0;i<shape.size();i++) {
			p=shape.get(i);
			if(p != tan.getPoint1()&& p!=tan.getPoint2()&&pointAboveLine(tan,p)==false)
				return false;
		}
		return true;
	}
	public Line upperTangent(ArrayList<Points> leftSide, ArrayList<Points> rightSide) {
		int i =0;
		Points a = leftSide.get(0);
		for(i = 0; i< leftSide.size();i++) {
			if(leftSide.get(i).getxCord()>a.getxCord())
				a=leftSide.get(i);
		} /* Rightmost point of a */
		int c = 0;
		Points b = rightSide.get(c);
		Line lT = new Line(a,b);  /* Leftmost point of b */
		while(isupperTangent(rightSide,lT)==false || isupperTangent(leftSide,lT)==false ) {
			while(isupperTangent(leftSide,lT)==false) {
				if(i == 0)
					i=leftSide.size()-1;
				else
					i--;		
				a=leftSide.get(i);
				lT = new Line(a,b);
			}
			while(isupperTangent(rightSide,lT)==false) {
				if(c == rightSide.size())
					c=0;
				else
					c++;
				b = rightSide.get(c);
				lT = new Line(a,b);
			}
		}
		return lT;
	}
	public boolean isupperTangent( ArrayList<Points> shape, Line tan) {
		int i = 0;
		Points p=shape.get(i);
		for(i=0;i<shape.size();i++) {
			p=shape.get(i);
			if(p != tan.getPoint1()&& p!=tan.getPoint2()&&pointAboveLine(tan,p)==true)
				return false;
		}
		return true;
	}
	public boolean pointAboveLine(Line l, Points p) {
		double lineY = l.getSlope()*p.getxCord()+l.getyIntercept();
		return p.getyCord()>lineY;
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
	
	public String toString()
	{
		String ret = "";
		for( Line line : this.convexLines )
		{
			ret += line + "\n";
		}
		return ret;
	}
}
