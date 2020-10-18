import java.util.Scanner;

/**
 * @author Neil Kingdom
 * @since 2020-10-03
 * @version 1.0
 * This class is the container for Meeting events, and has 1 additional fields than regular events.
 * */
public class Work extends Event {

    private String location;
    private double numHours;

    /**
     * @since 2020-10-03
     * @param input A scanner from Assignment class
     * This function reads the description of location from the user.
     * */
    public void readInfo(Scanner input) {
        super.readInfo(input);
        System.out.print("Enter work location: ");
        location = input.next();
        System.out.print("Enter number of hours worked: ");
        numHours = input.nextDouble();
    }

    /**
     * @since 2020-10-03
     * @param input A scanner from Assignment class
     * This function reads the description of location from a file.
     * */
    public void readInfoFromFile(Scanner input) {
        super.readInfoFromFile(input);
        location = input.next();
        numHours = input.nextDouble();
    }

    /**
     * @since 2020-10-03
     * Override toString to print date, time, and description.
     * */
    @Override
    public String toString() {
        return date.toString() + time.toString() + String.format("      %17s| %10s|  %3.2f ", description, location, numHours);
    }
}
