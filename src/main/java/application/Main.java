package application;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            new SimulatorApplication().run();
        } else if (args[0].equals("cli")) {
            new CliApplication().run();
        } else if (args[0].equals("gui")) {
            new GuiApplication().run();
        } else {
            System.out.println("Help:");
            System.out.println(" - no argument run the simulator");
            System.out.println(" - 'cli' argument run command-line interface");
            System.out.println(" - 'gui' argument run graphical user interface");
        }
    }
}
