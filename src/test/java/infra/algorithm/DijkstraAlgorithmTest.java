package infra.algorithm;

import domain.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DijkstraAlgorithmTest {
    private ShortestTrackAlgorithm shortestTrackAlgorithm = new DijkstraAlgorithm();

    @Test
    public void shouldDisplayTheShortestPath() {
        // Given
        City sillingy = new City("Sillingy");
        City annecy = new City("Annecy");
        City epagny = new City("Epagny");

        Road sillingyToEpany = new Road(sillingy, epagny, new Length(1, Metric.minute));
        Road epagnyToAnnecy = new Road(epagny, annecy, new Length(2, Metric.minute));
        Road sillingyToAnnecy = new Road(sillingy, annecy, new Length(4, Metric.minute));

        List<Road> roads = Arrays.asList(sillingyToEpany, epagnyToAnnecy, sillingyToAnnecy);

        // When
        List<Road> shortestTrack = shortestTrackAlgorithm.shortestTrack(sillingy, annecy, roads);

        // Then
        Assertions.assertThat(shortestTrack).containsExactly(sillingyToEpany, epagnyToAnnecy);
    }

    @Test(expected = ShortestTrackAlgorithm.Exception.class)
    public void shouldThrowExceptionIfRoadsContainIdenticalCities() {
        City sillingy = new City("Sillingy");
        City annecy = new City("Annecy");

        Road sillingyToAnnecy1 = new Road(sillingy, annecy, new Length(1, Metric.minute));
        Road sillingyToAnnecy2 = new Road(sillingy, annecy, new Length(3, Metric.minute));

        List<Road> roads = Arrays.asList(sillingyToAnnecy1, sillingyToAnnecy2);

        // When
        List<Road> shortestTrack = shortestTrackAlgorithm.shortestTrack(sillingy, annecy, roads);

        // Then
        // Booooum
    }

}