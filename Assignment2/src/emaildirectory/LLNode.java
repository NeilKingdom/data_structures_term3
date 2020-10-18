package emaildirectory;
/******************************************************************************************
 * Class: DLLNode
 * Purpose: This class creates doubly linked list nodes for the Email List class. Each
 * object contains a "pointer" to the next and previous node. If the node represents either
 * the head or tail node, they should contain null values for their next and previous
 * fields respectively.
 * @author Neil Kingdom
 * @version 1.0
 * @since 2020-10-08
 * Course: CST8130 - Data Structures
 *****************************************************************************************/
public class LLNode {

    private EmailAddress email;
    private LLNode next;

    /**
     * Constructor for building doubly linked list nodes
     * @since 2020-10-08
     * @param email An email address to be stored as the data element
     * */
    public LLNode(EmailAddress email) {
        this.email = email;
        this.next = null;
    }

    /**
     * Return the node's email
     * @since 2020-10-08
     * @return Returns the nodes email
     * */
    public EmailAddress getEmail() {
        return email;
    }

    /**
     * Return the next node
     * @since 2020-10-08
     * @return Returns the next node pointed to
     * */
    public LLNode getNext() {
        return next;
    }

    /**
     * Update the next node to be a new node
     * @since 2020-10-08
     * @param nextNode The node which will replace next
     * This method updates the node objects 'next' field
     * */
    public void updateNext(LLNode nextNode) {
        this.next = nextNode;
    }
}
