package application.print

import de.vandermeer.asciitable.AsciiTable
import de.vandermeer.asciitable.CWC_FixedWidth
import de.vandermeer.asciithemes.TA_GridThemes
import de.vandermeer.asciithemes.u8.U8_Grids
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment
import domain.City
import domain.Road

class Printer(private val printer: Printable,
              private val clock: Clockable) {
    private val width = 60

    fun title(title: String) {
        val at = AsciiTable()
        at.addRule()
        at.addRow(title).setTextAlignment(TextAlignment.CENTER)
        at.addRule()
        at.context.grid = U8_Grids.borderDouble()
        at.context.width = width
        printer.println("\n\n" + at.render())
    }

    fun map(cities: List<City>, roads: List<Road>) {
        val at = AsciiTable()
        at.addRule()
        at.addRow("Cities", "Roads").setTextAlignment(TextAlignment.CENTER)
        at.addRule()
        val citiesList = cities.joinToString("<br>") { it.name }
        val roadList = roads.joinToString("<br>") { this.toString(it) }
        at.addRow(citiesList, roadList).setTextAlignment(TextAlignment.LEFT)
        at.addRule()
        at.renderer.cwc = CWC_FixedWidth().add(18).add(39)
        printer.println(at.render(width))
    }

    fun route(from: City, to: City, route: List<Road>) {
        val durationInMinute = route.map { it.length.value }.sum()

        val at = AsciiTable()
        if (route.isEmpty()) {
            at.addRule()
            at.addRow("${from.name} ==>  ${to.name}").setTextAlignment(TextAlignment.CENTER)
            at.addRule()
            at.addRow("No route found :(").setTextAlignment(TextAlignment.CENTER)
            at.addRule()
        } else {
            at.addRule()
            at.addRow(null, "${from.name} ==>  ${to.name}").setTextAlignment(TextAlignment.CENTER)
            at.addRule()
            at.addRow("Arrived: ${clock.nowInHHmm(durationInMinute)}", "Duration: $durationInMinute min").setTextAlignment(TextAlignment.CENTER)
            at.addRule()
            var index = 1
            for (road in route) {
                at.addRow(null, "${index++}. ${toString(road)}").setTextAlignment(TextAlignment.LEFT)
            }
            at.addRule()
        }


        at.context.grid = U8_Grids.borderDouble()
        at.context.width = width
        printer.println(at.render())
    }

    fun result(road: Road) {
        result(toString(road))
    }

    fun result(city: City) {
        result(city.name)
    }

    fun result(message: String) {
        val at = AsciiTable()
        at.addRow("$message ✓").setTextAlignment(TextAlignment.RIGHT)
        at.context.setGridTheme(TA_GridThemes.NONE)
        at.context.width = width
        printer.println(at.render())
    }

    private fun toString(road: Road): String {
        return "${road.from.name} ══[${road.length.value}min]=> ${road.to.name}"
    }
}