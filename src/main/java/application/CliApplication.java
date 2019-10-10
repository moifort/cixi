package application;

import application.cli.CityEditorCli;
import application.cli.MapEditorCli;
import application.cli.RoadEditorCli;
import application.cli.RouteEditorCli;
import application.clock.SystemClock;
import application.print.Printer;
import application.print.TerminalPrinter;
import domain.City;
import domain.Map;
import domain.Road;
import domain.ShortestTrackAlgorithm;
import infra.algorithm.HipsterDijkstraAdaptor;
import infra.algorithm.homemade.MyDijkstraAdaptor;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import java.util.List;

public class CliApplication {
    private TextIO textIO = TextIoFactory.getTextIO();
    private TextTerminal terminal = textIO.getTextTerminal();
    private Printer printer = new Printer(new TerminalPrinter(terminal), new SystemClock());
    private CityEditorCli cityEditorCli = new CityEditorCli(textIO, printer);
    private RoadEditorCli roadEditorCli = new RoadEditorCli(textIO, printer);
    private MapEditorCli mapEditorCli = new MapEditorCli(textIO, printer);
    private RouteEditorCli routeEditorCli = new RouteEditorCli(textIO, printer);
    private List<ShortestTrackAlgorithm> algorithms = List.of(new HipsterDijkstraAdaptor(), new MyDijkstraAdaptor());

    public void run() {
        List<City> cities = cityEditorCli.cities();
        List<Road> roads = roadEditorCli.roads(cities);
        Map map = mapEditorCli.createMap(cities, roads, algorithms);
        routeEditorCli.createRoute(map);
        textIO.dispose("bye bye :)");
    }
}