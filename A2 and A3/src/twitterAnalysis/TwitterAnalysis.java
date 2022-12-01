package twitterAnalysis;

import graph.AdjacencyListGraph;
import graph.Algorithms;
import staff.Graph;
import staff.Vertex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TwitterAnalysis {
    public static void main(String[ ] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        String data ="";
        Set<List<Vertex>> dataSet;
        AdjacencyListGraph usersGraph = new AdjacencyListGraph();

        System.out.println("Enter a twitter file path");
        String filePath = sc.nextLine().trim();

        try{
            System.out.println("Downloading from " + filePath + " ...");
            data = getFileData(filePath);

        }catch (FileNotFoundException fnfe) {
            System.out.println("File not found : " + filePath);
        }

        dataSet = getVertexDataSet(data);

        for (List<Vertex> users : dataSet) {
            Vertex user1 = users.get(0);
            Vertex user2 = users.get(1);

            usersGraph.addEdge(user2,user1);
        }

        Set<String> queries;

        System.out.println("Enter a query file path");
        filePath = sc.nextLine().trim();

        try{
            System.out.println("Downloading from " + filePath + " ...");
            data = getFileData(filePath);

        }catch (FileNotFoundException fnfe) {
            System.out.println("File not found : " + filePath);
        }

        queries = getQuerySet(data);

        for (String query : queries) {

            String[] queryInfo = query.split(" ");

            String command = queryInfo[0];
            Vertex user1 = new Vertex(queryInfo[1]);
            Vertex user2 = new Vertex(queryInfo[2]);

            if(!usersGraph.vertexExists(user1) || !usersGraph.vertexExists(user2) || user1.equals(user2)) continue;

            if (command.startsWith("c")) {
                System.out.println(commonInfluencers(usersGraph, user1, user2));
            }else{
                System.out.println(retweets(usersGraph,user1,user2));
            }
        }


    }

    public static String commonInfluencers(Graph graph , Vertex user1, Vertex user2) {
        List<Vertex> commonUpstream = Algorithms.commonUpstreamVertices(graph, user1, user2);
        String result = "query: commonInfluencers "+user1 + " " + user2 + "\n";

        if (commonUpstream.isEmpty())
            return result + "There is no common influencers between " + user1 + " and " + user2 + "\n";

        result = result + "<result>\n";

        for (Vertex user : commonUpstream) result = result + user + "\n";

        result += "</result>\n";

        return result;
    }

    public static String retweets(Graph graph , Vertex user1, Vertex user2) {
        String result = "query: numRetweets "+user1 + " " + user2 + "\n";
        int distance = Algorithms.shortestDistance(graph, user1, user2) ;

        if (distance == -1) return result + "There is no connection between " + user1 + " and " + user2 + "\n";

        int retweet = distance-1;

        return result + "<result>\n" + retweet + "\n" + "</result>\n";
    }
    public static String getFileData (String filePath) throws IOException {


        InputStream stream = new FileInputStream(filePath);
        StringBuffer buffer = new StringBuffer();

        int line = stream.read();

        while (line >=0) {
            buffer.append((char) line);
            line = stream.read();
        }

        return buffer.toString();
    }
    public  static Set<List<Vertex>> getVertexDataSet (String data) {
        Set<List<Vertex>> dataSet = new LinkedHashSet<>();

        for (String line : data.split("\n")) {
            String[] users = line.split(" -> ");

            dataSet.add(
                    Arrays.stream(users)
                            .map(user -> new Vertex(user))
                            .toList());
        }
        return dataSet;
    }

    public static Set<String> getQuerySet (String data) {
        Set<String> querySet = new LinkedHashSet<>();

        for (String line : data.split("\n")) {
            if(!isQuery(line)) continue;
            querySet.add(line);
        }
        return querySet;
    }

    public static boolean isQuery (String query) {
        return (query.startsWith("commonInfluencers") || query.startsWith("numRetweets"))
                && query.endsWith("?")
                && (query.split(" ").length == 4);
    }
}
