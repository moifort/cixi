package application.print;

public class SystemOutPrinter implements Printable {
    @Override
    public void println(String text) {
        System.out.println(text);
    }
}
