import java.util.Scanner;

/**
 * @author Neil Kingdom
 * @since 2020-10-03
 * @version 1.0
 * This class is the container for Meeting events, and has 2 additional fields than regular events.
 * */
public class Meeting extends Event {

    private String location;

    /**
     * @since 2020-10-03
     * @param input A scanner from Assignment class
     * This function reads the description of location from the user.
     * */
    public void readInfo(Scanner input) {
        super.readInfo(input);
        System.out.print("Enter meeting location: ");
        location = input.next();
    }

    /**
     * @since 2020-10-03
     * @param input A scanner from Assignment class
     * This function reads the description of location from a file.
     * */
    public void readInfoFromFile(Scanner input) {
        super.readInfoFromFile(input);
        location = input.next();
    }

    /**
     * @since 2020-10-03
     * Override toString to print date, time, and description.
     * */
    @Override
    public String toString() {
        return date.toString() + time.toString() + String.format("      %17s| %10s|", description, location);
    }
}
