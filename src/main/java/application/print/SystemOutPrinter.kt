package application.print

class SystemOutPrinter : Printable {
    override fun println(text: String) {
        kotlin.io.println(text)
    }
}
