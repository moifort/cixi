package application

import application.print.Clockable
import application.print.Printable
import org.assertj.core.api.Assertions
import org.junit.Test

class SimulatorApplicationTest {

    @Test
    fun shouldDisplayTheCorrectOutput() {
        // Given
        val printer = TestPrinter()
        val simulator = SimulatorApplication(printer, TestClock())

        // When
        simulator.run()

        // Then
        Assertions.assertThat(printer.out.toString()).isEqualTo("""


╔══════════════════════════════════════════════════════════╗
║                           City                           ║
╚══════════════════════════════════════════════════════════╝
                                                 Sillingy ✓ 
                                                   Annecy ✓ 
                                                   Epagny ✓ 
                                               Metz-Tessy ✓ 
                                                   Seynod ✓ 


╔══════════════════════════════════════════════════════════╗
║                           Road                           ║
╚══════════════════════════════════════════════════════════╝
                               Sillingy ══[1min]=> Epagny ✓ 
                             Epagny ══[3min]=> Metz-Tessy ✓ 
                            Metz-Tessy ══[12min]=> Annecy ✓ 
                             Metz-Tessy ══[4min]=> Seynod ✓ 
                              Sillingy ══[11min]=> Seynod ✓ 
                              Sillingy ══[19min]=> Annecy ✓ 
                                 Annecy ══[9min]=> Seynod ✓ 


╔══════════════════════════════════════════════════════════╗
║                           Map                            ║
╚══════════════════════════════════════════════════════════╝
┌──────────────────┬───────────────────────────────────────┐
│      Cities      │                 Roads                 │
├──────────────────┼───────────────────────────────────────┤
│Sillingy          │Sillingy ══[1min]=> Epagny             │
│Annecy            │Epagny ══[3min]=> Metz-Tessy           │
│Epagny            │Metz-Tessy ══[12min]=> Annecy          │
│Metz-Tessy        │Metz-Tessy ══[4min]=> Seynod           │
│Seynod            │Sillingy ══[11min]=> Seynod            │
│                  │Sillingy ══[19min]=> Annecy            │
│                  │Annecy ══[9min]=> Seynod               │
└──────────────────┴───────────────────────────────────────┘


╔══════════════════════════════════════════════════════════╗
║                          Route                           ║
╚══════════════════════════════════════════════════════════╝
╔══════════════════════════════════════════════════════════╗
║                   Sillingy ==> Annecy                    ║
╠═════════════════════════════╦════════════════════════════╣
║       Arrived: 17:17        ║      Duration: 16 min      ║
╠═════════════════════════════╩════════════════════════════╣
║1. Sillingy ══[1min]=> Epagny                             ║
║2. Epagny ══[3min]=> Metz-Tessy                           ║
║3. Metz-Tessy ══[12min]=> Annecy                          ║
╚══════════════════════════════════════════════════════════╝

        """.trimIndent())
    }

    class TestPrinter : Printable {
        val out = StringBuilder()

        override fun println(text: String) {
            out.appendln(text)
        }
    }

    class TestClock : Clockable {
        override fun nowInHHmm(addMinutes: Int) = "17:17"

    }
}