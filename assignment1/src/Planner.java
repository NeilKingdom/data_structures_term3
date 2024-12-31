import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Neil Kingdom
 * @since 2020-10-03
 * @version 1.0
 * This class is sort of like an abstract class (not really), in that it is the behind the scenes
 * class which provides all methods called in main.
 * */
public class Planner {

    private ArrayList<Event> events = new ArrayList<>();

    Planner() {
    }

    /**
     * @since 2020-10-03
     * This function prints contents of events array list by getting event object at index i and overriding toString
     * */
    public void displayAllEvents() {
        for(int i = 0; i < events.size(); i++)
            System.out.println(events.get(i));
    }

    /**
     * @since 2020-10-03
     * This function prints the header of the formatted events
     * */
    public static void printTitle() {
        String s = "  Day| Month|  Year|  Hour| Minute |            Description|   Location| Hours";
        System.out.println(s);
        for (int i = 0; i < s.length(); i++)
            System.out.print("=");
        System.out.print("\n");
    }

    /**
     * @since 2020-10-03
     * @param date The date created through user input for sorting
     * This function displays the events based on date sort
     * */
    public void displayEventsDaily(OurDate date) {
        for (Event event : events) {
            if(event.date.getYear() == date.getYear() && event.date.getMonth() == date.getMonth() && event.date.getDay() == date.getDay()) {
                System.out.println(event);
            }
        }
    }

    /**
     * @since 2020-10-03
     * @param date The date created through user input for sorting
     * This function displays the events based on week sort
     * */
    public void displayEventsWeekly(OurDate date) {
        for (Event event : events) {
            if(
                event.date.getYear() == date.getYear() &&
                event.date.getMonth() == date.getMonth() &&
                event.date.getDay() >= date.getDay() &&
                event.date.getDay() <= (date.getDay() + 7)
            ) {
                System.out.println(event);
            }
        }
    }

    /**
     * @since 2020-10-03
     * @param month The month which should be printed
     * This function displays the events based on month sort
     * */
    public void displayEventsMonthly(int month) {
        for (Event event : events) {
            if(event.date.getMonth() == month)
                System.out.println(event);
        }
    }

    /**
     * @since 2020-10-03
     * @param myDate,myTime The date and time which are used as a token for searching
     * Search to see if event exists and then remove it via index position
     * */
    public void deleteEvent(OurDate myDate, OurTime myTime) {

        Event delete = new Event(myDate, myTime, null);
        int indexToDel = binarySearch(delete);
        if (indexToDel != -1) {
            events.remove(indexToDel);
            System.out.println("Event deleted");
        }
        else
            System.out.println("There is no event scheduled for this date and time");
    }

    /**
     * @since 2020-10-03
     * @param temp An object which is to be searched for using binary search
     * @return returns the index of the object if found, or -1 if an error occured
     * Finds out whether or not an event exists (must check day -> month -> year -> hour -> minute)
     * */
    public int binarySearch(Event temp) {

        if(events.isEmpty())
            return -1;

        int low = 0, high = events.size() - 1;
        int tempDay, tempMonth, tempYear, tempHour, tempMinute;
        int eventDay, eventMonth, eventYear, eventHour, eventMinute;

        tempDay = temp.date.getDay();
        tempMonth = temp.date.getMonth();
        tempYear = temp.date.getYear();
        tempHour = temp.time.getHour();
        tempMinute = temp.time.getMinute();

        do {
            int mid = (low + high) / 2;
            eventDay = events.get(mid).date.getDay();
            eventMonth = events.get(mid).date.getMonth();
            eventYear = events.get(mid).date.getYear();
            eventHour = events.get(mid).time.getHour();
            eventMinute = events.get(mid).time.getMinute();

            if (eventDay == tempDay && eventMonth == tempMonth && eventYear == tempYear
                    && eventHour == tempHour && eventMinute == tempMinute) {
                return mid;
            } else if (tempYear < eventYear)
                high = mid - 1;
            else if (tempYear > eventYear)
                low = mid + 1;
            else
                if (tempMonth < eventMonth)
                    high = mid - 1;
                else if (tempMonth > eventMonth)
                    low = mid + 1;
                else
                    if (tempDay < eventDay)
                        high = mid - 1;
                    else if (tempDay > eventDay)
                        low = mid + 1;
                    else
                        if (tempHour < eventHour)
                            high = mid - 1;
                        else if (tempHour > eventHour)
                            low = mid + 1;
                        else
                            if (tempMinute < eventMinute)
                                high = mid - 1;
                            else if (tempMinute > eventMinute)
                                low = mid + 1;
        } while (low <= high);
        return -1;
    }

    /**
     * @since 2020-10-03
     * @param e An object whos index is to be calculated
     * @return returns the next index where the object should go
     * Finds the index where the object should go
     * */
    public int findPosition(Event e) {

        if (events.isEmpty()) {
            return 0;
        }

        for (int i = 0; true; i++) {
            Event event = events.get(i);
            if (event.date.getYear() > e.date.getYear())
                return i;
            else if (event.date.getYear() < e.date.getYear())
                ;
            else if (event.date.getMonth() > e.date.getMonth())
                return i;
            else if (event.date.getMonth() < e.date.getMonth())
                ;
            else if (event.date.getDay() > e.date.getDay())
                return i;
            else if (event.date.getDay() < e.date.getDay())
                ;
            else if (event.time.getHour() > e.time.getHour())
                return i;
            else if (event.time.getHour() < e.time.getHour())
                ;
            else if (event.time.getMinute() > e.time.getMinute())
                return i;
            else if (event.time.getMinute() < e.time.getMinute())
                return i + 1;
            else
                return i + 1;
            return events.size();
        }
    }

    /**
     * @since 2020-10-03
     * @param input Scanner object from main
     * Store events in events arraylist (should store meeting, school, work, gym, or social event)
     * */
    public void addEvent(Scanner input) {

        System.out.println("1. Meeting");
        System.out.println("2. School");
        System.out.println("3. Work");
        System.out.println("4. Gym");
        System.out.println("5. Social");
        System.out.print("Enter type of the event: ");
        int type = input.nextInt();

        OurDate od = new OurDate();
        od.readDate(input);

        OurTime ot = new OurTime();
        ot.readTime(input);

        switch (type) {
            case 1:
                Event meet = new Meeting();
                meet.date = od;
                meet.time = ot;
                meet.readInfo(input);

                if (binarySearch(meet) != -1)
                    System.out.println("You already have an event scheduled for that date and time.... cannot be added");
                else {
                    events.add(findPosition(meet), meet);
                }
                break;
            case 2:
                Event school = new School();
                school.date = od;
                school.time = ot;
                school.readInfo(input);
                events.add(findPosition(school), school);
                break;
            case 3:
                Event work = new Work();
                work.date = od;
                work.time = ot;
                work.readInfo(input);

                if (binarySearch(work) != -1)
                    System.out.println("You already have an event scheduled for that date and time.... cannot be added");
                else {
                    events.add(findPosition(work), work);
                }
                break;
            case 4:
                Event gym = new Gym();
                gym.date = od;
                gym.time = ot;
                gym.readInfo(input);
                events.add(findPosition(gym), gym);
                break;
            case 5:
                Event social = new Social();
                social.date = od;
                social.time = ot;
                social.readInfo(input);
                events.add(findPosition(social), social);
                break;
        }
    }

    /**
     * @since 2020-10-03
     * @param input Scanner object from main
     * get events from Events.txt
     * */
    public void readEventsFromFile(Scanner input) {

        while (input.hasNextLine()) {
            int type = input.nextInt();
            int day = input.nextInt();
            int month = input.nextInt();
            int year = input.nextInt();
            int hour = input.nextInt();
            int minute = input.nextInt();

            OurDate od = new OurDate(day, month, year);
            OurTime ot = new OurTime(hour, minute);

            if (type == 1) {
                Event meet = new Meeting();
                meet.date = od;
                meet.time = ot;
                meet.readInfoFromFile(input);
                if (binarySearch(meet) != -1)
                    System.out.println("You already have an event scheduled for that date and time.... cannot be added");
                else {
                    System.out.println(meet);
                    events.add(findPosition(meet), meet);
                }
            } else if (type == 3) {
                Event work = new Work();
                work.date = od;
                work.time = ot;
                work.readInfoFromFile(input);
                if (binarySearch(work) != -1)
                    System.out.println("You already have an event scheduled for that date and time.... cannot be added");
                else {
                    System.out.println(work);
                    events.add(findPosition(work), work);
                }
            } else {
                Event e1 = new Event();
                e1.date = od;
                e1.time = ot;
                e1.readInfoFromFile(input);
                if (binarySearch(e1) != -1)
                    System.out.println("You already have an event scheduled for that date and time.... cannot be added");
                else {
                    System.out.println(e1);
                    events.add(findPosition(e1), e1);
                }
            }
        }
    }
}
