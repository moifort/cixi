package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MapTest {
    @Mock
    internal var shortestTrackAlgorithm: ShortestTrackAlgorithm? = null

    @Test
    fun shortestTrack() {
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

        `when`(shortestTrackAlgorithm!!.shortestTrack(sillingy, annecy, roads))
                .thenReturn(listOf(sillingyToEpany, epagnyToMetzTessy, metzTessyToAnnecy))

        val map = Map(roads, shortestTrackAlgorithm!!)

        // When
        val shortestTrack = map.shortestTrack(sillingy, annecy)

        // Then
        assertThat(shortestTrack).containsExactly(sillingyToEpany, epagnyToMetzTessy, metzTessyToAnnecy)
    }
}