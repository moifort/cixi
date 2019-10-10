package infra.algorithm.homemade;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Implementation of Dijkstra's algorithm: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 */
public class Dijkstra {
    private List<Vertex> nodes;
    private List<Edge> edges;
    private Map<Vertex, Set<Vertex>> vertexWithNeighbors;

    public Dijkstra(List<Vertex> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
        this.vertexWithNeighbors = edges.stream().collect(Collectors.groupingBy(Edge::from, Collectors.mapping(Edge::to, Collectors.toSet())));
    }

    public List<Vertex> shortestTrack(Vertex from, Vertex to) {
        Set<Vertex> settledNodes = new HashSet<>();
        Set<Vertex> unSettledNodes = new HashSet<>();
        Map<Vertex, Integer> distances = new HashMap<>();
        Map<Vertex, Vertex> predecessors = new HashMap<>();
        distances.put(from, 0);
        unSettledNodes.add(from);

        while (unSettledNodes.size() > 0) {
            Vertex target = unSettledNodes.stream()
                    .min(Comparator.comparing(vertex -> distances.getOrDefault(vertex, Integer.MAX_VALUE)))
                    .orElseThrow();

            // We found it!
            if (target.equals(to)) {
                return getPath(target, predecessors);
            }

            settledNodes.add(target);
            unSettledNodes.remove(target);

            Set<Vertex> neighbors = vertexWithNeighbors.getOrDefault(target, Collections.emptySet());
            for (Vertex neighbor : neighbors) {
                int targetDistance = distances.getOrDefault(target, Integer.MAX_VALUE);
                int length = edges.stream()
                        .filter(edge -> edge.from().equals(target) && edge.to().equals(neighbor))
                        .mapToInt(Edge::length)
                        .min()
                        .orElse(Integer.MAX_VALUE);
                int distanceFromRoot = (targetDistance == Integer.MAX_VALUE || length == Integer.MAX_VALUE) ? Integer.MAX_VALUE : targetDistance + length;
                int neighborDistance = distances.getOrDefault(neighbor, Integer.MAX_VALUE);
                if (distanceFromRoot < neighborDistance) {
                    distances.put(neighbor, distanceFromRoot);
                    predecessors.put(neighbor, target);
                    unSettledNodes.add(neighbor);
                }
            }
        }
        return List.of();
    }

    private List<Vertex> getPath(Vertex target, Map<Vertex, Vertex> predecessors) {
        List<Vertex> route = new ArrayList<>();
        Vertex previous = target;
        do {
            route.add(previous);
            previous = predecessors.getOrDefault(previous, null);
        } while (previous != null);
        Collections.reverse(route);
        return route;
    }
}