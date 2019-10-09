package application;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            new SimulatorApplication().run();
        } else if (args[0].equals("cli")) {
            new CliApplication().run();
        } else {
            System.out.println("Help:");
            System.out.println(" - no argument run the simulator");
            System.out.println(" - 'cli' argument run the command-line interface");
        }
    }
}
