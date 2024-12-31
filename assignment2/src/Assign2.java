package emaildirectory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/******************************************************************************************
 * Class: Assign2
 * Purpose: This is the driver class for the assignment. It is the menu system which calls
 * all required functions from the Directory class.
 * @author Neil Kingdom
 * @version 1.0
 * @since 2020-10-08
 * Course: CST8130 - Data Structures
 *****************************************************************************************/
public class Assign2 {

    public static void main(String[] args) {

        boolean loop = true;
        Directory emailDir = new Directory();
        Scanner scan;
        String filePath = "src/../res/files/emails.txt"; //Default file location
        File emailFile = new File(filePath);

        try {
            new Scanner(emailFile);
        } catch(FileNotFoundException fileNotFound) {
            System.err.println("Error: Important emails.txt file was not found\n");
            System.out.print("Please enter the absolute path of the file manually: ");
            try {
                scan = new Scanner(System.in);
                filePath = scan.next();
                emailFile = new File(filePath);
                new Scanner(emailFile);
            } catch(FileNotFoundException fileNotFoundAgain) {
                System.err.println("\nError: Important emails.txt file still could not be found");
                System.err.println("Now exiting...");
                System.exit(-1);
            }
        }

        System.out.println("=====================================");
        System.out.println("           Email Directory");
        System.out.println("=====================================");

        while(loop) {

            String userInput;
            int option = 0;
            scan = new Scanner(System.in);

            do {
                try {
                    /*The user should only be able to choose options 3 and 6 initially
                    (until at least 1 email list is created)*/
                    System.out.println("\n1) Add a new email address to an existing email list");
                    System.out.println("2) Delete an email address from an existing email list");
                    System.out.println("3) Create an email list");
                    System.out.println("4) Display all email lists with all email addresses");
                    System.out.println("5) Display email addresses of a specific email list");
                    System.out.println("6) Read email lists from a file");
                    System.out.println("7) Exit");
                    System.out.print("Please enter your option: ");

                    userInput = scan.next();
                    option = Integer.parseInt(userInput);

                    if(option < 1 || option > 7) {
                        System.err.println("Your option was outside of the available range. Please try again.");
                        option = 0;
                    }
                } catch (NumberFormatException invalidOption) {
                    System.err.println("Input mismatch exception. Please try again.");
                }
            } while(option < 1 || option > 7);

            switch(option) {
                case 1:
                    if (emailDir.isEmpty() == -1) {
                        System.err.println("The email list has not been populated");
                    }
                    else {
                        System.out.print("Enter the name of the email list to which the email is to be added: ");
                        emailDir.addToExisting(scan);
                    }
                    break;

                case 2:
                    if(emailDir.isEmpty() == -1) {
                        System.err.println("The email list has not been populated");
                    }
                    else {
                        System.out.print("Enter the name of email list from which address to be deleted: ");
                        emailDir.deleteEmail(scan);
                    }
                    break;

                case 3:
                    System.out.print("Enter the name of email list to be created: ");
                    int size3 = 1;
                    if(emailDir.isEmpty() == -1)
                        emailDir = new Directory(size3);
                    emailDir.createList(scan);
                    break;

                case 4:
                    if(emailDir.isEmpty() == -1) {
                        System.err.println("The email list has not been populated");
                    }
                    else {
                        System.out.println("\nThe email lists are\n===================");
                        emailDir.displayAll();
                    }
                    break;

                case 5:
                    if(emailDir.isEmpty() == -1) {
                        System.err.println("The email list has not been populated");
                    }
                    else {
                        System.out.print("Enter the name of the email list to be displayed: ");
                        emailDir.displayListMail(scan);
                    }
                    break;

                case 6:
                    try {
                        scan = new Scanner(emailFile);
                    } catch(FileNotFoundException fileNotFound) {
                        System.err.println("Woah... Something must have gone really wrong with the emails.txt file");
                        System.err.println("Please considering restarting the program.");
                    }

                    int size6 = scan.nextInt();
                    if(emailDir.isEmpty() == -1)
                        emailDir = new Directory(size6);
                    emailDir.readListFromFile(scan, size6);
                    break;

                case 7:
                    System.out.print("\n");
                    System.out.println("Now exiting...");
                    loop = false;
                    scan.close();
                    break;
            }
        }
    }
}
