package c6;

public class DequeExample {
    public static void main(String[] args) {
        DequeInterface<String> myDeque = new LinkedDeque<String>();
        myDeque.addToFront("Jim");
        myDeque.addToBack("Jess");
        myDeque.addToFront("Jill");
        myDeque.addToBack("Jane");
        //Jill, Jim, Jess, Jane
        System.out.println(myDeque.getFront());
        System.out.println(myDeque.getBack());

        String name = myDeque.getFront();
        myDeque.addToBack(name);
        //Jill, Jim, Jess, Jane, Jill
        System.out.println(myDeque.getFront());
        System.out.println(myDeque.getBack());

        myDeque.removeFront();
        //Jim, Jess, Jane, Jill
        System.out.println(myDeque.getFront());
        System.out.println(myDeque.getBack());

        myDeque.addToFront(myDeque.removeBack());
        //Jill, Jim, Jess, Jane
        System.out.println(myDeque.getFront());
        System.out.println(myDeque.getBack());
        myDeque.clear();
        System.out.println(myDeque.getFront());
        System.out.println(myDeque.getBack());

    }
}
