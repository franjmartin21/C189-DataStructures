package c4;

public class StackExample {
    public static void main(String[] args) {
        // Commented out as we still don't have OurStack implementation
        StackInterface<String> stringStack = new ArrayStack<>();
        stringStack.push("Jim");
        stringStack.push("Jess");
        stringStack.push("Jill");
        stringStack.push("Jane");
        stringStack.push("Joe");
        String top = stringStack.peek(); // returns "Joe"
        System.out.println(top + " is at the top of the stack.");
        top = stringStack.pop(); // removes and returns "Joe"
        System.out.println(top + " is removed from the stack.");
        top = stringStack.peek(); // returns "Jane"
        System.out.println(top + " is at the top of the stack.");
        top = stringStack.pop(); // removes and returns "Jane"
        System.out.println(top + " is removed from the stack.");
    }
}
