import graph.AdjacencyListGraph;
import graph.AdjacencyMatrixGraph;
import graph.Algorithms;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import staff.Graph;
import staff.Vertex;

import java.util.*;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class AlgorithmsTest {
    // Graph aGraph = new AdjacencyListGraph();
    Graph aGraph;
    static Vertex a;
    static Vertex b;
    static Vertex c;

    static Vertex d;
    static Vertex e;
    static Vertex f;

    static Vertex g;
    static Vertex h;
    static Vertex i;

    static Vertex j;
    static Vertex k;
    static Vertex l;

    static int GRAPH_SIZE = 12;

    // @BeforeClass
    // public void setupGraph() {
    //
    // }

    public AlgorithmsTest(Graph anInterface) {
        this.aGraph = anInterface;
    }

    @Before
    public void setup() throws InstantiationException, IllegalAccessException {
        this.aGraph = aGraph.getClass().newInstance();

        a = new Vertex("A");
        b = new Vertex("B");
        c = new Vertex("C");
        d = new Vertex("D");
        e = new Vertex("E");
        f = new Vertex("F");
        g = new Vertex("G");
        h = new Vertex("H");
        i = new Vertex("I");
        j = new Vertex("J");
        k = new Vertex("K");
        l = new Vertex("L");

        aGraph.addVertex(a);
        aGraph.addVertex(b);
        aGraph.addVertex(c);

        aGraph.addVertex(d);
        aGraph.addVertex(e);
        aGraph.addVertex(f);

        aGraph.addVertex(g);
        aGraph.addVertex(h);
        aGraph.addVertex(i);

        aGraph.addVertex(j);
        aGraph.addVertex(k);
        aGraph.addVertex(l);

        aGraph.addEdge(a, b);
        aGraph.addEdge(b, a);
        aGraph.addEdge(b, c);
        aGraph.addEdge(b, d);
        aGraph.addEdge(c, a);
        aGraph.addEdge(d, c);
        aGraph.addEdge(b, e);
        aGraph.addEdge(b, f);
        aGraph.addEdge(e, b);
        aGraph.addEdge(f, b);
        aGraph.addEdge(d, g);
        aGraph.addEdge(g, h);
        aGraph.addEdge(g, i);
        aGraph.addEdge(i, j);
        aGraph.addEdge(i, k);
    }

    @Test
    public void testDFS() {
        Set<List<Vertex>> setOfTraversalListsDFS = Algorithms.DFS(aGraph);

        // check the size of the set
        assertEquals(GRAPH_SIZE, setOfTraversalListsDFS.size());

        // check the traversal(s)
        for (List<Vertex> aList : setOfTraversalListsDFS) {
            Vertex startingVertex = aList.get(0);

            // Check the traversal of Vertex b
            if (startingVertex.equals(b)) {
                // check the size of each list
                // and make sure that there are no duplicates
                assertEquals(GRAPH_SIZE - 1, aList.size()); // "L" is not
                // connected to the
                // graph
                assertFalse(checkIfThereIsADuplicate(aList));

                // Check that A is immediatly after C
                assertEquals(1, (aList.indexOf(c) - aList.indexOf(a)));
            }
        }
    }

    @Test
    public void testBFS() {
        Set<List<Vertex>> setOfTraversalListsDFS = Algorithms.BFS(aGraph);

        // Check the size of the set
        assertEquals(GRAPH_SIZE, setOfTraversalListsDFS.size());

        // check the traversal(s)
        for (List<Vertex> aList : setOfTraversalListsDFS) {
            Vertex startingVertex = aList.get(0);

            // Check the traversal of Vertex b
            if (startingVertex.equals(b)) {
                // Check the size of each list
                // and make sure that there are no duplicates
                assertEquals(GRAPH_SIZE - 1, aList.size()); // "L" is not
                // connected to the
                // graph
                assertFalse(checkIfThereIsADuplicate(aList));

                // Check that H and I are not more than one hop distance apart
                assertTrue((Math.abs(aList.indexOf(h) - aList.indexOf(i))) == 1);
            }
        }
    }

    @Test
    public void shortestDistanceTest() {
        // Varias Tests with Distances >= 0
        try {
            assertEquals(0, Algorithms.shortestDistance(aGraph, a, a));
            assertEquals(2, Algorithms.shortestDistance(aGraph, a, f));
            assertEquals(1, Algorithms.shortestDistance(aGraph, b, c));
            assertEquals(3, Algorithms.shortestDistance(aGraph, d, b));
            assertEquals(4, Algorithms.shortestDistance(aGraph, d, e));
            assertEquals(5, Algorithms.shortestDistance(aGraph, f, k));
        } catch (Exception e) {
            fail(); // Should not through an exception
        }

        // Test no path == Exception or return special value
        try {
            assertTrue(Algorithms.shortestDistance(aGraph, j, k) < 0);
        } catch (Exception e) {
            // Its alright to through an exception if there is no path
        }
    }

    @Test
    public void commonDownstreamTest() {
        // Test Normal case
        List<Vertex> intersectionList = Algorithms.commonDownstreamVertices(aGraph, a, e);
        assertEquals(new HashSet<Vertex>() {
            {
                add(b);
            }
        }, new HashSet<Vertex>(intersectionList));

        // Test No commonUpstream
        assertEquals(0, Algorithms.commonDownstreamVertices(aGraph, l, a).size()); // "L"
        // is
        // not
        // connected
        // to
        // the
        // graph
        // Test Symmetry
        assertEquals(Algorithms.commonDownstreamVertices(aGraph, a, e).size(),
                Algorithms.commonDownstreamVertices(aGraph, e, a).size());
    }

    @Test
    public void commonUpstreamTest() {

        // Test Normal case
        List<Vertex> intersectionList = Algorithms.commonUpstreamVertices(aGraph, d, e);

        assertEquals(new HashSet<Vertex>() {
            {
                add(b);
            }
        }, new HashSet<Vertex>(intersectionList));

        // Test No commonUpstream
        assertEquals(0, Algorithms.commonUpstreamVertices(aGraph, l, a).size()); // "L"
        // is
        // not
        // connected
        // to
        // the
        // graph

        // Test Symmetry
        assertEquals(Algorithms.commonUpstreamVertices(aGraph, d, e).size(), Algorithms.commonUpstreamVertices(aGraph, e, d).size());
    }

    private boolean checkIfThereIsADuplicate(List<Vertex> aList) {
        Set<Vertex> set1 = new HashSet<Vertex>();
        for (Vertex aVerterx : aList) {
            if (!set1.add(aVerterx)) {
                return true;
            }
        }
        return false;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(new Object[] { new AdjacencyListGraph() }, new Object[] { new AdjacencyMatrixGraph() });
    }

}