package application.cli;

import application.clock.SystemClock;
import application.print.Printer;
import application.print.TerminalPrinter;
import domain.City;
import domain.Length;
import domain.Road;
import org.beryx.textio.TextIO;
import org.junit.Test;
import util.MockTextTerminal;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RoadEditorCliTest {

    @Test
    public void shouldDisplayRoadOutput() {
        // Given
        MockTextTerminal terminal = new MockTextTerminal();
        TextIO textIO = new TextIO(terminal);
        Printer printer = new Printer(new TerminalPrinter(terminal), new SystemClock());
        RoadEditorCli roadEditorCli = new RoadEditorCli(textIO, printer);

        City paris = new City("Paris");
        City nice = new City("Nice");
        City lyon = new City("Lyon");
        List<City> cities = List.of(paris, nice, lyon);
        // When
        terminal.getInputs().addAll(List.of("1", "1", "2", "1", "1", "3", "N"));
        List<Road> roads = roadEditorCli.roads(cities);

        // Then
        assertThat(terminal.getOutput()).isEqualTo("" +
                "╔══════════════════════════════════════════════════════════╗\n" +
                "║                           Road                           ║\n" +
                "╚══════════════════════════════════════════════════════════╝\n" +
                "From:\n" +
                "1: Paris\n" +
                "2: Nice\n" +
                "3: Lyon\n" +
                "Enter your choice: 1\n" +
                "To:\n" +
                "1: Nice\n" +
                "2: Lyon\n" +
                "Enter your choice: 1\n" +
                "How many minutes it's take? 2\n" +
                "Paris ══[2min]=> Nice ✓\n" +
                "From:\n" +
                "1: Paris\n" +
                "2: Nice\n" +
                "3: Lyon\n" +
                "Enter your choice: 1\n" +
                "To:\n" +
                "1: Lyon\n" +
                "Enter your choice: 1\n" +
                "How many minutes it's take? 3\n" +
                "Paris ══[3min]=> Lyon ✓\n" +
                "Add a new road? (Y/N): N");
        assertThat(roads).containsExactly(
                new Road(paris, nice, new Length(2)),
                new Road(paris, lyon, new Length(3)));
    }
}