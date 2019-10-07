package domain;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class MapTest {

    @Test
    public void shortestTrack() {
        // Given
        City sillingy = new City("Sillingy");
        City annecy = new City("Annecy");
        City epagny = new City("Epagny");
        City metzTessy = new City("Metz-Tessy");
        City seynod = new City("Seynod");

        Road sillingyToEpany = new Road(sillingy, annecy, new Length(1, Metric.minute));
        Road epagnyToMetzTessy = new Road(epagny, metzTessy, new Length(3, Metric.minute));
        Road metzTessyToAnnecy = new Road(metzTessy, annecy, new Length(12, Metric.minute));
        Road metzTessyToSeynod = new Road(metzTessy, seynod, new Length(4, Metric.minute));
        Road sillingyToSeynod = new Road(sillingy, seynod, new Length(11, Metric.minute));
        Road sillingyToAnnecy = new Road(metzTessy, annecy, new Length(19, Metric.minute));
        Road annecyToSeynod = new Road(annecy, seynod, new Length(9, Metric.minute));

        Map map = new Map(List.of(sillingyToEpany, epagnyToMetzTessy, metzTessyToAnnecy, metzTessyToSeynod, sillingyToSeynod, sillingyToAnnecy, annecyToSeynod));

        // When
        List<Road> shortestTrack = map.shortestTrack(sillingy, annecy);

        // Then
        assertThat(shortestTrack).containsExactly(sillingyToEpany, epagnyToMetzTessy, metzTessyToAnnecy);
    }
}