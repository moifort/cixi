package application.print;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_FixedWidth;
import de.vandermeer.asciithemes.TA_GridThemes;
import de.vandermeer.asciithemes.u8.U8_Grids;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import domain.City;
import domain.Length;
import domain.Road;

import java.util.List;
import java.util.stream.Collectors;

public class Printer {
    private Clockable clock;
    private Printable printer;
    private int width = 60;

    public Printer(Printable printer, Clockable clock) {
        this.clock = clock;
        this.printer = printer;
    }

    public void title(String title) {
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow(title).setTextAlignment(TextAlignment.CENTER);
        at.addRule();
        at.getContext().setGrid(U8_Grids.borderDouble());
        at.getContext().setWidth(width);
        printer.println("\n\n" + at.render());
    }

    public void map(List<City> cities, List<Road> roads) {
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Cities", "Roads").setTextAlignment(TextAlignment.CENTER);
        at.addRule();
        String citiesList = cities.stream().map(City::name).collect(Collectors.joining("<br>"));
        String roadList = roads.stream().map(this::toString).collect(Collectors.joining("<br>"));
        at.addRow(citiesList, roadList).setTextAlignment(TextAlignment.LEFT);
        at.addRule();
        at.getRenderer().setCWC(new CWC_FixedWidth().add(18).add(39));
        printer.println(at.render(width));
    }

    public void route(City from, City to, List<Road> route) {
        int durationInMinute = route.stream()
                .map(Road::length)
                .mapToInt(Length::value)
                .sum();


        AsciiTable at = new AsciiTable();
        if (route.isEmpty()) {
            at.addRule();
            at.addRow(from.name() + " ==> " + to.name()).setTextAlignment(TextAlignment.CENTER);
            at.addRule();
            at.addRow("No route found :(").setTextAlignment(TextAlignment.CENTER);
            at.addRule();
        } else {
            at.addRule();
            at.addRow(null, from.name() + " ==> " + to.name()).setTextAlignment(TextAlignment.CENTER);
            at.addRule();
            at.addRow("Arrived: " + clock.nowHHmm(durationInMinute), "Duration: " + durationInMinute + " min").setTextAlignment(TextAlignment.CENTER);
            at.addRule();
            int index = 1;
            for (Road road : route) {
                at.addRow(null, index++ + ". " + toString(road)).setTextAlignment(TextAlignment.LEFT);
            }
            at.addRule();
        }


        at.getContext().setGrid(U8_Grids.borderDouble());
        at.getContext().setWidth(width);
        printer.println(at.render());
    }

    public void result(Road road) {
        result(toString(road));
    }

    public void result(City city) {
        result(city.name());
    }

    public void result(String message) {
        AsciiTable at = new AsciiTable();
        at.addRow(message + " ✓").setTextAlignment(TextAlignment.RIGHT);
        at.getContext().setGridTheme(TA_GridThemes.NONE);
        at.getContext().setWidth(width);
        printer.println(at.render());
    }

    private String toString(Road road) {
        return road.from().name() + " ══[" + road.length().value() + "min]=> " + road.to().name();
    }
}