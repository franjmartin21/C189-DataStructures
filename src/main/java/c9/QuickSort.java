package c9;

import c8.InsertionSort;

public class QuickSort {

    private static final int MIN_SIZE = 3;

    public static<T extends Comparable<? super T>> void sort(T[] a){
        quickSort(a, 0, a.length - 1);
    }

    /** Sorts an array into ascending order. Uses quick sort with
     median-of-three pivot selection for arrays of at least
     MIN_SIZE entries, and uses insertion sort for other arrays. */
    public static <T extends Comparable<? super T>> void quickSort(T[] a, int first, int last)
    {
        if (last - first + 1 < MIN_SIZE)
        {
            InsertionSort.sort(a, first, last);
        }
        else
        {
            // create the partition: Smaller | Pivot | Larger
            int pivotIndex = partition(a, first, last);
            // sort subarrays Smaller and Larger
            quickSort(a, first, pivotIndex - 1);
            quickSort(a, pivotIndex + 1, last);
        } // end if
    } // end quickSort

    /** Sorts the first, middle, and last entries of an array
     into ascending order.
     @param a an array of Comparable objects
     @param first the integer index of the first array entry;
     first >= 0 and < a.length
     @param mid the integer index of the middle array entry
     @param last the integer index of the last array entry;
     last - first >= 2, last < a.length */
    private static <T extends Comparable<? super T>>
    void sortFirstMiddleLast(T[] a, int first, int mid, int last)
    {
        order(a, first, mid); // make a[first] <= a[mid]
        order(a, mid, last); // make a[mid] <= a[last]
        order(a, first, mid); // make a[first] <= a[mid]
    } // end sortFirstMiddleLast
    /** Orders two given array entries into ascending order
     so that a[i] <= a[j].
     @param a an array of Comparable objects
     @param i an integer >= 0 and < array.length
     @param j an integer >= 0 and < array.length */
    private static <T extends Comparable<? super T>>
    void order(T[] a, int i, int j)
    {
        if (a[i].compareTo(a[j]) > 0)
            swap(a, i, j);
    } // end order
    /** Swaps the array entries array[i] and array[j]. */
    private static void swap(Object[] array, int i, int j)
    {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    } // end swap

    /** Partitions an array as part of quick sort into two subarrays
     called Smaller and Larger that are separated by a single
     entry called the pivot.
     Entries in Smaller are <= pivot and appear before the
     pivot in the array.
     Entries in Larger are >= pivot and appear after the
     pivot in the array.
     @param a an array of Comparable objects
     @param first the integer index of the first array entry;
     first >= 0 and < a.length
     @param last the integer index of the last array entry;
     last - first >= 3; last < a.length
     @return the index of the pivot */
    private static <T extends Comparable<? super T>>
    int partition(T[] a, int first, int last)
    {
        int mid = (first + last) / 2;
        sortFirstMiddleLast(a, first, mid, last);
        // Assertion: The pivot is a[mid]; a[first] <= pivot and
        // a[last] >= pivot, so do not compare these two array entries
        // with pivot.
        // move pivot to next-to-last position in array
        swap(a, mid, last - 1);
        int pivotIndex = last - 1;
        T pivot = a[pivotIndex];
        // determine subarrays Smaller = a[first..endSmaller]
        // and Larger = a[endSmaller+1..last-1]
        // such that entries in Smaller are <= pivot and
        // entries in Larger are >= pivot; initially, these subarrays are empty
        int indexFromLeft = first + 1;
        int indexFromRight = last - 2;
        boolean done = false;
        while (!done)
        {
            // starting at beginning of array, leave entries that are < pivot;
            // locate first entry that is >= pivot; you will find one,
            // since last entry is >= pivot
            while (a[indexFromLeft].compareTo(pivot) < 0)
                indexFromLeft++;
            // starting at end of array, leave entries that are > pivot;
            // locate first entry that is <= pivot; you will find one,
            // since first entry is <= pivot
            while (a[indexFromRight].compareTo(pivot) > 0)
                indexFromRight--;
            assert a[indexFromLeft].compareTo(pivot) >= 0 && a[indexFromRight].compareTo(pivot) <= 0;
            if (indexFromLeft < indexFromRight)
            {
                swap(a, indexFromLeft, indexFromRight);
                indexFromLeft++;
                indexFromRight--;
            }
            else
                done = true;
        } // end while
        // place pivot between Smaller and Larger subarrays
        swap(a, pivotIndex, indexFromLeft);
        pivotIndex = indexFromLeft;
        // Assertion:
        // Smaller = a[first..pivotIndex-1]
        // Pivot = a[pivotIndex]
        // Larger = a[pivotIndex+1..last]
        return pivotIndex;
    } // end partition
}
