
//constructs the necessary fields of the class
public abstract class AbstractPlayerAlgorithm {
    private final Graph graph;
    private Vertex currentVertex;

    public AbstractPlayerAlgorithm(Graph graph) {
        this.graph = graph;
        this.currentVertex = null;
    }

    //eturns the underyling Graph
    public Graph getGraph() {
        return graph;
    }
    //returns the current Vertex of the player
    public Vertex getCurrentVertex() {
        return currentVertex;
    }
    //updates the current Vertex of the player to be the given Vertex vertex
    public void setCurrentVertex(Vertex vertex) {
        currentVertex = vertex;
    }
    //returns a Vertex for the player to start at and updates the current Vertex to that location
    public abstract Vertex chooseStart();
    //returns a Vertex for the player to start at based on where the other player chose to start
    public abstract Vertex chooseStart(Vertex other);
    //returns a Vertex for the player to move to based on where the other player currently is
    public abstract Vertex chooseNext(Vertex otherPlayer);
}
