package graph;

import staff.Graph;
import staff.Vertex;

import java.util.*;

public class AdjacencyMatrixGraph implements Graph {
    private Map<Vertex,Map<Vertex,Boolean>> adjacencyMatrix;

    private void validateGraph() {
        List<Vertex> vertices = getVertices();

        for (Vertex v1 : vertices) {
            for(Vertex v2 : vertices) {
                adjacencyMatrix.get(v1).putIfAbsent(v2,false);
            }
        }
    }
    public AdjacencyMatrixGraph() { adjacencyMatrix = new LinkedHashMap<>(); }
    @Override
    public void addVertex(Vertex v) {
        adjacencyMatrix.putIfAbsent(v, new LinkedHashMap<>());

        validateGraph();
    }

    @Override
    public void addEdge(Vertex v1, Vertex v2) {
        if (!vertexExists(v1)) addVertex(v1);
        if (!vertexExists(v2)) addVertex(v2);

        adjacencyMatrix.get(v1).put(v2,true);
    }

    public boolean vertexExists(Vertex v) { return adjacencyMatrix.containsKey(v); }
    @Override
    public boolean edgeExists(Vertex v1, Vertex v2) {
        return vertexExists(v1) && vertexExists(v2) && adjacencyMatrix.get(v1).get(v2);
    }

    @Override
    public List<Vertex> getDownstreamNeighbors(Vertex v) {
        if(!vertexExists(v)) return Collections.EMPTY_LIST;

        List<Vertex> downstream = new LinkedList<>();
        List<Vertex> vertices = getVertices();

        for (Vertex v1 : vertices) {
            if(edgeExists(v,v1)) downstream.add(v1);
        }

        if (downstream.isEmpty()) return Collections.EMPTY_LIST;
        return downstream;
    }

    @Override
    public List<Vertex> getUpstreamNeighbors(Vertex v) {
        if(!vertexExists(v)) return Collections.EMPTY_LIST;

        List<Vertex> upstream = new LinkedList<>();
        List<Vertex> vertices = getVertices();

        for (Vertex v1 : vertices) {
            if(edgeExists(v1,v)) upstream.add(v1);
        }

        if (upstream.isEmpty()) return Collections.EMPTY_LIST;
        return upstream;
    }

    @Override
    public List<Vertex> getVertices() { return adjacencyMatrix.keySet().stream().toList(); }

}
