package event;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Neil Kingdom
 * @since 2020-09-11
 * @version 1.0
 * This program reads from the Events.txt file and outputs the formatted contents to the terminal.
 * The Type field in the Events.txt file is used to identify the appropriate amount of information to be displayed.
 * */
public class Event {

    private Scanner scan;
    private byte type;
    private byte month;
    private byte day;
    private short year;
    private byte hour;
    private byte minute;
    private String description;
    private String location;
    private byte hours;

    /**
     * @throws IOException Potential IOException while finding Events.txt file
     * @since 2020-09-11
     * This method prepares the scanner to read from the file. It then calls an additional function to write the
     * formatted data to the console.
     * */
    protected void readFromFile() throws IOException {
        scan = new Scanner(new File("src/event/Events.txt"));

        while(scan.hasNextLine()) {
            type = scan.nextByte();
            month = scan.nextByte();
            day = scan.nextByte();
            year = scan.nextShort();
            hour = scan.nextByte();
            minute = scan.nextByte();
            description = scan.next();
            if (type == 1) {
                location = scan.next();
            } else if (type == 3) {
                location = scan.next();
                hours = scan.nextByte();
            }
            printEvents();
        }
        scan.close();
    }

    private void printEvents() {
        switch (type) {
            case 1:
                System.out.printf("%7d|%8d|%6d|%7d|%7d|%9d|%25s|%11s|\n", type, month, day, year, hour, minute, description, location);
                break;
            case 3:
                System.out.printf("%7d|%8d|%6d|%7d|%7d|%9d|%25s|%11s|%7d\n", type, month, day, year, hour, minute, description, location, hours);
                break;
            default:
                System.out.printf("%7d|%8d|%6d|%7d|%7d|%9d|%25s|\n", type, month, day, year, hour, minute, description);
                break;
        }
    }

    /**
     * @since 2020-09-11
     * This method prints the header for the data sheet.
     * */
    protected static void printTitle() {
        String s = String.format("%7s|%8s|%6s|%7s|%7s|%9s|%25s|%11s|%7s", "Type", "Month", "Day", "Year", "Hour", "Minute", "Description", "Location", "Hours");
        System.out.println(s);
        for(int i = 0; i < s.length(); i++)
            System.out.print("=");
        System.out.print("\n");
    }
}
