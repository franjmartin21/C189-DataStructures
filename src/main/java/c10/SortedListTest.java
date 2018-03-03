package c10;

public class SortedListTest {
    public static void main(String[] args) {
        SortedListInterface<String> nameList = null;//new SortedList<String>();
        nameList.add("Jamie");
        nameList.add("Brenda");
        nameList.add("Sarah");
        nameList.add("Tom");
        nameList.add("Carlos");
        nameList.getPosition("Jamie");
        nameList.contains("Jill");
        nameList.getPosition("Jill");
        nameList.getEntry(2);
        nameList.remove("Tom");
        nameList.remove(1);

    }
}
