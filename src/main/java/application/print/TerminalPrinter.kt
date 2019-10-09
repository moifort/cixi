package application.print;

import org.beryx.textio.TextTerminal;

public class TerminalPrinter implements Printable {
    private TextTerminal terminal;

    public TerminalPrinter(TextTerminal terminal) {
        this.terminal = terminal;
    }

    @Override
    public void println(String text) {
        terminal.println(text);
    }
}
