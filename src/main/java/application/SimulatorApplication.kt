package application

import application.print.*
import domain.*
import domain.Map
import infra.algorithm.DijkstraAlgorithm

class SimulatorApplication(print: Printable = SystemOutPrinter(),
                           clockable: Clockable = SystemClock()) {
    private val printer = Printer(print, clockable)

    fun run() {
        printer.title("City")
        val sillingy = City("Sillingy")
        val annecy = City("Annecy")
        val epagny = City("Epagny")
        val metzTessy = City("Metz-Tessy")
        val seynod = City("Seynod")
        val cities = listOf(sillingy, annecy, epagny, metzTessy, seynod)
        cities.forEach { printer.result(it) }

        printer.title("Road")
        val sillingyToEpany = Road(sillingy, epagny, Length(1, Metric.minute))
        val epagnyToMetzTessy = Road(epagny, metzTessy, Length(3, Metric.minute))
        val metzTessyToAnnecy = Road(metzTessy, annecy, Length(12, Metric.minute))
        val metzTessyToSeynod = Road(metzTessy, seynod, Length(4, Metric.minute))
        val sillingyToSeynod = Road(sillingy, seynod, Length(11, Metric.minute))
        val sillingyToAnnecy = Road(sillingy, annecy, Length(19, Metric.minute))
        val annecyToSeynod = Road(annecy, seynod, Length(9, Metric.minute))
        val roads = listOf(sillingyToEpany,
                epagnyToMetzTessy,
                metzTessyToAnnecy,
                metzTessyToSeynod,
                sillingyToSeynod,
                sillingyToAnnecy,
                annecyToSeynod)
        roads.forEach { printer.result(it) }

        printer.title("Map")
        val map = Map(roads, DijkstraAlgorithm())
        printer.map(cities, roads)

        printer.title("Route")
        val route = map.shortestTrack(sillingy, annecy)
        printer.route(sillingy, annecy, route)
    }
}
