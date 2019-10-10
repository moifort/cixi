package infra.algorithm.homemade;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Collection;
import java.util.List;


public class DijkstraTest {

    @Test
    public void shouldFindTheShortestTrack() {
        // Given
        Vertex vertexA = new Vertex("A");
        Vertex vertexB = new Vertex("B");
        Vertex vertexC = new Vertex("C");

        Edge edgeAB = new Edge("AB", vertexA, vertexB, 1);
        Edge edgeBC = new Edge("BC", vertexB, vertexC, 2);
        Edge edgeAC = new Edge("AC", vertexA, vertexC, 4);

        Dijkstra dijkstra = new Dijkstra(
                List.of(vertexA, vertexB, vertexC),
                List.of(edgeAB, edgeBC, edgeAC));

        // When
        Collection<Edge> route = dijkstra.shortestTrack(vertexA, vertexC);

        // Then
        Assertions.assertThat(route).containsExactly(edgeAB, edgeBC);
    }

    @Test
    public void shouldReturnEmptyRouteWhenTrackWithSameStartEndCity() {
        // Given
        Vertex vertexA = new Vertex("A");
        Vertex vertexB = new Vertex("B");
        Vertex vertexC = new Vertex("C");

        Edge edgeAB = new Edge("AB", vertexA, vertexB, 1);
        Edge edgeBC = new Edge("BC", vertexB, vertexC, 2);
        Edge edgeAC = new Edge("AC", vertexA, vertexC, 4);

        Dijkstra dijkstra = new Dijkstra(
                List.of(vertexA, vertexB, vertexC),
                List.of(edgeAB, edgeBC, edgeAC));

        // When
        Collection<Edge> route = dijkstra.shortestTrack(vertexA, vertexA);

        // Then
        Assertions.assertThat(route).isEmpty();
    }

    @Test
    public void shouldFindTheShortestTrackWith2SameEdge() {
        // Given
        Vertex vertexA = new Vertex("A");
        Vertex vertexB = new Vertex("B");
        Vertex vertexC = new Vertex("C");

        Edge edgeAB1 = new Edge("AB", vertexA, vertexB, 1);
        Edge edgeAB2 = new Edge("AB", vertexA, vertexB, 3);
        Edge edgeAC = new Edge("AC", vertexA, vertexC, 4);

        Dijkstra dijkstra = new Dijkstra(
                List.of(vertexA, vertexB, vertexC),
                List.of(edgeAB1, edgeAB2, edgeAC));

        // When
        Collection<Edge> route = dijkstra.shortestTrack(vertexA, vertexC);

        // Then
        Assertions.assertThat(route).containsExactly(edgeAC);
    }

    @Test
    public void shouldReturnEmptyRouteWhenTrackWithIsolatedVortex() {
        // Given
        Vertex vertexA = new Vertex("A");
        Vertex vertexB = new Vertex("B");
        Vertex vertexC = new Vertex("C");

        Edge edgeAB = new Edge("AB", vertexA, vertexB, 1);

        Dijkstra dijkstra = new Dijkstra(
                List.of(vertexA, vertexB, vertexC),
                List.of(edgeAB));

        // When
        Collection<Edge> route = dijkstra.shortestTrack(vertexA, vertexC);

        // Then
        Assertions.assertThat(route).isEmpty();
    }

    @Test
    public void shouldReturnEmptyRouteWhenVertexNotPresentInEdges() {
        // Given
        Vertex vertexA = new Vertex("A");
        Vertex vertexB = new Vertex("B");
        Vertex vertexC = new Vertex("C");

        Edge edgeAB = new Edge("AB", vertexA, vertexB, 1);

        Dijkstra dijkstra = new Dijkstra(
                List.of(vertexA, vertexB),
                List.of(edgeAB));

        // When
        Collection<Edge> route = dijkstra.shortestTrack(vertexA, vertexC);

        // Then
        Assertions.assertThat(route).isEmpty();
    }
}