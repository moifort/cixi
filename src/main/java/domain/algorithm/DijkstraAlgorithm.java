package domain.algorithm;

import domain.City;
import domain.Road;
import domain.algorithm.dijkstra.Dijkstra;
import domain.algorithm.dijkstra.Vertex;

import java.util.List;

public class DijkstraAlgorithm implements ShortestTrackAlgorithm {
    private Dijkstra dijkstra = new Dijkstra();

    @Override
    public List<Road> shortestTrack(City from, City to, List<Road> roads) {
        List<Vertex> vertex = toVertex(roads);
        Vertex start = findStartVertex(from, vertex);
        Vertex end = findEndVertex(to, vertex);
        List<Vertex> shortestTrack = dijkstra.shortestPathTo(start, end);
        return toRoad(shortestTrack);
    }

    private List<Road> toRoad(List<Vertex> shortestTrack) {
        return null;
    }

    private Vertex findEndVertex(City to, List<Vertex> vertex) {
        return null;
    }

    private Vertex findStartVertex(City from, List<Vertex> vertex) {
        return null;
    }

    private List<Vertex> toVertex(List<Road> roads) {
        return null;
    }

}
