import graph.AdjacencyListGraph;
import graph.AdjacencyMatrixGraph;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import staff.Graph;
import staff.Vertex;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class GraphTest {
    // Graph aGraph = new AdjacencyMatrixGraph();
    Graph aGraph;

    public GraphTest(Graph anInterface) {
        this.aGraph = anInterface;
    }

    @Before
    public void setup() throws InstantiationException, IllegalAccessException {
        this.aGraph = aGraph.getClass().newInstance();
    }

    @Test
    public void addVertexAndGetVerticesTest() {
        Vertex a = new Vertex("a");
        aGraph.addVertex(a);
        assertEquals(a, aGraph.getVertices().get(0));

        Vertex b = new Vertex("b");
        aGraph.addVertex(b);
        assertEquals(2, aGraph.getVertices().size());
    }

    @Test
    public void addEdgeAndEdgeExistsTest() {
        // Graph empty at initialization
        assertEquals(0, aGraph.getVertices().size());
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");

        // Test basic edgeExists()
        aGraph.addVertex(a);
        aGraph.addVertex(b);
        aGraph.addEdge(a, b);
        assertEquals(true, aGraph.edgeExists(a, b));
        assertEquals(false, aGraph.edgeExists(b, a));

        // The edge should not be added to the graph because c is not in the
        // graph
        try {
            aGraph.addEdge(a, c);
        } catch (Exception e) {
            // Even if an exception was thrown, this is not a failure
        }
        assertEquals(false, aGraph.edgeExists(a, c));
    }

    @Test
    public void getUpstreamNeighborsTest() {
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");

        aGraph.addVertex(a);
        aGraph.addVertex(b);
        aGraph.addEdge(a, b);

        // 0 upstream
        assertEquals(0, aGraph.getUpstreamNeighbors(a).size());

        // 1 >= upsteams
        assertEquals(1, aGraph.getUpstreamNeighbors(b).size());

        // 1 > = upstreams with loops
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        aGraph.addVertex(c);
        aGraph.addVertex(d);
        aGraph.addVertex(e);
        aGraph.addEdge(a, c);
        aGraph.addEdge(b, d);
        aGraph.addEdge(b, e);
        aGraph.addEdge(e, a);
        aGraph.addEdge(c, d);
        assertEquals(2, aGraph.getUpstreamNeighbors(d).size());
        assertEquals(1, aGraph.getUpstreamNeighbors(c).size());

        // Test the traversal(s)
        assertEquals(new HashSet<Vertex>() {
            {
                add(b);
                add(c);
            }
        }, new HashSet<Vertex>(aGraph.getUpstreamNeighbors(d)));
    }

    @Test
    public void getDownstreamNeighborsTest() {
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");

        aGraph.addVertex(a);
        aGraph.addVertex(b);
        aGraph.addEdge(a, b);

        // 0 downstream
        assertEquals(0, aGraph.getDownstreamNeighbors(b).size());

        // 1 >= downstreams
        assertEquals(1, aGraph.getDownstreamNeighbors(a).size());

        // 1 > = downstreams with loops
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        aGraph.addVertex(c);
        aGraph.addVertex(d);
        aGraph.addVertex(e);
        aGraph.addEdge(a, c);
        aGraph.addEdge(b, d);
        aGraph.addEdge(b, e);
        aGraph.addEdge(e, a);
        assertEquals(1, aGraph.getDownstreamNeighbors(e).size());
        assertEquals(2, aGraph.getDownstreamNeighbors(b).size());

        // Test the traversal(s)
        assertEquals(new HashSet<Vertex>() {
            {
                add(d);
                add(e);
            }
        }, new HashSet<Vertex>(aGraph.getDownstreamNeighbors(b)));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(new Object[] { new AdjacencyListGraph() }, new Object[] { new AdjacencyMatrixGraph() });
    }

}