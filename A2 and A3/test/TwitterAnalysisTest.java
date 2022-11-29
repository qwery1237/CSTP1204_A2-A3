import static org.junit.Assert.*;

import graph.AdjacencyListGraph;
import graph.AdjacencyMatrixGraph;
import org.junit.Test;
import staff.Vertex;
import twitterAnalysis.TwitterAnalysis;

public class TwitterAnalysisTest {
    @Test
    public void commonInfluencersTest(){
        AdjacencyListGraph graph = testMethods.getListGraph();

        String expected1 = "query: commonInfluencers G H\n" +
                "<result>\n" +
                "C\n" +
                "</result>\n";

        String expected2 = "query: commonInfluencers A G\n" +
                "There is no common influences between A and G\n";

        Vertex a = new Vertex("A");
        Vertex g = new Vertex("G");
        Vertex h = new Vertex("H");

        assertEquals(expected1,TwitterAnalysis.commonInfluencers(graph,g,h));
        assertEquals(expected2,TwitterAnalysis.commonInfluencers(graph,a,g));


    }
    @Test
    public void retweetsTest(){
        AdjacencyListGraph graph = testMethods.getListGraph();

        String expected1 = "query: numRetweets A J\n" +
                "<result>\n" +
                "3\n" +
                "</result>\n";

        String expected2 = "query: numRetweets B J\n" +
                "There is no connection between B and J\n";

        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex j = new Vertex("J");

        assertEquals(expected1, TwitterAnalysis.retweets(graph,a,j));
        assertEquals(expected2,TwitterAnalysis.retweets(graph,b,j));

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
