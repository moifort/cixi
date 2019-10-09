package domain

data class Length(val value: Int, val metric: Metric)

enum class Metric {
    minute,
    meter
}
