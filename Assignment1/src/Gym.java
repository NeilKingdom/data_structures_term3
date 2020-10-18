/**
 * @author Neil Kingdom
 * @since 2020-10-03
 * @version 1.0
 * This class is only for calling the gym event.
 * */
public class Gym extends Event {

    /**
     * @since 2020-10-03
     * Override toString to print date, time, and description.
     * */
    @Override
    public String toString() {
        return date.toString() + time.toString() + description;
    }
}
