package application.print

import org.beryx.textio.TextTerminal

class TerminalPrinter(private val terminal: TextTerminal<*>) : Printable {

    override fun println(text: String) {
        terminal.println(text)
    }
}
