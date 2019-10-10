package infra.algorithm.homemade;

public class Edge {
    private String id;
    private Vertex from;
    private Vertex to;
    private int length;

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
}