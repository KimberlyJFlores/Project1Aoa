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
		while(!noPointsBelow(leftSide,lT) /**&& !noPointsBelow(rightSide,lT)**/) {
			while(!noPointsBelow(leftSide,lT)) {
				if(i == 0)
					i=leftSide.size()-1;
				else
					i--;
				lT=new Line(leftSide.get(i).point1,rightSide.get(c).point1);
			}
			while(!noPointsBelow(rightSide,lT)) {
				if(c == rightSide.size()-1)
					c=0;
				else
					c++;
				lT=new Line(leftSide.get(i).point1,rightSide.get(c).point1);
			}
		}
		System.out.println("Monster equation:"+lT.getPoint1() + "\t"+lT.getPoint2());
		return lT;
	}
	public boolean noPointsBelow(ArrayList<Line> shape,Line tangent) {
		int i =0;
		Points test = shape.get(0).point1;
		for(i=0;i<shape.size()-1;i++) {
			if(test != tangent.getPoint1() && test != tangent.getPoint2()) {
				if(test.getyCord() < tangent.getPoint1().getyCord() || test.getyCord() < tangent.getPoint2().getyCord()) {
					System.out.println(test+"\t"+ tangent.getPoint1()+"\t"+tangent.getPoint2());
					return false;
					
				}
			}
		}
		return true;
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
