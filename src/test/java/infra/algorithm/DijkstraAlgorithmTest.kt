package infra.algorithm;

import domain.*;
import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.graph.GraphBuilder;
import es.usc.citius.hipster.graph.GraphSearchProblem;
import es.usc.citius.hipster.graph.HipsterDirectedGraph;
import es.usc.citius.hipster.model.problem.SearchProblem;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DijkstraAlgorithmTest {
    private ShortestTrackAlgorithm shortestTrackAlgorithm = new DijkstraAlgorithm();

    @Test
    public void librarySample() {
        // Given
        HipsterDirectedGraph<String, Double> graph = GraphBuilder.<String, Double>create()
                .connect("A").to("B").withEdge(4d)
                .connect("A").to("C").withEdge(2d)
                .connect("B").to("C").withEdge(5d)
                .connect("B").to("D").withEdge(10d)
                .connect("C").to("E").withEdge(3d)
                .connect("D").to("F").withEdge(11d)
                .connect("E").to("D").withEdge(4d)
                .createDirectedGraph();


        SearchProblem p = GraphSearchProblem
                .startingFrom("A")
                .in(graph)
                .takeCostsFromEdges()
                .build();


        // When
        //System.out.println(Hipster.createDijkstra(p).search("F"));
        //System.out.println(Algorithm.recoverActionPath(Hipster.createDijkstra(p).search("F").getGoalNode()));

        List<String> shortestPath = (List<String>) Hipster.createDijkstra(p).search("F").getOptimalPaths().get(0);

        // Then
        assertThat(shortestPath).containsExactly("A", "C", "E", "D", "F");
    }

    @Test
    public void shouldDisplayTheShortestPath() {
        // Given
        City sillingy = new City("Sillingy");
        City annecy = new City("Annecy");
        City epagny = new City("Epagny");
        City metzTessy = new City("Metz-Tessy");
        City seynod = new City("Seynod");

        Road sillingyToEpany = new Road(sillingy, epagny, new Length(1, Metric.minute));
        Road epagnyToMetzTessy = new Road(epagny, metzTessy, new Length(3, Metric.minute));
        Road metzTessyToAnnecy = new Road(metzTessy, annecy, new Length(12, Metric.minute));
        Road metzTessyToSeynod = new Road(metzTessy, seynod, new Length(4, Metric.minute));
        Road sillingyToSeynod = new Road(sillingy, seynod, new Length(11, Metric.minute));
        Road sillingyToAnnecy = new Road(sillingy, annecy, new Length(19, Metric.minute));
        Road annecyToSeynod = new Road(annecy, seynod, new Length(9, Metric.minute));

        List<Road> roads = Arrays.asList(
                sillingyToEpany,
                epagnyToMetzTessy,
                metzTessyToAnnecy,
                metzTessyToSeynod,
                sillingyToSeynod,
                sillingyToAnnecy,
                annecyToSeynod);

        // When
        List<Road> shortestTrack = shortestTrackAlgorithm.shortestTrack(sillingy, annecy, roads);

        // Then
        Assertions.assertThat(shortestTrack).containsExactly(sillingyToEpany, epagnyToMetzTessy, metzTessyToAnnecy);
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