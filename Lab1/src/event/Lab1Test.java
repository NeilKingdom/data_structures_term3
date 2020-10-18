package event;

import java.io.IOException;

/**
 * @author Neil Kingdom
 * @since 2020-09-11
 * @version 1.0
 * This class is solely used to house the main method and call functions from the Event class.
 * */
public class Lab1Test {
    public static void main(String[] args) throws IOException {
        Event e = new Event();
        Event.printTitle();
        e.readFromFile();
    }
}
