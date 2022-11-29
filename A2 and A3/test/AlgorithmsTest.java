import static org.junit.Assert.*;

import graph.AdjacencyListGraph;
import graph.AdjacencyMatrixGraph;
import graph.Algorithms;
import org.junit.Test;
import staff.Vertex;

import java.util.*;

public class AlgorithmsTest {


    @Test
    public void BFSTest(){
        AdjacencyListGraph listGraph = testMethods.getListGraph();
        AdjacencyMatrixGraph matrixGraph = testMethods.getMatrixGraph();

        Set<List<Vertex>> bfs1 = Algorithms.BFS(listGraph);
        Set<List<Vertex>> bfs2 = Algorithms.BFS(matrixGraph);

        String[][] expected =  {
                {"A","B","C","D","G","H","I","E","F","J"},
                {"B","D","E","F"},
                {"C","G","H","I","J"},
                {"D","E","F"},
                {"E"},
                {"F"},
                {"G"},
                {"H"},
                {"I","J"},
                {"J"}
        };

        Set<List<Vertex>> expectedSet = new LinkedHashSet<>();

        for (String[] li : expected) expectedSet.add(testMethods.toList(li));

        assertEquals(expectedSet,bfs1);
        assertEquals(expectedSet,bfs2);

    }
    @Test
    public void DFSTest(){
        AdjacencyListGraph listGraph = testMethods.getListGraph();
        AdjacencyMatrixGraph matrixGraph = testMethods.getMatrixGraph();

        Set<List<Vertex>> dfs1 = Algorithms.DFS(listGraph);
        Set<List<Vertex>> dfs2 = Algorithms.DFS(matrixGraph);

        String[][] expected =  {
                {"A","B","D","E","F","C","G","H","I","J"},
                {"B","D","E","F"},
                {"C","G","H","I","J"},
                {"D","E","F"},
                {"E"},
                {"F"},
                {"G"},
                {"H"},
                {"I","J"},
                {"J"}
        };

        Set<List<Vertex>> expectedSet = new LinkedHashSet<>();

        for (String[] li : expected) expectedSet.add(testMethods.toList(li));

        assertEquals(expectedSet,dfs1);
        assertEquals(expectedSet,dfs2);
    }
    @Test
    public void shortestDistanceTest() {
        AdjacencyListGraph graph1 = testMethods.getListGraph();
        AdjacencyMatrixGraph graph2 = testMethods.getMatrixGraph();

        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");

        assertEquals(0,Algorithms.shortestDistance(graph1,a,a));
        assertEquals(0,Algorithms.shortestDistance(graph2,a,a));

        assertEquals(1,Algorithms.shortestDistance(graph1,a,b));
        assertEquals(1,Algorithms.shortestDistance(graph2,a,b));

        assertEquals(2,Algorithms.shortestDistance(graph1,a,d));
        assertEquals(2,Algorithms.shortestDistance(graph2,a,d));

        assertEquals(3,Algorithms.shortestDistance(graph1,a,e));
        assertEquals(3,Algorithms.shortestDistance(graph2,a,e));

        assertEquals(-1,Algorithms.shortestDistance(graph1,e,f));
        assertEquals(-1,Algorithms.shortestDistance(graph2,e,f));

    }
    @Test
    public void commonUpstreamTest() {
        AdjacencyListGraph graph1 = testMethods.getListGraph();
        AdjacencyMatrixGraph graph2 = testMethods.getMatrixGraph();

        Vertex a = new Vertex("A");
        Vertex g = new Vertex("G");
        Vertex h = new Vertex("H");

        String[] expected = {"C"};

        assertEquals(Collections.EMPTY_LIST,Algorithms.commonUpstreamVertices(graph1,a,g));
        assertEquals(Collections.EMPTY_LIST,Algorithms.commonUpstreamVertices(graph2,a,g));

        assertEquals(testMethods.toList(expected),Algorithms.commonUpstreamVertices(graph1,g,h));
        assertEquals(testMethods.toList(expected),Algorithms.commonUpstreamVertices(graph2,g,h));

    }
    /** There is no common downstream neighbors in the graph.
     * So I added another edge to see the function works properly */
    @Test
    public void commonDownstreamTest() {
        AdjacencyListGraph graph1 = testMethods.getListGraph();
        AdjacencyMatrixGraph graph2 = testMethods.getMatrixGraph();

        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex g = new Vertex("G");

        graph1.addEdge(b,g);
        graph2.addEdge(b,g);

        String[] expected = {"G"};

        assertEquals(Collections.EMPTY_LIST,Algorithms.commonDownstreamVertices(graph1,a,g));
        assertEquals(Collections.EMPTY_LIST,Algorithms.commonDownstreamVertices(graph2,a,g));

        assertEquals(testMethods.toList(expected),Algorithms.commonDownstreamVertices(graph1,b,c));
        assertEquals(testMethods.toList(expected),Algorithms.commonDownstreamVertices(graph2,b,c));
    }


}
