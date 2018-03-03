package c11;

public class SearchingInList<T extends Comparable<? super T>> {

    T[] list;

    int numberOfEntries;

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

    /** Searches the list for anEntry. */
    public boolean containsRecursive(T anEntry)
    {
        return search(0, numberOfEntries - 1, anEntry);
    } // end contains
    /** Searches list[first] through list[last] for desiredItem.
     @param first an integer index >= 0 and < numberOfEntries
     @param last an integer index >= 0 and < numberOfEntries
     @param desiredItem the object to be found
     @return true if desiredItem is found */
    private boolean search(int first, int last, T desiredItem)
    {
        boolean found;
        if (first > last)
            found = false; // no elements to search
        else if (desiredItem.equals(list[first]))
            found = true;
        else
            found = search(first + 1, last, desiredItem);
        return found;
    } // end search

    private boolean binarySearch(T[] array, T anEntry){
        if (array.length == 0) {
            return false;
        }
        int low = 0;
        int high = array.length-1;

        while(low <= high ) {
            int middle = (low+high) /2;
            if (anEntry.compareTo(array[middle]) > 0 ){
                low = middle +1;
            } else if (anEntry.compareTo(array[middle]) < 0){
                high = middle -1;
            } else { // The element has been found
                return true;
            }
        }
        return false;
    }
}
