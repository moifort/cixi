package application.cli;

import application.clock.SystemClock;
import application.print.Printer;
import application.print.TerminalPrinter;
import domain.City;
import domain.Length;
import domain.Road;
import org.assertj.core.api.Assertions;
import org.beryx.textio.TextIO;
import org.junit.Test;
import util.MockShortestPathAlgorithm;
import util.MockTextTerminal;

import java.util.List;

public class MapEditorCliTest {

    @Test
    public void shouldDisplayMapOutput() {
        // Given
        MockTextTerminal terminal = new MockTextTerminal();
        TextIO textIO = new TextIO(terminal);
        Printer printer = new Printer(new TerminalPrinter(terminal), new SystemClock());
        MapEditorCli mapEditorCli = new MapEditorCli(textIO, printer);
        City city = new City("A");
        Road road = new Road(city, city, new Length(1));

        // When
        terminal.getInputs().addAll(List.of("1"));
        mapEditorCli.createMap(
                List.of(city),
                List.of(road),
                List.of(new MockShortestPathAlgorithm("Algo 1"),
                        new MockShortestPathAlgorithm("Algo 2"),
                        new MockShortestPathAlgorithm("Algo 3")));

        // Then
        Assertions.assertThat(terminal.getOutput()).isEqualTo("" +
                "╔══════════════════════════════════════════════════════════╗\n" +
                "║                           Map                            ║\n" +
                "╚══════════════════════════════════════════════════════════╝\n" +
                "Select the shortest path algorithm:\n" +
                "1: Algo 1\n" +
                "2: Algo 2\n" +
                "3: Algo 3\n" +
                "Enter your choice: 1\n" +
                "┌──────────────────────────────────────────────────────────┐\n" +
                "│                    Algorithm: Algo 1                     │\n" +
                "├──────────────────┬───────────────────────────────────────┤\n" +
                "│      Cities      │                 Roads                 │\n" +
                "├──────────────────┼───────────────────────────────────────┤\n" +
                "│A                 │A ══[1min]=> A                         │\n" +
                "└──────────────────┴───────────────────────────────────────┘");
    }

    @Test
    public void shouldDisplayMapOutputWithoutAlgoQuestionWhenOnlyOneAlgo() {
        // Given
        MockTextTerminal terminal = new MockTextTerminal();
        TextIO textIO = new TextIO(terminal);
        Printer printer = new Printer(new TerminalPrinter(terminal), new SystemClock());
        MapEditorCli mapEditorCli = new MapEditorCli(textIO, printer);
        City city = new City("A");
        Road road = new Road(city, city, new Length(1));

        // When
        terminal.getInputs().addAll(List.of("1"));
        mapEditorCli.createMap(
                List.of(city),
                List.of(road),
                List.of(new MockShortestPathAlgorithm("Algo 1")));

        // Then
        Assertions.assertThat(terminal.getOutput()).isEqualTo("" +
                "╔══════════════════════════════════════════════════════════╗\n" +
                "║                           Map                            ║\n" +
                "╚══════════════════════════════════════════════════════════╝\n" +
                "┌──────────────────────────────────────────────────────────┐\n" +
                "│                    Algorithm: Algo 1                     │\n" +
                "├──────────────────┬───────────────────────────────────────┤\n" +
                "│      Cities      │                 Roads                 │\n" +
                "├──────────────────┼───────────────────────────────────────┤\n" +
                "│A                 │A ══[1min]=> A                         │\n" +
                "└──────────────────┴───────────────────────────────────────┘");
    }
}