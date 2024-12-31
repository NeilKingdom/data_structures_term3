package emaildirectory;
/******************************************************************************************
 * Class: EmailList
 * Purpose: This class is the doubly linked list class. It manages DLLNode objects by
 * updating the appropriate pointers for each node. It has methods for finding specific
 * emails, printing emails, and adding emails.
 * @author Neil Kingdom
 * @version 1.0
 * @since 2020-10-08
 * Course: CST8130 - Data Structures
 *****************************************************************************************/
public class EmailList {

    private String listName;
    private LLNode head;

    /**
     * Empty constructor for creating dummy EmailList objects
     * @since 2020-10-08
     * */
    public EmailList() {
    }

    /**
     * Empty constructor for creating dummy EmailList objects
     * @since 2020-10-08
     * @param list The name of the email list
     * */
    public EmailList(String list) {
        this.listName = list;
        this.head = null;
    }

    /**
     * Constructor for making a new EmailList
     * @since 2020-10-08
     * @param list The name of the email list
     * @param data The array of email addresses to be added in email list
     * */
    public EmailList(String list, EmailAddress[] data) {
        this.listName = list;
        this.head = null;

        for(EmailAddress email : data)
            addNode(email);
    }

    /**
     * Return the email list's name
     * @since 2020-10-08
     * */
    public String getListName() {
        return listName;
    }

    /**
     * Add a new EmailAddress
     * @since 2020-10-08
     * @param email An EmailAddress object to be added to the list
     * This method will sort EmailAddress objects by their alphanumeric value according to compareTo() method.
     * It then manages the appropriate LLNode pointers for positioning
     * */
    public void addNode(EmailAddress email) {

        LLNode newNode = new LLNode(email);

        if(head == null) { //Head will be null on first call
            head = newNode;
            head.updateNext(null);
            return;
        }

        newNode.updateNext(head);
        head = newNode;
    }

    /**
     * Delete an email address
     * @since 2020-10-08
     * @param delPending The EmailAddress to be deleted
     * @return Returns 0 if the email found successfully found and removed. Otherwise returns -1
     * This method will search for an email address to be deleted. If the email is found, we point
     * the next and previous nodes to each other, effectively removing the pending node.
     * */
    public int deleteNode(EmailAddress delPending) {
        LLNode temp = head;
        while(temp != null) {
            if(temp == head && head.getEmail().toString().equals(delPending.toString())) {
                if(head.getNext() != null) {
                    head = head.getNext();
                }
                else {
                    head = null;
                }
                return 0;
            }
            //If next node == delPending, update this node's next to be next node's next, effectively removing next
            else if(temp.getNext() != null && temp.getNext().getEmail().toString().equals(delPending.toString())) {
                if(temp.getNext().getNext() != null) {
                    temp.updateNext(temp.getNext().getNext());
                }
                else {
                    temp.updateNext(null);
                }
                return 0;
            }
            temp = temp.getNext();
        }
        return -1;
    }

    /**
     * Check is a node already exists
     * @since 2020-10-08
     * @param pending The email address to be searched
     * @return Returns 0 if the address was found, otherwise it returns -1
     * This method searches for a matching email address and returns an integer (0 or -1) based on the result.
     * The method checks equivalence by comparing the String data, not by object equivalence
     * */
    public int findNode(EmailAddress pending) {
        LLNode temp = head;
        while(temp != null) {
            if(temp.getEmail().toString().equals(pending.toString()))
                return 0;
            temp = temp.getNext();
        }
        return -1;
    }

    /**
     * Display all email addresses
     * @since 2020-10-08
     * This method will print all email addresses in the current email list from head node to tail node (left to right).
     * If the node data is null, it's contents are not printed
     * */
    @Override
    public String toString() {

        LLNode temp = head;
        String s = "";

        if(temp == null)
            return null;

        while(temp != null) {
            s += temp.getEmail();
            if(temp.getNext() != null)
                s += ", ";
            temp = temp.getNext();
        }
        return s;
    }
}
