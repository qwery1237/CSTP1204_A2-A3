import graph.AdjacencyListGraph;
import graph.AdjacencyMatrixGraph;
import staff.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * I added my graph IMG to img directory
 * You can see how my graph looks like
 *
 * */
public class testMethods {
    public static AdjacencyListGraph getListGraph () {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");
        Vertex g = new Vertex("G");
        Vertex h = new Vertex("H");
        Vertex i = new Vertex("I");
        Vertex j = new Vertex("J");

        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);
        graph.addVertex(g);
        graph.addVertex(h);
        graph.addVertex(i);
        graph.addVertex(j);

        graph.addEdge(a,b);
        graph.addEdge(a,c);
        graph.addEdge(b,d);
        graph.addEdge(c,g);
        graph.addEdge(c,h);
        graph.addEdge(c,i);
        graph.addEdge(d,e);
        graph.addEdge(d,f);
        graph.addEdge(i,j);

        return graph;
    }

    public static AdjacencyMatrixGraph getMatrixGraph () {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");
        Vertex g = new Vertex("G");
        Vertex h = new Vertex("H");
        Vertex i = new Vertex("I");
        Vertex j = new Vertex("J");

        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);
        graph.addVertex(g);
        graph.addVertex(h);
        graph.addVertex(i);
        graph.addVertex(j);

        graph.addEdge(a,b);
        graph.addEdge(a,c);
        graph.addEdge(b,d);
        graph.addEdge(c,g);
        graph.addEdge(c,h);
        graph.addEdge(c,i);
        graph.addEdge(d,e);
        graph.addEdge(d,f);
        graph.addEdge(i,j);

        return graph;
    }

    public static List<Vertex> toList(String[] expected) {
        List<Vertex> li = new ArrayList<>();
        for (String el: expected) li.add(new Vertex(el));
        return li;
    }
}
