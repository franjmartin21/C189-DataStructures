package c4;

public class LinkedStack<T> implements StackInterface<T> {

    private Node topNode; // references the first node in the chain

    public void push(T newEntry) {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    }

    public T peek()
    {
        T top = null;
        if (topNode != null)
            top = topNode.getData();
        return top;
    } // end peek

    public T pop()
    {
        T top = peek();
        if (topNode != null)
            topNode = topNode.getNextNode();
        return top;
    } // end pop
    //This operation also is O(1).

    public boolean isEmpty()
    {
        return topNode == null;
    } // end isEmpty

    public void clear()
    {
        topNode = null;
    } // end clear

    private class Node
    {
        private T data; // entry in stack
        private Node next; // link to next node

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public Node getNextNode() {
            return next;
        }
    } // end Node
}
