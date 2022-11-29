package graph;

import staff.Graph;
import staff.Vertex;

import java.util.*;

public class Algorithms {

    public static Set<List<Vertex>> BFS (Graph graph){

        Set<List<Vertex>> BFS_Set = new LinkedHashSet<>();

        for (Vertex v1 : graph.getVertices()){

            List<Vertex> childV = graph.getDownstreamNeighbors(v1);
            Queue<Vertex> queue = new LinkedList<>();
            List<Vertex> traverse = new ArrayList<>();

            traverse.add(v1);
            queue.addAll(childV);

            while(!queue.isEmpty()) {

                Vertex crrV = queue.remove();

                traverse.add(crrV);

                if(queue.isEmpty()) {
                    List<Vertex> grandChildV = new ArrayList<>();

                    for (Vertex v2 : childV) {
                        for(Vertex v3 : graph.getDownstreamNeighbors(v2)) {
                            if(!traverse.contains(v3)) grandChildV.add(v3);
                        }
                    }

                    childV = grandChildV;
                    queue.addAll(childV);
                }
            }
            BFS_Set.add(traverse);
        }
        return BFS_Set;
    }
    public static Set<List<Vertex>> DFS (Graph graph){

        Set<List<Vertex>> DFS_Set = new LinkedHashSet<>();
        Stack<Vertex> stack = new Stack<>();

        for (Vertex v1 : graph.getVertices()){

            List<Vertex> traverse = new ArrayList<>();

            stack.push(v1);

            while (!stack.isEmpty()) {

                Vertex crrV = stack.peek();
                List<Vertex> childV = graph.getDownstreamNeighbors(crrV);

                if (!traverse.contains(crrV)) traverse.add(crrV);

                if (childV.isEmpty() || traverse.containsAll(childV)) {
                    stack.pop();
                    continue;
                }

                for (Vertex v2 : childV) {

                    if(!traverse.contains(v2)) {
                        stack.push(v2);
                        break;
                    }

                }
            }
            DFS_Set.add(traverse);
        }
        return DFS_Set;
    }
    public static int shortestDistance(Graph graph, Vertex a, Vertex b) {

        if (a.equals(b)) return 0;

        int distance = 1;
        List<Vertex> checkLi = new ArrayList<>();
        List<Vertex> childV = graph.getDownstreamNeighbors(a);
        Queue<Vertex> queue = new LinkedList<>();

        queue.addAll(childV);

        while (!queue.isEmpty()) {
            Vertex crrV = queue.remove();

            if(b.equals(crrV)) return distance;

            checkLi.add(crrV);

            if(queue.isEmpty()) {
                List<Vertex> grandChildV = new ArrayList<>();

                for (Vertex v2 : childV) {
                    for(Vertex v3 : graph.getDownstreamNeighbors(v2)) {
                        if(!checkLi.contains(v3)) grandChildV.add(v3);
                    }
                }

                childV = grandChildV;
                queue.addAll(childV);
                distance++;
            }

        }
        return -1;
    }
    public static List<Vertex> commonUpstreamVertices(Graph graph, Vertex v1, Vertex v2){
        List<Vertex> li = new ArrayList<>();
        li.addAll(graph.getUpstreamNeighbors(v1));
        li.retainAll(graph.getUpstreamNeighbors(v2));
        return li;
    }
    public static List<Vertex> commonDownstreamVertices(Graph graph, Vertex v1, Vertex v2){
        List<Vertex> li = new ArrayList<>();
        li.addAll(graph.getDownstreamNeighbors(v1));
        li.retainAll(graph.getDownstreamNeighbors(v2));
        return li;
    }

}