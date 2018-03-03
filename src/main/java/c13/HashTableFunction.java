package c13;

public class HashTableFunction<T>{

    /**
     * The size of a hash table should be a prime number n greater than 2. Then, if you compress a positive hash code c into
     * an index for the table by using c % n, the indices will be distributed uniformly between 0 and n - 1.
     */
    private static final int NUMBER_OF_BUCKETS = 27;

    private static Object[] hashTable = new Object[NUMBER_OF_BUCKETS];


    private static<T> int getHashIndex(T key)
    {
        int hashIndex = key.hashCode() % hashTable.length;
        if (hashIndex < 0)
            hashIndex = hashIndex + hashTable.length;
        return hashIndex;
    } // end getHashIndex
}
