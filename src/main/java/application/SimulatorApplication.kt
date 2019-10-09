package application;

import application.print.Printer;
import application.print.SystemOutPrinter;
import domain.*;
import infra.algorithm.DijkstraAlgorithm;

import java.util.List;

public class SimulatorApplication {
    private Printer printer = new Printer(new SystemOutPrinter());

    public void run() {
        printer.title("City");
        City sillingy = new City("Sillingy");
        City annecy = new City("Annecy");
        City epagny = new City("Epagny");
        City metzTessy = new City("Metz-Tessy");
        City seynod = new City("Seynod");
        List<City> cities = List.of(sillingy, annecy, epagny, metzTessy, seynod);
        cities.stream().forEach(printer::result);

        printer.title("Road");
        Road sillingyToEpany = new Road(sillingy, epagny, new Length(1, Metric.minute));
        Road epagnyToMetzTessy = new Road(epagny, metzTessy, new Length(3, Metric.minute));
        Road metzTessyToAnnecy = new Road(metzTessy, annecy, new Length(12, Metric.minute));
        Road metzTessyToSeynod = new Road(metzTessy, seynod, new Length(4, Metric.minute));
        Road sillingyToSeynod = new Road(sillingy, seynod, new Length(11, Metric.minute));
        Road sillingyToAnnecy = new Road(sillingy, annecy, new Length(19, Metric.minute));
        Road annecyToSeynod = new Road(annecy, seynod, new Length(9, Metric.minute));
        List<Road> roads = List.of(sillingyToEpany,
                epagnyToMetzTessy,
                metzTessyToAnnecy,
                metzTessyToSeynod,
                sillingyToSeynod,
                sillingyToAnnecy,
                annecyToSeynod);
        roads.stream().forEach(printer::result);

        printer.title("Map");
        Map map = new Map(roads, new DijkstraAlgorithm());
        printer.map(cities, roads);

        printer.title("Route");
        City from = sillingy;
        City to = annecy;
        List<Road> route = map.shortestTrack(from, to);
        printer.route(from, to, route);
    }
}
