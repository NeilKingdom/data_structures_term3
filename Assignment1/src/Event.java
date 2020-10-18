import java.util.Scanner;

/**
 * @author Neil Kingdom
 * @since 2020-10-03
 * @version 1.0
 * This class is the container for OurDate and OurTime objects and also formats the description for all events.
 * */
public class Event {

    protected String description;
    protected OurDate date;
    protected OurTime time;

    Event() {
    }

    Event(OurDate date, OurTime time, String desc) {
        this.date = date;
        this.time = time;
        this.description = desc;
    }

    public boolean isEqual(Event e) {
        return false;
    }

    public boolean isGreater(Event e) {
        return false;
    }

    /**
     * @since 2020-10-03
     * @param input A scanner from Assignment class
     * This function reads the description from the user.
     * */
    public void readInfo(Scanner input) {
        System.out.print("Enter an event description: ");
        description = input.next();
    }

    /**
     * @since 2020-10-03
     * @param input A scanner from Assignment class
     * This function reads the description from a file.
     * */
    public void readInfoFromFile(Scanner input) {
        description = input.next();
    }

    /**
     * @since 2020-10-03
     * Override toString to print date, time, and description.
     * */
    @Override
    public String toString() {
        return date.toString() + time.toString() + String.format("      %17s|", description);
    }
}
