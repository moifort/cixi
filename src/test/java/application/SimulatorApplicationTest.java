package application;

import application.clock.Clock;
import application.print.Printable;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class SimulatorApplicationTest {

    @Test
    public void ShouldValidTheConsoleOutput() {
        // Given
        TestPrinter printer = new TestPrinter();
        SimulatorApplication app = new SimulatorApplication(printer, new TestClock());

        // When
        app.run();

        // Then
        Assertions.assertThat(printer.out.toString()).isEqualTo("\n\n" +
                "╔══════════════════════════════════════════════════════════╗\n" +
                "║                           City                           ║\n" +
                "╚══════════════════════════════════════════════════════════╝\n" +
                "                                                 Sillingy ✓ \n" +
                "                                                   Annecy ✓ \n" +
                "                                                   Epagny ✓ \n" +
                "                                               Metz-Tessy ✓ \n" +
                "                                                   Seynod ✓ \n" +
                "\n" +
                "\n" +
                "╔══════════════════════════════════════════════════════════╗\n" +
                "║                           Road                           ║\n" +
                "╚══════════════════════════════════════════════════════════╝\n" +
                "                               Sillingy ══[1min]=> Epagny ✓ \n" +
                "                             Epagny ══[3min]=> Metz-Tessy ✓ \n" +
                "                            Metz-Tessy ══[12min]=> Annecy ✓ \n" +
                "                             Metz-Tessy ══[4min]=> Seynod ✓ \n" +
                "                              Sillingy ══[11min]=> Seynod ✓ \n" +
                "                              Sillingy ══[19min]=> Annecy ✓ \n" +
                "                                 Annecy ══[9min]=> Seynod ✓ \n" +
                "\n" +
                "\n" +
                "╔══════════════════════════════════════════════════════════╗\n" +
                "║                           Map                            ║\n" +
                "╚══════════════════════════════════════════════════════════╝\n" +
                "┌──────────────────┬───────────────────────────────────────┐\n" +
                "│      Cities      │                 Roads                 │\n" +
                "├──────────────────┼───────────────────────────────────────┤\n" +
                "│Sillingy          │Sillingy ══[1min]=> Epagny             │\n" +
                "│Annecy            │Epagny ══[3min]=> Metz-Tessy           │\n" +
                "│Epagny            │Metz-Tessy ══[12min]=> Annecy          │\n" +
                "│Metz-Tessy        │Metz-Tessy ══[4min]=> Seynod           │\n" +
                "│Seynod            │Sillingy ══[11min]=> Seynod            │\n" +
                "│                  │Sillingy ══[19min]=> Annecy            │\n" +
                "│                  │Annecy ══[9min]=> Seynod               │\n" +
                "└──────────────────┴───────────────────────────────────────┘\n" +
                "\n" +
                "\n" +
                "╔══════════════════════════════════════════════════════════╗\n" +
                "║                          Route                           ║\n" +
                "╚══════════════════════════════════════════════════════════╝\n" +
                "╔══════════════════════════════════════════════════════════╗\n" +
                "║                   Sillingy ==> Annecy                    ║\n" +
                "╠═════════════════════════════╦════════════════════════════╣\n" +
                "║       Arrived: 17:53        ║      Duration: 16 min      ║\n" +
                "╠═════════════════════════════╩════════════════════════════╣\n" +
                "║1. Sillingy ══[1min]=> Epagny                             ║\n" +
                "║2. Epagny ══[3min]=> Metz-Tessy                           ║\n" +
                "║3. Metz-Tessy ══[12min]=> Annecy                          ║\n" +
                "╚══════════════════════════════════════════════════════════╝\n");
    }

    class TestPrinter implements Printable {
        StringBuilder out = new StringBuilder();

        @Override
        public void println(String text) {
            out.append(text + "\n");
        }
    }

    class TestClock implements Clock {

        @Override
        public String nowHHmm(int minutesAdded) {
            return "17:53";
        }
    }

}