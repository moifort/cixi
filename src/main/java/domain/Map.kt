package domain;

import java.util.List;

public class Map {
    private List<Road> roads;
    private ShortestTrackAlgorithm shortestTrackAlgorithm;

    public Map(List<Road> roads, ShortestTrackAlgorithm shortestTrackAlgorithm) {
        this.roads = roads;
        this.shortestTrackAlgorithm = shortestTrackAlgorithm;
    }

    public List<Road> shortestTrack(City from, City to) {
        return shortestTrackAlgorithm.shortestTrack(from, to, roads);
    }
}
