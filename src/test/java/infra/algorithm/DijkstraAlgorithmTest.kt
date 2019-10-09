package infra.algorithm

import domain.*
import es.usc.citius.hipster.algorithm.Hipster
import es.usc.citius.hipster.graph.GraphBuilder
import es.usc.citius.hipster.graph.GraphSearchProblem
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.*

class DijkstraAlgorithmTest {
    private val shortestTrackAlgorithm = DijkstraAlgorithm()

    @Test
    fun librarySample() {
        // Given
        val graph = GraphBuilder.create<String, Double>()
                .connect("A").to("B").withEdge(4.0)
                .connect("A").to("C").withEdge(2.0)
                .connect("B").to("C").withEdge(5.0)
                .connect("B").to("D").withEdge(10.0)
                .connect("C").to("E").withEdge(3.0)
                .connect("D").to("F").withEdge(11.0)
                .connect("E").to("D").withEdge(4.0)
                .createDirectedGraph()


        val p = GraphSearchProblem
                .startingFrom("A")
                .`in`(graph)
                .takeCostsFromEdges()
                .build()


        // When
        //System.out.println(Hipster.createDijkstra(p).search("F"));
        //System.out.println(Algorithm.recoverActionPath(Hipster.createDijkstra(p).search("F").getGoalNode()));

        val shortestPath = Hipster.createDijkstra(p).search("F").optimalPaths[0] as List<String>

        // Then
        assertThat(shortestPath).containsExactly("A", "C", "E", "D", "F")
    }

    @Test
    fun shouldDisplayTheShortestPath() {
        // Given
        val sillingy = City("Sillingy")
        val annecy = City("Annecy")
        val epagny = City("Epagny")
        val metzTessy = City("Metz-Tessy")
        val seynod = City("Seynod")

        val sillingyToEpany = Road(sillingy, epagny, Length(1, Metric.minute))
        val epagnyToMetzTessy = Road(epagny, metzTessy, Length(3, Metric.minute))
        val metzTessyToAnnecy = Road(metzTessy, annecy, Length(12, Metric.minute))
        val metzTessyToSeynod = Road(metzTessy, seynod, Length(4, Metric.minute))
        val sillingyToSeynod = Road(sillingy, seynod, Length(11, Metric.minute))
        val sillingyToAnnecy = Road(sillingy, annecy, Length(19, Metric.minute))
        val annecyToSeynod = Road(annecy, seynod, Length(9, Metric.minute))

        val roads = listOf(
                sillingyToEpany,
                epagnyToMetzTessy,
                metzTessyToAnnecy,
                metzTessyToSeynod,
                sillingyToSeynod,
                sillingyToAnnecy,
                annecyToSeynod)

        // When
        val shortestTrack = shortestTrackAlgorithm.shortestTrack(sillingy, annecy, roads)

        // Then
        assertThat(shortestTrack).containsExactly(sillingyToEpany, epagnyToMetzTessy, metzTessyToAnnecy)
    }

    @Test(expected = ShortestTrackAlgorithmException::class)
    fun shouldThrowExceptionIfRoadsContainIdenticalCities() {
        val sillingy = City("Sillingy")
        val annecy = City("Annecy")

        val sillingyToAnnecy1 = Road(sillingy, annecy, Length(1, Metric.minute))
        val sillingyToAnnecy2 = Road(sillingy, annecy, Length(3, Metric.minute))

        val roads = Arrays.asList(sillingyToAnnecy1, sillingyToAnnecy2)

        // When
        val shortestTrack = shortestTrackAlgorithm.shortestTrack(sillingy, annecy, roads)

        // Then
        // Booooum
    }

}