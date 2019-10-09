package application.print

import java.text.SimpleDateFormat
import java.util.*

class SystemClock : Clockable {
    private val formatter = SimpleDateFormat("HH:mm")

    override fun nowInHHmm(addMinutes: Int): String {
        val now = Calendar.getInstance()
        now.add(Calendar.MINUTE, addMinutes)
        return formatter.format(now.time)
    }
}