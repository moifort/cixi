package application

import application.print.Printer
import application.print.SystemClock
import application.print.TerminalPrinter
import domain.*
import domain.Map
import infra.algorithm.DijkstraAlgorithm
import org.beryx.textio.TextIO
import org.beryx.textio.TextIoFactory
import java.util.*

class CliApplication {
    private val textIO = TextIoFactory.getTextIO()
    private val terminal = textIO.textTerminal
    private val printer = Printer(TerminalPrinter(terminal), SystemClock())

    fun run() {
        val cities = createCities(textIO, printer)
        val roads = createRoads(textIO, printer, cities)
        val map = createMap(printer, cities, roads)
        createRoute(cities, roads, map)
        textIO.dispose("bye bye :)")
    }

    private fun createCities(textIO: TextIO, printer: Printer): List<City> {
        printer.title("City")
        val cities = ArrayList<City>()
        while (true) {
            val cityName = textIO.newStringInputReader()
                    .withMaxLength(0)
                    .withValueChecker { value, _ ->
                        val alreadyExist = cities.any { it.name == value }
                        if (alreadyExist) listOf("You already have a city named: $value") else emptyList()
                    }
                    .read("City name")

            val city = City(cityName)
            cities.add(city)
            printer.result(city)

            if (cities.size > 2) {
                val newCityToAdd = textIO.newBooleanInputReader().read("Add new City?")
                if (!newCityToAdd) break
            }
        }
        return cities
    }

    private fun createRoads(textIO: TextIO, printer: Printer, cities: List<City>): List<Road> {
        printer.title("Road")
        val roads = ArrayList<Road>()
        while (true) {
            val from = textIO.newGenericInputReader<City>(null)
                    .withNumberedPossibleValues(cities)
                    .read("From")

            // Build list of selectable cities
            val citiesWithoutStartedCity = cities.filter { it != from }.toList()
            val citiesAlreadyUsed = roads.filter { it.from == from }.map { it.to }.toList()
            val selectableCities = citiesWithoutStartedCity.filter { !citiesAlreadyUsed.contains(it) }.toList()
            val to = textIO.newGenericInputReader<City>(null)
                    .withNumberedPossibleValues(selectableCities)
                    .read("To")

            val duration = textIO.newIntInputReader()
                    .withPropertiesPrefix("minutes")
                    .withMinVal(1)
                    .read("How many minutes it's take?")

            val road = Road(from, to, Length(duration, Metric.minute))
            roads.add(road)
            printer.result(road)

            if (roads.size > 1) {
                val newRoadToAdd = textIO.newBooleanInputReader().read("Add a new road?")
                if (!newRoadToAdd) break
            }
        }
        return roads
    }

    private fun createMap(printer: Printer, cities: List<City>, roads: List<Road>): Map {
        printer.title("Map")
        printer.map(cities, roads)
        return Map(roads, DijkstraAlgorithm())
    }

    private fun createRoute(cities: List<City>, roads: List<Road>, map: domain.Map) {
        printer.title("Route")
        while (true) {
            terminal.println("Where do you want to go?")
            val from = textIO.newGenericInputReader<City>(null)
                    .withNumberedPossibleValues(cities)
                    .read("From")

            // Build list of selectable cities
            val citiesWithoutStartedCity = cities.filter { it != from }.toList()
            val to = textIO.newGenericInputReader<City>(null)
                    .withNumberedPossibleValues(citiesWithoutStartedCity)
                    .read("To")

            val route = map.shortestTrack(from, to)
            printer.route(from, to, route)

            val exit = textIO.newBooleanInputReader().read("Finish?")
            if (exit) break
        }
    }
}