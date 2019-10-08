package domain;

public class Length {
    private int value;
    private Metric metric;

    public Length(int value, Metric metric) {
        this.value = value;
        this.metric = metric;
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
}
