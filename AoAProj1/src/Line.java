
public class Line {
		Points point1;
		Points point2;
		double slope;
		
		public Line(Points point1, Points point2) {
			this.point1=point1;
			this.point2=point2;
			this.setSlope(this.point1, this.point2);
		}
		public double getSlope() 
		{
			return this.slope;
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
		/**
		 * @param slope the slope to set
		 */
		public void setSlope(Points point1, Points point2) {
			this.slope = (point2.getyCord() - point1.getyCord() ) / ( point2.getxCord() - point1.getxCord());
		}
		
}
