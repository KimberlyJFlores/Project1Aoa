
public class Points {
	double xCord;
	double yCord;
	int excelNum;
	public Points(double x, double y, int excelNum) {
		this.xCord=x;
		this.yCord=y;
		this.excelNum = excelNum;
		}
	//todo: add functions to solve alg
	/**
	 * @return the xCord
	 */
	public double getxCord() {
		return xCord;
	}
	/**
	 * @param xCord the xCord to set
	 */
	public void setxCord(double xCord) {
		this.xCord = xCord;
	}
	/**
	 * @return the yCord
	 */
	public double getyCord() {
		return yCord;
	}
	/**
	 * @param yCord the yCord to set
	 */
	public void setyCord(double yCord) {
		this.yCord = yCord;
	}
	public String toString()
	{
		return "excelNum: " + excelNum + " " + xCord+" ," + yCord + "";
	}
	
	/**
	 * @return the excelNum
	 */
	public int getExcelNum() {
		return excelNum;
	}
	/**
	 * @param excelNum the excelNum to set
	 */
	public void setExcelNum(int excelNum) {
		this.excelNum = excelNum;
	}
	boolean pointIsAboveLine( Line l )
	{
		double yLine = l.getSlope() * this.getxCord() + l.getyIntercept();
		return this.getyCord() > yLine;
	}
}
