package c10;

import c7.LList;

public class SortedLinkedList<T extends Comparable<? super T>> implements SortedListInterface<T>
{
    private Node firstNode; // reference to first node of chain
    private int numberOfEntries;
    public SortedLinkedList()
    {
        firstNode = null;
        numberOfEntries = 0;
    } // end default constructor

    /**
     * Iteraive implementation of the method add
     * @param newEntry the object to be added as a new entry
     */
    /*
    @Override
    public void add(T newEntry)
    {
        Node newNode = new Node(newEntry);
        Node nodeBefore = getNodeBefore(newEntry);
        if (isEmpty() || (nodeBefore == null)) // add at beginning
        {
            newNode.setNextNode(firstNode);
            firstNode = newNode;
        }
        else // add after nodeBefore
        {
            Node nodeAfter = nodeBefore.getNextNode();
            newNode.setNextNode(nodeAfter);
            nodeBefore.setNextNode(newNode);
        } // end if
        numberOfEntries++;
    } // end add

    /** Finds the node that is before the node that should or does contain a given entry.
     @param anEntry the object to be located
     @return either a reference to the node that is before the node
     that contains or should contain anEntry, or null if
     no prior node exists (that is, if anEntry belongs at
     the beginning of the list) */
    /*
    private Node getNodeBefore(T anEntry)
    {
        Node currentNode = firstNode;
        Node nodeBefore = null;
        while ( (currentNode != null) &&(anEntry.compareTo(currentNode.getData()) > 0) )
        {
            nodeBefore = currentNode;
            currentNode = currentNode.getNextNode();
        } // end while
        return nodeBefore;
    } // end getNodeBefore
    */

    /**
     * Recursive implementation of the method add
     * @param newEntry the object to be added as a new entry
     */
    public void add(T newEntry)
    {
        firstNode = add(newEntry, firstNode);
        numberOfEntries++;
    } // end add

    private Node add(T newEntry, Node currentNode)
    {
        if ( (currentNode == null) || (newEntry.compareTo(currentNode.getData()) <= 0) )
        {
            currentNode = new Node(newEntry, currentNode);
        }
        else
        {
            Node nodeAfter = add(newEntry, currentNode.getNextNode());
            currentNode.setNextNode(nodeAfter);
        } // end if
        return currentNode;
    } // end add



    @Override
    public boolean remove(T anEntry) {
        boolean removed = false;
        Node currentNode = firstNode;
        Node nodeBefore = null;
        while (currentNode != null && !removed)
        {
            if(anEntry.equals(currentNode.getData())){
                if(nodeBefore == null){
                    firstNode = currentNode.getNextNode();
                } else{
                    nodeBefore.setNextNode(currentNode.getNextNode());
                }
                numberOfEntries--;
                removed = true;
            }
            currentNode = currentNode.getNextNode();
        }
        return removed;
    }

    @Override
    public int getPosition(T anEntry) {
        int position = 0;
        int index = 0;
        boolean found = false;
        Node currentNode = firstNode;
        while (currentNode != null && position == 0)
        {
            index++;
            if(anEntry.equals(currentNode.getData())){
                position = index;
                found = true;
            } else if(anEntry.compareTo(currentNode.getData()) > 0){
                position = index;
            }
            currentNode = currentNode.getNextNode();
        }
        if(found)
            return position;
        else
            return position * -1;
    }

    @Override
    public T getEntry(int givenPosition) {
        T result = null; // result to return
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            assert !isEmpty();
            result = getNodeAt(givenPosition).getData();
        } // end if
        return result;
    }

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
    public boolean contains(T anEntry) {
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
    }

    @Override
    public T remove(int givenPosition) {
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
    }

    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public int getLength() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries > 0;
    }

    @Override
    public T[] toArray() {
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
} // end SortedLinkedList

