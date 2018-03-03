package c8;

public class InsertionSort {

    public static <T extends Comparable<? super T>> void sort(T[] a, int first, int last){
        for(int unsorted = first + 1; unsorted < last; unsorted++){
            T nextToInsert = a[unsorted];
            insertInOrder(nextToInsert, a, first, unsorted -1);
        }
    }

    private static <T extends Comparable<? super T>>void insertInOrder(T entry, T[] a, int begin, int end){
        // Inserts anEntry into the sorted entries a[begin] through a[end].
        int index = end;
        // make room, if needed, in sorted portion for another entry
        while(index >= begin && entry.compareTo(a[index]) < 0){
            a[index + 1] = a[index];
            index--;
        }
        a[index + 1] = entry;
    }
}
