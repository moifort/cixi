package domain

class Map(private val roads: List<Road>,
          private val shortestTrackAlgorithm: ShortestTrackAlgorithm) {

    fun shortestTrack(from: City, to: City): List<Road> {
        return shortestTrackAlgorithm.shortestTrack(from, to, roads)
    }
}
