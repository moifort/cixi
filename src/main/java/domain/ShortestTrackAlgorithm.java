package domain;

import java.util.List;

public interface ShortestTrackAlgorithm {
    List<Road> shortestTrack(City from, City to, List<Road> roads);

    String getName();

    class Exception extends RuntimeException {
        public Exception(String message) {
            super(message);
        }
    }
}
