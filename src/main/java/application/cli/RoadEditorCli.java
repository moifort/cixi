package application.cli;

import application.print.Printer;
import domain.City;
import domain.Length;
import domain.Road;
import org.beryx.textio.TextIO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoadEditorCli {
    private TextIO textIO;
    private Printer printer;
    private int minimumRoads;

    public RoadEditorCli(TextIO textIO, Printer printer) {
        this.textIO = textIO;
        this.printer = printer;
        this.minimumRoads = 1;
    }

    public List<Road> roads(List<City> cities) {
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

            Road road = new Road(from, to, new Length(duration));
            roads.add(road);
            printer.result(road);

            if (roads.size() > minimumRoads) {
                Boolean newRoadToAdd = textIO.newBooleanInputReader().read("Add a new road?");
                if (!newRoadToAdd) break;
            }
        }
        return roads;
    }
}
