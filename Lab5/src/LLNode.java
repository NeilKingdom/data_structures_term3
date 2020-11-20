/**
 * Template for linked list nodes
 * @author Neil Kingdom
 * @since 2020-11-05
 * @version 1.0
 * Course: CST8130_300
 * This class creates the template for a singly linked list node. It contains
 * basic setters and getters to update data
 * */
public class LLNode {

    private String data;
    private LLNode next;

    /**
     * Constructor for initializing new node
     * @since 2020-11-05
     * @param data Data for the node being created
     * */
    public LLNode(String data) {
        this.data = data;
        next = null;
    }

    /**
     * Return the next node
     * @since 2020-11-05
     * @return Return the next node
     * */
    public LLNode getNext() {
        return this.next;
    }

    /**
     * Return the current nodes data
     * @since 2020-11-05
     * @return Return the current nodes data
     * */
    public String getData() {
        return this.data;
    }

    /**
     * Update the current nodes 'next' field
     * @since 2020-11-05
     * @param next Updates current node's next field to be the specified node
     * */
    public void setNext(LLNode next) {
        this.next = next;
    }
}
