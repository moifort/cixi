package domain.algorithm.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;


/***
 * How it's work: https://fr.wikipedia.org/wiki/Algorithme_de_Dijkstra
 * Code sample take from: https://gist.github.com/artlovan/a07f29e16ab725f8077157de7abdf125#file-dijkstra-java
 */

public class Dijkstra {


    public List<Vertex> shortestPathTo(Vertex from, Vertex to) {
        from.setMinDistance(0);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(from);

        while (!priorityQueue.isEmpty()) {
            Vertex vertex = priorityQueue.poll();

            for (Edge edge : vertex.getEdges()) {
                Vertex v = edge.getTargetVertex();
                double weight = edge.getWeight();
                double minDistance = vertex.getMinDistance() + weight;

                if (minDistance < v.getMinDistance()) {
                    priorityQueue.remove(vertex);
                    v.setPreviosVertex(vertex);
                    v.setMinDistance(minDistance);
                    priorityQueue.add(v);
                }
            }
        }

        List<Vertex> path = new ArrayList<>();

        for (Vertex vertex = to; vertex != null; vertex = vertex.getPreviosVertex()) {
            path.add(vertex);
        }

        Collections.reverse(path);
        return path;
    }
}