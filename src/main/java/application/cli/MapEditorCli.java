package application.cli;

import application.print.Printer;
import domain.City;
import domain.Map;
import domain.Road;
import domain.ShortestTrackAlgorithm;
import org.beryx.textio.TextIO;

import java.util.List;

public class MapEditorCli {
    private TextIO textIO;
    private Printer printer;

    public MapEditorCli(TextIO textIO, Printer printer) {
        this.textIO = textIO;
        this.printer = printer;
    }

    public Map createMap(List<City> cities, List<Road> roads, List<ShortestTrackAlgorithm> algorithms) {
        printer.title("Map");

        ShortestTrackAlgorithm selectedAlgorithm;
        if (algorithms.size() > 1) {
            selectedAlgorithm = textIO.<ShortestTrackAlgorithm>newGenericInputReader(null)
                    .withNumberedPossibleValues(algorithms)
                    .withValueFormatter(ShortestTrackAlgorithm::getName)
                    .read("Select the shortest path algorithm");
        } else {
            selectedAlgorithm = algorithms.get(0);
        }

        Map map = new Map(cities, roads, selectedAlgorithm);
        printer.map(map);
        return map;
    }
}
