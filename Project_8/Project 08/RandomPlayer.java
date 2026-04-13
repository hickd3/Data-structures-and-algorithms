/*
 * Filename: RandomPlayer.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS 231 A
 * Project 08
 */

import java.util.List;
import java.util.Random;

//This class will not be very smart, but will be useful for testing purposes. This RandomPlayer will extend the AbstractPlayerAlgorithm class, so it only needs to implement the abstract methods (and the constructor). 
public class RandomPlayer extends AbstractPlayerAlgorithm {
    private Random random;

    public RandomPlayer(Graph graph) {
        super(graph);
        random = new Random();
    }

    @Override
    public Vertex chooseStart() {
        List<Vertex> vertices = (List<Vertex>) getGraph().getVertices();
        int randomIndex = random.nextInt(vertices.size());
        Vertex startVertex = vertices.get(randomIndex);
        setCurrentVertex(startVertex);
        return startVertex;
    }

    @Override
    public Vertex chooseStart(Vertex other) {
        return chooseStart();
    }

    @Override
    public Vertex chooseNext(Vertex otherPlayer) {
        Vertex currentVertex = getCurrentVertex();
        List<Edge> edges = currentVertex.getEdges();

        if (edges.isEmpty()) {
            return currentVertex;
        }

        int randomIndex = random.nextInt(edges.size());
        Vertex nextVertex = edges.get(randomIndex).getOther(currentVertex);
        setCurrentVertex(nextVertex);
        return nextVertex;
    }
}
