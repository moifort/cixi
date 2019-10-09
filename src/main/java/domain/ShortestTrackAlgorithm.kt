package domain;

import java.util.List;

public interface ShortestTrackAlgorithm {
    List<Road> shortestTrack(City from, City to, List<Road> roads);

    class Exception extends RuntimeException {
        public Exception(String message) {
            super(message);
        }
    }
}
