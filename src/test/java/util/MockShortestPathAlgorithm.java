package util;

import domain.City;
import domain.Road;
import domain.ShortestTrackAlgorithm;

import java.util.Collections;
import java.util.List;

public class MockShortestPathAlgorithm implements ShortestTrackAlgorithm {
    private List<Road> route;
    private String name;

    public MockShortestPathAlgorithm(String name) {
        this(name, Collections.emptyList());
    }

    public MockShortestPathAlgorithm(String name, List<Road> route) {
        this.route = route;
        this.name = name;
    }

    @Override
    public List<Road> shortestTrack(City from, City to, List<Road> roads) {
        return route;
    }

    @Override
    public String getName() {
        return name;
    }
}
