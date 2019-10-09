package application.print

interface Clockable {
    fun nowInHHmm(addMinutes: Int): String
}