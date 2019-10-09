package infra.algorithm

import domain.City
import domain.Road
import domain.ShortestTrackAlgorithm
import domain.ShortestTrackAlgorithmException
import es.usc.citius.hipster.algorithm.Algorithm
import es.usc.citius.hipster.algorithm.Hipster
import es.usc.citius.hipster.graph.GraphBuilder
import es.usc.citius.hipster.graph.GraphSearchProblem

/**
 * Use of Dijkstra algorithm: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 * This algorithm canNOT manage two roads starting and ending with the same cities and will throw a RuntimeException
 */

class DijkstraAlgorithm : ShortestTrackAlgorithm {

    override fun shortestTrack(from: City, to: City, roads: List<Road>): List<Road> {
        if (isRoadWithIdenticalCities(roads))
            throw ShortestTrackAlgorithmException("Cannot have road with identical started and ended cities")

        val graphBuilder = GraphBuilder.create<City, Road>()
        roads.forEach { road -> graphBuilder.connect(road.from).to(road.to).withEdge(road) }
        val graph = graphBuilder.createDirectedGraph()
        val path = GraphSearchProblem
                .startingFrom(from)
                .`in`(graph)
                .extractCostFromEdges { it.length.value.toDouble() }
                .build()

        val shortestTrack = Hipster.createDijkstra(path).search(to)
        return Algorithm.recoverActionPath(shortestTrack.goalNode)
    }

    private fun isRoadWithIdenticalCities(roads: List<Road>): Boolean {
        for (road in roads) {
            val hasSameCities = roads
                    .filter { it != road }
                    .any { road.from === it.from && road.to == it.to }
            if (hasSameCities)
                return true
        }
        return false
    }
}
