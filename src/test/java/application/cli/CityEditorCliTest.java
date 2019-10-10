package application.cli;

import application.clock.SystemClock;
import application.print.Printer;
import application.print.TerminalPrinter;
import domain.City;
import org.beryx.textio.TextIO;
import org.junit.Test;
import util.MockTextTerminal;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CityEditorCliTest {

    @Test
    public void shouldDisplayCityOutput() {
        // Given
        MockTextTerminal terminal = new MockTextTerminal();
        TextIO textIO = new TextIO(terminal);
        Printer printer = new Printer(new TerminalPrinter(terminal), new SystemClock());
        CityEditorCli cityEditorCli = new CityEditorCli(textIO, printer);

        // When
        terminal.getInputs().addAll(List.of("Paris", "Nice", "Lyon", "N"));
        List<City> cities = cityEditorCli.cities();

        // Then
        assertThat(terminal.getOutput()).isEqualTo("" +
                "╔══════════════════════════════════════════════════════════╗\n" +
                "║                           City                           ║\n" +
                "╚══════════════════════════════════════════════════════════╝\n" +
                "City name: Paris\n" +
                "Paris ✓\n" +
                "City name: Nice\n" +
                "Nice ✓\n" +
                "City name: Lyon\n" +
                "Lyon ✓\n" +
                "Add new City? (Y/N): N");
        assertThat(cities).containsExactly(new City("Paris"), new City("Nice"), new City("Lyon"));
    }

    @Test
    public void shouldForbidToCreateSameCity() {
        // Given
        MockTextTerminal terminal = new MockTextTerminal();
        TextIO textIO = new TextIO(terminal);
        Printer printer = new Printer(new TerminalPrinter(terminal), new SystemClock());
        CityEditorCli cityEditorCli = new CityEditorCli(textIO, printer);

        // When
        terminal.getInputs().addAll(List.of("Paris", "Paris", "Lyon", "Nice", "N"));
        List<City> cities = cityEditorCli.cities();

        // Then
        assertThat(terminal.getOutput()).isEqualTo("" +
                "╔══════════════════════════════════════════════════════════╗\n" +
                "║                           City                           ║\n" +
                "╚══════════════════════════════════════════════════════════╝\n" +
                "City name: Paris\n" +
                "Paris ✓\n" +
                "City name: Paris\n" +
                "Invalid value.\n" +
                "You already have a city named: Paris\n" +
                "City name: Lyon\n" +
                "Lyon ✓\n" +
                "City name: Nice\n" +
                "Nice ✓\n" +
                "Add new City? (Y/N): N");
        assertThat(cities).containsExactly(new City("Paris"), new City("Lyon"), new City("Nice"));
    }
}