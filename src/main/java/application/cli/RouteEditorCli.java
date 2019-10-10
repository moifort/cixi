package application.cli;

import application.print.Printer;
import domain.City;
import domain.Map;
import domain.Road;
import org.beryx.textio.TextIO;

import java.util.List;
import java.util.stream.Collectors;

public class RouteEditorCli {
    private TextIO textIO;
    private Printer printer;

    public RouteEditorCli(TextIO textIO, Printer printer) {
        this.textIO = textIO;
        this.printer = printer;
    }

    public void createRoute(Map map) {
        List<City> cities = map.cities();

        printer.title("Route");
        while (true) {
            printer.println("Where do you want to go?");
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
