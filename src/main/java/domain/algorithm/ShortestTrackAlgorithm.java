package domain.algorithm;

import domain.City;
import domain.Road;

import java.util.List;

public interface ShortestTrackAlgorithm {

    List<Road> shortestTrack(City from, City to, List<Road> roads);
}
