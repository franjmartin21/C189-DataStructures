package c7;

import java.util.Arrays;

/**
 A class that implements a list of objects by using an array.
 The list is never full.
 @author Frank M. Carrano
 */
public class AList<T> implements ListInterface<T>
{
    private T[] list; // array of list entries
    private int numberOfEntries;
    private static final int DEFAULT_INITIAL_CAPACITY = 25;
    public AList()
    {
        this(DEFAULT_INITIAL_CAPACITY); // call next constructor
    } // end default constructor
    public AList(int initialCapacity)
    {
        numberOfEntries = 0;
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempList = (T[])new Object[initialCapacity];
        list = tempList;
    } // end constructor

    //This easy add method could be even easier by calling the other add method defined below, however it will be less efficient
    //as it makes a needless check and call to makeRoom method
    public void add(T newEntry)
    {
        ensureCapacity();
        list[numberOfEntries] = newEntry;
        numberOfEntries++;
    }

    @Override
    public boolean add(int newPosition, T newEntry)
    {
        boolean isSuccessful = true;
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
        {
            ensureCapacity();
            makeRoom(newPosition);
            list[newPosition - 1] = newEntry;
            numberOfEntries++;
        }
        else
            isSuccessful = false;
        return isSuccessful;
    } // end add

    // Makes room for a new entry at newPosition.
    // Precondition: 1 <= newPosition <= numberOfEntries+1;
    //             numberOfEntries is list's length before addition.
    private void makeRoom(int newPosition)
    {
        assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);
        int newIndex = newPosition - 1;
        int lastIndex = numberOfEntries - 1;
        // move each entry to next higher index, starting at end of
        // list and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--)
            list[index + 1] = list[index];
    } // end makeRoom

    public T remove(int givenPosition)
    {
        T result = null; // return value
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            assert !isEmpty();
            result = list[givenPosition - 1]; // get entry to be removed
            // move subsequent entries toward entry to be removed,
            // unless it is last in list
            if (givenPosition < numberOfEntries)
                removeGap(givenPosition);
            numberOfEntries--;
        } // end if
        return result; // return reference to removed entry, or
        // null if either list is empty or givenPosition
        // is invalid
    } // end remove

    // Shifts entries that are beyond the entry to be removed to the
    // next lower position.
    // Precondition: 1 <= givenPosition < numberOfEntries;
    // numberOfEntries is list's length before removal.
    private void removeGap(int givenPosition)
    {
        assert (givenPosition >= 1) && (givenPosition < numberOfEntries);
        int removedIndex = givenPosition - 1;
        int lastIndex = numberOfEntries - 1;
        for (int index = removedIndex; index < lastIndex; index++)
            list[index] = list[index + 1];
    } // end removeGap

    @Override
    public void clear() {

    }

    @Override
    public boolean replace(int givenPosition, T newEntry)
    {
        boolean isSuccessful = true;
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            assert !isEmpty();
            list[givenPosition - 1] = newEntry;
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
            result = list[givenPosition - 1];
        } // end if
        return result;
    } // end getEntry

    @Override
    public boolean contains(T anEntry)
    {
        boolean found = false;
        for (int index = 0; !found && (index < numberOfEntries); index++)
        {
            if (anEntry.equals(list[index]))
                found = true;
        } // end for
        return found;
    } // end contains

    @Override
    public int getLength() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    public T[] toArray()
    {
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];
        for (int index = 0; index < numberOfEntries; index++)
        {
            result[index] = list[index];
        } // end for
        return result;
    } // end toArray
    // Doubles the size of the array list if it is full.
    private void ensureCapacity()
    {
        if (numberOfEntries == list.length)
            list = Arrays.copyOf(list, 2 * list.length);
    } // end ensureCapacity
} // end AList