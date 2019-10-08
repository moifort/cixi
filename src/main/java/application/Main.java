package application;

import domain.*;
import infra.algorithm.DijkstraAlgorithm;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("\n" +
                "   _____ _______   _______ \n" +
                "  / ____|_   _\\ \\ / |_   _|\n" +
                " | |      | |  \\ V /  | |  \n" +
                " | |      | |   > <   | |  \n" +
                " | |____ _| |_ / . \\ _| |_ \n" +
                "  \\_____|_____/_/ \\_|_____|\n" +
                "                           \n" +
                "                           \n");

        // Setup
        ShortestTrackAlgorithm shortestTrackAlgorithm = new DijkstraAlgorithm();

        City sillingy = new City("Sillingy");
        City annecy = new City("Annecy");
        City epagny = new City("Epagny");
        City metzTessy = new City("Metz-Tessy");
        City seynod = new City("Seynod");

        Road sillingyToEpany = new Road(sillingy, epagny, new Length(1, Metric.minute));
        Road epagnyToMetzTessy = new Road(epagny, metzTessy, new Length(3, Metric.minute));
        Road metzTessyToAnnecy = new Road(metzTessy, annecy, new Length(12, Metric.minute));
        Road metzTessyToSeynod = new Road(metzTessy, seynod, new Length(4, Metric.minute));
        Road sillingyToSeynod = new Road(sillingy, seynod, new Length(11, Metric.minute));
        Road sillingyToAnnecy = new Road(sillingy, annecy, new Length(19, Metric.minute));
        Road annecyToSeynod = new Road(annecy, seynod, new Length(9, Metric.minute));

        Map map = new Map(Arrays.asList(sillingyToEpany,
                epagnyToMetzTessy,
                metzTessyToAnnecy,
                metzTessyToSeynod,
                sillingyToSeynod,
                sillingyToAnnecy,
                annecyToSeynod),
                shortestTrackAlgorithm);

        // Run
        List<Road> shortestTrack = map.shortestTrack(sillingy, annecy);
        System.out.println("Shortest track from Sillingy to Annecy:\n\n" + shortestTrack + "\n\n\n\n");
    }

}
