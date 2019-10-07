package domain.algorithm;

import domain.City;
import domain.Road;
import domain.algorithm.dijkstra.Dijkstra;

import java.util.List;

public class DijkstraAlgorithm implements ShortestTrackAlgorithm {
    private Dijkstra dijkstra = new Dijkstra();

    @Override
    public List<Road> shortestTrack(City from, City to, List<Road> roads) {
        throw new RuntimeException("Not Implemented Yet");
    }

}
