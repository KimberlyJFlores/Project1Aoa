
public class Main {

	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.setPoints("input.csv");
		System.out.println(graph.findConvexHull(graph.allPoints));
		
	}

}

