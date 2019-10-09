package domain

import java.util.*

data class Road(val from: City,
                val to: City,
                val length: Length) {
    val id = UUID.randomUUID().toString()
}