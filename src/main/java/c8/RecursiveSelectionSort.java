package c8;
/**
 Class for sorting an array of Comparable objects from smallest to largest.
 */
public class RecursiveSelectionSort
{
    /** Sorts the first n objects in an array into ascending order.
     @param first Actual position to compare
     @param last Last position of the array*/
    public static <T extends Comparable<? super T>> void sort(T[] a, int first, int last)
    {
        //Condition to exit the recursion
        if(first < last) {
            int indexOfNextSmallest = getIndexOfSmallest(a, first, last - 1);
            swap(a, first, indexOfNextSmallest);
            //This one uses the method recursively by adding 1 to the first position to compare
            sort(a, first + 1, last);
        }
    } // end selectionSort
    /** Finds the index of the smallest value in a portion of an array.
     @param a an array of Comparable objects
     @param first an integer >= 0 and < a.length that is the index of
     the first array entry to consider
     @param last an integer >= first and < a.length that is the
     index of the last array entry to consider
     @return the index of the smallest value among
     a[first], a[first + 1], . . . , a[last] */
    private static <T extends Comparable<? super T>>
    int getIndexOfSmallest(T[] a, int first, int last)
    {
        T min = a[first];
        int indexOfMin = first;
        for (int index = first + 1; index <= last; index++)
        {
            if (a[index].compareTo(min) < 0)
            {
                min = a[index];
                indexOfMin = index;
            } // end if
            // Assertion: min is the smallest of a[first] through a[index].
        } // end for
        return indexOfMin;
    } // end getIndexOfSmallest
    /** Swaps the array entries a[i] and a[j].
     @param a an array of objects
     @param i an integer >= 0 and < a.length
     @param j an integer >= 0 and < a.length */
    private static void swap(Object[] a, int i, int j)
    {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    } // end swap
} // end SortArray
