package infra.algorithm.homemade;

import domain.City;
import domain.Length;
import domain.Road;
import domain.ShortestTrackAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyDijkstraAdaptor implements ShortestTrackAlgorithm {

    @Override
    public List<Road> shortestTrack(City from, City to, List<Road> roads) {
        List<Vertex> vertexes = toVertexes(extractCities(roads));
        List<Edge> edges = toEdges(roads);
        Dijkstra dijkstra = new Dijkstra(vertexes, edges);
        List<Edge> routeEdges = dijkstra.shortestTrack(toVertex(from), toVertex(to));
        return toRoads(routeEdges);
    }

    @Override
    public String getName() {
        return "Home made Djikstra by Tibo";
    }

    private List<City> extractCities(List<Road> roads) {
        Stream<City> froms = roads.stream().map(Road::from);
        Stream<City> tos = roads.stream().map(Road::to);
        return new ArrayList<>(Stream.concat(froms, tos).collect(Collectors.toSet()));
    }

    private List<Vertex> toVertexes(List<City> city) {
        return city.stream().map(this::toVertex).collect(Collectors.toList());
    }

    private Vertex toVertex(City city) {
        return new Vertex(city.name());
    }

    private List<Edge> toEdges(List<Road> roads) {
        return roads.stream().map(this::toEdge).collect(Collectors.toList());
    }

    private Edge toEdge(Road road) {
        return new Edge(toVertex(road.from()), toVertex(road.to()), road.length().value());
    }

    private List<Road> toRoads(List<Edge> edges) {
        return edges.stream().map(this::toRoad).collect(Collectors.toList());
    }

    private Road toRoad(Edge edge) {
        return new Road(toCity(edge.from()), toCity(edge.to()), new Length(edge.length()));
    }

    private City toCity(Vertex vertex) {
        return new City(vertex.getId());
    }
}
