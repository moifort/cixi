package domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return from.equals(road.from) &&
                to.equals(road.to) &&
                length.equals(road.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, length);
    }
}
