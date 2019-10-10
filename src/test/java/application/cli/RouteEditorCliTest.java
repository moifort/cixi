package application.cli;

import application.print.Printer;
import application.print.TerminalPrinter;
import domain.City;
import domain.Length;
import domain.Map;
import domain.Road;
import org.beryx.textio.TextIO;
import org.junit.Test;
import util.MockClock;
import util.MockShortestPathAlgorithm;
import util.MockTextTerminal;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RouteEditorCliTest {

    @Test
    public void shouldDisplayRouteOutput() {
        // Given
        MockTextTerminal terminal = new MockTextTerminal();
        TextIO textIO = new TextIO(terminal);
        Printer printer = new Printer(new TerminalPrinter(terminal), new MockClock("17:33"));
        RouteEditorCli routeEditorCli = new RouteEditorCli(textIO, printer);

        City paris = new City("Paris");
        City nice = new City("Nice");
        City lyon = new City("Lyon");
        List<City> cities = List.of(paris, nice, lyon);

        Road parisNice = new Road(paris, nice, new Length(2));
        Road niceLyon = new Road(nice, lyon, new Length(3));
        List<Road> roads = List.of(parisNice, niceLyon);

        Map map = new Map(cities, roads, new MockShortestPathAlgorithm("Algo Test", roads));
        // When
        terminal.getInputs().addAll(List.of("1", "1", "Y"));
        routeEditorCli.createRoute(map);

        // Then
        assertThat(terminal.getOutput()).isEqualTo("" +
                "╔══════════════════════════════════════════════════════════╗\n" +
                "║                          Route                           ║\n" +
                "╚══════════════════════════════════════════════════════════╝\n" +
                "Where do you want to go?\n" +
                "From:\n" +
                "1: Paris\n" +
                "2: Nice\n" +
                "3: Lyon\n" +
                "Enter your choice: 1\n" +
                "To:\n" +
                "1: Nice\n" +
                "2: Lyon\n" +
                "Enter your choice: 1\n" +
                "╔══════════════════════════════════════════════════════════╗\n" +
                "║                      Paris ==> Nice                      ║\n" +
                "╠═════════════════════════════╦════════════════════════════╣\n" +
                "║       Arrived: 17:33        ║      Duration: 5 min       ║\n" +
                "╠═════════════════════════════╩════════════════════════════╣\n" +
                "║1. Paris ══[2min]=> Nice                                  ║\n" +
                "║2. Nice ══[3min]=> Lyon                                   ║\n" +
                "╚══════════════════════════════════════════════════════════╝\n" +
                "Finish? (Y/N): Y");
    }
}