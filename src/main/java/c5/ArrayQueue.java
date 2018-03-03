package c5;

/**
 A class that implements a queue of objects by using an array.
 @author Frank M. Carrano
 */
public class ArrayQueue<T> implements QueueInterface<T>
{
    private T[] queue; // circular array of queue entries and one unused
    // location
    private int frontIndex;
    private int backIndex;
    private static final int DEFAULT_INITIAL_CAPACITY = 50;
    public ArrayQueue()
    {
        this(DEFAULT_INITIAL_CAPACITY);
    } // end default constructor
    public ArrayQueue(int initialCapacity)
    {
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[initialCapacity + 1];
        queue = tempQueue;
        frontIndex = 0;
        backIndex = initialCapacity;
    } // end constructor

    @Override
    public void enqueue(T newEntry) {
        ensureCapacity();
        //since the array is circular, we use the operator % to make backIndex zero after reaching its maximum value.
        backIndex = (backIndex + 1) % queue.length;
        queue[backIndex] = newEntry;
    }

    @Override
    public T dequeue()
    {
        T front = null;
        if (!isEmpty())
        {
            front = queue[frontIndex];
            queue[frontIndex] = null;
            //since the array is circular, we use the operator % to make backIndex zero after reaching its maximum value.
            frontIndex = (frontIndex + 1) % queue.length;
        } // end if
        return front;
    } // end dequeue

    @Override
    public T getFront()
    {
        T front = null;
        if (!isEmpty())
            front = queue[frontIndex];
        return front;
    } // end getFront

    /**
     * The private method ensureCapacity. When we increase the size of an array, we must copy its entries into the newly
     * allocated space. We need to be careful, though, because here the array is circular. We must copy entries in the
     * order in which they appear in the queue.
     * @return
     */
    // Doubles the size of the array queue if it is full
    private void ensureCapacity()
    {
        if (frontIndex == ((backIndex + 2) % queue.length)) // if array is full,
        { // double size of array
            T[] oldQueue = queue;
            int oldSize = oldQueue.length;
            // the cast is safe because the new array contains null entries
            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[]) new Object[2 * oldSize];
            queue = tempQueue;
            //For is just used until oldsize -1, as one position of the array is not used
            for (int index = 0; index < oldSize - 1; index++)
            {
                queue[index] = oldQueue[frontIndex];
                frontIndex = (frontIndex + 1) % oldSize;
            } // end for
            frontIndex = 0;
            backIndex = oldSize - 2;
        } // end if
    } // end ensureCapacity

    /**
     * Empty means that front index is backindex + 1 (full means back index + 2),
     * As the array can be circular we use module
     */
    @Override
    public boolean isEmpty()
    {
        return frontIndex == ((backIndex + 1) % queue.length);
    } // end isEmpty

    @Override
    public void clear() {
        while(!isEmpty())
            dequeue();
        /* Alternative implementation that leaves the indexes in initial position
        if (!isEmpty())
        {
            for (int index = frontIndex; index != backIndex; index = (index + 1) % queue.length)
            {
                queue[index] = null;
            } // end for
            queue[backIndex] = null;
        } // end if
        frontIndex = 0;
        backIndex = queue.length - 1;
         */
    }
} // end ArrayQueue