package domain;

import java.util.Objects;

public class Length {
    private int value;
    private Metric metric;

    public Length(int value) {
        this.value = value;
        this.metric = Metric.minute;
    }

    public int value() {
        return value;
    }

    public Metric metric() {
        return metric;
    }

    @Override
    public String toString() {
        return value + " " + metric;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Length length = (Length) o;
        return value == length.value &&
                metric == length.metric;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, metric);
    }
}
