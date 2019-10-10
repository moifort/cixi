package infra.algorithm.homemade;

import java.util.Objects;
import java.util.UUID;

public class Edge {
    private String id;
    private Vertex from;
    private Vertex to;
    private int length;

    public Edge(Vertex from, Vertex to, int length) {
        this(UUID.randomUUID().toString(), from, to, length);
    }

    public Edge(String id, Vertex from, Vertex to, int length) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.length = length;
    }

    public String id() {
        return id;
    }

    public Vertex to() {
        return to;
    }

    public Vertex from() {
        return from;
    }

    public int length() {
        return length;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "id='" + id + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", weight=" + length +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return length == edge.length &&
                id.equals(edge.id) &&
                from.equals(edge.from) &&
                to.equals(edge.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, length);
    }
}