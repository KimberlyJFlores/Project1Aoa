
public class Line {
		Points point1;
		Points point2;
		public Line(Points point1, Points point2) {
			this.point1=point1;
			this.point2=point2;
		}
		public double getSlope() 
		{
			//TODO: calculate slope
			return 0.0;
		}
		/**
		 * @return the point1
		 */
		public Points getPoint1() {
			return point1;
		}
		/**
		 * @param point1 the point1 to set
		 */
		public void setPoint1(Points point1) {
			this.point1 = point1;
		}
		/**
		 * @return the point2
		 */
		public Points getPoint2() {
			return point2;
		}
		/**
		 * @param point2 the point2 to set
		 */
		public void setPoint2(Points point2) {
			this.point2 = point2;
		}
		
}
