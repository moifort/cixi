package domain;

import java.util.Objects;
import java.util.UUID;

public class Road {
    private String id;
    private City from;
    private City to;
    private Length length;

    public Road(City from, City to, Length length) {
        this.id = UUID.randomUUID().toString();
        this.from = from;
        this.to = to;
        this.length = length;
    }

    public String id() {
        return id;
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
        return Objects.equals(id, road.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
