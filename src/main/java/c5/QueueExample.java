package c5;

public class QueueExample {
    public static void main(String[] args) {
        QueueInterface<String> myQueue = new ArrayQueue<>();
        myQueue.enqueue("Jim");
        myQueue.enqueue("Jess");
        myQueue.enqueue("Jill");
        myQueue.enqueue("Jane");
        myQueue.enqueue("Joe");
        String front = myQueue.getFront(); // returns "Jim"
        System.out.println(front + " is at the front of the queue.");
        //front = myQueue.dequeue(); // removes and returns "Jim"
        System.out.println(front + " is removed from the queue.");
        myQueue.enqueue("Jerry");
        front = myQueue.getFront(); // returns "Jess"
        System.out.println(front + " is at the front of the queue.");
        //front = myQueue.dequeue(); // removes and returns "Jess"
        System.out.println(front + " is removed from the queue.");
        myQueue.clear();
        System.out.println(myQueue.getFront());
    }
}
