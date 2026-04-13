/*
 * Filename: Vertex.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS 231 A
 * Project 08
 */

 import java.util.ArrayList;
 import java.util.List;
 
 
 public class Vertex {
     private List<Edge> edges;
 
     //initializes a Vertex
     public Vertex() {
         edges = new ArrayList<>();
     }
     //returns the Edge which connects this vertex and the given Vertex vertex if such an Edge exists
     public Edge getEdgeTo(Vertex vertex) {
         for (Edge edge : edges) {
             if (edge.connectsTo(vertex)) {
                 return edge;
             }
         }
         return null;
     }
     //adds the specified Edge edge to the collection of Edges incident to this Vertex.
     public void addEdge(Edge edge) {
         edges.add(edge);
     }
     //removes this Edge from the collection of Edges incident to this Vertex
     public boolean removeEdge(Edge edge) {
         return edges.remove(edge);
     }
     //returns a collection of all the Vertices adjacent to this Vertex
     public Iterable<Vertex> adjacentVertices() {
         List<Vertex> adjacentVertices = new ArrayList<>();
         for (Edge edge : edges) {
             Vertex adjacentVertex = edge.getOppositeVertex(this);
             adjacentVertices.add(adjacentVertex);
         }
         return adjacentVertices;
     }
     //returns a collection of all the Edges incident to this Vertex
     public Iterable<Edge> incidentEdges() {
         return edges;
     }
    public List<Edge> getEdges() {
        return null;
    }
 }
 