import graph.AdjacencyListGraph;
import org.junit.Test;
import staff.Vertex;
import twitterAnalysis.TwitterAnalysis;

import static org.junit.Assert.assertEquals;

public class TwitterAnalysisTest {

    /** I added one more edge because there is no vertexes have a common influencer */
    @Test
    public void commonInfluencersTest(){
        AdjacencyListGraph graph = testMethods.getListGraph();

        String expected1 = "query: commonInfluencers B C\n" +
                "<result>\n" +
                "G\n" +
                "</result>\n";

        String expected2 = "query: commonInfluencers A G\n" +
                "There is no common influencers between A and G\n";

        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex g = new Vertex("G");

        graph.addEdge(b,g); // add edge to have a common influencer between b and c.

        assertEquals(expected1,TwitterAnalysis.commonInfluencers(graph,b,c));
        assertEquals(expected2,TwitterAnalysis.commonInfluencers(graph,a,g));


    }
    @Test
    public void retweetsTest(){
        AdjacencyListGraph graph = testMethods.getListGraph();

        String expected1 = "query: numRetweets J A\n" +
                "<result>\n" +
                "2\n" +
                "</result>\n";

        String expected2 = "query: numRetweets J B\n" +
                "There is no connection between J and B\n";

        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex j = new Vertex("J");

        assertEquals(expected1, TwitterAnalysis.retweets(graph,j,a));
        assertEquals(expected2,TwitterAnalysis.retweets(graph,j,b));

    }
    @Test
    public void QueryTest(){
        String validQuery1 ="numRetweets 14838508 98032178 ?";
        String validQuery2 = "commonInfluencers 14838508 98032178 ?";

        String notEndswith = "numRetweets 14838508 98032178";
        String noSpaceBetween = "numRetweets 14838508 98032178?";
        String exceededArgumentNum = "numRetweets 14838508 98032178  14838508 ?";
        String notEnoughArgument = "commonInfluencers 14838508 ?";
        String notValidCommand1 = "numRet 14838508 98032178 ?";
        String notValidCommand2 = "commenInf 14838508 98032178 ?";

        assertEquals(true,TwitterAnalysis.isQuery(validQuery1));
        assertEquals(true,TwitterAnalysis.isQuery(validQuery2));

        assertEquals(false,TwitterAnalysis.isQuery(notEndswith));
        assertEquals(false,TwitterAnalysis.isQuery(noSpaceBetween));
        assertEquals(false,TwitterAnalysis.isQuery(exceededArgumentNum));
        assertEquals(false,TwitterAnalysis.isQuery(notEnoughArgument));
        assertEquals(false,TwitterAnalysis.isQuery(notValidCommand1));
        assertEquals(false,TwitterAnalysis.isQuery(notValidCommand2));
    }

}
