package c2;

import c1.BagInterface;

public class LinkedBag<T> implements BagInterface<T>
{
    private Node firstNode;
    private int numberOfEntries;
    public LinkedBag()
    {
        firstNode = null;
        numberOfEntries = 0;
    } // end default constructor

    public int getCurrentSize() {
        return numberOfEntries;
    }

    public boolean isFull() {
        return false;
    }

    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    /** Adds a new entry to this bag.
     @param newEntry the object to be added as a new entry
     @return true */
    public boolean add(T newEntry) // OutOfMemoryError possible
    {
        // add to beginning of chain:
        Node newNode = new Node(newEntry);
        newNode.next = firstNode; // make new node reference rest of chain
        // (firstNode is null if chain is empty)
        firstNode = newNode; // new node is at beginning of chain
        numberOfEntries++;
        return true;
    } // end add

    public T remove()
    {
        T result = null;
        if (firstNode != null)
        {
            result = firstNode.data;
            firstNode = firstNode.next; // remove first node from chain
            numberOfEntries--;
        } // end if
        return result;
    } // end remove

    public boolean remove(T anEntry)
    {
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);
        if (nodeN != null)
        {
            nodeN.data = firstNode.data; // replace located entry with entry
            // in first node
            remove(); // remove first node
            result = true;
        } // end if
        return result;
    } // end remove

    // Locates a given entry within this bag.
    // Returns a reference to the node containing the entry, if located,
    // or null otherwise.
    private Node getReferenceTo(T anEntry)
    {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null))
        {
            if (anEntry.equals(currentNode.data))
                found = true;
            else
                currentNode = currentNode.next;
        } // end while
        return currentNode;
    } // end getReferenceTo

    public void clear()
    {
        while (!isEmpty())
            remove();
    } // end clear

    /** Counts the number of times a given entry appears in this bag.
     @param anEntry the entry to be counted
     @return the number of times anEntry appears in the bag */
    public int getFrequencyOf(T anEntry)
    {
        int frequency = 0;
        int counter = 0;
        Node currentNode = firstNode;
        while ((counter < numberOfEntries) && (currentNode != null))
        {
            if (anEntry.equals(currentNode.data))
                frequency++;
            counter++;
            currentNode = currentNode.next;
        } // end while
        return frequency;
    } // end getFrequencyOf

    public boolean contains(T anEntry)
    {
        return getReferenceTo(anEntry) != null;
        /* METHOD REFACTORED after adding private method getReferenceTo
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null))
        {
            if (anEntry.equals(currentNode.data))
                found = true;
            else
                currentNode = currentNode.next;
        } // end while
        return found;
        */
    } // end contains

    /** Retrieves all entries that are in this bag.
     @return a newly allocated array of all the entries in the bag */
    public T[] toArray()
    {
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries]; // unchecked cast
        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null))
        {
            result[index] = currentNode.data;
            index++;
            currentNode = currentNode.next;
        } // end while
        return result;
    } // end toArray

    //PRIVATE INNER CLASS NODE
    private class Node
    {
        private T data; // entry in bag
        private Node next; // link to next node
        private Node(T dataPortion)
        {
            this(dataPortion, null);
        } // end constructor
        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        } // end constructor
    } // end Node
} // end LinkedBag