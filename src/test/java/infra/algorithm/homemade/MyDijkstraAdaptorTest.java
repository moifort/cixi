package infra.algorithm.homemade;

import domain.City;
import domain.Length;
import domain.Road;
import domain.ShortestTrackAlgorithm;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MyDijkstraAdaptorTest {
    private ShortestTrackAlgorithm shortestTrackAlgorithm = new MyDijkstraAdaptor();

    @Test
    public void shouldDisplayTheShortestPath() {
        // Given
        City sillingy = new City("Sillingy");
        City annecy = new City("Annecy");
        City epagny = new City("Epagny");

        Road sillingyToEpany = new Road(sillingy, epagny, new Length(1));
        Road epagnyToAnnecy = new Road(epagny, annecy, new Length(2));
        Road sillingyToAnnecy = new Road(sillingy, annecy, new Length(4));

        List<Road> roads = Arrays.asList(sillingyToEpany, epagnyToAnnecy, sillingyToAnnecy);

        // When
        List<Road> shortestTrack = shortestTrackAlgorithm.shortestTrack(sillingy, annecy, roads);

        // Then
        Assertions.assertThat(shortestTrack).containsExactly(sillingyToEpany, epagnyToAnnecy);
    }
}