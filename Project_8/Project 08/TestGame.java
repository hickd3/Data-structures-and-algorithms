public class TestGame {
    public static void main(String[] args) {
        // Create a graph
        Graph graph = new Graph(6, 0.5);

        // Add vertices
        Vertex v1 = graph.addVertex();
        Vertex v2 = graph.addVertex();
        Vertex v3 = graph.addVertex();
        Vertex v4 = graph.addVertex();
        Vertex v5 = graph.addVertex();
        Vertex v6 = graph.addVertex();

        // Add edges
        graph.addEdge(v1, v2, 1.0);
        graph.addEdge(v1, v3, 1.0);
        graph.addEdge(v2, v4, 1.0);
        graph.addEdge(v3, v4, 1.0);
        graph.addEdge(v3, v5, 1.0);
        graph.addEdge(v4, v6, 1.0);
        graph.addEdge(v5, v6, 1.0);

        // Print graph details
        System.out.println("Graph:");
        System.out.println("Vertices: " + graph.getVertices());
        System.out.println("Edges: " + graph.getEdges());
        System.out.println();

        // Create players
        AbstractPlayerAlgorithm evader = new MoveAwayPlayerAlgorithm(graph);
        AbstractPlayerAlgorithm pursuer = new MoveTowardsPlayerAlgorithm(graph);

        // Choose start vertices
        Vertex evaderStart = evader.chooseStart();
        Vertex pursuerStart = pursuer.chooseStart(evaderStart);

        // Print start vertices
        System.out.println("Evader start: " + evaderStart);
        System.out.println("Pursuer start: " + pursuerStart);
        System.out.println();

        // Play the game
        System.out.println("Game in progress:");
        int steps = 5;
        for (int i = 0; i < steps; i++) {
            Vertex evaderNext = evader.chooseNext(pursuer.getCurrentVertex());
            Vertex pursuerNext = pursuer.chooseNext(evader.getCurrentVertex());

            System.out.println("Evader moves to: " + evaderNext);
            System.out.println("Pursuer moves to: " + pursuerNext);
            System.out.println();
        }
    }
}
