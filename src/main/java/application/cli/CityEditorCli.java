package application.cli;

import application.print.Printer;
import domain.City;
import org.beryx.textio.InputReader;
import org.beryx.textio.TextIO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CityEditorCli {
    private TextIO textIO;
    private Printer printer;
    private int minimumCities;

    public CityEditorCli(TextIO textIO, Printer printer) {
        this.textIO = textIO;
        this.printer = printer;
        this.minimumCities = 2;
    }

    public List<City> cities() {
        printer.title("City");
        List<City> cities = new ArrayList<>();
        while (true) {
            String cityName = textIO.newStringInputReader()
                    .withMaxLength(0)
                    .withValueChecker(isCityAlreadyExist(cities))
                    .read("City name");

            City city = new City(cityName);
            cities.add(city);
            printer.result(city);

            if (cities.size() > minimumCities) {
                Boolean newCityToAdd = textIO.newBooleanInputReader().read("Add new City?");
                if (!newCityToAdd) break;
            }
        }
        return cities;
    }

    private InputReader.ValueChecker<String> isCityAlreadyExist(List<City> cities) {
        return (val, itemName) -> {
            List<String> cityNames = cities.stream().map(City::name).collect(Collectors.toList());
            if (cityNames.contains(val)) return List.of("You already have a city named: " + val);
            return null;
        };
    }
}
