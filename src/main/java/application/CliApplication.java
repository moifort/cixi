package application;

import application.print.Printer;
import application.print.TerminalPrinter;
import domain.*;
import infra.algorithm.DijkstraAlgorithm;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CliApplication {
    private TextIO textIO = TextIoFactory.getTextIO();
    private TextTerminal terminal = textIO.getTextTerminal();
    private Printer printer = new Printer(new TerminalPrinter(terminal));

    public void run() {
        List<City> cities = createCities(textIO, printer);
        List<Road> roads = createRoads(textIO, printer, cities);
        Map map = createMap(printer, cities, roads);
        createRoute(cities, roads, map);
        textIO.dispose("bye bye :)");
    }

    private List<City> createCities(TextIO textIO, Printer printer) {
        printer.title("City");
        List<City> cities = new ArrayList<>();
        while (true) {
            String cityName = textIO.newStringInputReader()
                    .withMaxLength(0)
                    .withValueChecker((val, itemName) -> {
                        List<String> cityNames = cities.stream().map(City::name).collect(Collectors.toList());
                        if (cityNames.contains(val)) return List.of("You already have a city named: " + val);
                        return null;
                    })
                    .read("City name");

            City city = new City(cityName);
            cities.add(city);
            printer.result(city);

            if (cities.size() > 2) {
                Boolean newCityToAdd = textIO.newBooleanInputReader().read("Add new City?");
                if (!newCityToAdd) break;
            }
        }
        return cities;
    }

    private List<Road> createRoads(TextIO textIO, Printer printer, List<City> cities) {
        printer.title("Road");
        List<Road> roads = new ArrayList<>();
        while (true) {
            City from = textIO.<City>newGenericInputReader(null)
                    .withNumberedPossibleValues(cities)
                    .read("From");

            // Build list of selectable cities
            List<City> citiesWithoutStartedCity = cities.stream()
                    .filter(city -> !city.equals(from))
                    .collect(Collectors.toList());
            List<City> citiesAlreadyUsed = roads.stream()
                    .filter(road -> road.from().equals(from))
                    .map(Road::to)
                    .collect(Collectors.toList());
            List<City> selectableCities = citiesWithoutStartedCity.stream()
                    .filter(city -> !citiesAlreadyUsed.contains(city))
                    .collect(Collectors.toList());

            City to = textIO.<City>newGenericInputReader(null)
                    .withNumberedPossibleValues(selectableCities)
                    .read("To");

            int duration = textIO.newIntInputReader()
                    .withPropertiesPrefix("minutes")
                    .withMinVal(1)
                    .read("How many minutes it's take?");

            Road road = new Road(from, to, new Length(duration, Metric.minute));
            roads.add(road);
            printer.result(road);

            if (roads.size() > 1) {
                Boolean newRoadToAdd = textIO.newBooleanInputReader().read("Add a new road?");
                if (!newRoadToAdd) break;
            }
        }
        return roads;
    }

    private Map createMap(Printer printer, List<City> cities, List<Road> roads) {
        printer.title("Map");
        printer.map(cities, roads);
        return new Map(roads, new DijkstraAlgorithm());
    }

    private void createRoute(List<City> cities, List<Road> roads, Map map) {
        printer.title("Route");
        while (true) {
            terminal.println("Where do you want to go?");
            City from = textIO.<City>newGenericInputReader(null)
                    .withNumberedPossibleValues(cities)
                    .read("From");

            // Build list of selectable cities
            List<City> citiesWithoutStartedCity = cities.stream()
                    .filter(city -> !city.equals(from))
                    .collect(Collectors.toList());
            City to = textIO.<City>newGenericInputReader(null)
                    .withNumberedPossibleValues(citiesWithoutStartedCity)
                    .read("To");

            List<Road> route = map.shortestTrack(from, to);
            printer.route(from, to, route);

            Boolean exit = textIO.newBooleanInputReader().read("Finish?");
            if (exit) break;
        }
    }
}