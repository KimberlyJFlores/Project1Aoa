
public class Main {

	public static void main(String[] args) {
		System.out.println("Works!");
		Graph graph = new Graph();
		graph.setPoints("input.csv");
		System.out.println(graph.getAllPoints());
	}

}

