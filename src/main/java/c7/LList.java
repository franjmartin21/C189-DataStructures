package c7;

/**
 A linked implementation of the ADT list.
 @author Frank M. Carrano
 */
public class LList<T> implements ListInterface<T>
{
    private Node firstNode; // reference to first node
    private int numberOfEntries;
    public LList()
    {
        clear();
    } // end default constructor


    public final void clear() // note the final method
    {
        firstNode = null;
        numberOfEntries = 0;
    } // end clear
    // Returns a reference to the node at a given position.
    // Precondition: List is not empty;
    // 1 <= givenPosition <= numberOfEntries.
    private Node getNodeAt(int givenPosition)
    {
        assert (firstNode != null) && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
        Node currentNode = firstNode;
        // traverse the chain to locate the desired node
        for (int counter = 1; counter < givenPosition; counter++)
            currentNode = currentNode.getNextNode();
        assert currentNode != null;
        return currentNode;
    } // end getNodeAt

    @Override
    public void add(T newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty())
            firstNode = newNode;
        else // add to end of nonempty list
        {
            Node lastNode = getNodeAt(numberOfEntries);
            lastNode.setNextNode(newNode); // make last node reference new node
        } // end if
        numberOfEntries++;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {

        boolean isSuccessful = true;
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
        {
            Node newNode = new Node(newEntry);
            if (newPosition == 1) // case 1
            {
                newNode.setNextNode(firstNode);
                firstNode = newNode;
            }
            else // case 2: list is not empty
            { // and newPosition > 1
                Node nodeBefore = getNodeAt(newPosition - 1);
                Node nodeAfter = nodeBefore.getNextNode();
                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode);
            } // end if
            numberOfEntries++;
        }
        else
            isSuccessful = false;
        return isSuccessful;
    }

    @Override
    public T remove(int givenPosition)
    {
        T result = null; // return value
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            assert !isEmpty();
            if (givenPosition == 1) // case 1: remove first entry
            {
                result = firstNode.getData(); // save entry to be removed
                firstNode = firstNode.getNextNode();
            }
            else // case 2: not first entry
            {
                Node nodeBefore = getNodeAt(givenPosition - 1);
                Node nodeToRemove = nodeBefore.getNextNode();
                Node nodeAfter = nodeToRemove.getNextNode();
                nodeBefore.setNextNode(nodeAfter);
                result = nodeToRemove.getData(); // save entry to be removed
            } // end if
            numberOfEntries--;
        } // end if
        return result; // return removed entry, or
    // null if operation fails
    } // end remove

    @Override
    public boolean replace(int givenPosition, T newEntry)
    {
        boolean isSuccessful = true;
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            assert !isEmpty();
            Node desiredNode = getNodeAt(givenPosition);
            desiredNode.setData(newEntry);
        }
        else
            isSuccessful = false;
        return isSuccessful;
    } // end replace

    @Override
    public T getEntry(int givenPosition)
    {
        T result = null; // result to return
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            assert !isEmpty();
            result = getNodeAt(givenPosition).getData();
        } // end if
        return result;
    } // end getEntry

    @Override
    public boolean contains(T anEntry)
    {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null))
        {
            if (anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNextNode();
        } // end while
        return found;
    } // end contains

    @Override
    public int getLength() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty()
    {
        boolean result;
        //We use this implementation as the assert can help us to catch errors
        if (numberOfEntries == 0) // or getLength() == 0
        {
            assert firstNode == null;
            result = true;
        }
        else
        {
            assert firstNode != null;
            result = false;
        } // end if
        return result;
    } // end isEmpty

    @Override
    public T[] toArray()
    {
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null))
        {
            result[index] = currentNode.getData();
            currentNode = currentNode.getNextNode();
            index++;
        } // end while
        return result;
    } // end toArray

    private class Node {
        private T data; // entry in stack
        private Node next; // link to next node

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node next) {
            this(data);
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNextNode() {
            return next;
        }

        public void setNextNode(Node next) {
            this.next = next;
        }
    }
} // end LList