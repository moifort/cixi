package domain;

public class Road {
    private City from;
    private City to;
    private Length length;

    public Road(City from, City to, Length length) {
        this.from = from;
        this.to = to;
        this.length = length;
    }

    public City from() {
        return from;
    }

    public City to() {
        return to;
    }

    public Length length() {
        return length;
    }

    @Override
    public String toString() {
        return "Road{" +
                "from=" + from +
                ", to=" + to +
                ", length=" + length +
                '}';
    }
}
