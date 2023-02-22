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
			return ret;
		}
		if( points.size() == 2 ) /* 2 points left */
		{
			ret.add( new Line( points.get(1), points.get(0) ) );
			//System.out.println(ret);
			return ret;
		}
		ArrayList<Line> leftSide = findConvexHull( new ArrayList<Points>( points.subList(0, points.size()/2) ) ); // left side
		//System.out.println(leftSide);
		ArrayList<Line> rightSide = findConvexHull( new ArrayList<Points>( points.subList(points.size()/2, points.size() ) ) ); // left side
		//System.out.println(rightSide);
		ArrayList<Line> exp = combineSides(leftSide,rightSide);
		return null; /* combine sides */
	}
	
	/* TODO: this stuff */
	public ArrayList<Line> combineSides( ArrayList<Line> leftSide, ArrayList<Line> rightSide )
	{
		Line lT = lowerTangent(leftSide,rightSide);
		Line uT = upperTangent(leftSide,rightSide);
		return null;
	}
	
	/* TODO: this stuff */
	public Line lowerTangent( ArrayList<Line> leftSide, ArrayList<Line> rightSide )
	{
		int i =0;
		Points a = leftSide.get(0).point1;
		for(i = 0; i< leftSide.size()-1;i++) {
			if(leftSide.get(i).getPoint1().getxCord()>a.getxCord())
				a=leftSide.get(i).getPoint1();
		} /* Rightmost point of a */
		int c = 0;
		Points b = rightSide.get(c).point1; /* Leftmost point of b */
		Line lT = new Line(a,b);
		System.out.println("Start: "+lT + "\t" + lT.getSlope() + "\t"+lT.getyIntercept());
		while(isLowerTangent(rightSide,lT)==false || isLowerTangent(leftSide,lT)==false ) {
			while(isLowerTangent(leftSide,lT)==false) {
				if(i == 0)
					i=leftSide.size()-1;
				else
					i--;
				a=leftSide.get(i).getPoint1();
				lT = new Line(a,b);
			}
			while(isLowerTangent(rightSide,lT)==false) {
				if(c == leftSide.size()-1)
					c=0;
				else
					c++;
				b = rightSide.get(c).getPoint1();
				lT = new Line(a,b);
			}
		}
		System.out.println("Final: "+lT);
		
		return lT;
	}
	public boolean isLowerTangent( ArrayList<Line> shape, Line tan) {
		int i = 0;
		Points p=shape.get(i).getPoint1();
		for(i=0;i<shape.size();i++) {
			p=shape.get(i).getPoint1();
			if(p != tan.getPoint1()&& p!=tan.getPoint2()&&pointAboveLine(tan,p)==false)
				return false;
		}
		return true;
	}
	public Line upperTangent( ArrayList<Line> leftSide, ArrayList<Line> rightSide )
	{
		int i =0;
		Points a = leftSide.get(0).point1;
		for(i = 0; i< leftSide.size()-1;i++) {
			if(leftSide.get(i).getPoint1().getxCord()>a.getxCord())
				a=leftSide.get(i).getPoint1();
		} /* Rightmost point of a */
		int c = 0;
		Points b = rightSide.get(c).point1; /* Leftmost point of b */
		Line lT = new Line(a,b);
		System.out.println("Upper Start: "+lT + "\t" + lT.getSlope() + "\t"+lT.getyIntercept());
		while(isUpperTangent(rightSide,lT)==false || isUpperTangent(leftSide,lT)==false ) {
			while(isUpperTangent(leftSide,lT)==false) {
				if(i == 0)
					i=leftSide.size()-1;
				else
					i--;
				a=leftSide.get(i).getPoint1();
				lT = new Line(a,b);
			}
			while(isUpperTangent(rightSide,lT)==false) {
				if(c == leftSide.size()-1)
					c=0;
				else
					c++;
				b = rightSide.get(c).getPoint1();
				lT = new Line(a,b);
			}
		}
		System.out.println("Upper Final: "+lT);
		
		return lT;
	}
	public boolean isUpperTangent( ArrayList<Line> shape, Line tan) {
		int i = 0;
		Points p=shape.get(i).getPoint1();
		for(i=0;i<shape.size();i++) {
			p=shape.get(i).getPoint1();
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
	
}
