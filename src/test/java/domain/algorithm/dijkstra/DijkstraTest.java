package domain.algorithm.dijkstra;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class DijkstraTest {

    /**
     * +-------+        +--------+
     * 2     |       |   1    |        |
     * +----------+   A   +--------+    B   |
     * |          |       |        |        |
     * +---+---+      +---+---+        +----+---+
     * |       |          |                 |
     * |   D   |          | 10              |
     * |       |          |                 |
     * +---+---+      +---+---+             | 5
     * |          |       |             |
     * +----------+   C   +-------------+
     * 2    |       |
     * +-------+
     */

    @Test
    public void shortestPathTo() {
        // Given
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");

        a.addNeighbour(new Edge(1, a, b));
        a.addNeighbour(new Edge(10, a, c));
        a.addNeighbour(new Edge(2, a, d));
        b.addNeighbour(new Edge(5, b, c));
        d.addNeighbour(new Edge(2, d, c));

        Dijkstra dijkstra = new Dijkstra();

        // When
        List<Vertex> shortestPath = dijkstra.shortestPathTo(a, c);

        // Then
        assertThat(shortestPath).containsExactly(a, d, c);
    }
}