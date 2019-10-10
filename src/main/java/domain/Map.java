package domain;

import java.util.List;

public class Map {
    private List<City> cities;
    private List<Road> roads;
    private ShortestTrackAlgorithm shortestTrackAlgorithm;

    public Map(List<City> cities, List<Road> roads, ShortestTrackAlgorithm shortestTrackAlgorithm) {
        this.cities = cities;
        this.roads = roads;
        this.shortestTrackAlgorithm = shortestTrackAlgorithm;
    }

    public List<Road> shortestTrack(City from, City to) {
        return shortestTrackAlgorithm.shortestTrack(from, to, roads);
    }

    public List<City> cities() {
        return cities;
    }

    public List<Road> roads() {
        return roads;
    }

    public ShortestTrackAlgorithm getShortestTrackAlgorithm() {
        return shortestTrackAlgorithm;
    }
}
