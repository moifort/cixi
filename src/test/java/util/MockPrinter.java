package util;

import application.print.Printable;

public class MockPrinter implements Printable {
    private StringBuilder out = new StringBuilder();

    @Override
    public void println(String text) {
        out.append(text + "\n");
    }

    public StringBuilder getOut() {
        return out;
    }
}