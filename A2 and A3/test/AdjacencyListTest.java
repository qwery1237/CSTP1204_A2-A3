import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

import graph.AdjacencyListGraph;
import staff.Vertex;

public class AdjacencyListTest {

    @Test
    public void downstreamTest() {
        AdjacencyListGraph graph = testMethods.getListGraph();

        Vertex v = new Vertex("A");

        String[] expected = { "B","C" };
        List<Vertex> expectedList = testMethods.toList(expected);

        assertEquals(expectedList,graph.getDownstreamNeighbors(v));

        /** Add another downstream */
        Vertex newDownstream = new Vertex("J");

        String[] expected2 = {"B","C","J"};
        expectedList= testMethods.toList(expected2);

        graph.addEdge(v,newDownstream);

        assertEquals(expectedList,graph.getDownstreamNeighbors(v));


    }
    @Test
    public void upstreamTest() {
        AdjacencyListGraph graph = testMethods.getListGraph();

        Vertex v = new Vertex("E");

        String[] expected = { "D" };
        List<Vertex> expectedList = testMethods.toList(expected);

        assertEquals(expectedList,graph.getUpstreamNeighbors(v));

        /** Add another upstream */
        Vertex newUpstream = new Vertex("A");

        String[] expected2 = {"A","D"};
        expectedList= testMethods.toList(expected2);

        graph.addEdge(newUpstream,v);

        assertEquals(expectedList,graph.getUpstreamNeighbors(v));
    }

    @Test
    public void getVerticesTest() {
        AdjacencyListGraph graph = testMethods.getListGraph();

        String[] expected = {"A","B","C","D","E","F","G","H","I","J"};
        List<Vertex> expectedList = testMethods.toList(expected);

        assertEquals(expectedList,graph.getVertices());
    }



}
