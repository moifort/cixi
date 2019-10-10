package infra.algorithm.homemade;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Implementation of Dijkstra's algorithm: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 * <p>
 * With little improvement to return the edges instead of vertex.
 */
public class Dijkstra {
    private List<Vertex> vertexes;
    private List<Edge> edges;
    private Map<Vertex, Set<Vertex>> vertexWithNeighbors;

    public Dijkstra(List<Vertex> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
        this.vertexWithNeighbors = edges.stream().collect(Collectors.groupingBy(Edge::from, Collectors.mapping(Edge::to, Collectors.toSet())));
    }

    public List<Edge> shortestTrack(Vertex from, Vertex to) {
        Set<Vertex> settledNodes = new HashSet<>();
        Set<Vertex> unSettledNodes = new HashSet<>();
        Map<Vertex, Integer> distances = new HashMap<>();
        Map<Vertex, Map.Entry<Vertex, Edge>> predecessors = new HashMap<>();
        distances.put(from, 0);
        unSettledNodes.add(from);

        while (unSettledNodes.size() > 0) {
            Vertex target = unSettledNodes.stream()
                    .min(Comparator.comparing(vertex -> distances.getOrDefault(vertex, Integer.MAX_VALUE)))
                    .orElseThrow();

            // We found it!
            if (target.equals(to)) {
                return getRoute(to, predecessors);
            }

            settledNodes.add(target);
            unSettledNodes.remove(target);

            Set<Vertex> neighbors = vertexWithNeighbors.getOrDefault(target, Collections.emptySet());
            for (Vertex neighbor : neighbors) {
                int targetDistance = distances.getOrDefault(target, Integer.MAX_VALUE);
                Edge minEdge = edges.stream()
                        .filter(edge -> edge.from().equals(target) && edge.to().equals(neighbor))
                        .min(Comparator.comparing(Edge::length))
                        .orElse(null);
                int distanceFromRoot = (targetDistance == Integer.MAX_VALUE || minEdge == null) ? Integer.MAX_VALUE : targetDistance + minEdge.length();
                int neighborDistance = distances.getOrDefault(neighbor, Integer.MAX_VALUE);
                if (distanceFromRoot < neighborDistance) {
                    distances.put(neighbor, distanceFromRoot);
                    predecessors.put(neighbor, Map.entry(target, minEdge));
                    unSettledNodes.add(neighbor);
                }
            }
        }
        return Collections.emptyList();
    }

    private List<Edge> getRoute(Vertex to, Map<Vertex, Map.Entry<Vertex, Edge>> predecessors) {
        List<Edge> route = new ArrayList<>();
        Vertex previous = to;
        do {
            Map.Entry<Vertex, Edge> entry = predecessors.get(previous);
            if (entry != null) {
                previous = entry.getKey();
                route.add(entry.getValue());
            } else {
                previous = null;
            }
        } while (previous != null);
        Collections.reverse(route);
        return route;
    }
}