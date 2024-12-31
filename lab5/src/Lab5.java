import java.util.Scanner;

/**
 * Driver class for program
 * @author Neil Kingdom
 * @since 2020-11-05
 * @version 1.0
 * Course: CST8130_300
 * This class is the driver class for the program, and acts as a menu system. It will
 * loop, requesting the user to enter a valid option until the exit option is selected
 * */
public class Lab5 {

    public static void main(String[] args) {

        LList list = new LList();
        boolean loop = true;

        list.addAtHead("John");
        list.addAtHead("Doe");
        list.addAtHead("James");
        list.addAtHead("Tom");
        list.addAtHead("Doe");
        list.addAtHead("Matt");
        list.addAtHead("Doe");
        list.addAtHead("Doe");

        while(loop) {

            Scanner scan = new Scanner(System.in);
            String uInput;
            int toInt = 0;

            do {
                System.out.println("\n1. Add at head");
                System.out.println("2. Delete from head");
                System.out.println("3. Display linked list");
                System.out.println("4. Remove data");
                System.out.println("5. Exit");
                System.out.print("Enter your option: ");

                try {
                    uInput = scan.nextLine();
                    toInt = Integer.parseInt(uInput);

                    if (toInt < 1 || toInt > 6)
                        System.out.println("Invalid Input");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input");
                }
            }
            while (toInt < 1 || toInt > 6);

            switch (toInt) {
                case 1:
                    System.out.print("Enter the data for the node: ");
                    String data = scan.next();
                    list.addAtHead(data);
                    break;
                case 2:
                    if (list.isEmpty() == 0) {
                        list.deleteFromHead();
                        System.out.println("Deleted first node");
                    }
                    else
                        System.err.println("Linked list is empty");
                    break;
                case 3:
                    if (list.isEmpty() == 0)
                        list.printAll();
                    else
                        System.err.println("Linked list is empty");
                    break;
                case 4:
                    if(list.isEmpty() == 0) {
                        System.out.print("Enter the data you'd like to remove: ");
                        String remove = scan.next();
                        if(list.searchAndDelete(remove) > 0)
                            System.out.println("Found the data and deleted it");
                        else
                            System.out.println("Could not find the data");
                    }
                    else {
                        System.err.println("Linked list is empty");
                        break;
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scan.close();
                    loop = false;
                    break;
            }
        }
    }
}
