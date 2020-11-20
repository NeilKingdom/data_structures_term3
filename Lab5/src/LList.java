/**
 * Linked list functionality
 * @author Neil Kingdom
 * @since 2020-11-05
 * @version 1.0
 * Course: CST8130_300
 * This class creates the required functionality for native singly linked lists.
 * It contains methods for adding to head, removing from head, removing specific
 * nodes based on data, and printing all data
 * */
public class LList {

    private LLNode head;

    /**
     * Constructor for new linked list
     * @since 2020-11-05
     * */
    public LList() {
        head = null;
    }

    /**
     * Add a new node to head of linked list
     * @since 2020-11-05
     * @param data The data which the newly added node will contain
     * This method adds a node with user specified data to the head of the linked list
     * */
    public void addAtHead(String data) {
        LLNode add = new LLNode(data);
        add.setNext(head);
        head = add;
    }

    /**
     * Delete the head node of linked list
     * @since 2020-11-05
     * Deletes the first node in the linked list
     * */
    public void deleteFromHead() {
        head = head.getNext();
    }

    /**
     * Search for a specific token and remove it from linked list
     * @since 2020-11-05
     * @param remove The search string entered by the user to be deleted
     * @return Returns the number of matches made between the control string and node data
     * This method searches linked list for node containing the data specified by the user.
     * If a matching string is located, each node containing it will be removed.
     * */
    public int searchAndDelete(String remove) {

        boolean foundNode;
        int occurrences = 0;
        LLNode temp = head;

        //If 3 or more nodes exist:
        while(temp.getNext() != null && temp.getNext().getNext() != null) {

            foundNode = false;
            if(temp.getNext().getNext().getData().equals(remove) && temp.getNext().getNext().getNext() == null) {
                temp.getNext().setNext(null);
                foundNode = true;
                occurrences++;
            }
            else if(temp.getNext().getNext().getData().equals(remove)) {
                temp.getNext().setNext(temp.getNext().getNext().getNext());
                foundNode = true;
                occurrences++;
            }
            //Will cause loop to repeat and check for duplicates if match was found
            //Have to start over from head since we cant set temp = prev after removing it
            temp = (foundNode) ? head : temp.getNext();
        }
        temp = head;
        //if second node contains remove string:
        if(temp.getNext() != null && temp.getNext().getData().equals(remove)) {
            if(temp.getNext().getNext() == null)
                head.setNext(null);
            else
                head.setNext(temp.getNext().getNext());
            occurrences++;
        }
        //if head contains remove string:
        if(temp.getData().equals(remove)) {
            if(temp.getNext() == null)
                head = null;
            else
                head = temp.getNext();
            occurrences++;
        }
        return occurrences;
    }

    /**
     * Print all data within linked list
     *  ==
     * @since 2020-11-05
     * Prints all data contained in each node of the linked list
     * */
    public void printAll() {
        LLNode temp = head;
        while(temp.getNext() != null) {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }
        System.out.println(temp.getData());
    }

    /**
     * Check if list is empty
     * @since 2020-11-05
     * @return Returns exit status specifying whether or not linked list is empty
     * This method checks if the linked list contains any data. Returns -1 if empty, or 0 if
     * at least 1 element exists
     * */
    public int isEmpty() {
        if(head == null)
            return -1;
        else return 0;
    }
}
