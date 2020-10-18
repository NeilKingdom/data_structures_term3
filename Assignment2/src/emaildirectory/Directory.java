package emaildirectory;

import java.util.ArrayList;
import java.util.Scanner;
/******************************************************************************************
 * Class: Directory
 * Purpose: This class acts as the backbone of the driver class. It contains all functions
 * which the user may access through the main menu.
 * @author Neil Kingdom
 * @version 1.0
 * @since 2020-10-08
 * Course: CST8130 - Data Structures
 *****************************************************************************************/
public class Directory {

    private ArrayList<EmailList> emailList;

    /**
     * Empty constructor for creating dummy Directory objects
     * @since 2020-10-08
     * */
    public Directory() {
    }

    /**
     * Constructor for initializing arrayList
     * @since 2020-10-08
     * @param size The size of array list
     * */
    public Directory(int size) {
        emailList = new ArrayList<>(size);
    }

    /**
     * Read a properly formatted text file into array list
     * @since 2020-10-08
     * @param scan Scanner object passed from driver class. scan should have text file as it's input when passed to this function
     * @param lineCount An extra precaution in case the file has a trailing line
     * This function reads each item from the specified file and creates EmailList objects appropriately.
     * The function decides how to handle each email which is read, and will call sortByDescending() function.
     * */
    public void readListFromFile(Scanner scan, int lineCount) {

        int lineNumber = 1;

        while (scan.hasNextLine() && lineNumber <= lineCount) {

            lineNumber++;
            ArrayList<String> errorBuffer = new ArrayList<>();
            String listName = scan.next();
            int numOfEmails = scan.nextInt();
            EmailAddress[] e_vars = new EmailAddress[numOfEmails];

            //If empty, we can safely add any email list
            if(isEmpty() == -1) {
                for (int i = 0; i < numOfEmails; i++) {
                    try {
                        e_vars[i] = new EmailAddress(scan.next());
                    } catch (IndexOutOfBoundsException outOfBounds) {
                        System.err.println("Number of emails (" + numOfEmails + ") on line " + lineNumber + " does not match the argument count.");
                    }
                }
                EmailList addList = new EmailList(listName, e_vars);
                sortByDescending(addList);
            }
            else {
                //If group doesnt exist, we can add the group, otherwise check each individual email
                boolean exists = false;
                EmailList duplicate = new EmailList();
                for(EmailList eList : emailList) {
                    if(eList.getListName().equals(listName)) {
                        duplicate = eList;
                        exists = true;
                        break;
                    }
                }
                if(!exists) {
                    for (int i = 0; i < numOfEmails; i++) {
                        try {
                            e_vars[i] = new EmailAddress(scan.next());
                        } catch (IndexOutOfBoundsException outOfBounds) {
                            System.err.println("Number of emails (" + numOfEmails + ") on line " + lineNumber + " does not match the argument count.");
                        }
                    }
                    //Sort array list lexicographically
                    EmailList addList = new EmailList(listName, e_vars);
                    sortByDescending(addList);
                }
                //If group does exist, use findNode() to see if node exists. If not use addNode()
                else {
                    for (int i = 0; i < numOfEmails; i++) {
                        try {
                            EmailAddress nextEmail = new EmailAddress(scan.next());
                            if(duplicate.findNode(nextEmail) == 0) {
                                errorBuffer.add("Could not add email " + nextEmail.toString() + " from group" + duplicate.getListName() + ". Email already exists");
                            }
                            else {
                                duplicate.addNode(nextEmail);
                            }
                        } catch (IndexOutOfBoundsException outOfBounds) {
                            System.err.println("Number of emails (" + numOfEmails + ") on line " + lineNumber + " does not match the argument count.");
                        }
                    }
                }
            }
            //Optional error messages for emails which already exist
            /*if(!errorBuffer.isEmpty())
                for(String error : errorBuffer)
                    System.err.println(error);*/
        }
    }

    /**
     * Sort EmailList objects lexicographically
     * @since 2020-10-08
     * @param eList The EmailList to be sorted
     * This function handles placing EmailList objects at the beginning or end of the array list
     * depending on their numeric ascii value
     * */
    public void sortByDescending(EmailList eList) {

        if(isEmpty() == 0) {
            boolean isAdded = false;
            for(int i = 0; i < emailList.size(); i++) {
                if(eList.getListName().toLowerCase().compareTo(emailList.get(i).getListName().toLowerCase()) > 0) {
                    emailList.add(i, eList);
                    isAdded = true;
                    break;
                }
            }
            if(!isAdded)
                emailList.add(emailList.size(), eList);
        }
        else {
            emailList.add(emailList.size(), eList);
        }
    }

    /**
     * Create a new email list
     * @since 2020-10-08
     * @param scan Scanner object passed from driver class for user input
     * This function allows the user to create new email list objects. These act as placeholders until
     * actual values are placed in them
     * */
    public void createList(Scanner scan) {

        String listName = scan.next();
        EmailList newList = new EmailList(listName);
        for(EmailList eList : emailList) {
            if (eList.getListName().equals(listName)) {
                System.err.println("A list already exists with this name");
                return;
            }
        }
        sortByDescending(newList);
    }

    /**
     * Displays all emails from all email lists
     * @since 2020-10-08
     * This method will display all emails from all email lists which are not empty
     * */
    public void displayAll() {

        for (EmailList eList : emailList) {
            if(eList.toString() != null) {
                System.out.print(eList.getListName() + ": [ ");
                System.out.print(eList);
                System.out.print(" ]\n");
            }
        }
    }

    /**
     * Display all emails from a specified list
     * @since 2020-10-08
     * @param scan Scanner object passed from driver class for user input
     * This method displays only the emails from a user specified list (assuming it is not empty)
     * */
    public void displayListMail(Scanner scan) {

        String findList = scan.next();
        boolean foundGroup = false;
        EmailList listChoice = new EmailList(findList);

        for (EmailList eList : emailList) {
            if (eList.getListName().equals(findList)) {
                foundGroup = true;
                listChoice = eList;
            }
        }

        if (!foundGroup)
            System.err.println("Could not find the email list \"" + listChoice.getListName() + "\"");

        else {
            if(listChoice.toString() != null) {
                System.out.print(listChoice.getListName() + ": [ ");
                System.out.print(listChoice);
                System.out.print(" ]\n");
            }
            else System.err.println("The email list you entered is empty");
        }
    }

    /**
     * Add a new email to an existing email list
     * @since 2020-10-08
     * @param scan Scanner object passed from driver class for user input
     * This method adds a valid user created email (according to EmailAddress class) to a specified email list
     * */
    public void addToExisting(Scanner scan) {

        String findList = scan.next();
        boolean foundGroup = false;
        EmailList listChoice = new EmailList(findList);

        for (EmailList eList : emailList) {
            if (eList.getListName().equals(findList)) {
                foundGroup = true;
                listChoice = eList;
                break;
            }
        }

        if (!foundGroup)
            System.err.println("Could not find the email list \"" + listChoice.getListName() + "\"");

        else {
            //If group was found, ask user to enter a new email
            EmailAddress addNew = new EmailAddress();
            boolean exists;
            do {
                exists = false;
                addNew.readAddress(scan);
                if(listChoice.findNode(addNew) == 0) {
                    System.err.println("This email already exists");
                    exists = true;
                }
            } while(exists);
            listChoice.addNode(addNew);
        }
    }

    /**
     * Deletes a specified email
     * @since 2020-10-08
     * @param scan Scanner object passed from driver class for user input
     * This method will delete an email specified by the user from an existing email list
     * */
    public void deleteEmail(Scanner scan) {

        String findList = scan.next();
        String[] options;
        int i = 0, choice;
        boolean foundGroup = false;
        EmailList listChoice = new EmailList(findList);

        for(EmailList eList : emailList) {
            if(eList.getListName().equals(findList)) {
                foundGroup = true;
                listChoice = eList;
                break;
            }
        }

        if (!foundGroup)
            System.err.println("Could not find the email list \"" + listChoice.getListName() + "\"");

        else if(listChoice.toString() == null)
            System.err.println("There are no emails in this list yet");

        else {
            EmailAddress del = new EmailAddress();
            do {
                try {
                    options = listChoice.toString().split(",");
                    for(i = 0; i < options.length; i++)
                        System.out.print(String.format("%3d %s%n", (i+1), options[i]));

                    System.out.print("Enter the index of the address you'd like to delete: ");
                    String s = scan.next();
                    choice = Integer.parseInt(s);

                    if(choice < 1 || choice > i) {
                        System.err.println("Your option was outside of the available range. Please try again.");
                    }
                    else del = new EmailAddress(options[choice-1].trim());
                } catch(NumberFormatException e) {
                    System.err.println("Input mismatch exception. Please try again.");
                    choice = 0;
                }
            } while(choice < 1 || choice > i);

            if((listChoice.deleteNode(del) == 0))
                System.out.println("Email deleted");
            else
                System.err.println("Error. Could not delete email.");
        }
    }

    /**
     * Check if array list is empty or null
     * @since 2020-10-08
     * @return Returns -1 if the array list is null or empty, otherwise returns 0
     * This method is used to check if the array list has been properly initialized
     * */
    public int isEmpty() {
        if(this.emailList == null || this.emailList.isEmpty())
            return -1;
        else
            return 0;
    }
}
