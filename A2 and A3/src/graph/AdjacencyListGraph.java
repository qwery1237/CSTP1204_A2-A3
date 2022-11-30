package graph;

import staff.Graph;
import staff.Vertex;

import java.util.*;

public class AdjacencyListGraph implements Graph {
    private Map<Vertex,List<Vertex>> adjacencyList;

    public AdjacencyListGraph() { adjacencyList = new LinkedHashMap<>(); }
    @Override
    public void addVertex(Vertex v) { adjacencyList.putIfAbsent(v, new LinkedList<>()); }

    @Override
    public void addEdge(Vertex v1, Vertex v2) {
        if (!vertexExists(v1)) addVertex(v1);
        if (!vertexExists(v2)) addVertex(v2);

        adjacencyList.get(v1).add(v2);
    }

    public boolean vertexExists(Vertex v) { return adjacencyList.containsKey(v); }
    @Override
    public boolean edgeExists(Vertex v1, Vertex v2) {
        return vertexExists(v1) && vertexExists(v2) && adjacencyList.get(v1).contains(v2);
    }

    @Override
    public List<Vertex> getDownstreamNeighbors(Vertex v) {
        if (!vertexExists(v)) return Collections.EMPTY_LIST;

        List<Vertex> downstream = adjacencyList.get(v);

        if (downstream == null) return Collections.EMPTY_LIST;
        return downstream;
    }

    @Override
    public List<Vertex> getUpstreamNeighbors(Vertex v) {
        if (!vertexExists(v)) return Collections.EMPTY_LIST;

        List<Vertex> upstream = new LinkedList<>();
        List<Vertex> vertices = getVertices();

        for (Vertex v1 : vertices) {
            if(edgeExists(v1,v)) upstream.add(v1);
        }

        if (upstream.isEmpty()) return Collections.EMPTY_LIST;
        return upstream;
    }

    @Override
    public List<Vertex> getVertices() { return adjacencyList.keySet().stream().toList(); }

}
