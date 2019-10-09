package domain

interface ShortestTrackAlgorithm {
    fun shortestTrack(from: City, to: City, roads: List<Road>): List<Road>
}

class ShortestTrackAlgorithmException(message: String) : RuntimeException(message)
