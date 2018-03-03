package c6;
/**
 A class that implements a deque of objects by using
 a chain of doubly linked nodes.
 @author Frank M. Carrano
 */
public class LinkedDeque<T> implements DequeInterface<T>
{
    private DLNode firstNode; // references node for front of deque
    private DLNode lastNode; // references node for back of deque
    public LinkedDeque()
    {
        firstNode = null;
        lastNode = null;
    } // end default constructor

    @Override
    public void addToFront(T newEntry)
    {
        DLNode newNode = new DLNode(null, newEntry, firstNode);
        if (isEmpty())
            lastNode = newNode;
        else
            firstNode.setPreviousNode(newNode);
        firstNode = newNode;
    } // end addToFront

    @Override
    public void addToBack(T newEntry) {
        DLNode newNode = new DLNode(lastNode, newEntry, null);
        if (isEmpty())
            firstNode = newNode;
        else
            lastNode.setNextNode(newNode);
        lastNode = newNode;
    } // end addToBack

    @Override
    public T removeFront()
    {
        T front = null;
        if (!isEmpty())
        {
            front = firstNode.getData();
            firstNode = firstNode.getNextNode();
            if (firstNode == null)
                lastNode = null;
            else
                firstNode.setPreviousNode(null);
        } // end if
        return front;
    } // end removeFront

    @Override
    public T removeBack()
    {
        T back = null;
        if (!isEmpty())
        {
            back = lastNode.getData();
            lastNode = lastNode.getPreviousNode();
            if (lastNode == null)
                firstNode = null;
            else
                lastNode.setNextNode(null);
        } // end if
        return back;
    } // end removeBack

    @Override
    public T getFront() {
        T front = null;
        if(!isEmpty())
            front = firstNode.getData();
        return front;
    }

    @Override
    public T getBack() {
        T back = null;
        if(!isEmpty())
            back = lastNode.getData();
        return back;
    }

    @Override
    public boolean isEmpty() {
        return firstNode == null && lastNode == null;
    }

    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
    }

    private class DLNode
    {
        private T data; // deque entry
        private DLNode next; // link to next node
        private DLNode previous; // link to previous node
        private DLNode(DLNode previous, T data, DLNode next){
            this.data = data;
            this.next = next;
            this.previous = previous;
        }

        public T getData() {
            return data;
        }

        public DLNode getNextNode() {
            return next;
        }

        public DLNode getPreviousNode() {
            return previous;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNextNode(DLNode next) {
            this.next = next;
        }

        public void setPreviousNode(DLNode previous) {
            this.previous = previous;
        }
    } // end DLNode
} // end LinkedDeque
