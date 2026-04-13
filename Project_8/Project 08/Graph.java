/*
 * Filename: Graph.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS 231 A
 * Project 08
 */


 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class Graph {
    private List<Vertex> vertices;
    private List<Edge> edges;

    public Graph() {
        this(0);
    }

    public Graph(int n) {
        this(n, 0.0);
    }

    public Graph(int n, double probability) {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            addVertex();
        }
        connectVerticesWithProbability(probability);
    }

    private void connectVerticesWithProbability(double probability) {
    }

    public int size() {
        return vertices.size();
    }

    public Iterable<Vertex> getVertices() {
        return vertices;
    }

    public Iterable<Edge> getEdges() {
        return edges;
    }

    public Vertex addVertex() {
        Vertex vertex = new Vertex();
        vertices.add(vertex);
        return vertex;
    }

    public Edge addEdge(Vertex u, Vertex v, double distance) {
        Edge edge = new Edge(u, v, distance);
        edges.add(edge);
        u.addEdge(edge);
        v.addEdge(edge);
        return edge;
    }

    public Edge getEdge(Vertex u, Vertex v) {
        for (Edge edge : edges) {
            if ((edge.hasEndpoints(u, v))) {
                return edge;
            }
        }
        return null;
    }

    public boolean remove(Vertex vertex) {
        if (vertices.remove(vertex)) {
            removeEdgesWithVertex(vertex);
            return true;
        }
        return false;
    }

    private void removeEdgesWithVertex(Vertex vertex) {
    }

    public boolean remove(Edge edge) {
        if (edges.remove(edge)) {
            edge.getU().removeEdge(edge);
            edge.getU().removeEdge(edge);
            return true;
        }
        return false;
    }

    public HashMap<Vertex, Double> distanceFrom(Vertex source) {
        Map<Vertex, Double> distances = initializeDistances();
        Set<Vertex> unvisited = new HashSet<>(vertices);
        distances.put(source, 0.0);

        while (!unvisited.isEmpty()) {
            Vertex current = getVertexWithMinimumDistance(distances, unvisited);
            unvisited.remove(current);

            for (Edge edge : current.getEdges()) {
                Vertex neighbor = edge.getOther(current);
                double newDistance = distances.get(current) + edge.getDistance();
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                }
            }
        }
        return (HashMap<Vertex, Double>) distances;
    }

    private Vertex getVertexWithMinimumDistance(Map<Vertex, Double> distances, Set<Vertex> unvisited) {
        return null;
    }

    private Map<Vertex, Double> initializeDistances() {
        return null;
    }
   
}

