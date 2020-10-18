import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Neil Kingdom
 * @since 2020-10-03
 * @version 1.0
 * This class acts as the main menu for the entire program.
 * The program is a virtual planner system that can schedule, delete, and display events that the user
 * would like to arrange.
 * */
public class Assignment1 {

    public static void main(String[] args) {

        Planner plan = new Planner();
        boolean loop = true;

        System.out.println("=================================");
        System.out.println("         EVENTS PLANNER");
        System.out.println("=================================");

        while(loop) {

            Scanner inFromUser = new Scanner(System.in);
            try {
                Scanner inFromFile = new Scanner(new File("src/../res/files/Events.txt"));

                String cfe; //check for error
                int day = 0, month = 0, year, hour = 0, minute = 0;
                int option = 0;

                do {
                    System.out.println("1 ... Add an event from keyboard");
                    System.out.println("2 ... Display events of a day");
                    System.out.println("3 ... Display events of a week");
                    System.out.println("4 ... Display events of a month");
                    System.out.println("5 ... Delete an event");
                    System.out.println("6 ... Add events from a file");
                    System.out.println("7 ... Display all events");
                    System.out.println("8 ... Quit");
                    System.out.print("Enter your option: ");

                    try {
                        cfe = inFromUser.nextLine();
                        option = Integer.parseInt(cfe);

                        if (option < 1 || option > 8)
                            System.out.println("Invalid Input");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input");
                    }
                } while (option < 1 || option > 8);

                switch (option) {
                    case 1:
                        plan.addEvent(inFromUser);
                        break;
                    case 2:
                        System.out.println("Enter the day for which events to be displayed: ");
                        do {
                            try {
                                System.out.print("Enter day - between 1 and 31: ");
                                cfe = inFromUser.nextLine();
                                day = Integer.parseInt(cfe);
                            } catch (NumberFormatException e) {
                                day = 0;
                            }
                            if (day < 1 || day > 31)
                                System.out.println("Invalid day input");
                        } while (day < 1 || day > 31);

                        do {
                            try {
                                System.out.print("Enter month - between 1 and 12: ");
                                cfe = inFromUser.nextLine();
                                month = Integer.parseInt(cfe);
                            } catch (NumberFormatException e) {
                                month = 0;
                            }
                            if (month < 1 || month > 12)
                                System.out.println("Invalid month input");
                        } while (month < 1 || month > 12);

                        do {
                            try {
                                System.out.print("Enter year: ");
                                cfe = inFromUser.nextLine();
                                year = Integer.parseInt(cfe);
                            } catch (NumberFormatException e) {
                                year = -1;
                            }
                            if (year < 0)
                                System.out.println("Invalid year input");
                        } while (year < 0);

                        OurDate dailyEvents = new OurDate(day, month, year);
                        System.out.println("Your calendar events for the given date are");
                        Planner.printTitle();
                        plan.displayEventsDaily(dailyEvents);
                        break;
                    case 3:
                        System.out.print("Enter the day from which events for a week to be displayed: ");
                        do {
                            try {
                                System.out.print("Enter day - between 1 and 31: ");
                                cfe = inFromUser.nextLine();
                                day = Integer.parseInt(cfe);
                            } catch (NumberFormatException e) {
                                day = 0;
                            }
                            if (day < 1 || day > 31)
                                System.out.println("Invalid day input");
                        } while (day < 1 || day > 31);

                        do {
                            try {
                                System.out.print("Enter month - between 1 and 12: ");
                                cfe = inFromUser.nextLine();
                                month = Integer.parseInt(cfe);
                            } catch (NumberFormatException e) {
                                month = 0;
                            }
                            if (month < 1 || month > 12)
                                System.out.println("Invalid month input");
                        } while (month < 1 || month > 12);

                        do {
                            try {
                                System.out.print("Enter year: ");
                                cfe = inFromUser.nextLine();
                                year = Integer.parseInt(cfe);
                            } catch (NumberFormatException e) {
                                year = -1;
                            }
                            if (year < 0)
                                System.out.println("Invalid year input");
                        } while (year < 0);

                        OurDate weeklyEvents = new OurDate(day, month, year);
                        System.out.println("Your calendar events for a week starting from the given date are");
                        Planner.printTitle();
                        plan.displayEventsWeekly(weeklyEvents);
                        break;
                    case 4:
                        do {
                            try {
                                System.out.print("Enter month - between 1 and 12: ");
                                cfe = inFromUser.nextLine();
                                month = Integer.parseInt(cfe);
                            } catch (NumberFormatException e) {
                                month = 0;
                            }
                            if (month < 1 || month > 12)
                                System.out.println("Invalid month input");
                        } while (month < 1 || month > 12);

                        System.out.println("Your events during the month " + month + " are");
                        plan.displayEventsMonthly(month);
                        break;
                    case 5:
                        do {
                            try {
                                System.out.print("Enter day - between 1 and 31: ");
                                cfe = inFromUser.nextLine();
                                day = Integer.parseInt(cfe);
                            } catch (NumberFormatException e) {
                                day = 0;
                            }
                            if (day < 1 || day > 31)
                                System.out.println("Invalid day input");
                        } while (day < 1 || day > 31);

                        do {
                            try {
                                System.out.print("Enter month - between 1 and 12: ");
                                cfe = inFromUser.nextLine();
                                month = Integer.parseInt(cfe);
                            } catch (NumberFormatException e) {
                                month = 0;
                            }
                            if (month < 1 || month > 12)
                                System.out.println("Invalid month input");
                        } while (month < 1 || month > 12);

                        do {
                            try {
                                System.out.print("Enter year: ");
                                cfe = inFromUser.nextLine();
                                year = Integer.parseInt(cfe);
                            } catch (NumberFormatException e) {
                                year = -1;
                            }
                            if (year < 0)
                                System.out.println("Invalid year input");
                        } while (year < 0);

                        do {
                            System.out.print("Enter hour (0-23): ");
                            if (inFromUser.hasNextInt())
                                hour = inFromUser.nextInt();
                            else {
                                System.out.println("Invalid hour input");
                                inFromUser.next();
                            }
                        } while (hour < 0 || hour > 23);

                        do {
                            System.out.print("Enter minute (0-59): ");
                            if (inFromUser.hasNextInt())
                                minute = inFromUser.nextInt();
                            else {
                                System.out.println("Invalid minute input");
                                inFromUser.next();
                            }
                        } while (minute < 0 || minute > 59);

                        OurDate delDate = new OurDate(day, month, year);
                        OurTime delTime = new OurTime(hour, minute);

                        plan.deleteEvent(delDate, delTime);
                        break;
                    case 6:
                        plan.readEventsFromFile(inFromFile);
                        break;
                    case 7:
                        Planner.printTitle();
                        plan.displayAllEvents();
                        break;
                    case 8:
                        System.out.println("Good bye... have a nice day");
                        inFromUser.close();
                        inFromFile.close();
                        loop = false;
                        break;
                }
            }
            catch(IOException e) {
                    System.err.println("The file path specified was invalid or file is missing");
                    inFromUser.close();
                    break;
            }
        }
    }
}
