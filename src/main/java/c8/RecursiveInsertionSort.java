package c8;

public class RecursiveInsertionSort {

    public static <T extends Comparable<? super T>> void sort(T[] a, int first, int last){
        if (first < last)
        {
            // sort all but the last entry
            sort(a, first, last - 1);
            insertInOrder(a[last], a, first, last -1);
            System.out.println(a);
        }
    }

    private static <T extends Comparable<? super T>>void insertInOrder(T entry, T[] a, int begin, int end) {
        // Inserts anEntry into the sorted array entries a[begin] through a[end].
        // Revised draft.
        if (entry.compareTo(a[end]) >= 0)
            a[end + 1] = entry;
        else if (begin < end) {
            a[end + 1] = a[end];
            insertInOrder(entry, a, begin, end - 1);
        } else // begin == end and anEntry < a[end]
        {
            a[end + 1] = a[end];
            a[end] = entry;
        }
    }
}
