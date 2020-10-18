/**
 * @author Neil Kingdom
 * @since 2020-10-03
 * @version 1.0
 * This class is only for calling the social event.
 * */
public class Social extends Event {

    /**
     * @since 2020-10-03
     * Override toString to print date, time, and description.
     * */
    @Override
    public String toString() {
        return date.toString() + time.toString() + description;
    }
}
