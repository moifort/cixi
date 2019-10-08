package infra.algorithm;

import domain.City;
import domain.Road;
import domain.ShortestTrackAlgorithm;
import es.usc.citius.hipster.algorithm.Algorithm;
import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.graph.GraphBuilder;
import es.usc.citius.hipster.graph.GraphSearchProblem;
import es.usc.citius.hipster.graph.HipsterDirectedGraph;
import es.usc.citius.hipster.model.problem.SearchProblem;

import java.util.List;

/**
 * Use of Dijkstra algorithm: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 * <p>
 * this algorithm cannot manage tow road starting and ending to the same cities
 */

public class DijkstraAlgorithm implements ShortestTrackAlgorithm {

    @Override
    public List<Road> shortestTrack(City from, City to, List<Road> roads) {
        if (isRoadWithIdenticalCities(roads))
            throw new ShortestTrackAlgorithm.Exception("Cannot have road with identical started and ended cities");
        GraphBuilder<City, Road> graphBuilder = GraphBuilder.create();
        roads.forEach(road -> graphBuilder.connect(road.from()).to(road.to()).withEdge(road));
        HipsterDirectedGraph<City, Road> graph = graphBuilder.createDirectedGraph();
        SearchProblem path = GraphSearchProblem
                .startingFrom(from)
                .in(graph)
                .extractCostFromEdges(road -> (double) road.length().value())
                .build();

        Algorithm.SearchResult shortestTrack = Hipster.createDijkstra(path).search(to);
        return Algorithm.recoverActionPath(shortestTrack.getGoalNode());
    }

    private boolean isRoadWithIdenticalCities(List<Road> roads) {
        for (Road roadToCheck : roads) {
            boolean hasDuplicate = roads
                    .stream()
                    .anyMatch(road -> !road.id().equals(roadToCheck.id())
                            && road.from().equals(roadToCheck.from())
                            && road.to().equals(roadToCheck.to()));
            if (hasDuplicate) return true;
        }
        return false;
    }
}
